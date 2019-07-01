package nestservicestgtocaliber.address;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class UpdateCustomerAddress extends TestScriptBase{
 
  @Test
  public void TC01_Positive_UpdateCA(Method method) {
	  
	  Response response = null;	  
	  nest().addresService().load();	  
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  sleep(20);
	  String customerId = nest().addresService().getMercuryCustomerId();
	  String mercuryAddId = nest().addresService().getMercuryAddressId();
	  dbService().addressTable(customerId,mercuryAddId).validateTable().addressId(mercuryAddId).firstName("grant").lastName("gortsema")
	  	.addressLine1("123 Sesame Sts").addressLine2("APT 223").city("New York City").state("NY").zipCode("10010");
	  
	  nest().addresService().modifyJSONAddressRequest().lastModified("2014-07-06T11:46:54.000+0000").addressLine1("76 9th Updated Ave");
	  response = nest().addresService().update();
	  	  
	  responseService().validate(response).statusCode(200).statusLine("OK");

	  sleep(20);
	  dbService().addressTable(customerId,mercuryAddId).validateTable().addressId(mercuryAddId).addressLine1("76 9th Updated Ave");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC02_BlankAddressNickname(Method method){
	  Response response = null;
	  String time = nest().addresService().getModifiedUpdatedTime();
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("Tr01JHo7jL0BKiv1");
	  response = nest().addresService().create();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  sleep(20);
	  String customerId = nest().addresService().getMercuryCustomerId();
	  String mercuryAddId = nest().addresService().getMercuryAddressId();
	  nest().addresService().modifyJSONAddressRequest().lastModified("2014-07-06T11:46:54.000+0000").addressNickName("");
	  response = nest().addresService().update();
	  
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("addressNickname can not be empty ");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC03_Blank_LastModified(Method method){
	  Response response = null;
	  String time = nest().addresService().getModifiedUpdatedTime();
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("000SrkLU108HrWd3");
	  nest().addresService().setMercuryAddressId("5836582");
	  nest().addresService().modifyJSONAddressRequest().lastModified("");
	  response = nest().addresService().update();
	  
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("lastModified should be in the past | lastModified can not be null ");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC04_Invalid_LastModified(Method method){
	  Response response = null;
	  String time = nest().addresService().getModifiedUpdatedTime();
	  nest().addresService().load();
	  nest().addresService().setMercuryCustomerId("000SrkLU108HrWd3");
	  nest().addresService().setMercuryAddressId("5836582");
	  nest().addresService().modifyJSONAddressRequest().lastModified("2014-0");
	  response = nest().addresService().update();
	  
	  
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().addresService().validateErrorMessage(response).code("1000").description("Can not construct instance of java.util.Date from String value '2014-0': not a valid representation (error: Can not parse date \"2014-0\": not compatible with any of standard forms (\"yyyy-MM-dd'T'HH:mm:ss.SSSZ\", \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\", \"EEE, dd MMM yyyy HH:mm:ss zzz\", \"yyyy-MM-dd\"))\n at [Source: com.bn.common.logging.web.filter.WrappedHttpServletRequest$WrappedServletInputStream@");
	  assert_All(method.getName());
  }
}
