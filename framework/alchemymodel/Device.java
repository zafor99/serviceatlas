package framework.alchemymodel;

import utils.XMLUtil;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 09, 2014
 */
public class Device extends RationalTestScript
{
	protected static Client client = null;//new Client("localhost", 8889);
	private static String iosApp = "com.barnesandnoble.B-N-eReader";
	private static String androidApp = "bn.ereader/.MainActivity";
	private static String app = iosApp;
	private static XMLUtil deviceInfoXML = new XMLUtil();
	
	public Device(Client client){
		Device.client = client;
	}
	
	public static void startLocalClient(){
		client = new com.experitest.client.Client("localhost", 8889,false);
		//client = new com.experitest.client.Client("localhost", 8889);
	}
	
	public static Client getClient(){
		return client;
	}
	
	public static void setDeviceGalaxyTab7Inch(){
		client = new Client("localhost", 8889,false);
		client.setDevice("adb:Galaxy Tab 4");
		app = "bn.ereader/.MainActivity";
		//client.setProjectBaseDirectory("C:\\Users\\zsadeque\\workspace\\project2");
		//client.setProjectBaseDirectory("C:\\Users\\fahmed\\workspace\\project3");
	}
	
	public static void setDeviceGalaxyTab10Inch(){
		client = new Client("localhost", 8889,false);
		client.setDevice("adb:Galaxy Tab 4 10 Inch");
		app = "bn.ereader/.MainActivity";
		//client.setProjectBaseDirectory("C:\\Users\\zsadeque\\workspace\\project2");
		//client.setProjectBaseDirectory("C:\\Users\\fahmed\\workspace\\project3");
	}
	
	public static void setiOSDevice(){
		client = new Client("localhost", 8889,false);
		client.setDevice("ios_app:iPhone 4S");
		app = "com.barnesandnoble.B-N-eReader";
		
		//client.setProjectBaseDirectory("C:\\Users\\zsadeque\\workspace\\project2");
		//client.setProjectBaseDirectory("C:\\Users\\fahmed\\workspace\\project3");
	}
	
	public static String getOS(){

		return deviceInfoXML().getRootNode().getChild("device").getAttributeValue("os");
		
	}
	
	public static boolean isIOS(){
		boolean result=false;
		if(getOS().contains("ios")){
			result = true;
		}
		return result;
	}
	
	public static boolean isAndroid(){
		boolean result=false;
		if(!getOS().contains("ios")){
			result = true;
		}
		return result;
	}
	
	public static String getiOSDevice(){
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
	
	public static String getAndroidDevice(){
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
	
	public static void launchApp(){
		client.launch(app, true, false);
	}
	
	public static XMLUtil deviceInfoXML(){
		deviceInfoXML.readFromString(client.getDevicesInformation());
		return deviceInfoXML;
	}
}
