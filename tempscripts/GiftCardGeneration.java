package tempscripts;
import resources.tempscripts.GiftCardGenerationHelper;
import utils.GiftCardUtil;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class GiftCardGeneration extends GiftCardGenerationHelper
{
	/**
	 * Script Name   : <b>GiftCardGeneration</b>
	 * Generated     : <b>Dec 11, 2013 11:31:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/12/11
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		GiftCardUtil giftCard = new GiftCardUtil();
		String amount = "5";
		
		for(int i=1;i<2;i++){
			giftCard.generateGiftCard(amount);
			System.out.println("Gift Card "+i+" : "+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
			logInfo("Gift Card "+i+" : "+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		}
		
/*		giftCard.generateGiftCard(amount);
		
		System.out.println("Gift Card1 : "+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard(amount);
		System.out.println("Gift Card2 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard(amount);
		System.out.println("Gift Card3 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard(amount);
		System.out.println("Gift Card4 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard(amount);
		System.out.println("Gift Card5 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard(amount);
		System.out.println("Gift Card6 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
*/
/*		giftCard.generateGiftCard("100");
		System.out.println("Gift Card7 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
		giftCard.generateGiftCard("100");
		System.out.println("Gift Card8 :"+giftCard.getGiftCardNo()+" and Pin :"+ giftCard.getGiftCardPin()) ;
*/	}
}

