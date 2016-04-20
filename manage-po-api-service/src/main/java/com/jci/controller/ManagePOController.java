package com.jci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.model.ManagePO;
import com.jci.model.ManagePOCollectionResponse;
import com.jci.model.request.FlatFileRequest;
import com.jci.model.request.PullPoDataRequest;
import com.jci.model.response.ProcessPoDataResponse;
import com.jci.model.response.PullPoDataResponse;
import com.jci.service.ManagePOService;

@RestController
public class ManagePOController {
	
	@Autowired
	private ManagePOService poService;

	
	@RequestMapping(value = "/pullPoData", method = RequestMethod.POST, headers = "Accept=application/json")
    public  PullPoDataResponse getPoData(@RequestBody PullPoDataRequest request){
		System.out.println(" #### Starting ManagePOController. getPoData ####->"+request);
		PullPoDataResponse  res = poService.getPoData(request);
		System.out.println(" #### Ending ManagePOController. getPoData ####->"+res);
		return res;
    }
    	
	@RequestMapping(value = "/processPoData", method = RequestMethod.POST, headers = "Accept=application/json")
    public  ProcessPoDataResponse processPoData(@RequestBody FlatFileRequest request){
		System.out.println(" #### Starting ManagePOController. processPoData ####->"+request);
		
		ProcessPoDataResponse  res = poService.processPoData(request);
		
		System.out.println(" #### Ending ManagePOController. processPoData ####->"+res);
		return res;
    }
	
	
	    
    @RequestMapping(value="/accounts/{accountNumber}/transactions", method = RequestMethod.GET, headers = "Accept=application/json")
    public ManagePOCollectionResponse getTransactions(
    		@PathVariable("accountNumber") String accountNumber) {
    	
    	/*TransactionCollectionResponse response = new TransactionCollectionResponse();
    	response.setTransactions(transactionService.getTransactions(accountNumber));*/
    	return poService.getTransactions(accountNumber);
    }
    
    @RequestMapping("/accounts/{accountNumber}/transactions/{transactionId}")
    public ManagePO getTransactionDetail(
    		@PathVariable("accountNumber") String accountNumber,
    		@PathVariable("transactionId") long transactionId
    		) {
        return poService.getTransactionDetail(accountNumber, transactionId);
    }
    
    
    
}
