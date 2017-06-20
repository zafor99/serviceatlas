package framework.alchemymodel;

import utils.XMLUtil;
import utils.XMLUtility;

import com.experitest.client.*;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 04, 2014
 */
public abstract class DeviceModelBase 
{
	protected static Client client = null;//new Client("localhost", 8889);
	private static String iosApp = "com.barnesandnoble.B-N-eReader";
	private static String androidApp = "bn.ereader/.MainActivity";
	private static String app = iosApp;
	private static XMLUtil deviceInfoXML = new XMLUtil();
	
	public DeviceModelBase(Client client){
		this.client = client;
		
	}
	
	public void setApp(String app){
		this.app = app;
	}
	
	public Client getClient(){
		return client;
	}
	
	public static String getOS(){

		return deviceInfoXML().getRootNode().getChild("device").getAttributeValue("os");
		
	}
	
	public static XMLUtil deviceInfoXML(){
		deviceInfoXML.readFromString(client.getDevicesInformation());
		return deviceInfoXML;
	}
	
	public static void main(String[] args) {
		System.out.println(getOS());
		System.out.println();
		
	}
	
	public boolean isIOS(){
		boolean result=false;
		if(getOS().contains("ios")){
			result = true;
		}
		return result;
	}
	
	public boolean isAndroid(){
		boolean result=false;
		if(!getOS().contains("ios")){
			result = true;
		}
		return result;
	}
	
	public String getiOSDevice(){
		String device = null;
		String [] devices = client.getConnectedDevices().split("\n");
		for (int i=0;i<devices.length;i++)
		{
			if(devices[i].contains("ios")){
				device = devices[i];
			}
		}
		
		return device;
	}
	
	public String getAndroidDevice(){
		String device = null;
		String [] devices = client.getConnectedDevices().split("\n");
		for (int i=0;i<devices.length;i++)
		{
			if(devices[i].contains("adb")){
				device = devices[i];
			}
		}
		
		return device;
	}
	
	
	
}
