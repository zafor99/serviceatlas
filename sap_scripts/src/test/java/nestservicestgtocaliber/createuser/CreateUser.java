package nestservicestgtocaliber.createuser;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class CreateUser extends TestScriptBase{

	
  @Test
  public void TC01_Positive_CreateUser(Method method) {
	  
	  Response response = null;
	  String email = customerAccount().generateRandomEmailAddress();
	  //loading the default json file
	  nest().customerService().load();
	  //modify the json request with the new email address
	  nest().customerService().modifyCustomerJSONRequest().email(email);
	  response = nest().customerService().create();
	  String userId = nest().customerService().getUserId();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  dbService().accountTable(email).validateTable().emailAddress(email).firstName("mitsu").lastName("bn").hintAnswer("welcome");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC02_ExistingEmail_Negetive(Method method) {
	  
	  Response response = null;
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().load();
	  nest().customerService().modifyCustomerJSONRequest().email("ngupta777@book.com");
	  response = nest().customerService().create();
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().customerService().validateErrorMessage(response).code("1000").description("account with email: ngupta777@book.com already exists.");
	  assert_All(method.getName());
	  
  }
  
  @Test
  public void TC05_InvalidEmail(Method method) {
	  
	  Response response = null;
	  nest().customerService().load();
	  nest().customerService().modifyCustomerJSONRequest().email("ngupta777book.com");
	  response = nest().customerService().create();
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().customerService().validateErrorMessage(response).code("1000").description("invalid email format ");
	  assert_All(method.getName());
	  
  }
  
  @Test
  public void TC06_InvalidLogon(Method method) {
	  
	  Response response = null;
	  nest().customerService().load();
	  nest().customerService().modifyCustomerJSONRequest().login("aaa").email("ngupta777book.com");
	  response = nest().customerService().create();
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().customerService().validateErrorMessage(response).code("1000").description("invalid email format ");
	  assert_All(method.getName());
	  
  }
  
  @Test
  public void TC07_BlnakEmail(Method method) {
	  
	  Response response = null;
	  nest().customerService().load();
	  nest().customerService().modifyCustomerJSONRequest().email("");
	  response = nest().customerService().create();
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().customerService().validateErrorMessage(response).code("1000").description("email can not be blank ");
	  assert_All(method.getName());
	  
  }
	
	  @Test
	  public void TC08_Blnaklogin(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().login("").email("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("email can not be blank ");
		  assert_All(method.getName());
		  
	  }
	
	  @Test
	  public void TC09_BlnakPassword(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().password("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("password can not be blank ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC10_BlnakFirstname(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().firstName("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("first name must be 1 to 15 chars long ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC11_BlnakLastName(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().lastName("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("last name must be 1 to 25 chars long ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC12_BlnakRegistrationDate(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().registrationDate("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("registrationDate can not be null | registration date should be in the past ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC13_BlnakLastModified(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().registrationDate("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("lastModified should be in the past | lastModified can not be null ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC15_InvalidSecretQuestionID(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().secretQuestionId("ab");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("Can not construct instance of java.lang.Integer from String value 'ab': not a valid Integer value");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC16_BlankSecretQuestionID(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().secretQuestionId("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("secret question id and secret question answer must both have values or both be null ");
		  assert_All(method.getName());
		  
	  }
	  
	  @Test
	  public void TC18_BlankSecretAnswer(Method method) {
		  
		  Response response = null;
		  nest().customerService().load();
		  nest().customerService().modifyCustomerJSONRequest().secretAnswer("");
		  response = nest().customerService().create();
		  
		  responseService().validate(response).statusCode(400).statusLine("Bad Request");
		  nest().customerService().validateErrorMessage(response).code("1000").description("invalid secretAnswer pattern | secretAnswer should be 1 to 30 chars long ");
		  assert_All(method.getName());
		  
	  }
	
}
