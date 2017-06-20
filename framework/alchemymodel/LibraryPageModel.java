package framework.alchemymodel;

import com.experitest.client.Client;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  October 22, 2014
 */
public class LibraryPageModel extends DeviceModelBase
{

	public LibraryPageModel(Client client) {
		super(client);
		
	}
	
	public ElementObject libraryContainer(){
		return new ElementObject("NATIVE", "xpath=//*[@class='com.nook.widget.scrollcaster.ScrollCasterGridView']", 0);
	}
	
	
	public ElementObject[] libraryContents(){
		
		ElementObject[] contents = null;
		int count = 0;
		
		count = client.getElementCount("NATIVE", "xpath=(//*/*/*/*[@id='title_bottom'])");
		System.out.println("Library Content Count: " + count);
		contents = new ElementObject[count];
		for(int i=0; i<count;i++){
			contents[i] = new ElementObject("NATIVE", "xpath=(//*/*/*/*[@id='title_bottom'])[" + i+1 + "]" ,0);
		}
		return contents;
	}
}
