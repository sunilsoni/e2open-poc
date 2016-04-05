package rest.core.service;

import java.util.List;

import rest.core.model.ManagePO;

public interface ManagePOService {

	public ManagePO getTransactionDetail(long transactionId);
	
	public List<ManagePO> getTransactions(String accountId);
}
