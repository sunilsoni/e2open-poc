package rest.api.service;

import rest.api.model.ManagePO;
import rest.api.model.ManagePOCollectionResponse;

public interface ManagePOService {

	public ManagePO getTransactionDetail(String accountNumber, long transactionId);
	
	public ManagePOCollectionResponse getTransactions(String accountId);
}
