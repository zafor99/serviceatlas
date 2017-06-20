package framework;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.EnvironmentUtility;

import com.main.SerializeToDatabase;
import com.main.VpUtils;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  July 10, 2014
 */
public class HealthCheckSmokeTest 
{
	private static Boolean m_First = true;
	private static Boolean m_HealthCheck = false;
	private static String m_BuildNumber = "";
	private static Integer m_EnvId = null;
	private static String m_serverName = null;
	private static String m_LogName = "";
	private static String m_ShellName = "";
	private static String m_ScriptName = "";
	private static String m_ScriptDescription = "";
	private static String m_Comments = "";
	private static Long m_ScriptId = null;
	private static long m_RunId = 0;
	private static int m_ServiceId = 0;
	private static Boolean m_Script = false;
	private static ArrayList<Boolean> m_ResultList = new ArrayList<Boolean>();
	
	public HealthCheckSmokeTest(){
		
	}
	
	public static void start(){
		try {
			m_RunId = SerializeToDatabase.getLatestRunId();
			//m_RunId = SerializeToDatabase.logRunStart(m_BuildNumber, "", getComputerName(),"Integration Test");
			//m_RunId = SerializeToDatabase.logRunStart(m_BuildNumber, "", getComputerName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_HealthCheck = true;
	}
	
	
	public static Boolean scriptStarted(){
		
		return m_Script;
	}
	
	public static Boolean first(){
		return m_First;
	}
	
	public static void first(Boolean first){
		 m_First = first;
	}
	
	public static void end(){
		try {
			SerializeToDatabase.logRunEnd(m_RunId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_HealthCheck = false;
	}
	
	private static void resetArrayList(){
		m_ResultList.clear();
	}
	
	public static Boolean isRunning(){
		return m_HealthCheck;
	}
	
	public static void setBuildNumber(String buildNumber){
		m_BuildNumber = buildNumber;
	}
	
	public static void setLogName(String logName){
		m_LogName = logName;
	}
	
	public static void setShellName(String shellName){
		m_ShellName = shellName;
	}
	

	public static void setScriptName(String scriptName){
		m_ScriptName = scriptName;
	}
	
	public static void setScriptDescription(String scriptDesc){
		m_ScriptDescription = scriptDesc;
	}
	
	public static void setServiceId(int serviceId){
		m_ServiceId = serviceId;
	}
	
	public static void setEnvId(int envId){
		m_EnvId = envId;
	}
	
	public static void setRunId(long runId){
		m_RunId= runId;
	}
	
	public static void setServerName(String serverName){
		m_serverName = serverName;
	}
	
	public static void setComment(String comment){
		m_Comments = comment;
	}
	
	public static String getBuildNumber(){
		return m_BuildNumber;
	}
	
	public static String getLogName(){
		return m_LogName;
	}
	
	public static String getShellName(){
		return m_ShellName;
	}
	
	public static String getScriptName(){
		return m_ScriptName;
	}
	
	public static int getServiceId(){
		return m_ServiceId;
	}
	
	public static Long getRunId(){
		return m_RunId;
	}
	
	public static String getServerName(){
		return m_serverName;
	}
	
	public static int getEnvId(){
		return m_EnvId;
	}
	
	
	private static Boolean getScriptResult(){
		Boolean result = true;
		
		for(int i=0; i<m_ResultList.size();i++){
			if(m_ResultList.get(i)==false){
				result = false;
			}
		}
		
		return result;
	}
	
	private static void addVPResult(Boolean result){
		
		m_ResultList.add(result);
	}
	
	private static String getComputerName(){
		String hostname = "Unknown";

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    System.out.println("Hostname : " + hostname);
		    
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}
		return hostname;
	}
	
	public static void startScript(){
		
		if(isRunning()){
			
			setScriptDescription("");
			m_Script = true;
			try {
				m_ScriptId = SerializeToDatabase.logTestStart(m_RunId, m_ShellName, m_ScriptName, m_ScriptDescription);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void endScript(){
		if(isRunning()){
			try {
				SerializeToDatabase.logTestEnd(m_ScriptId, m_RunId, String.valueOf(getScriptResult()));
				m_Script = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void resetScript(){
		if(isRunning()){
			setScriptName("");
			setShellName("");
			setComment("");
			resetArrayList();
		}
		
	}
	
	public static void writeResultToDB(String vp_Name, Boolean result, String comments, String status){
		if(isRunning()){
			if(!HealthCheckSmokeTest.getScriptName().contentEquals(AtlasScriptbase.getExecutingScript().getClass().getName()))
			{
				HealthCheckSmokeTest.resetScript();
				HealthCheckSmokeTest.setShellName(VpUtils.getShellName(AtlasScriptbase.getExecutingScript().getScriptCaller().getScriptName()));

				HealthCheckSmokeTest.setScriptName(AtlasScriptbase.getExecutingScript().getClass().getName());
				HealthCheckSmokeTest.startScript();	

			}
			
			if(comments.contentEquals("")){
				comments = m_Comments;
			}
			addVPResult(result);
			try {
				SerializeToDatabase.vpStatic(m_ScriptId, vp_Name, result, comments, m_ServiceId, m_serverName,m_EnvId,status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
}
