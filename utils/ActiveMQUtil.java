package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.xerces.impl.dv.util.Base64;
import org.openqa.selenium.internal.seleniumemulation.GetExpression;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 05, 2014
 */
public  class ActiveMQUtil 
{
	private URLConnection uc = null;
	public ActiveMQUtil(){
		connect();
	}
	
	public URLConnection connect(){
		try {
			URL url = new URL(EnvironmentUtility.caliber().serverName().substring(0, 20)+"8161/admin/xml/queues.jsp");
			System.out.println(url);
			uc = url.openConnection();
			String userpass = "testadmin" + ":" + "mq&NspEe";
			String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
			uc.setRequestProperty ("Authorization", basicAuth);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uc;
	}
	public int getRoutePendingMessageCount(String routeName){
		int count = 0;
		uc = connect();
			try {
				InputStream in = uc.getInputStream();
				BufferedReader buff = null;
				String line, responseText = "";
				buff = new BufferedReader(new InputStreamReader(in));
				String xml;
				
				while ((line = buff.readLine()) != null) {
						responseText = responseText + line;
				}
				System.out.println(responseText);
				XMLUtil xmlUtilyy = new XMLUtil();
				
				xmlUtilyy.readFromString(responseText);
				System.out.println(xmlUtilyy.getXMLString());
				
				count = xmlUtilyy.getSizeByRouteName(routeName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//	}
		
			
		return count;
	}
	public boolean verifyPendingMessageCountByRoute(String vpName,String routeName){
		boolean result = false;
		int routeCount = getRoutePendingMessageCount(routeName);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, routeCount).performTest();
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
		
	}
}
