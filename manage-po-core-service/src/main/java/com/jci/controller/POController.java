package com.jci.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jci.domain.Item;
import com.jci.domain.ItemDiv;
import com.jci.domain.PO;
import com.jci.domain.PoDiv;
import com.jci.domain.PoItem;
import com.jci.domain.ShipTo;
import com.jci.domain.VendAddr;
import com.jci.domain.Vendor;
import com.jci.domain.VendorDiv;
import com.jci.model.request.FlatFileRequest;
import com.jci.model.request.PullPoDataRequest;
import com.jci.model.response.PoNumDataResponse;
import com.jci.model.response.PullPoDataResponse;
import com.jci.repository.PORepository;
import com.jci.utils.CommonUtils;
import com.jci.utils.Constants;
import com.jci.utils.PrepareQueryData;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class POController {

    @Inject
    PORepository poRepository;

    @Autowired
    @Qualifier("jdbcPostgresqlTemplate")
    private JdbcTemplate jdbcPostgresqlTemplate;
    
    @Autowired
    @Qualifier("jdbcOpenedgeTemplate")
    private JdbcTemplate jdbcOpenedgeTemplate;
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello there !";
    }

    @RequestMapping(value = "/po/{po_num}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PO create(@PathVariable int po_num) {
    	System.out.println("po_num--->"+po_num);
        return poRepository.save(new PO(po_num));
    }

    @RequestMapping(value = "/po/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PO save(@RequestBody final PO saveRequest) {
    	System.out.println("saveRequest--->"+saveRequest);
    	
        return poRepository.save(saveRequest);
    }
    
    @RequestMapping(value = "/po", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PO> findAll() {
    	System.out.println("#### Starting findAll ####");
        final List<PO> resultList = new ArrayList<>();
        
        final Iterable<PO> all = poRepository.findAll();
        
        
        all.forEach(new Consumer<PO>() {
            @Override
            public void accept(PO appUser) {
                resultList.add(appUser);
            }
        });
		
        System.out.println("resultList.size--->"+resultList.size());
        //System.out.println("#### Ending findAll ####"+resultList);
        return resultList;
    }
   
    @RequestMapping(value = "/pullPoData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public  PullPoDataResponse getPoData(@RequestBody PullPoDataRequest request){
    	System.out.println(" Starting getPoData(core)--->"+request);
    	
    	List<Integer> status = new ArrayList<>();
    	status.add(Constants.STATUS_INTRANSIT);
    	status.add(Constants.STATUS_ERRO_IN_PROCESS);
    	
    	List<PO> poList = poRepository.findByStatusIn(status);
    	System.out.println(" Ending getPoData(core)--->"+poList);
		return CommonUtils.preparePullPoDataResponse(poList);
    }
    	
	@RequestMapping(value = "/getPoNumData", method = RequestMethod.POST, headers = "Accept=application/json")
    public  PoNumDataResponse getPoNumData(@RequestBody FlatFileRequest request){
		
		//getAndSaveSymixData("2013-05-03");
		System.out.println(" Starting processPoData(core)--->"+request);
		PoNumDataResponse res = new PoNumDataResponse();
		
		List<Integer> poNums =  request.getPoNums();
		int poIdsSize = (poNums == null) ? 0 : poNums.size();
    	System.out.println("poIdsSize--->"+poIdsSize);
    	
    	if(poIdsSize==0){
    		res.setErrorMsg("Please select atleast 1 PO!");
    	}
    	
    	if(poIdsSize>0){
    		List<PO> poList = poRepository.findByPoNumIn(poNums);
    		int poListSize = (poList == null) ? 0 : poList.size();
    		System.out.println("poListSize--->"+poListSize);
    		
    		if(poListSize==0){
        		res.setErrorMsg("Data is not available for the give PO numbers!");
        	}
    		
    		if(poListSize>0){
    			try{
    				List<String> lines =  CommonUtils.fixedLengthString(poList);
            		res.setLines(lines);
            		
            		//For now we are assuming that we will get only one po from UI to generate only one file at a time
            		System.out.println("poNums.get(0)--->"+poNums.get(0));
            		res.setPoNum(poNums.get(0));
            		res.setFileName(CommonUtils.getFileName(poNums.get(0)));            		
    			}catch(Exception e){
    				res.setError(true);
    				e.printStackTrace();
    			}
    		}
    	}
    	System.out.println(" Ending processPoData(core)--->"+res);
		return res;
    }
    
    

    
  private int getAndSaveSymixData(String dateStr){
    	Date date = CommonUtils.stringToDate(dateStr);
        Object[] parameters = new Object[] {date};
        
        List<Map<String,Object>> rows =    jdbcOpenedgeTemplate.queryForList(Constants.SIMUX_QUERY,parameters);
        try{
     	   for (Map<String,Object> row : rows) {
         	   PO po = PrepareQueryData.preparePoData(row);
         	   if(po==null){
         		   continue;
         	   }
         	   
         	   PoItem poItem = PrepareQueryData.preparePoItemData(row); 
         	   po.setPoItem(poItem);
         	   
         	   Vendor vendor = PrepareQueryData.prepareVendorData(row); 
         	   po.setVendor(vendor);
         	   
         	   Item item = PrepareQueryData.prepareItemData(row);
         	   poItem.setItemKey(item);
         	   
         	   ShipTo shipTo = PrepareQueryData.prepareShipToData(row); 
         	   po.setShipTo(shipTo);
         	   
         	   PoDiv poDiv = PrepareQueryData.preparePoDivData(row); 
         	   po.setPoDiv(poDiv);
         	   
         	   VendorDiv vendorDiv = PrepareQueryData.prepareVendorDivData(row); 
         	   vendor.setVendorDiv(vendorDiv);
         	   
         	   ItemDiv itemDiv = PrepareQueryData.prepareItemDivData(row); 
         	   item.setItemDiv(itemDiv);
         	   
         	   VendAddr vendAddr = PrepareQueryData.prepareVendAddrData(row); 
        	   vendor.setVendAddr(vendAddr);
        	   
         	   poRepository.save(po);
        	}
        }catch(Exception e){
     	   System.out.println("### Exception in  MultiDatasource.testQuery ###"+e.getMessage());
     	   e.printStackTrace();
        }
    	return rows.size();
    }
    
}
