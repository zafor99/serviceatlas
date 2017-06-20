package framework.alchemycontroller;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import framework.AtlasScriptbase;
import framework.alchemymodel.ElementObject;
import framework.alchemymodel.LibraryPageModel;
import framework.alchemymodel.SettingsPageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  November 04, 2014
 */
public class LibraryPageController extends DeviceControllerBase
{
	private SettingsPageModel settingsPageModel = null;
	private LibraryPageModel librayPageModel = null;
	
	public LibraryPageController(Client client) {
		super(client);
		settingsPageModel = new SettingsPageModel(client);
		librayPageModel = new LibraryPageModel(client);
	}
	
	
	public boolean verifyContent(String vpName){
		
		boolean result = false;
		String[][] data = null;
		ElementObject[] contents = librayPageModel.libraryContainer().getElementObjects("NATIVE", "/*[@class='com.nook.lib.library.LibraryItem']", 0);
		
		data = new String[contents.length][4];		
		for(int i=0; i<contents.length; i++){
			
			ElementObject[] textObjects = contents[i].getElementObjects("NATIVE", "/*/*[@text]", 0);
			for(int j=0; j<textObjects.length;j++){
				data[i][j] = textObjects[j].getText();
			}
			ElementObject button = contents[i].getElementObject("NATIVE", "/*/*/*[@id='buttons']/*[@id='download_btn' and @hidden='false']", 0);
			if(button.isElementFound()){
				data[i][3] = "Download";
			}
			else
			{
				data[i][3] = "";
			}
		}
		
		ITestData testData = VpUtil.getTestData(data);
		
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, testData).performTest();
		
		return result;
	}
	
}
