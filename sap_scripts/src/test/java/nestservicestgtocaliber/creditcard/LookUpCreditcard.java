package nestservicestgtocaliber.creditcard;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class LookUpCreditcard extends TestScriptBase{
	
	 @Test
	  public void TC01_Positive_LookUp_CA(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("000DIJ0a09YoNmJ2");
		  response = nest().creditCardService().lookup();
		  responseService().validate(response).statusCode(200).statusLine("OK");
		  nest().creditCardService().response().validate(response).path("mercuryAccountId", "[000DIJ0a09YoNmJ2]")
		  	.path("creditCardToken", "[545454EEWGYM5454]")
		  	.path("displayNumber", "[5454]")
		  	.path("creditCardType", "[MC]")
		  	.path("creditCardExpMo", "[6]")
		  	.path("creditCardExpYr", "[2019]")
		  	.path("addressId", "[1]")
		  	.path("mercuryCreditCardId", "[83538749]");
		  assert_All(method.getName());
	  }
	 
	 @Test
	  public void TC02_InvalidUserID(Method method) {
		  Response response = null;	  

		  nest().creditCardService().setCustomerId("abc");
		  response = nest().creditCardService().lookup();
		  responseService().validate(response).statusCode(200).statusLine("OK");
		  assert_All(method.getName());
	  }

	 @Test
	  public void TC03_BlankUserID(Method method) {
		  Response response = null;	  
		  nest().creditCardService().load();
		  nest().creditCardService().setCustomerId("");
		  response = nest().creditCardService().lookup();
		  responseService().validate(response).statusCode(404).statusLine("Not Found");		 
		  assert_All(method.getName());
	  }
	 
	 
	 @Test
	  public void TC06_NoCCinCustomerAccount(Method method) {
		  Response response = null;	  
		  
		  nest().creditCardService().setCustomerId("000Y3K0aR9YoNmJ2");		  
		  response = nest().creditCardService().lookup();
		  responseService().validate(response).statusCode(200).statusLine("OK");		 
		  assert_All(method.getName());
	  }
	 
}
