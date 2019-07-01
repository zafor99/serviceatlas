package nestservicestgtocaliber.address;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class LookupAddressByAddressId extends TestScriptBase{

    @Test
  public void TC01_Positive_LookUpCA_ID(Method method) {
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
  public void TC02_InvalidUID_LookUpCA_ID(Method method) {
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("000Z1111111fo0");
	  nest().addresService().setMercuryAddressId("96960033");
	  response = nest().addresService().lookup();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("address with id: 96960033 does not belong to user: 000Z1111111fo0");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC03_BlankUID_LookUpCA_ID(Method method) {
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("");
	  nest().addresService().setMercuryAddressId("96960033");
	  response = nest().addresService().lookup();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");
	  nest().addresService().setMercuryCustomerId("000pBSPU80O9xfo0");
	  assert_All(method.getName());
  }
  
}
