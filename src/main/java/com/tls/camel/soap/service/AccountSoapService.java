package com.tls.camel.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tls.camel.rest.dao.Account;
import com.tls.camel.rest.service.AccountService;
import com.tml.springsoap.gen.AccountResponse;
import com.tml.springsoap.gen.AddAccountRequest;
import com.tml.springsoap.gen.Error;
import com.tml.springsoap.gen.Success;

@Service
public class AccountSoapService {

	@Autowired
	public AccountService service;
	public AccountResponse addAccount(AddAccountRequest request) {
		Account account = new Account();
		 account.setK_number(request.getKNumber());
		 account.setCaller_id(request.getCallerId());
		 account.setCustomer_number(request.getCustomerNumber());
		 account.setAgent_number(request.getAgentNumber());
		 
		com.tls.camel.rest.dao.AccountResponse response =service.addAccountDetails(account);
				
				if(response!=null)
				{
					System.out.println("in if "+response.toString());
				}else
				{
					System.out.println("in else"+response.toString());
				}
				AccountResponse rs=new AccountResponse();
				Success su=new Success();
				
				if(response.getSuccess()!=null)
				{
					System.out.println(">>in if");
				su.setCallerId(response.getSuccess().getCall_id());
				su.setMessage(response.getSuccess().getMessage());
				su.setStatus(response.getSuccess().getStatus());
				}
				else
				{
					System.out.println(">>in else");
					su.setCallerId("none");
					su.setMessage("none");
					su.setStatus("none");
				}
				Error error=new Error();
				System.out.println("nnnnnn,,,"+error.getMessage());
				if(response.getError()!=null)
				{
					error.setMessage(response.getError().getMessage());
					
				}else {
					error.setMessage("noerror");
				}
				rs.setSuccess(su);
				rs.setError(error);
		return rs;
	}

}
