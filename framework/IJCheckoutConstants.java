package framework;

public interface IJCheckoutConstants {

	// Services Names 
	public static final String BASE_GET_PROMOTIONAL_MESSAGING_PATH = "/CheckoutService/getPromotionalMessaging?";
	public static final String BASE_ADD_ITEM_PATH = "/CheckoutService/cartItem/add?";
	public static final String BASE_AGGREGATION_PATH = "/CheckoutService/aggregation";
	public static final String BASE_GET_CART_PATH = "/CheckoutService/getCart?";
	public static final String BASE_UPDATE_QUANTITY_PATH = "/CheckoutService/cartItem/updateQuantity?";
	public static final String BASE_ADD_GIFTCARD_PATH = "/CheckoutService/addGiftCard?";
	public static final String BASE_SET_ACCOUNT_DEFAULT_PATH = "/CheckoutService/setAccountDefault?";
	public static final String BASE_GET_SHIPPING_METHODS_PATH = "/CheckoutService/getShippingMethods?";
	public static final String BASE_SET_SHIP_SERVICE_LEVEL_PATH = "/CheckoutService/shipment/setServiceLevel?";     
	public static final String BASE_US_SET_SHIP_SERVICE_LEVEL_PATH = "/CheckoutService/shipment/setServiceLevel?";  // US Checkout uses lower case setServiceLevel
	public static final String BASE_INSTANTPURCHASE_PATH = "/CheckoutService/cartItem/instantPurchase?";
	public static final String BASE_GET_PAYMENT_METHODS_PATH = "/CheckoutService/getPaymentMethods?";
	public static final String BASE_CLEAR_CART_PATH = "/CheckoutService/clearCart?";
	public static final String BASE_SET_SHIP_SEPERATELY_PATH = "/CheckoutService/shipment/shipSeparately?";
	public static final String BASE_CONVERT_CART_PATH = "/CheckoutService/convertCart?";
	public static final String BASE_Get_CUSTOMER_NOTIFICATION_PATH = "/CustomerNotificationService/getCustomerNotification?";
	public static final String BASE_REMOVE_PAYMENT_PATH = "/CheckoutService/payment/remove?";
	//Hello Moto
	public static final String BASE_SUBMIT_ORDER_PATH = "/CheckoutService/submitOrder?";
	public static final String BASE_ADD_CREDITCARD_PATH = "/CheckoutService/payment/addCreditCard?";
	public static final String BASE_ADD_COUPON_PATH = "/CheckoutService/addCoupon?";
	public static final String BASE_SET_TAX_EXEMPT_ID_PATH = "/CheckoutService/setTaxExemptID?";
	
	
	//******************************************************
	public static final String TAXABLE_POSITIVE_SCENARIOS_XML = "integrationtesting/positiveScenarios/xml/";
	public static final String TAXABLE_POSITIVE_SCENARIOS_JSON = "integrationtesting/positiveScenarios/json/";
	public static final String IP_COPA__XML = "copa/xml/";
	public static final String TAXABLE_ENEWSPAPER_XML = "integrationtesting/salestax/xml/taxable/";
	public static final String GIFTCARDS_XML = "integrationtesting/giftcards/xml/";
	public static final String GIFTCARDS_JSON = "integrationtesting/giftcards/json/";
	public static final String WHITELIST_CUSTOMER_XML = "integrationtesting/whitelistcustomers/xml/";
	//*******************************************************
	
	public static final String JCHECKOUT_ERROR_CODE_XPATH = "/response/errors/error/code";
	public static final String JCHECKOUT_ERROR_DESCRIPTION_XPATH = "/response/errors/error/description";
	public static final String JCHECKOUT_ERROR_ID_XPATH = "/response/errors/error/id";
	
	public static final String JCHECKOUT_ERROR_CODE_XPATH2 = "/response/errors/error[2]/code";
	public static final String JCHECKOUT_ERROR_DESCRIPTION_XPATH2 = "/response/errors/error[2]/description";
	public static final String JCHECKOUT_ERROR_ID_XPATH2 = "/response/errors/error[2]/id";
	
	public static final String JCHECKOUT_ERROR_COUNT = "count(//response/errors/error)";
	
	public static final String JCHECKOUTDOC_ERROR_CODE_XPATH = "/response/checkoutDocument/errors/error/code";
	public static final String JCHECKOUTDOC_ERROR_DESCRIPTION_XPATH = "/response/checkoutDocument/errors/error/description";
	public static final String JCHECKOUTDOC_ERROR_ID_XPATH = "/response/checkoutDocument/errors/error/id";
	
	public static final String JCHECKOUT_ORDER_XPATH = "//response/checkoutDocument/order";
	public static final String JCHECKOUT_ORDER_JPATH = "//response/checkoutDocument/order";
	public static final String JCHECKOUT_SAP_ORDER_XPATH = "/response/checkoutDocument/orderg/feMessage";
	public static final String JCHECKOUT_SAP_ORDER_JPATH = "//response/checkoutDocument/order/feMessage";
	
	public static final String JCHECKOUT_ORDER_NUMBER_XPATH = "//response/checkoutDocument/order/@orderNumber";
	public static final String JCHECKOUT_ORDER_NUMBER_JPATH = "//orderNumber";
	public static final String JCHECKOUT_ORDER_DATE_XPATH = "/response/checkoutDocument/order/@orderDate";
	public static final String JCHECKOUT_AUTH_TYPE_XPATH = "/response/checkoutDocument/order/payment/creditCard/authType";
	public static final String JCHECKOUT_AUTH_CODE_XPATH = "/response/checkoutDocument/order/payment/creditCard/authCode";
	public static final String JCHECKOUT_AUTH_RESPONSE_XPATH = "/response/checkoutDocument/order/payment/creditCard/authResponse";
	public static final String JCHECKOUT_AUTH_RESPONSE_CODE_XPATH = "/response/checkoutDocument/order/payment/creditCard/authResponseCode";
	public static final String JCHECKOUT_AUTH_AMOUNT_XPATH = "/response/checkoutDocument/order/payment/creditCard/authAmount";
	public static final String JCHECKOUT_AUTH_AMOUNT_FROM_GC_XPATH = "/payment/giftCards/giftCard[1]/authAmount";
	public static final String JCHECKOUT_AUTH_APPROVED_AMOUNT_XPATH = "/response/checkoutDocument/order/payment/creditCard/authApprovedAmount";
	public static final String JCHECKOUT_AUTH_APPROVED_AMOUNT_FROM_GC_XPATH = "/payment/giftCards/giftCard[1]/authApprovedAmount";
	public static final String JCHECKOUT_CHANNEL_TYPE_XPATH = "/response/checkoutDocument/order/@channelType";
	public static final String JCHECKOUT_ALLOCATION_PAYMENTAMOUNT_XPATH = "/response/checkoutDocument/order/allocations/allocation/@paymentAmount";
	public static final String JCHECKOUT_ALLOCATION_PAYMENTAMOUNT_JPATH = "//allocations/e/paymentAmount";
	public static final String JCHECKOUT_SAP_ORDER_NUMBER_XPATH = "/response/checkoutDocument/order/feMessage/msgID";
	public static final String JCHECKOUT_WL_PARTNER_ID_XPATH = "/response/checkoutDocument/order/@whiteLabelPartnerID";
	public static final String JCHECKOUT_ALLOCATION_XPATH = "/response/checkoutDocument/order/allocations/allocation";
	public static final String JCHECKOUT_ALLOCATION_JPATH = "//allocations/e";
	public static final String JCHECKOUT_GIFTCARD_XPATH = "/payment/giftCards/giftCard[1]";
	public static final String JCHECKOUT_GIFTCARD_JPATH = "//payment/giftCards/e[1]/giftCard";
	public static final String JCHECKOUT_PAYMENT_CREDITCARD_XPATH = "/response/checkoutDocument/order/payment/creditCard";
	public static final String JCHECKOUT_PAYMENT_CREDITCARD_JPATH = "//response/checkoutDocument/order/payment/creditCard";
	
	
	public static final String JCHECKOUT_ORDER_ALLOCATION_XPATH = "/response/checkoutDocument/order/allocations/allocation";
	public static final String JCHECKOUT_ORDER_ALLOCATION_JPATH = "//response/checkoutDocument/order/allocations/e";
	
	public static final String JCHECKOUTDOC_ISSUE_CODE_XPATH1 = "/response/checkoutDocument/completeness/issue[1]/code";
	public static final String JCHECKOUTDOC_ISSUE_DESCRIPTION_XPATH1 = "/response/checkoutDocument/completeness/issue[1]/description";
	public static final String JCHECKOUTDOC_ISSUE_ID_XPATH1 = "/response/checkoutDocument/completeness/issue[1]/id";
	public static final String JCHECKOUTDOC_ISSUE_CODE_XPATH2 = "/response/checkoutDocument/completeness/issue[2]/code";
	public static final String JCHECKOUTDOC_ISSUE_DESCRIPTION_XPATH2 = "/response/checkoutDocument/completeness/issue[2]/description";
	public static final String JCHECKOUTDOC_ISSUE_ID_XPATH2 = "/response/checkoutDocument/completeness/issue[2]/id";
	public static final String JCHECKOUTDOC_ISSUE_CODE_XPATH3 = "/response/checkoutDocument/completeness/issue[3]/code";
	public static final String JCHECKOUTDOC_ISSUE_DESCRIPTION_XPATH3 = "/response/checkoutDocument/completeness/issue[3]/description";
	public static final String JCHECKOUTDOC_ISSUE_ID_XPATH3 = "/response/checkoutDocument/completeness/issue[3]/id";
	public static final String JCHECKOUTDOC_ISSUE_CODE_XPATH4 = "/response/checkoutDocument/completeness/issue[4]/code";
	public static final String JCHECKOUTDOC_ISSUE_DESCRIPTION_XPATH4 = "/response/checkoutDocument/completeness/issue[4]/description";
	public static final String JCHECKOUTDOC_ISSUE_ID_XPATH4 = "/response/checkoutDocument/completeness/issue[4]/id";
	
	public static final String JCHECKOUTDOC_CUSTOMERID_XPATH = "/response/checkoutDocument/order/profile/customerID";
	public static final String JCHECKOUTDOC_PAYMENT_XPATH = "/response/checkoutDocument/order/payment";
	public static final String JCHECKOUTDOC_PAYMENT_JPATH = "//response/checkoutDocument/order/payment";
	
	public static final String JCHECKOUTDOC_PAYMENT_JSON_XPATH = "/o/response/checkoutDocument/order/payment";
	public static final String JCHECKOUTDOC_PAYMENT_CREDITCARD_DISPLAYNUMBER_XPATH = "/response/checkoutDocument/order/payment/creditCard/displayNumber";
	public static final String JCHECKOUTDOC_PAYMENT_CREDITCARD_CARDID_XPATH = "/response/checkoutDocument/order/payment/creditCard/cardID";
	public static final String JCHECKOUTDOC_PAYMENT_CREDITCARD_CODE_XPATH = "/response/checkoutDocument/order/payment/creditCard/code";
	public static final String JCHECKOUTDOC_PAYMENT_DIRECTDEBIT_PAYMENTID_XPATH = "/response/checkoutDocument/order/payment/type";
	public static final String JCHECKOUTDOC_PAYMENT_BILLINGADDRESS_XPATH = "/response/checkoutDocument/order/payment/billingAddress";
	public static final String JCHECKOUTDOC_DELTASTATES_XPATH = "/response/checkoutDocument/order/deltaStates";
	public static final String JCHECKOUTDOC_ORDER_XPATH = "/response/checkoutDocument/order";
	public static final String JCHECKOUTDOC_ORDER_PROFILE_XPATH = "/response/checkoutDocument/order/profile";
	
	public static final String JCHECKOUT_ERROR_CODE_JPATH = "//bnresponse/errors";
	public static final String JCHECKOUT_ERROR_DESCRIPTION_JPATH = "//response/errors/e/description";
	public static final String JCHECKOUT_ERROR_ID_JPATH = "//response/errors/e/id";
	public static final String JCHECKOUT_ERROR_CODE_COUNT_JPATH = "count(//response/errors)";
	 
	public static  String JCHECKOUTDOC_BN_ITEM_COUNT_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/count/@value";
	public static  String JCHECKOUTDOC_BN_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[1]/@value";
	public static  String JCHECKOUTDOC_BN_CARTITEMTOTAL_JPATH = "//response/checkoutDocument/order/totals/totalsGroup/e[1]/total/e[1]/value"; 
	public static  String JCHECKOUTDOC_BN_GIFTWRAP_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[2]/@value";
	public static  String JCHECKOUTDOC_BN_ORDERTAXTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[3]/@value";
	public static  String JCHECKOUTDOC_BN_ORDERTAXTOTAL_JPATH = "//response/checkoutDocument/order/totals/totalsGroup/e[1]/total/e[3]/value";
	public static  String JCHECKOUTDOC_BN_ORDERTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[4]/@value";
	public static  String JCHECKOUTDOC_BN_ORDERTOTAL_JPATH = "//response/checkoutDocument/order/totals/totalsGroup/e[1]/total/e[4]/value";
	public static  String JCHECKOUTDOC_BN_SAVING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[5]/@value";
	public static  String JCHECKOUTDOC_BN_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/total[6]/@value";
	public static  String JCHECKOUTDOC_BN_DISCOUNTS_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/discounts/discount[1]/@value";
	public static  String JCHECKOUTDOC_BN_DISCOUNTS_ORDER_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/discounts/discount[2]/@value";
	public static  String JCHECKOUTDOC_BN_DISCOUNTS_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/discounts/discount[3]/@value";
	public static  String JCHECKOUTDOC_BN_DISCOUNTS_ITEMLEVEL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/discounts/discount[4]/@value";
//	public static  String JCHECKOUTDOC_BN_CARTITEMTOTAL_DISCOUNT_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[1]/discounts/disount[1]/@value";
	
	public static  String JCHECKOUTDOC_AGGREGATE_ITEM_COUNT_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/count/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[1]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_GIFTWRAP_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[2]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_ORDERTAXTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[3]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_ORDERTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[4]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_SAVING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[5]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/total[6]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_DISCOUNTS_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/discounts/discount[1]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_DISCOUNTS_ORDER_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/discounts/discount[2]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_DISCOUNTS_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/discounts/discount[3]/@value";
	public static  String JCHECKOUTDOC_AGGREGATE_DISCOUNTS_ITEMLEVEL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[2]/discounts/discount[4]/@value";
	
	
	public static  String JCHECKOUTDOC_MP_ITEM_COUNT_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/count/@value";
	public static  String JCHECKOUTDOC_MP_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[1]/@value";
	public static  String JCHECKOUTDOC_MP_GIFTWRAP_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[2]/@value";
	public static  String JCHECKOUTDOC_MP_ORDERTAXTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[3]/@value";
	public static  String JCHECKOUTDOC_MP_ORDERTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[4]/@value";
	public static  String JCHECKOUTDOC_MP_SAVING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[5]/@value";
	public static  String JCHECKOUTDOC_MP_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/total[6]/@value";
	public static  String JCHECKOUTDOC_MP_DISCOUNTS_CARTITEMTOTAL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/discounts/discount[1]/@value";
	public static  String JCHECKOUTDOC_MP_DISCOUNTS_ORDER_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/discounts/discount[2]/@value";
	public static  String JCHECKOUTDOC_MP_DISCOUNTS_SHIPPING_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/discounts/discount[3]/@value";
	public static  String JCHECKOUTDOC_MP_DISCOUNTS_ITEMLEVEL_XPATH = "/response/checkoutDocument/order/totals/totalsGroup[3]/discounts/discount[4]/@value";
	
	public static  String JCHECKOUTDOC_BNPRICE_XPATH = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem/productListing/priceInfo/bnPrice";
	public static  String JCHECKOUTDOC_MARKETPLACEPRICE_XPATH = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem/productListing/priceInfo/marketplacePricing";
	public static  String JCHECKOUTDOC_SHIPMENTDISCOUNT_XPATH = "/response/checkoutDocument/order/shipments/shipment/shipmentDiscount";
	public static  String JCHECKOUTDOC_QUANTITY_XPATH = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem/@quantity";
    
	public static String JCHECKOUT_CARTITEM_XPATH = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem";
	public static String JCHECKOUT_CARTITEM_COUNT_XPATH = "count(/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem)";
	public static String JCHECKOUT_SHIPMENT_XPATH = "/response/checkoutDocument/order/shipments/shipment";
	public static String JCHECKOUT_SHIPMENTS_XPATH = "/response/checkoutDocument/order/shipments";
	public static String JCHECKOUT_SHIPMENTS_METHOD_CODE1_XPATH = "/response/availableShippingMethods/shipment/method[1]/code";
	public static String JCHECKOUT_SHIPMENTS_METHOD_CODE2_XPATH = "/response/availableShippingMethods/shipment/method[2]/code";
	public static String JCHECKOUT_SHIPMENT_COUNT_XPATH = "count(/response/checkoutDocument/order/shipments/shipment)";
	public static String JCHECKOUT_SHIPMENT_SERVICE_CODE_XPATH = "//order/shipments/shipment/serviceLevel/code";
	public static String JCHECKOUT_SHIPMENT_SERVICE_SHIPPINGMETHODID_XPATH = "//order/shipments/shipment/serviceLevel/shippingMethodId";
	public static String JCHECKOUT_SHIPMENT_SERVICE_METHOD_XPATH = "//availableShippingMethods/shipment/method[1]";
	public static String JCHECKOUT_SHIPMENT_SERVICE_METHOD2_XPATH = "//availableShippingMethods/shipment/method[2]";
	public static String JCHECKOUT_SHIPMENT_SERVICELEVEL_XPATH = "/response/checkoutDocument/order/shipments/shipment/serviceLevel";
	public static String JCHECKOUT_SHIPMENT_SHIPPINGADDRESS_XPATH = "/response/checkoutDocument/order/shipments/shipment/shippingAddress";
	public static String JCHECKOUT_GETPAYMENT_METHOD1_XPATH = "//paymentMethods/paymentMethod/paymentMethod[1]";
	public static String JCHECKOUT_GETPAYMENT_METHOD2_XPATH = "//paymentMethods/paymentMethod/paymentMethod[2]";
	public static String JCHECKOUT_GETPAYMENT_METHOD3_XPATH = "//paymentMethods/paymentMethod/paymentMethod[3]";
	public static String JCHECKOUT_GETPAYMENT_CODE1_XPATH = "//paymentMethods/paymentMethod/code[1]";
	public static String JCHECKOUT_GETPAYMENT_CODE2_XPATH = "//paymentMethods/paymentMethod[2]/code";
	public static String JCHECKOUT_GETPAYMENT_CODE3_XPATH = "//paymentMethods/paymentMethod[3]/code";
	public static String JCHECKOUT_GETPAYMENT_DESCRIPTION1_XPATH = "//paymentMethods/paymentMethod/description[1]";
	public static String JCHECKOUT_GETPAYMENT_DESCRIPTION2_XPATH = "//paymentMethods/paymentMethod[2]/description";
	public static String JCHECKOUT_GETPAYMENT_DESCRIPTION3_XPATH = "//paymentMethods/paymentMethod[3]/description";
	public static String JCHECKOUT_GETPAYMENT_ID1_XPATH = "//paymentMethods/paymentMethod/id[1]";
	public static String JCHECKOUT_GETPAYMENT_ID2_XPATH = "//paymentMethods/paymentMethod[2]/id";
	public static String JCHECKOUT_GETPAYMENT_ID3_XPATH = "//paymentMethods/paymentMethod[3]/id";
	
	public static String JCHECKOUTDOC_CARTITEM_GIFTCARDINFO_XPATH = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem/giftcardInfo";
	
	public static  String JCHECKOUTDOC_PRODUCT_DIMENSIONS_XPATH1 = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem[1]/productListing/product/dimensions";
	public static  String JCHECKOUTDOC_PRODUCT_DIMENSIONS_XPATH2 = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem[1]/productListing/product/dimensions";
	public static  String JCHECKOUTDOC_PRODUCT_DIMENSIONS_XPATH3 = "/response/checkoutDocument/order/shipments/shipment/cartitems/cartItem[1]/productListing/product/dimensions";
	
	public static  String JCHECKOUTDOC_PAYMENT_CREDITCARD_XPATH = "/response/checkoutDocument/order/payment/creditCard"; //"/response/checkoutDocument/order";  //fix this path
	public static  String JCHECKOUTDOC_PAYMENT_CREDITCARDERROR_XPATH = "/response/checkoutDocument/order";  
	
	public static  String JCHECKOUTDOC_TAXEXEMPTID_XPATH = "/response/checkoutDocument/order/taxexemptInfo/taxExemptID"; 
	
	public static String JCHECKOUTDOC_IP_SERVICECALLINFO_XPATH = "/response/checkoutDocument/order/serviceCallInfo";
	public static String JCHECKOUTDOC_IP_SERVICECALLINFO_JPATH = "//response/checkoutDocument/order/serviceCallInfo";
	public static String JCHECKOUTDOC_IP_ACERTIFY_XPATH = "//response/checkoutDocument/order/serviceCallInfo/acertifyStatus";
	public static String JCHECKOUTDOC_IP_AUTHSTATUS_XPATH = "//response/checkoutDocument/order/serviceCallInfo/authStatus";
	public static String JCHECKOUTDOC_IP_AUTHTYPE_XPATH = "//response/checkoutDocument/order/serviceCallInfo/authType";
	public static String JCHECKOUTDOC_IP_EDS_INSERTSTATUS_XPATH = "//response/checkoutDocument/order/serviceCallInfo/edsInsertStatus";
	public static String JCHECKOUTDOC_IP_EDS_STATUS_XPATH = "//response/checkoutDocument/order/serviceCallInfo/edsStatus";
	public static String JCHECKOUTDOC_IP_VERTEX_STATUS_XPATH = "//response/checkoutDocument/order/serviceCallInfo/vertexStatus";
	
	public static String JCHECKOUTDOC_IP_ACERTIFY_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/acertifyStatus";
	public static String JCHECKOUTDOC_IP_AUTHSTATUS_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/authStatus";
	public static String JCHECKOUTDOC_IP_AUTHTYPE_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/authType";
	public static String JCHECKOUTDOC_IP_EDS_INSERTSTATUS_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/edsInsertStatus";
	public static String JCHECKOUTDOC_IP_EDS_STATUS_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/edsStatus";
	public static String JCHECKOUTDOC_IP_VERTEX_STATUS_JSON_XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/vertexStatus";

	public static String JCHECKOUTDOC_PAYMENT_MSWALLET_XPATH = "/response/checkoutDocument/order/payment/msWallet";
	public static String JCHECKOUTDOC_PAYMENT_MSWALLET_JPATH = "//response/checkoutDocument/order/payment/msWallet";

	public static String JCHECKOUTDOC__XPATH = "/o/response/checkoutDocument/order/serviceCallInfo/vertexStatus";
	
	public static String AVAILABLE_SHIP_METHODS_SHIPMENT = "/response/availableShippingMethods/shipment/";
	public static String JCHECKOUTDOC_IP_ALLOCATIONS = "//response/checkoutDocument/order/allocations";
	public static String JCHECKOUTDOC_IP_ALLOCATION = "//response/checkoutDocument/order/allocations/allocation";
	
	public static String CHECKOUT_DOCUMENT_NODES[] = {
		    "/response/checkoutDocument/completeness/issue"	,
			"/response/checkoutDocument/order/profile",
			"/response/checkoutDocument/order/shipments",
			"/response/checkoutDocument/order/totals",
			"/response/checkoutDocument/order/profile/customerID",
			"/response/checkoutDocument/order/shipments/shipment[1]/@shipmentType",
			"/response/checkoutDocument/order/shipments/shipment[1]/@shipmentID",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/@amount",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/@cartItemId",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/@quantity",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/EAN",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/availability",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/orderAndFulfillment",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/priceInfo",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/product",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/productCode",
			"/response/checkoutDocument/order/shipments/shipment[1]/cartitems/cartItem/productListing/type",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/@groupType",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/count/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/discounts",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[1]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[1]/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[2]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[2]/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[3]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[3]/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[4]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[4]/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[5]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[5]/@value",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[6]/@name",
			"/response/checkoutDocument/order/totals/totalsGroup[1]/total[6]/@value",
			
			
	};
}
