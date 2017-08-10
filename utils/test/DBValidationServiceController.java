package com.atg_sync_services.database;

public class DBValidationServiceController {
	
	private UPDBAccountTableValidation accounTable;
	private UpdbAddressTableValidation addressTable;
	private UPDBCardTableValidation cardTable;
	
	public UPDBAccountTableValidation accountTable(){
		accounTable =  new UPDBAccountTableValidation();
		return accounTable;
	}
	
	public UPDBAccountTableValidation accountTable(String emailAddress){
		//if(accounTable==null){
			accounTable =  new UPDBAccountTableValidation(emailAddress);
		//}
		return accounTable;
	}
	
	public UpdbAddressTableValidation addressTable(String custId, String addId){
		//if(addressTable==null){
			addressTable = new UpdbAddressTableValidation(custId,addId);
		//}
		return addressTable;
	}
	
	public UpdbAddressTableValidation addressTable(String custId){
		//if(addressTable==null){
			addressTable = new UpdbAddressTableValidation(custId);
		//}
		return addressTable;
	}
	
	public UPDBCardTableValidation cardTable(String customerId, String cardId){
		//if(cardTable==null){
			cardTable = new UPDBCardTableValidation(customerId, cardId);
		//}
		return cardTable;
	}

}
