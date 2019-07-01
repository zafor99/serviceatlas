package nestservicestgtocaliber.address;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

import java.lang.reflect.Method;

public class CreateAddress extends TestScriptBase{
	

  @Test
  public void TC01_Positive(Method method) {
	  Response response = null;	  
	  nest().addresService().load();
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  String customerId = nest().addresService().getMercuryCustomerId();
	  String mercuryAddId = nest().addresService().getMercuryAddressId();
	  sleep(10);
	  dbService().addressTable(customerId,mercuryAddId).validateTable().addressId(mercuryAddId).firstName("grant").lastName("gortsema")
	  	.addressLine1("123 Sesame Sts").addressLine2("APT 223").city("New York City").state("NY").zipCode("10010");
	  assert_All(method.getName());
	  
  }
  
  @Test
  public void TC02_BlankAddressNickname(Method method){
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().modifyJSONAddressRequest().addressNickName("");
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("addressNickname can not be empty ");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC03_BlankLastModified(Method method){
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().modifyJSONAddressRequest().lastModified("");
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("lastModified should be in the past | lastModified can not be null ");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC04_InvalidLastModified(Method method){
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().modifyJSONAddressRequest().lastModified("2032-03-23T15:46:48.000+0000");
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("lastModified should be in the past ");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC05_InvalidAddress1(Method method){
	  Response response = null;
	  nest().addresService().load();
	  nest().addresService().modifyJSONAddressRequest().addressLine1("ghrnmjh");
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  String customerId = nest().addresService().getMercuryCustomerId();
	  String mercuryAddId = nest().addresService().getMercuryAddressId();
	  sleep(10);
	  dbService().addressTable(customerId,mercuryAddId).validateTable().addressId(mercuryAddId).firstName("grant").lastName("gortsema")
	  	.addressLine1("ghrnmjh").addressLine2("APT 223").city("New York City").state("NY").zipCode("10010");
	  assert_All(method.getName());
  }
  
  
}
