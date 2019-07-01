package nestservicestgtocaliber.creditcard;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class LookUpWithMercuryId extends TestScriptBase{

	 @Test
	  public void TC01_Positive_LookupCreditCard_Id(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("000DIJ0a09YoNmJ2");
		  nest().creditCardService().setMercuryCCId("83538749");
		  response = nest().creditCardService().lookupWithCCId();
		  responseService().validate(response).statusCode(200).statusLine("OK");
		  nest().creditCardService().response().validate(response).path("mercuryAccountId", "000DIJ0a09YoNmJ2")
		  	.path("creditCardToken", "545454EEWGYM5454")
		  	.path("displayNumber", "5454")
		  	.path("creditCardType", "MC")
		  	.path("creditCardExpMo", "6")
		  	.path("creditCardExpYr", "2019")
		  	.path("addressId", "1")
		  	.path("mercuryCreditCardId", "83538749");
		  assert_All(method.getName());
	  }
	 
	 @Test
	  public void TC02_InvalidMercuryID(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("000DIJ0a09");
		  nest().creditCardService().setMercuryCCId("83538749");
		  response = nest().creditCardService().lookupWithCCId();
		  responseService().validate(response).statusCode(404).statusLine("Not Found");		
		  nest().creditCardService().validateErrorMessage(response).code("2000").description("CreditCard 83538749 does not exist for user 000DIJ0a09");
		  assert_All(method.getName());
	  }
	 
	 @Test
	  public void TC03_ValidMercuryIDInvalidCCID(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("000DIJ0a09YoNmJ2");
		  nest().creditCardService().setMercuryCCId("83538");
		  response = nest().creditCardService().lookupWithCCId();
		  responseService().validate(response).statusCode(404).statusLine("Not Found");		
		  nest().creditCardService().validateErrorMessage(response).code("2000").description("CreditCard 83538 does not exist for user 000DIJ0a09YoNmJ2");
		  assert_All(method.getName());
	  }
	 
	 @Test
	  public void TC04_BlankMercuryID(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("");
		  nest().creditCardService().setMercuryCCId("83538");
		  response = nest().creditCardService().lookupWithCCId();
		  responseService().validate(response).statusCode(404).statusLine("Not Found");				 
		  assert_All(method.getName());
	  }
	 
	 @Test
	  public void TC05_MercuryIDAssociatedWithOtherUsr(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("000DIJ0a09YoNmJ2");
		  nest().creditCardService().setMercuryCCId("83538814");
		  response = nest().creditCardService().lookupWithCCId();
		  responseService().validate(response).statusCode(404).statusLine("Not Found");		
		  nest().creditCardService().validateErrorMessage(response).code("2000").description("CreditCard 83538814 does not exist for user 000DIJ0a09YoNmJ2");
		  assert_All(method.getName());
	  }
	 
}
