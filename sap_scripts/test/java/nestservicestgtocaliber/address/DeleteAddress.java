package nestservicestgtocaliber.address;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class DeleteAddress extends TestScriptBase{

	@Test
	public void TC01_Positive(Method method) {

		Response response = null;	  
		nest().addresService().load();
		response = nest().addresService().create();
		responseService().validate(response).statusCode(201).statusLine("Created");
		String customerId = nest().addresService().getMercuryCustomerId();
		String mercuryAddId = nest().addresService().getMercuryAddressId();	  
		response = nest().addresService().delete();
		responseService().validate(response).statusCode(200).statusLine("OK");
		dbService().addressTable(customerId,mercuryAddId).validateTable().emptyRows();
		assert_All(method.getName());
	}

	@Test
	public void TC02_InvalidCustomerId(Method method) {

		Response response = null;	  
		nest().addresService().load();
		nest().addresService().setMercuryAddressId("89411704");
		nest().addresService().setMercuryCustomerId("rereew");	  
		response = nest().addresService().delete();
		responseService().validate(response).statusCode(404).statusLine("Not Found");
		nest().addresService().validateErrorMessage(response).code("2000").description("Unable to find user rereew");
		assert_All(method.getName());

	}
	
	@Test
	public void TC03_InvalidAddressId(Method method) {

		Response response = null;	  
		nest().addresService().load();
		nest().addresService().setMercuryAddressId("89499704");  // Address ID is fake
		nest().addresService().setMercuryCustomerId("000pBSPU80O9xfo0");  // Mercury ID is taken from NestAddressService default request  
		response = nest().addresService().delete();
		responseService().validate(response).statusCode(404).statusLine("Not Found");
		nest().addresService().validateErrorMessage(response).code("2000").description("address 89499704 does not exist");
		assert_All(method.getName());

	}
	
	@Test
	public void TC04_Mismatch_AddID_MercuryUID(Method method) {

		Response response = null;	  
		nest().addresService().load();
		nest().addresService().setMercuryAddressId("151684");  // Address ID is from QC
		nest().addresService().setMercuryCustomerId("000onVQUD0O9xfo0");  // Mercury ID is from QC  
		response = nest().addresService().delete();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().addresService().validateErrorMessage(response).code("1000").description("address with id: 151684 does not belong to user: 000onVQUD0O9xfo0");
		assert_All(method.getName());

	}
	
	@Test
	public void TC05_DeleteNonExistingID(Method method) {

		Response response = null;	  
		nest().addresService().load();
		nest().addresService().setMercuryAddressId("89411704");  // Address ID is from QC
		nest().addresService().setMercuryCustomerId("000onVQUD0O9xfo0");  // Mercury ID is from QC 
		response = nest().addresService().delete();
		responseService().validate(response).statusCode(404).statusLine("Not Found");
		nest().addresService().validateErrorMessage(response).code("2000").description("address 89411704 does not exist");
		assert_All(method.getName());

	}
}
