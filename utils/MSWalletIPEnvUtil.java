package utils;

import com.rational.test.ft.script.RationalTestScript;
/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 19, 2014
 */
public  class MSWalletIPEnvUtil
{
	private String m_env = null;
	private static String m_clientType = "WINDEVICE";
	private  static String m_customerId = "";
	private  static String m_cartClientId = "41";
	private  static String m_idType = "customerId";
	private static MSWalletClient ms_WalletClient = null;
	
	private EnvironmentUtility envUtil = new EnvironmentUtility();
	private String ipCheckoutEndPoint = "/CheckoutService/cartItem/instantPurchase?";
	
	public MSWalletIPEnvUtil(String clientType, String id, String cartClientId, String idType){
		this.m_clientType = clientType;
		this.m_customerId = id;
		this.m_cartClientId = cartClientId;
		this.m_idType = idType;
	}
	
	public MSWalletIPEnvUtil(){
		
	}
	
	public void setCustomerId(String id)
	{
		this.m_customerId = id;
	}
	
	public static void setClientType(String clientType)
	{
		m_clientType = clientType;
	}
	
	
	public String getEnvironment(){
		return envUtil.getEnvironment();
	}
	
	public String getClientType(){
		return m_clientType;
	}
	
	public String getId(){
		return m_customerId;
	}
	
	public String getCartClientId(){
		return m_cartClientId;
	}
	
	public String getIdType(){
		return m_idType;
	}
	
	public String getUserName(){
		return ms_WalletClient.userName();
	}
	
	public String getPassword(){
		return ms_WalletClient.password();
	}
	
	public String getCcountryCode(){
		return ms_WalletClient.country();
	}
	
	public String getLocale(){
		return ms_WalletClient.locale();
	}
	
	public String getRetailer(){
		return ms_WalletClient.retailer();
	}
	
	public String getEan(){
		return ms_WalletClient.ean();
	}
	
	public String getInstantPurchaseURL(){
		return ms_WalletClient.getInstantPurchaseURL();
	}
	
	/*public static MSWalletIPEnvUtil createWindeviceTokenCredentials(String id)
	{
		return new MSWalletIPEnvUtil("WINDEVICE",id,m_cartClientId, m_idType);
	}*/
	
	public MSWalletClient clientInfo(){
		return ms_WalletClient;
	}
	
	public void readClientInfo(String country, String clientType){
		
		if(clientType.contentEquals("WINDEVICE")){
			setClientType("WINDEVICE");			
			if(country.toUpperCase().contentEquals("US")){
				ms_WalletClient = this.new MSWalletClient("bnbuyertest0700@live-int.com", "password", "US", "en_US", "BN2", "9780983196327");
			}else if(country.toUpperCase().contentEquals("GB")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0701@live-int.com", "password", "GB", "en_GB", "NOK", "2940046816167");
			}else if(country.toUpperCase().contentEquals("CZ")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0702@live-int.com", "password", "CZ", "cs_CZ", "NOK", "2940012053213");
			}else if(country.toUpperCase().contentEquals("DK")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0703@live-int.com", "password", "DK", "da_DK", "NOK", "2940013149779");
			}else if(country.toUpperCase().contentEquals("DE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0704@live-int.com", "password", "DE", "de_DE", "NOK", "2940013278585");
			}else if(country.toUpperCase().contentEquals("GR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0705@live-int.com", "password", "GR", "el_GR", "NOK", "2940014722520");
			}else if(country.toUpperCase().contentEquals("EE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0706@live-int.com", "password", "EE", "et_EE", "NOK", "2940015080681");
			}else if(country.toUpperCase().contentEquals("FR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0707@live-int.com", "password", "FR", "fr_FR", "NOK", "2940015532784");
			}else if(country.toUpperCase().contentEquals("HU")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0708@live-int.com", "password", "HU", "hu_HU", "NOK", "2940015532784");
			}
			else if(country.toUpperCase().contentEquals("IT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0709@live-int.com", "password", "IT", "it_IT", "NOK", "2940016377056");
			}else if(country.toUpperCase().contentEquals("LT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0710@live-int.com", "password", "LT", "lt_LT", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("LV")){
					ms_WalletClient = new MSWalletClient("bnbuyertest0711@live-int.com", "password", "LV", "lv_LV", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("MT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0712@live-int.com", "password", "MT", "mt_MT", "NOK", "9780849949203");
			}else if(country.toUpperCase().contentEquals("NL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0713@live-int.com", "password", "NL", "nl_NL", "NOK", "9780857896346");
			}else if(country.toUpperCase().contentEquals("PL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0714@live-int.com", "password", "PL", "pl_PL", "NOK", "9780957111615");
			}else if(country.toUpperCase().contentEquals("PT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0715@live-int.com", "password", "DK", "pt_PT", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("RO")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0716@live-int.com", "password", "RO", "ro_RO", "NOK", "9781402246968");
			}else if(country.toUpperCase().contentEquals("SK")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0717@live-int.com", "password", "SK", "sk_SK", "NOK", "9781406326260");
			}else if(country.toUpperCase().contentEquals("SI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0718@live-int.com", "password", "SI", "sl_SI", "NOK", "9781446428504");
			}else if(country.toUpperCase().contentEquals("FI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0719@live-int.com", "password", "FI", "sv_FI", "NOK", "9781446428504");
			}
			else if(country.toUpperCase().contentEquals("SE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0720@live-int.com", "password", "SE", "sv_SE", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("CY")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0721@live-int.com", "password", "CY", "tr_CY", "NOK", "2940012053213");
			}
		}else if(clientType.contentEquals("WEB")){
			setClientType("WEB");
			
			if(country.toUpperCase().contentEquals("US")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0730@live-int.com", "password", "US", "en_US", "BN2", "9780983196327");
			}else if(country.toUpperCase().contentEquals("GB")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0731@live-int.com", "password", "GB", "en_GB", "NOK", "2940046816167");
			}else if(country.toUpperCase().contentEquals("CZ")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0732@live-int.com", "password", "CZ", "cs_CZ", "NOK", "2940012053213");
			}else if(country.toUpperCase().contentEquals("DK")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0733@live-int.com", "password", "DK", "da_DK", "NOK", "2940013149779");
			}else if(country.toUpperCase().contentEquals("DE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0734@live-int.com", "password", "DE", "de_DE", "NOK", "2940013278585");
			}else if(country.toUpperCase().contentEquals("GR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0735@live-int.com", "password", "GR", "el_GR", "NOK", "2940014722520");
			}else if(country.toUpperCase().contentEquals("EE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0736@live-int.com", "password", "EE", "et_EE", "NOK", "2940015080681");
			}else if(country.toUpperCase().contentEquals("FR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0737@live-int.com", "password", "FR", "fr_FR", "NOK", "2940015532784");
			}else if(country.toUpperCase().contentEquals("HU")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0738@live-int.com", "password", "HU", "hu_HU", "NOK", "2940015532784");
			}
			else if(country.toUpperCase().contentEquals("IT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0739@live-int.com", "password", "IT", "it_IT", "NOK", "2940016377056");
			}else if(country.toUpperCase().contentEquals("LT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0740@live-int.com", "password", "LT", "lt_LT", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("LV")){
					ms_WalletClient = new MSWalletClient("bnbuyertest0741@live-int.com", "password", "LV", "lv_LV", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("MT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0742@live-int.com", "password", "MT", "mt_MT", "NOK", "9780849949203");
			}else if(country.toUpperCase().contentEquals("NL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0743@live-int.com", "password", "NL", "nl_NL", "NOK", "9780857896346");
			}else if(country.toUpperCase().contentEquals("PL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0744@live-int.com", "password", "PL", "pl_PL", "NOK", "9780957111615");
			}else if(country.toUpperCase().contentEquals("PT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0745@live-int.com", "password", "DK", "pt_PT", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("RO")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0746@live-int.com", "password", "RO", "ro_RO", "NOK", "9781402246968");
			}else if(country.toUpperCase().contentEquals("SK")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0747@live-int.com", "password", "SK", "sk_SK", "NOK", "9781406326260");
			}else if(country.toUpperCase().contentEquals("SI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0748@live-int.com", "password", "SI", "sl_SI", "NOK", "9781446428504");
			}else if(country.toUpperCase().contentEquals("FI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0749@live-int.com", "password", "FI", "sv_FI", "NOK", "9781446428504");
			}
			else if(country.toUpperCase().contentEquals("SE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0750@live-int.com", "password", "SE", "sv_SE", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("CY")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0751@live-int.com", "password", "CY", "tr_CY", "NOK", "2940012053213");
			}
		}else if(clientType.contentEquals("BINGNEWSSTAND")){
			setClientType("BINGNEWSSTAND");
			if(country.toUpperCase().contentEquals("US")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0760@live-int.com", "password", "US", "en_US", "BN2", "9780983196327");
			}else if(country.toUpperCase().contentEquals("GB")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0761@live-int.com", "password", "GB", "en_GB", "NOK", "2940046816167");
			}else if(country.toUpperCase().contentEquals("CZ")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0762@live-int.com", "password", "CZ", "cs_CZ", "NOK", "2940012053213");
			}else if(country.toUpperCase().contentEquals("DK")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0763@live-int.com", "password", "DK", "da_DK", "NOK", "2940013149779");
			}else if(country.toUpperCase().contentEquals("DE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0764@live-int.com", "password", "DE", "de_DE", "NOK", "2940013278585");
			}else if(country.toUpperCase().contentEquals("GR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0765@live-int.com", "password", "GR", "el_GR", "NOK", "2940014722520");
			}else if(country.toUpperCase().contentEquals("EE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0766@live-int.com", "password", "EE", "et_EE", "NOK", "2940015080681");
			}else if(country.toUpperCase().contentEquals("FR")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0767@live-int.com", "password", "FR", "fr_FR", "NOK", "2940015532784");
			}else if(country.toUpperCase().contentEquals("HU")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0768@live-int.com", "password", "HU", "hu_HU", "NOK", "2940015532784");
			}
			else if(country.toUpperCase().contentEquals("IT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0769@live-int.com", "password", "IT", "it_IT", "NOK", "2940016377056");
			}else if(country.toUpperCase().contentEquals("LT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0770@live-int.com", "password", "LT", "lt_LT", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("LV")){
					ms_WalletClient = new MSWalletClient("bnbuyertest0771@live-int.com", "password", "LV", "lv_LV", "NOK", "9780753547519");
			}else if(country.toUpperCase().contentEquals("MT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0772@live-int.com", "password", "MT", "mt_MT", "NOK", "9780849949203");
			}else if(country.toUpperCase().contentEquals("NL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0773@live-int.com", "password", "NL", "nl_NL", "NOK", "9780857896346");
			}else if(country.toUpperCase().contentEquals("PL")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0774@live-int.com", "password", "PL", "pl_PL", "NOK", "9780957111615");
			}else if(country.toUpperCase().contentEquals("PT")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0775@live-int.com", "password", "DK", "pt_PT", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("RO")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0776@live-int.com", "password", "RO", "ro_RO", "NOK", "9781402246968");
			}else if(country.toUpperCase().contentEquals("SK")){
				ms_WalletClient = new  MSWalletClient("bnbuyertest0777@live-int.com", "password", "SK", "sk_SK", "NOK", "9781406326260");
			}else if(country.toUpperCase().contentEquals("SI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0778@live-int.com", "password", "SI", "sl_SI", "NOK", "9781446428504");
			}else if(country.toUpperCase().contentEquals("FI")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0779@live-int.com", "password", "FI", "sv_FI", "NOK", "9781446428504");
			}
			else if(country.toUpperCase().contentEquals("SE")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0780@live-int.com", "password", "SE", "sv_SE", "NOK", "9781401689575");
			}else if(country.toUpperCase().contentEquals("CY")){
				ms_WalletClient = new MSWalletClient("bnbuyertest0781@live-int.com", "password", "CY", "tr_CY", "NOK", "2940012053213");
			}
		}

	}

	
	public MSWalletClient US(){
		return new MSWalletClient("bnbuyertest0700@live-int.com", "password", "US", "en_US", "BN2", "9780983196327");
	}
	
	public MSWalletClient GB(){
		return new MSWalletClient("bnbuyertest0701@live-int.com", "password", "GB", "en_GB", "NOK", "2940046816167");
	}
	
	public MSWalletClient CZ(){
		return new MSWalletClient("bnbuyertest0702@live-int.com", "password", "CZ", "cs_CZ", "NOK", "2940012053213");
	}
	
	public MSWalletClient DK(){
		return new MSWalletClient("bnbuyertest0703@live-int.com", "password", "DK", "da_DK", "NOK", "2940013149779");
	}
	
	public MSWalletClient DE(){
		return new MSWalletClient("bnbuyertest0704@live-int.com", "password", "DE", "de_DE", "NOK", "2940013278585");
	}
	
	public MSWalletClient GR(){
		return new MSWalletClient("bnbuyertest0705@live-int.com", "password", "GR", "el_GR", "NOK", "2940014722520");
	}
	
	public MSWalletClient EE(){
		return new MSWalletClient("bnbuyertest0706@live-int.com", "password", "EE", "et_EE", "NOK", "2940015080681");
	}
	
	public MSWalletClient FR(){
		return new MSWalletClient("bnbuyertest0707@live-int.com", "password", "DK", "fr_FR", "NOK", "2940015532784");
	}
	
	public MSWalletClient HU(){
		return new MSWalletClient("bnbuyertest0708@live-int.com", "password", "HU", "hu_HU", "NOK", "2940016340326");
	}
	
	public MSWalletClient IT(){
		return new MSWalletClient("bnbuyertest0709@live-int.com", "password", "IT", "it_IT", "NOK", "2940016377056");
	}
	
	public MSWalletClient LT(){
		return new MSWalletClient("bnbuyertest0710@live-int.com", "password", "DK", "lt_LT", "NOK", "9780753547519");
	}
	
	public MSWalletClient LV(){
		return new MSWalletClient("bnbuyertest0711@live-int.com", "password", "LV", "lv_LV", "NOK", "9780758277664");
	}
	
	public MSWalletClient MT(){
		return new MSWalletClient("bnbuyertest0712@live-int.com", "password", "MT", "mt_MT", "NOK", "9780849949203");
	}
	
	public MSWalletClient NL(){
		return new MSWalletClient("bnbuyertest0713@live-int.com", "password", "NL", "nl_NL", "NOK", "9780857896346");
	}
	
	public MSWalletClient PL(){
		return new MSWalletClient("bnbuyertest0714@live-int.com", "password", "PL", "pl_PL", "NOK", "9780957111615");
	}
	
	public MSWalletClient PT(){
		return new MSWalletClient("bnbuyertest0715@live-int.com", "password", "DK", "pt_PT", "NOK", "9781401689575");
	}
	
	public MSWalletClient RO(){
		return new MSWalletClient("bnbuyertest0716@live-int.com", "password", "RO", "ro_RO", "NOK", "9781402246968");
	}
	
	public MSWalletClient SK(){
		return new MSWalletClient("bnbuyertest0717@live-int.com", "password", "SK", "sk_SK", "NOK", "9781406326260");
	}
	
	public MSWalletClient SI(){
		return new MSWalletClient("bnbuyertest0718@live-int.com", "password", "SI", "sl_SI", "NOK", "9781446428504");
	}
	
	public MSWalletClient FI(){
		return new MSWalletClient("bnbuyertest0719@live-int.com", "password", "FI", "sv_FI", "NOK","2940016340326");
	}
	
	public MSWalletClient SE(){
		return new MSWalletClient("bnbuyertest0720@live-int.com", "password", "SE", "sv_SE", "NOK", "9781401689575");
	}
	
	public MSWalletClient CY(){
		return new MSWalletClient("bnbuyertest0721@live-int.com", "password", "CY", "tr_CY", "NOK", "2940012053213");
	}
	
	public class MSWalletClient {
		
		private String username = null;
		private String password = null;
		private String country = null;
		private String locale = null;
		
		private String retailer = null;
		private String ean = null; 		
		private String ipURL = null;
		
		public MSWalletClient(){
			
		}
		
		public MSWalletClient(String username, String password, String country, String locale, String retailer, String ean){			
			this.username = username;
			this.password = password;
			this.country = country;
			this.locale = locale;
			this.retailer = retailer;
			this.ean = ean;
		}
		
		public void setInfo(String username, String password, String country, String locale, String retailer, String ean){
			
			if(!username.contentEquals("!") && username!=null){
				this.username = username;
			}
			
			if(!password.contentEquals("!") && password!=null){
				this.password = password;
			}
			
			if(!country.contentEquals("!") && country!=null){
				this.country = country;
			}
			
			if(!locale.contentEquals("!") && locale!=null){
				this.locale = locale;
			}
			
			if(!retailer.contentEquals("!") && retailer!=null){
				this.retailer = retailer;
			}
			
			if(!ean.contentEquals("!") && ean!=null){
				this.ean = ean;
			}
		}
		
		public String userName(){
			return this.username;
		}
		
		public String password(){
			return this.password;
		}
		
		public String country(){
			return this.country;
		}
		
		public String locale(){
			return this.locale;
		}
		
		public String retailer(){
			return this.retailer;
		}
		
		public String ean(){
			return this.ean;
		}
		
		public String getInstantPurchaseURL(){
			String url = "";
			if(country.contentEquals("US")){
				url= envUtil.caliber().serverName() + ipCheckoutEndPoint + "cartClientId=" + getCartClientId() + "&idType=" + getIdType() + "&id=" + getId() ;
			}
			else
			{
				url= envUtil.caliber().serverName() + ipCheckoutEndPoint +  "cartClientId=" + getCartClientId() + "&idType=" + getIdType() + "&id=" + getId() + "&retailerID=" + this.retailer + "&country=" + this.country;
			}
			return url;
		}
	}
}
