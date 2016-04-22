package com.jci.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.jci.domain.Item;
import com.jci.domain.PO;
import com.jci.domain.PoItem;
import com.jci.domain.ShipTo;
import com.jci.domain.VendAddr;
import com.jci.domain.Vendor;
import com.jci.model.PoDetails;
import com.jci.model.response.PullPoDataResponse;

public class CommonUtils {

public static Date stringToDate(String dateStr){
		
		if(StringUtils.isBlank(dateStr) || "null".equals(dateStr)){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date convertedCurrentDate=null;
		try {
			convertedCurrentDate = sdf.parse(dateStr);
			// String date=sdf.format(convertedCurrentDate );
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return convertedCurrentDate;
	}

	public static List<String> fixedLengthString(List<PO> poList){
		
		List<String> lines = new ArrayList<String>();
		StringBuilder line = null;
		
		for (PO po : poList) {
			line = new StringBuilder();
			
			//1: Order Number
			line.append(appendTab(po.getPoNum()));
			
			//2: Order Creation Date
			line.append(appendTab(po.getOrderDate()));
			
			//3: Order Status
			line.append(appendTab(po.getStat()));
			
			//4: Order Priority
			line.append(appendTab(null));
			
			//5: Customer ID
			line.append(appendTab("JCI-BE"));
			
			//6: Customer Description
			line.append(appendTab("Johnson Controls, Inc. Building Efficiency"));
			
			//7: Customer DUNS
			line.append(appendTab("6092860"));
			
			//8: Customer DUNS+4
			line.append(appendTab(null));
			
			//9: Customer Tax Number
			line.append(appendTab(null));
			
			//10: Customer Address - Descriptor
			line.append(appendTab("Milwaukee Office"));
			
			//11: Customer Address 1
			line.append(appendTab("5757 N. Green Bay Avenue"));
			
			//12: Customer Address 2
			line.append(appendTab("P.O. Box 591"));
			
			//13: Customer Address 3
			line.append(appendTab("Milwaukee, WI 53201"));
			
			//14: Customer Address 4
			line.append(appendTab("AddressLine4"));
			
			//15: Customer Address 5
			line.append(appendTab("AddressLine5"));
			
			
			//ShipTo shipTo = po.getShipTo();
			
			//16: Customer City
			line.append(appendTab("Milwaukee"));
			
			//17: Customer County
			line.append(appendTab(null));
			
			//18: Customer State
			line.append(appendTab("WI"));
			
			//19: Customer Country
			line.append(appendTab("USA"));
			
			//20: Customer Zip
			line.append(appendTab("53201"));
			
			
			Vendor vendor = po.getVendor();
			//21: Supplier ID
			line.append(appendTab(vendor.getVendNum()));
			
			VendAddr vendAddr = vendor.getVendAddr();
			//22: Supplier Description
			line.append(appendTab(vendAddr.getName()));
			
			//23: Supplier DUNS
			line.append(appendTab(null));
			
			//24: Supplier DUNS+4
			line.append(appendTab(null));
			

			//25: Supplier Address - Descriptor
			line.append(appendTab("SH-DOYEN Office"));
			
			//26: Supplier Address 1
			line.append(appendTab(vendAddr.getAddress1()));
			
			//27: Supplier Address 2
			line.append(appendTab(vendAddr.getAddress2()));
			
			//28: Supplier Address 3
			line.append(appendTab(null));
			
			//29: Supplier Address 4
			line.append(appendTab(null));
			
			//30: Supplier Address 5
			line.append(appendTab(null));
			
			//31: Supplier City
			line.append(appendTab(vendAddr.getCity()));
			
			//32: Supplier County
			line.append(appendTab(null));
			
			//33: Supplier State
			line.append(appendTab(vendAddr.getState()));
			
			//34: Supplier Country
			if(vendAddr.getCounty()==null || vendAddr.getCounty().equals("null") || "".equals(vendAddr.getCounty())){
				line.append(appendTab(vendAddr.getCountry()));
			}else if(vendAddr.getCountry()==null || vendAddr.getCountry().equals("null") || "".equals(vendAddr.getCountry())){
				line.append(appendTab(vendAddr.getCounty()));
			}else{
				line.append(appendTab(vendAddr.getCounty()+" "+vendAddr.getCountry()));
			}
			
			//35: Supplier Zip
			line.append(appendTab(vendAddr.getZip()));
			
			//36: Buyer Code
			line.append(appendTab("BuyerCode01"));
			
			ShipTo  shipTo = po.getShipTo();
			//37: Buyer Contact
			line.append(appendTab(shipTo.getContact()));
			
			//38: Buyer Name
			line.append(appendTab(po.getBuyer()));
			
			//39: Buyer Email
			line.append(appendTab(null));
			
			//40: Supplier Email
			line.append(appendTab(null));
			
			//41: Delivery Term
			line.append(appendTab(po.getShipCode()));
			
			//42: Payment Terms
			line.append(appendTab(po.getTermsCode()));
			
			//43: Total Order Amount
			
			BigDecimal poCost = new BigDecimal(po.getPoCost());
			line.append(appendTab(poCost.add(new BigDecimal(po.getSalesTaxT()))));
			
			//44: InCo Terms
			line.append(appendTab(null));
			
			//45: Customer Order Notes
			line.append(appendTab(null));
			
			//46: Supplier Order Notes
			line.append(appendTab(null));
			

			//47: Bill To
			line.append(appendTab("JCI"));
			
			//48: Bill To Address - Descriptor
			line.append(appendTab(shipTo.getName()));
			
			//49: Bill To Address 1
			line.append(appendTab(shipTo.getAddress1()));
			
			//50: Bill To Address 2
			line.append(appendTab(shipTo.getAddress2()));
			
			//51: Bill To Address 3
			line.append(appendTab(null));
			
			//52: Bill To Address 4
			line.append(appendTab(null));
			
			//53: Bill To Address 5
			line.append(appendTab(null));
			
			//54: Bill To City
			line.append(appendTab(shipTo.getCity()));
			
			//55: Bill To County
			line.append(appendTab(null));
			
			//56: Bill To State
			line.append(appendTab(shipTo.getState()));
			
			//57: Bill To Country
			if(shipTo.getCounty()==null || shipTo.getCounty().equals("null") || "".equals(shipTo.getCounty())){
				line.append(appendTab(shipTo.getCountry()));
			}else if(shipTo.getCountry()==null || shipTo.getCountry().equals("null") || "".equals(shipTo.getCountry())){
				line.append(appendTab(shipTo.getCounty()));
			}else{
				line.append(appendTab(shipTo.getCounty()+" "+shipTo.getCountry()));
			}
			
			
			//58: Bill To Zip
			line.append(appendTab(shipTo.getZip()));
			

			//59: Remit To Address - Descriptor
			line.append(appendTab(null));
			
			//60: Remit To Address 1
			line.append(appendTab(null));
			
			//61: Remit To Address 2
			line.append(appendTab(null));
			
			//62: Remit To Address 3
			line.append(appendTab(null));
			
			//63: Remit To Address 4
			line.append(appendTab(null));
			
			//64: Remit To Address 5
			line.append(appendTab(null));
			
			//65: Remit To City
			line.append(appendTab(null));
			
			//66: Remit To County
			line.append(appendTab(null));
			
			//67: Remit To State
			line.append(appendTab(null));
			
			//68: Remit To Country
			line.append(appendTab(null));
			
			//69: Remit To Zip
			line.append(appendTab(null));
			
			//70: Buyer Contact - Phone
			line.append(appendTab(null));
			
			//71: Buyer Contact - Fax
			line.append(appendTab(null));
			
			//72: Order Type
			line.append(appendTab(null));
			
			//73: Flex String PO Header 4
			line.append(appendTab(null));
			
			//74: Flex String PO Header 5
			line.append(appendTab(null));
			
			//75: Flex String PO Header 6
			line.append(appendTab(null));
			
			//76: Flex String PO Header 7
			line.append(appendTab(null));
			
			//77: Flex String PO Header 8
			line.append(appendTab(null));
			
			//78: Flex String PO Header 9
			line.append(appendTab(null));
			
			//79: Flex Int PO Header 1
			line.append(appendTab(null));
			
			//80: Flex Int PO Header 2
			line.append(appendTab(null));
			
			//81: Flex Int PO Header 3
			line.append(appendTab(null));
			
			//82: Flex Int PO Header 4
			line.append(appendTab(null));
			
			//83: Flex Int PO Header 5
			line.append(appendTab(null));
			
			//84: Flex Float PO Header 1
			line.append(appendTab(null));
			
			//85: Flex Float PO Header 2
			line.append(appendTab(null));
			
			//86: Flex Float PO Header 3
			line.append(appendTab(null));
			
			//87: Flex Float PO Header 4
			line.append(appendTab(null));
			
			//88: Flex Float PO Header 5
			line.append(appendTab(null));
			
			//89: Flex Date PO Header 1
			line.append(appendTab(null));
			
			//90: Flex Date PO Header 2
			line.append(appendTab(null));
			
			//91: Flex Date PO Header 3
			line.append(appendTab(null));
			
			//92: Flex Date PO Header 4
			line.append(appendTab(null));
			
			//93: Flex Date PO Header 5
			line.append(appendTab(null));
			
			PoItem poItem = po.getPoItem();
			//94: Line ID
			line.append(appendTab(poItem.getPoLine()));
			
			//95: Line Status
			line.append(appendTab(poItem.getPoRelease()));
			
			//96: Customer Item ID
			line.append(appendTab(poItem.getItem()));
			
			Item item = poItem.getItemKey();
			System.out.println("item-->"+item);
			
			//97: Customer Item Description
			line.append(appendTab(item.getDescription()));
			
			//98: Supplier Item ID
			line.append(appendTab(poItem.getVendItem()));
			
			//99: Supplier Item Description
			line.append(appendTab(null));
			
			//101: Unit Price
			line.append(appendTab(null));
			
			//102: Price Basis
			line.append(appendTab(null));
			
			//103: Payment Currency
			line.append(appendTab(null));
			
			//104: Total Line Amount
			line.append(appendTab(poItem.getItemCost()));
			
			//105: UOM
			line.append(appendTab(null));
			
			//106: Customer Order Line Notes
			line.append(appendTab(null));
			
			//107: Supplier Order Line Notes
			line.append(appendTab(null));
			
			//108: Drawing Version
			line.append(appendTab(null));
			
			//109: Drawing Number
			line.append(appendTab(item.getDrawingNbr()));
			
			//110: Item Category
			line.append(appendTab(item.getProductCode()));
			
			//111: Ship To Location
			line.append(appendTab(po.getDropShipNo()));
			
			//112: Flex String PO Line 5
			line.append(appendTab(null));
			
			//113: Flex String PO Line 6
			line.append(appendTab(null));
			
			//114: Flex String PO Line 7
			line.append(appendTab(null));
			
			//115: Flex String PO Line 8
			line.append(appendTab(null));
			
			//116: Flex String PO Line 9
			line.append(appendTab(null));
			
			//117: Free Item Flag
			line.append(appendTab(null));
			
			//118: Flex Int PO Line 2
			line.append(appendTab(null));
			
			//119: Flex Int PO Line 3
			line.append(appendTab(null));
			
			//120: Flex Int PO Line 4
			line.append(appendTab(null));
			
			//121: Flex Int PO Line 5
			line.append(appendTab(null));
			
			//122: Flex Float PO Line 1
			line.append(appendTab(null));
			
			//123: Flex Float PO Line 2
			line.append(appendTab(null));
			
			//124: Flex Float PO Line 3
			line.append(appendTab(null));
			
			//125: Flex Float PO Line 4
			line.append(appendTab(null));
			
			//126: Flex Float PO Line 5
			line.append(appendTab(null));
			
			//127: Flex Date PO Line 1
			line.append(appendTab(null));
			
			//128: Flex Date PO Line 2
			line.append(appendTab(null));
			
			//129: Flex Date PO Line 3
			line.append(appendTab(null));
			
			//130: Flex Date PO Line 4
			line.append(appendTab(null));
			
			//131: Flex Date PO Line 5
			line.append(appendTab(null));
			
			//132: Request ID
			line.append(appendTab(null));
			
			//133: Request Status
			line.append(appendTab(null));
			
			//134: Action
			line.append(appendTab(null));
			
			//135: Request Qty
			line.append(appendTab(poItem.getQtyOrdered()));
			
			//136: Request Date
			line.append(appendTab(po.getOrderDate()));
			
			//137: Requested Ship Date
			line.append(appendTab(poItem.getDueDate()));
			
			//138: Carrier
			line.append(appendTab(null));
			
			//139: Customer Site
			line.append(appendTab(null));
			
			//140: Ship To Address - Descriptor
			line.append(appendTab(shipTo.getName()));
			
			//141: Ship To Address 1
			line.append(appendTab(shipTo.getAddress1()));
			
			//142: Ship To Address 2
			line.append(appendTab(shipTo.getAddress2()));
			
			//143: Ship To Address 3
			line.append(appendTab(null));
			
			//144: Ship To Address 4
			line.append(appendTab(null));
			
			//145: Ship To Address 5
			line.append(appendTab(null));
			
			//146: Ship To City
			line.append(appendTab(shipTo.getCity()));
			
			
			//147: Ship To County
			line.append(appendTab(null));
			
			//148: Ship To State
			line.append(appendTab(shipTo.getState()));
			
			//149: Ship To Country
			
			if(shipTo.getCounty()==null || shipTo.getCounty().equals("null") || "".equals(shipTo.getCounty())){
				line.append(appendTab(shipTo.getCountry()));
			}else if(shipTo.getCountry()==null || shipTo.getCountry().equals("null") || "".equals(shipTo.getCountry())){
				line.append(appendTab(shipTo.getCounty()));
			}else{
				line.append(appendTab(shipTo.getCounty()+" "+shipTo.getCountry()));
			}
			
			
			
			//150: Ship To Zip
			line.append(appendTab(shipTo.getZip()));
			
			//151: Ref Order Type
			line.append(appendTab(null));
			
			//152: Ref Order ID
			line.append(appendTab(null));
			
			//153: Ref Order Line ID
			line.append(appendTab(null));
			
			//154: Ref Order Request ID
			line.append(appendTab(null));
			
			
			//155: Ref Customer ID
			line.append(appendTab(null));
			
			//156: Ref Supplier ID
			line.append(appendTab(null));
			
			//157: Flex String PO Request 1
			line.append(appendTab(null));
			
			//158: Flex String PO Request 2
			line.append(appendTab(null));
			
			//159: Flex String PO Request 3
			line.append(appendTab(null));
			
			//160: Flex String PO Request 4
			line.append(appendTab(null));
			
			//161: Flex String PO Request 5
			line.append(appendTab(null));
			
			//162: Flex String PO Request 6
			line.append(appendTab(null));
			
			//163: Flex String PO Request 7
			line.append(appendTab(null));
			
			//164: Flex String PO Request 8
			line.append(appendTab(null));
			
			//165: Flex String PO Request 9
			line.append(appendTab(null));
			
			//166: Flex Int PO Request 1
			line.append(appendTab(null));
			
			//167: Flex Int PO Request 2
			line.append(appendTab(null));
			
			//168: Flex Int PO Request 3
			line.append(appendTab(null));
			
			
			//169: Flex Int PO Request 4
			line.append(appendTab(null));
			
			//170: 	Flex Int PO Request 5
			line.append(appendTab(null));
			
			//171: 	Flex Float PO Request 1
			line.append(appendTab(null));
			
			//172: 	Flex Float PO Request 2
			line.append(appendTab(null));
			
			//173: 	Flex Float PO Request 3
			line.append(appendTab(null));
			
			//174: 	Flex Float PO Request 4
			line.append(appendTab(null));
			
			//175: 	Flex Float PO Request 5
			line.append(appendTab(null));
			
			//176: 	Flex Date PO Request 1
			line.append(appendTab(null));
			
			//177: 	Flex Date PO Request 2
			line.append(appendTab(null));
			
			//178: 	Flex Date PO Request 3
			line.append(appendTab(null));
			
			//179: 	Flex Date PO Request 4
			line.append(appendTab(null));
			
			//180: 	Flex Date PO Request 5
			line.append(appendTab(null));
			
			//181: 	Promise ID
			line.append(appendTab(null));
			
			//182: 	Promise Qty
			line.append(appendTab(null));
			
			//183: 	Promise Date
			line.append(appendTab(null));
			
			//184: 	Promised Ship Date
			line.append(appendTab(null));
			
			//185: 	Supplier Site
			line.append(appendTab(null));
			
			//186: 	Ship From Address - Descriptor
			line.append(appendTab(null));
			
			
			//187: 	Ship From Address 1
			line.append(appendTab(null));
			
			//188: 	Ship From Address 2
			line.append(appendTab(null));
			
			//189: 	Ship From Address 3
			line.append(appendTab(null));
			
			//190: 	Ship From Address 4
			line.append(appendTab(null));
			
			//191: 	Ship From Address 5
			line.append(appendTab(null));
			
			
			//192: 	Ship From City
			line.append(appendTab(null));
			
			//193: 	Ship From County
			line.append(appendTab(null));
			
			//194: 	Ship From State
			line.append(appendTab(null));
			
			//195: 	Ship From Country
			line.append(appendTab(null));
			
			//196: 	Ship From Zip
			line.append(appendTab(null));
			
			//197: 	Flex String PO Promise 1
			line.append(appendTab(null));
			
			//198: 	Flex String PO Promise 2
			line.append(appendTab(null));
			
			//199: 	Flex String PO Promise 3
			line.append(appendTab(null));
			
			//200: 	Flex String PO Promise 4
			line.append(appendTab(null));
			
			//201: 	Flex String PO Promise 5
			line.append(appendTab(null));
			
			//202: 		Flex String PO Promise 6
			line.append(appendTab(null));
			
			//203: 	Flex String PO Promise 7
			line.append(appendTab(null));
			
			//204: 	Flex String PO Promise 8
			line.append(appendTab(null));
			
			//205: 	Flex String PO Promise 9
			line.append(appendTab(null));
			
			//206: 	Flex Int PO Promise 1
			line.append(appendTab(null));
			
			//207: 	Flex Int PO Promise 2
			line.append(appendTab(null));
			
			//208: 	Flex Int PO Promise 3
			line.append(appendTab(null));
			
			//209: 	Flex Int PO Promise 4
			line.append(appendTab(null));
			
			//210: 	Flex Int PO Promise 5
			line.append(appendTab(null));
			
			//211: 	Flex Float PO Promise 1
			line.append(appendTab(null));
			
			//212: 	Flex Float PO Promise 2
			line.append(appendTab(null));
			
			//213: 	Flex Float PO Promise 3
			line.append(appendTab(null));
			
			//214: 	Flex Float PO Promise 4
			line.append(appendTab(null));
			
			//215: 	Flex Float PO Promise 5
			line.append(appendTab(null));
			
			//216: 	Flex Date PO Promise 1
			line.append(appendTab(null));
			
			//217: 	Flex Date PO Promise 2
			line.append(appendTab(null));
			
			//218: 	Flex Date PO Promise 3
			line.append(appendTab(null));
			
			//219: 	Flex Date PO Promise 4
			line.append(appendTab(null));
			
			//220: 	Flex Date PO Promise 5
			line.append(appendTab(null));
			
			//221: 	Rev #
			line.append(appendTab(null));
			
			//222: 	Ship To Customer ID
			line.append("");

			lines.add(line.toString());
			System.out.println("line--->"+line);
		 }
		return lines;
	}
	
	public static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	    
	}
	

	/*public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}*/
	
	public static String getFileName(int poNum) {
		
		StringBuilder name = new StringBuilder();
		
		name.append(poNum);
		name.append(".");
		name.append(Constants.FILE_SENDER_DUNS);
		name.append("_");
		name.append(Constants.FILE_RECEIVER_DUNS);
		name.append("_");
		name.append(Constants.FILE_MESSAGE_TYPE);
		name.append("_");
		name.append(Constants.FILE_VERSION);
		name.append("_");
		name.append(Constants.FILE_SITEID);
		name.append("_");
		
		SimpleDateFormat isoFormat = new SimpleDateFormat(Constants.FILE_DATE_FORMAT);
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String timestamp = isoFormat.format(new Date());
		
		name.append(timestamp);
		name.append(".txt");
		return name.toString();
		
	}
	
	public static PullPoDataResponse preparePullPoDataResponse(List<PO> poList){
		System.out.println("#### Starting preparePullPoDataResponse(core) ####");
		PullPoDataResponse res = new PullPoDataResponse();
		
		if(poList==null){
			res.setErrorMsg("Data not found !");
			return res;
		}
		
		ArrayList<PoDetails> poDetails = new ArrayList<PoDetails>() ;
		
		for (PO row : poList) {
			PoDetails details = new PoDetails();
			
			details.setDataSource(row.getDataSource());
			
			details.setPoDesc(row.getBuyer());
			details.setPoId(row.getId());
			details.setPoNum(row.getPoNum());
			details.setStatus(row.getStatus());
			poDetails.add(details);
		}
		
		res.setPoDetails(poDetails);
		res.setErrorInDataFetch(false);
		res.setErrorInDataSave(false);
		System.out.println("#### Ending preparePullPoDataResponse(core) ####"+res);
		return res;
	}
	
}
