package scripts.gxs_inbound.negative;
import resources.scripts.gxs_inbound.negative.GSX_Inbound_Negative_ShellScriptHelper;
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
public class GSX_Inbound_Negative_ShellScript extends GSX_Inbound_Negative_ShellScriptHelper
{
	
	/**
	 * Script Name   : <b>GSX_Inbound_Negative_ShellScript</b>
	 * Generated     : <b>Jun 4, 2013 3:21:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/04
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		callScript("scripts.gxs_inbound.negative.TC01_BlankReferenceID");
		callScript("scripts.gxs_inbound.negative.TC02_BatchNumberReferenceIDDuplicate");
		callScript("scripts.gxs_inbound.negative.TC03_AlternamteDocumentIDDuplicate");
		callScript("scripts.gxs_inbound.negative.TC04_DocumentID_IDMoreThan16");
		callScript("scripts.gxs_inbound.negative.TC05_BlankAlternateDocumentID");
		callScript("scripts.gxs_inbound.negative.TC06_AlternateDocumentIDFieldMoreThan20");
		callScript("scripts.gxs_inbound.negative.TC07_BlankDocumentDateTime");
		callScript("scripts.gxs_inbound.negative.TC08_InvalidDocumentDateTime");
		callScript("scripts.gxs_inbound.negative.TC09_BlankActualShipDateTime");
		callScript("scripts.gxs_inbound.negative.TC10_InvalidActualShipDateTime");
		callScript("scripts.gxs_inbound.negative.TC11_BlankGrossWeightMeasure");
		callScript("scripts.gxs_inbound.negative.TC12_InvalidGrossWeightMeasure");
		callScript("scripts.gxs_inbound.negative.TC13_BlankCarrierPartyPartyIDsSCACID");
		callScript("scripts.gxs_inbound.negative.TC14_CarrierPartyPartyIDsSCACIDlengthLimit");
		callScript("scripts.gxs_inbound.negative.TC15_BlankSupplierPartyPartyIDsID");
		callScript("scripts.gxs_inbound.negative.TC16_SupplierPartyPartyIDsIDLengthLingth");
		callScript("scripts.gxs_inbound.negative.TC17_BlankShiptoPartyPartyIDsID");
		callScript("scripts.gxs_inbound.negative.TC18_ShiptoPartyPartyIDsIDLengthLimit");
		callScript("scripts.gxs_inbound.negative.TC19_BlankShipmentUnitID");
		callScript("scripts.gxs_inbound.negative.TC20_ShipmentUnitIDlengthMoreThan16");
		callScript("scripts.gxs_inbound.negative.TC21_BlankShipmentUnitTrackingID");
		callScript("scripts.gxs_inbound.negative.TC22_ShipmentUnitTrackingIDlengthLimit");
		callScript("scripts.gxs_inbound.negative.TC23_BlankGrossWeightMeasure");
		callScript("scripts.gxs_inbound.negative.TC24_InvalidGrossWeightMeasure");
		callScript("scripts.gxs_inbound.negative.TC27_BlankShipmentUnitItemItemIDID");
		callScript("scripts.gxs_inbound.negative.TC28_ShipmentUnitItemItemIDIDlengthLimit");
		callScript("scripts.gxs_inbound.negative.TC29_BlankShipmentUnitItemShippedQty");
		callScript("scripts.gxs_inbound.negative.TC30_ShipmentUnitItemShippedQtyotherthanSerialNumbercount");
		callScript("scripts.gxs_inbound.negative.TC31_ShipmentUnitItemShippedQtyascharacters");
		callScript("scripts.gxs_inbound.negative.TC32_BlankShipmentUnitItemPOReferenceDocumentID");
		callScript("scripts.gxs_inbound.negative.TC33_ShipmentUnitItemPOReferenceDocumentIDLengthLimit");
		callScript("scripts.gxs_inbound.negative.TC34_BlankShipmentUnitItemPOReferenceLineNumber");
		callScript("scripts.gxs_inbound.negative.TC35_ShipmentUnitItemPOReferenceLineNumberLengthLimit");
		callScript("scripts.gxs_inbound.negative.TC36_ShipmentUnitItemPOReferenceLineNumberasCharacters");
		callScript("scripts.gxs_inbound.negative.TC37_BlankShipmentUnitItemItemSubLineSerialNumber");
		callScript("scripts.gxs_inbound.negative.TC38_ShipmentUnitItemItemSubLineSerialNumberLengthLimit");
		callScript("scripts.gxs_inbound.negative.TC39_BlankShipmentUnitItemUnitSalePriceAmount");
		callScript("scripts.gxs_inbound.negative.TC40_InvalidShipmentUnitItemUnitSalePriceAmount");
		callScript("scripts.gxs_inbound.negative.TC41_ReferenceIDLengthKimit");
		callScript("scripts.gxs_inbound.negative.TC42_BlankDocumentIDIDfield");

	}
}

