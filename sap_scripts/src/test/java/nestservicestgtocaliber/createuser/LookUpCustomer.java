package nestservicestgtocaliber.createuser;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class LookUpCustomer extends TestScriptBase{

  @Test
  public void TC01_Positive_CreateUser(Method method) {
	  
	  Response response = null;
	  nest().customerService().load();
	  nest().customerService().setUserId("0008eIGVO2O9xfo0");
	  response = nest().customerService().lookup();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  nest().customerService().response().validate(response).path("email", "qaautomation22@bn.com").path("firstName", "mitsu").path("lastName", "bn")
	  .path("secretAnswer", "wlecome");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC02_InvalidMercuryID(Method method) {
	  
	  Response response = null;
	  nest().customerService().load();
	  nest().customerService().setUserId("0008eAAAAAO9xfo0");
	  response = nest().customerService().lookup();
	  nest().customerService().validateErrorMessage(response).code("2000").description("an Account for 0008eAAAAAO9xfo0 was not found.");
	  assert_All(method.getName());
  }
}
