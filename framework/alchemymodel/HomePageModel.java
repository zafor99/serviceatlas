package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  September 17, 2014
 */
public class HomePageModel extends DeviceModelBase
{

	public HomePageModel(Client client) {
		super(client);		
	}
	
	
	public ElementObject settingsSlideBarLink(){
		return new ElementObject("NATIVE", "xpath=//*[@class='com.android.internal.widget.ActionBarView$HomeView']/*[@id='up']", 0);
		//return new ElementObject("NATIVE", "xpath=//*[@id='up']", 0);
	}
	
	public SettingsSlideBar settingSlideBar(){
		return new SettingsSlideBar();
	}
	
	
	public ElementObject buyBook(){
		return new ElementObject("WEB", "xpath=//*[@value='$9.99' and @width>0 and ./parent::*[./parent::*[./preceding-sibling::*[./*[@text='Bestselling Books Test 2']]]]]", 0);
	}
	
	
	public class SettingsSlideBar{
		
		public ElementObject myHomeLink(){
			return new ElementObject("NATIVE", "xpath=//*[@text='My Home']", 0);
		}
		
		public ElementObject myShopLink(){
			return new ElementObject("NATIVE", "xpath=//*[@text='Shop' and @id='textview']", 0);
		}
		
		public ElementObject quickReadsLink(){
			return new ElementObject("NATIVE", "xpath=//*[@text='Quick Reads' and @id='textview']", 0);
		}
		
		public ElementObject libraryLink(){
			return new ElementObject("NATIVE", "xpath=//*[@text='Library' and @id='textview']", 0);
		}
		
		public ElementObject settingsLink(){
			return new ElementObject("NATIVE", "xpath=//*[@text='Settings']", 0);
		}
	}
}
