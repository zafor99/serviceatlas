package com.atg_sync_services.framework;

public class NestController {
	
	private NestAddressService addressService = null;
	private NestCreditCardService creditCardService = null;
	private NestCustomerService customerService = null;

	public NestController(){
		
	}
	
	public NestAddressService addresService(){
		if(addressService==null){
			addressService = new NestAddressService();
		}
		
		return addressService;
	}
	
	public NestCreditCardService creditCardService(){
		if(creditCardService==null){
			creditCardService = new NestCreditCardService();
		}
		
		return creditCardService;
	}
	
	public NestCustomerService customerService(){
		if(customerService==null){
			customerService = new NestCustomerService();
		}
		
		return customerService;
	}
}
