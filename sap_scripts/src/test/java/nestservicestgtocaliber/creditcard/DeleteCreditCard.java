package nestservicestgtocaliber.creditcard;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.atg_sync_services.framework.TestScriptBase;

import io.restassured.response.Response;

public class DeleteCreditCard extends TestScriptBase{
  @Test
  public void TC01_Positive_DeleteCreditCard(Method method) {
	  
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
	  
	  
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  dbService().cardTable(customerID, ccId).validateTable().emptyRows();
	  
	  assert_All(method.getName());
	  
	  
  }
  
  @Test
  public void TC02_Positive_DeleteCreditCardVI(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("545454EEWGYM5454").creditcardType("VI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 

	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("545454").cardType("VI").expirationDate("30-Jun-19");
	  
	  
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  dbService().cardTable(customerID, ccId).validateTable().emptyRows();
	  
	  assert_All(method.getName());
	  
	  
  }
  
  @Test
  public void TC03_Positive_DeleteCreditCardDI(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 
	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("601100").cardType("DI").expirationDate("30-Jun-19");	  
	  
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  dbService().cardTable(customerID, ccId).validateTable().emptyRows();
	  
	  assert_All(method.getName());

  }
  
  @Test
  public void TC04_Positive_DeleteCreditCardAX(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("371449JKMVE8431").displayNumber("8431").creditcardType("AX").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	 
	  dbService().cardTable(customerID, ccId).validateTable().accountNumber("371449").cardType("AX").expirationDate("30-Jun-19");	  
	  
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  dbService().cardTable(customerID, ccId).validateTable().emptyRows();
	  
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC05_CardId_Associated_2_UserId(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("371449JKMVE8431").displayNumber("8431").creditcardType("AX").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId1 = nest().creditCardService().getMercuryCCId();	 

	  dbService().cardTable(customerID, ccId1).validateTable().accountNumber("371449").cardType("AX").expirationDate("30-Jun-19");	
	  
	  nest().creditCardService().setCustomerId(customerID);
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
		.statusLine("Created")
		.path("entity", "CreditCard");
	  String ccId2 = nest().creditCardService().getMercuryCCId();	
	  
	  dbService().cardTable(customerID, ccId2).validateTable().accountNumber("601100").cardType("DI").expirationDate("30-Jun-19");	
	  
	  nest().creditCardService().setMercuryCCId(ccId1);
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(200).statusLine("OK");
	  dbService().cardTable(customerID, ccId1).validateTable().emptyRows();
	  
	  assert_All(method.getName());
	  
	  
  }
  
  @Test
  public void TC06_InvalidUserId(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	  
	  nest().creditCardService().setCustomerId("abc");
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");
	  nest().creditCardService().validateErrorMessage(response).code("2000").description("Unable to find customerId abc");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC07_Invalid_CardId(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	  
	  nest().creditCardService().setCustomerId(customerID);
	  nest().creditCardService().setMercuryCCId("abc");
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");
	  assert_All(method.getName());	  
	  
  }
  
  @Test
  public void TC08_Blank_UserId(Method method) {
	  
	  Response response = null;	
	  String email = customerAccount().generateRandomEmailAddress();
	  nest().customerService().create(email);
	  String customerID = nest().customerService().getUserId();

	  nest().creditCardService().setCustomerId(customerID); 
	  nest().creditCardService().modifyJSONRequest().creditCardToken("601100AUKVRS0000").displayNumber("0000").creditcardType("DI").creditCardExpMonth("6").creditCardExpYear("2019");
	  response = nest().creditCardService().create();
	  nest().creditCardService().response().validate(response).statusCode(201)
	  		.statusLine("Created")
	  		.path("entity", "CreditCard");
	  String ccId = nest().creditCardService().getMercuryCCId();
	  
	  nest().creditCardService().setCustomerId("");
	  response = nest().creditCardService().delete();
	  responseService().validate(response).statusCode(404).statusLine("Not Found");
	  assert_All(method.getName());	  
	  
  }
}
