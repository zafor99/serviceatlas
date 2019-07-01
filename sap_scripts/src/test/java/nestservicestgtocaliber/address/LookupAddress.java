package nestservicestgtocaliber.address;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class LookupAddress extends TestScriptBase{
	
  @Test
  public void TC01_Positive_LookUp_CA(Method method) {
	  Response response = null;	  
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("000pBSPU80O9xfo0");
	  nest().addresService().setMercuryAddressId("96960033");
	  response = nest().addresService().lookup();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  nest().addresService().response().validate(response).path("addressNickname", "default address 1");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC02_InvalidID_LookUp_CA(Method method) {
	  Response response = null;	  
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("000O9xfo0");
	  response = nest().addresService().lookup();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC03_BlankID_LookUp_CA(Method method) {
	  Response response = null;	  
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("");
	  response = nest().addresService().lookup();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");
	  assert_All(method.getName());
  }
}
