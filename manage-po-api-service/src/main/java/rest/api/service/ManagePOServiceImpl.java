package rest.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import rest.api.model.ManagePO;
import rest.api.model.ManagePOCollectionResponse;

@Service
public class ManagePOServiceImpl implements ManagePOService{
	
	@Autowired
	private RestTemplate transactionsRestTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackTransactionDetail", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public ManagePO getTransactionDetail(String accountNumber, long transactionId) {
		//Transaction transaction = new Transaction("123", "FromAcc-Detail", "2014-12-12", transactionId);
		ManagePO transaction = transactionsRestTemplate.getForObject("http://manage-po-core-service/accounts/"+accountNumber+"/transactions/"+transactionId, ManagePO.class);
		return transaction;
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackTransactions", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public ManagePOCollectionResponse getTransactions(String accountNumber) {
		/*List<Transaction> transactions = new ArrayList<Transaction>();
		for(int i=0; i<3; i++){
			Transaction transaction = new Transaction(accountId, "FromAcc-"+i, "2014-12-12", 10);
			transactions.add(transaction);
		}*/
		
		ManagePOCollectionResponse response = transactionsRestTemplate.getForObject("http://manage-po-core-service/accounts/"+accountNumber+"/transactions/", ManagePOCollectionResponse.class);;
		return response;
	}
	
	
	public ManagePOCollectionResponse getFallbackTransactions(String accountNumber) {
		ManagePOCollectionResponse response = new ManagePOCollectionResponse();
		List<ManagePO> transactions = new ArrayList<ManagePO>();
		for(int i=0; i<3; i++){
			ManagePO transaction = new ManagePO(i, accountNumber, "FromAcc-"+i, "2014-12-12", 10);
			transactions.add(transaction);
		}
		response.setTransactions(transactions);
		return response;
	}
	
	public ManagePO getFallbackTransactionDetail(String accountNumber, long transactionId) {
		return new ManagePO(transactionId, accountNumber, "FromAcc-Detail", "2014-12-12", 123.00);
	}

}
