package com.atg_sync_services.utils;

public class EnvironmentUtility 
{	
	//private static String ENVIRONMENT = "DEV";
	//private static String ENVIRONMENT = "QA06";
	private static String ENVIRONMENT = "QA02";
	//private static String ENVIRONMENT = "Prod UT";
	private static String CATALOG_SERVER = "";
	private static String MONGO_DB_SERVER = "";
	private static String MYSQL_DB_SERVER = "";
	private static Server caliber = null;
	private static Server mySQLDB = null;
	private static Server atlas = null;
	private static Server sap = null;
	private static Server eai = null;
	private static Server crm = null;
	private static Server customerAccDom = null;
	private static Server customerAccIntl = null;
	
	public EnvironmentUtility(){
		
	}
	public String  getEnvironment(){
		return this.ENVIRONMENT;
	}
	
	public  static Server crm(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			crm = new Server("https://crmq2.barnesandnoble.com/","zsadeque","Aminai@6");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			crm = new Server("https://crmq2.barnesandnoble.com/","zsadeque","Aminai@6");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			crm = new Server("https://crmq1.barnesandnoble.com/","qa_broadcast","qa_brdc");
			
		}
		return crm;
	}
	public static Server caliber(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://injsvcjapp01:8080","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			caliber = new Server("http://injsvcjapp05:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://dsvdevctg02.sjc1700.bnweb.user.bn:8080","qa_broadcast","qa_brdc");
			
		}
		return caliber;
	}
	
	public static Server nest(){
		if (ENVIRONMENT.contentEquals("QA01")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://injnest01:8080","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			caliber = new Server("http://injnest02:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://injnest01:8080","qa_broadcast","qa_brdc");
			
		}
		else if (ENVIRONMENT.contentEquals("Prod UT")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://pwbnest01:8080","qa_broadcast","qa_brdc");
			
		}
		return caliber;
	}
	
	public static Server customerAccDom(){
		if (ENVIRONMENT.contentEquals("QA01")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			//atlas = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccDom = new Server("http://injsvcjapp01:8080","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			customerAccDom = new Server("injsvcjapp02:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV01")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccDom = new Server("dnjsvcjapp01:8080","qa_broadcast","qa_brdc");
			
		}
		else if (ENVIRONMENT.contentEquals("DEV02")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccDom = new Server("dnjsvcjapp02:8080","qa_broadcast","qa_brdc");
			
		}
		else if (ENVIRONMENT.contentEquals("QA06")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			//atlas = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccDom = new Server("http://injsvcjapp01:8080","qa_broadcast","qa_brdc");
		} 
		return customerAccDom;
	}
	
	public static Server customerAccIntl(){
		if (ENVIRONMENT.contentEquals("QA01")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			//atlas = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccIntl = new Server("https://csqa2.barnesandnoble.com","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			customerAccIntl = new Server("https://csdev.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV01")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccIntl = new Server("https://csdev2.barnesandnoble.com","qa_broadcast","qa_brdc");
			
		}
		else if (ENVIRONMENT.contentEquals("DEV02")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccIntl = new Server("https://csdev2.barnesandnoble.com","qa_broadcast","qa_brdc");
			
		}
		else if (ENVIRONMENT.contentEquals("QA06")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			//atlas = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			customerAccIntl = new Server("https://csqa2.barnesandnoble.com","qa_broadcast","qa_brdc");
		} 
		return customerAccIntl;
	}
	
	public static Server catalog(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("https://csqa2.barnesandnoble.com","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			caliber = new Server("http://csdev.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://csqa2.barnesandnoble.com","qa_broadcast","qa_brdc");
			
		}
		return caliber;
	}
	
	public static Server atlas(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			//atlas = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			atlas = new Server("http://injeaijapp01:8080","rouser","read0nly!");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			atlas = new Server("http://injeaijapp01:8080","rouser","read0nly!");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			atlas = new Server("http://dsvdevctg02.sjc1700.bnweb.user.bn:8080","qa_broadcast","qa_brdc");
			
		}
		return atlas;
	}
	
	public static Server sap(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			sap = new Server("3a.  RQ2  -  ECC  SI2","zsadeque","Aminai@9");
			//sap = new Server("New RQ2","zsadeque","Aminai@6");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			sap = new Server("3a.  RQ2  -  ECC  SI2","zsadeque","Aminai@9");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			sap = new Server("http://dsvdevctg02.sjc1700.bnweb.user.bn:8080","qa_broadcast","qa_brdc");
			
		}
		return sap;
	}
	
	public static Server eaiInternal(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			eai = new Server("http://injeaijapp01:8080","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			eai = new Server("http://injeaijapp01:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			eai = new Server("http://dsvdevctg02.sjc1700.bnweb.user.bn:8080","qa_broadcast","qa_brdc");
			
		}
		return eai;
	}
	public static Server eaiExternal(){
		if (ENVIRONMENT.contentEquals("QA")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			eai = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			eai = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			//catalog = new Server("https://csdevctg.barnesandnoble.com","qa_broadcast","qa_brdc");
			eai = new Server("https://qa-api.barnesandnoble.com","qa_broadcast","qa_brdc");
			
		}
		return eai;
	}
	@Deprecated
	public static Server msWallet(){
		if (ENVIRONMENT.contentEquals("QA01")){
			//catalog = new Server("https://csqactg.barnesandnoble.com","qa_broadcast","qa_brdc");
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		} 
		else if (ENVIRONMENT.contentEquals("QA02")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA03")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA04")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA05")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA06")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA07")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("QA08")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");
		}
		else if (ENVIRONMENT.contentEquals("DEV01")){
			caliber = new Server("http://injsvcjapp06:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV02")){
			caliber = new Server("http://dnjsvcjapp02:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV03")){
			caliber = new Server("http://dnjsvcjapp02:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV04")){
			caliber = new Server("http://dnjsvcjapp04:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV05")){
			caliber = new Server("http://dnjsvcjapp05:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV06")){
			caliber = new Server("http://dnjsvcjapp06:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV07")){
			caliber = new Server("http://dnjsvcjapp07:8080","qa_broadcast","qa_brdc");			
		}
		else if (ENVIRONMENT.contentEquals("DEV08")){
			caliber = new Server("http://dnjsvcjapp08:8080","qa_broadcast","qa_brdc");			
		}
		return caliber;
	}
	
	public static Server mySQLDB(){
		if (ENVIRONMENT.contentEquals("QA")){
			mySQLDB = new Server("injqa1mysql01","BNCLD_READ_USER","BNCLD_READ_USER");
			//mySQLDB = new Server("injqa1mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}else if (ENVIRONMENT.contentEquals("QA02")){
			mySQLDB = new Server("injqa1mysql01","BNCLD_READ_USER","BNCLD_READ_USER");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			mySQLDB = new Server("dsvdevmysql01.sjc1700.bnweb.user.bn","BNCLD_READ_USER","BNCLD_READ_USER");
			//mySQLDB = new Server("dsvsb2mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		return mySQLDB;
	}
	
	
	public static Server userProfileDB(){
		if (ENVIRONMENT.contentEquals("QA01")){
			mySQLDB = new Server("//10.1.161.175:1521/INYHG.barnesandnoble.com","si_user","si_user");
			//mySQLDB = new Server("injqa1mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}else if (ENVIRONMENT.contentEquals("QA02")){
			mySQLDB = new Server("//10.1.161.175:1521/INYHG.barnesandnoble.com","si_user","si_user");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			mySQLDB = new Server("//10.1.161.175:1521/INYHG.barnesandnoble.com","si_user","si_user");
			//mySQLDB = new Server("dsvsb2mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		return mySQLDB;
	}
	public static Server communityDB(){
		if (ENVIRONMENT.contentEquals("QA01")){
			mySQLDB = new Server("//INJCOMMDB01/INJCMTY.barnesandnoble.com","si_user","si_user");
			//mySQLDB = new Server("injqa1mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}else if (ENVIRONMENT.contentEquals("QA02")){
			mySQLDB = new Server("//INJCOMMDB01/INJCMTY.barnesandnoble.com","si_user","si_user");
		}
		else if (ENVIRONMENT.contentEquals("DEV")){
			mySQLDB = new Server("//10.1.161.175:1521/INYHG.barnesandnoble.com","si_user","si_user");
			//mySQLDB = new Server("dsvsb2mysql01.barnesandnoble.com","qa_broadcast","qa_brdc");
		}
		return mySQLDB;
	}
	public static class Server
	{
		private String server = "";
		private String port = "";
		private String userName = "";
		private String passWord = "";

		public Server(String server, String userName, String passWord){
			this.server = server;
			this.userName = userName;
			this.passWord = passWord;
		}		

		public String serverName(){
			return server;
		}
		

		public String userName(){
			return userName;
		}

		public String passWord(){
			return passWord;
		}

	}

}

