package com.jci.service;

import com.jci.model.ManagePO;
import com.jci.model.ManagePOCollectionResponse;
import com.jci.model.request.FlatFileRequest;
import com.jci.model.request.PullPoDataRequest;
import com.jci.model.response.ProcessPoDataResponse;
import com.jci.model.response.PullPoDataResponse;

public interface ManagePOService {

	public ManagePO getTransactionDetail(String accountNumber, long transactionId);
	
	public ManagePOCollectionResponse getTransactions(String accountId);
	
	public  PullPoDataResponse getPoData( PullPoDataRequest request);
	public  ProcessPoDataResponse processPoData(FlatFileRequest request);
}
