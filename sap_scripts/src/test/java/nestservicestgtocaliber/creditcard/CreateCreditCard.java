package nestservicestgtocaliber.creditcard;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class CreateCreditCard extends TestScriptBase{
  
  @Test
  public void TC01_Positive_CreateCreditCard(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("545454").cardType("MC").expirationDate("30-Jun-19");
	  assert_All(method.getName());
  }
  
  @Test
  public void TC02_Positive_CreditCardTypeVI(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("545454EEWGYM5454").creditcardType("VI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("545454").cardType("VI").expirationDate("30-Jun-19");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC03_Positive_CreditCardTypeAMX(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("371449JKMVE8431").displayNumber("8431").creditcardType("AX").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("371449").cardType("AX").expirationDate("30-Jun-19");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC04_Positive_CreditCardTypeDI(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("601100").cardType("DI").expirationDate("30-Jun-19");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC05_Invalid_CC_Token(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("abc");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("neither cardType nor token can be null. Token must be uppercase letters and numbers. CardType must be AX, MC, VI, DC, JB, or DI, and token length must be 14 for type DC and 15 for type AX and 16 for type MC, VI, JB or DI ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC06_Blank_CC_Token(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("neither cardType nor token can be null. Token must be uppercase letters and numbers. CardType must be AX, MC, VI, DC, JB, or DI, and token length must be 14 for type DC and 15 for type AX and 16 for type MC, VI, JB or DI ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC07_InvalidAccDisplayNum(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().displayNumber("abc");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("displayNumber must be numbers only | displayNumber must be 4 numbers long ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC08_BlnkAccDisplayNum(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().displayNumber("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("displayNumber must be 4 numbers long | displayNumber must be numbers only ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC09_Invalid_CardType(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditcardType("VS");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("neither cardType nor token can be null. Token must be uppercase letters and numbers. CardType must be AX, MC, VI, DC, JB, or DI, and token length must be 14 for type DC and 15 for type AX and 16 for type MC, VI, JB or DI ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC10_Blank_CardType(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditcardType("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("neither cardType nor token can be null. Token must be uppercase letters and numbers. CardType must be AX, MC, VI, DC, JB, or DI, and token length must be 14 for type DC and 15 for type AX and 16 for type MC, VI, JB or DI ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC11_Invalid_CardExpMo(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardExpMonth("13");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("creditCardExpMo can't be higher than 12 ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC12_Blank_CardExpMo(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardExpMonth("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("creditCardExpMo can not be null ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC13_Invalid_CardExpYr(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardExpYear("199A");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("Can not construct instance of java.lang.Integer from String value '199A': not a valid Integer value");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC14_Blank_CardExpYr(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardExpYear("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("creditCardExpYr can not be null ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC15_Invalid_LastModified(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().lastModified("20.12-03-11T15:46:48.000+0000");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("Can not construct instance of java.util.Date from String value '20.12-03-11T15:46:48.000+0000': not a valid representation");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC16_Blank_LastModified(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().lastModified("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(400).statusLine("Bad Request");
	  nest().creditCardService().validateErrorMessage(response).code("1000").description("lastModified can not be null | lastModified should be in the past ");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC17_NoMatchMercuryUsrID(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(""); 
	  nest().creditCardService().modifyJSONRequest().lastModified("");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");	
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC18_CC_AlreadyExists(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(201).statusLine("Created");
	  response = nest().creditCardService().create();
	  responseService().validate(response).statusCode(409).statusLine("Conflict");
	  nest().creditCardService().validateErrorMessage(response).code("3000").description("Card already exists with that token,type, and user.");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC25_Postivie_CreditCardTypeJB(Method method) {
	 
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();
	  nest().creditCardService().load();
	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("A817ADBFA7D5A1D8").displayNumber("0000").creditcardType("JB").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("A817AD").cardType("JB").expirationDate("30-Jun-19");
	  assert_All(method.getName());	  
	  
  }
}
