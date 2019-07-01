package nestservicestgtocaliber.createuser;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class UpdateCustomer extends TestScriptBase{
 
  @Test
  public void TC01_Positive_UpdateUser(Method method) 
  {
	  	Response response = null;
	  	String email = customerAccount().generateRandomEmailAddress();
		nest().customerService().load();		
		nest().customerService().modifyCustomerJSONRequest().email(email);
	  	nest().customerService().create();

	  	//Update Hints answer
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").secretAnswer("Updated");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(200).statusLine("OK");
		dbService().accountTable(email).validateTable().hintAnswer("Updated");
		assert_All(method.getName());
  }
  
  @Test
  public void TC02_Invalid_CustomerId(Method method) 
  {
	  	//Update Hints answer
	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("Modna");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").secretAnswer("Updated");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(404).statusLine("Not Found");
		nest().customerService().validateErrorMessage(response).code("2000").description("an Account for Modna was not found.");
		assert_All(method.getName());
  }
  
  @Test
  public void TC03_Blank_CustomerId(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").secretAnswer("Updated");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(405).statusLine("Method Not Allowed");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC05_Invalid_Email(Method method) 
  {

	  	Response response = null;
	  	String email = customerAccount().generateRandomEmailAddress();
		nest().customerService().load();		
		nest().customerService().modifyCustomerJSONRequest().login(email).email(email);
	  	nest().customerService().create();
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").email("Boka@Chele");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("could not execute statement; SQL [n/a]; constraint [USERPROFILE.UNQ_EMAIL_ISBNACCOUNT]");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC06_Invalid_Login(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000FY8yZC1YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").login("Boka@Chele");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC07_Blank_Email(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").email("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("email can not be blank ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC08_Blank_Login(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").login("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		assert_All(method.getName());
  }
  
  @Test
  public void TC09_Blank_Password(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").password("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("password can not be blank ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC10_Blank_FirstName(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").firstName("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("first name must be 1 to 15 chars long ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC11_Blank_LastName(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").lastName("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("last name must be 1 to 25 chars long ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC12_Blank_RegistrationDate(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").registrationDate("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("registration date should be in the past | registrationDate can not be null ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC13_Blank_LastModified(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("lastModified can not be null | lastModified should be in the past ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC14_Blank_LastModified(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().lastModified("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("lastModified can not be null | lastModified should be in the past ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC14_Invalid_Password(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().password("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("password can not be blank ");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC15_Invalid_SecretQuestionId(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().secretQuestionId("invalid");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("Can not construct instance of java.lang.Integer from String value 'invalid': not a valid Integer value");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC16_Blank_SecretQuestionId(Method method) 
  {

	  	Response response = null;
		nest().customerService().load();		
	  	
	  	nest().customerService().setUserId("000qp7yZ21YoNmJ2");
		nest().customerService().modifyCustomerJSONRequest().secretQuestionId("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("secret question id and secret question answer must both have values or both be null ");
		assert_All(method.getName());
		
  }
  
  
  @Test
  public void TC17_Invalid_SecretAnswer(Method method) 
  {

	  	Response response = null;
	  	String email = customerAccount().generateRandomEmailAddress();
		nest().customerService().load();		
		nest().customerService().modifyCustomerJSONRequest().email(email);
	  	nest().customerService().create();
		nest().customerService().modifyCustomerJSONRequest().lastModified("2014-04-17T16:54:26.000+0000").secretAnswer("Invalid");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(200).statusLine("OK");
		assert_All(method.getName());
		
  }
  
  @Test
  public void TC18_Blank_SecretAnswer(Method method) 
  {

	  	Response response = null;
	  	String email = customerAccount().generateRandomEmailAddress();
		nest().customerService().load();		
		nest().customerService().modifyCustomerJSONRequest().email(email);
	  	nest().customerService().create();
		nest().customerService().modifyCustomerJSONRequest().secretAnswer("");
		response = nest().customerService().update();
		responseService().validate(response).statusCode(400).statusLine("Bad Request");
		nest().customerService().validateErrorMessage(response).code("1000").description("invalid secretAnswer pattern | secretAnswer should be 1 to 30 chars long ");
		assert_All(method.getName());
		
  }
  
}
