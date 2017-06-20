package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 02, 2014
 */
public  class DownloadFile 
{
	private static String filePath =  System.getProperty("user.dir") + "/src/com/uploadfiles/uploadedFile.txt";	
	public static String server = "Injeaijapp02";
    public static String userName = "rouser";
    public static String password = "read0nly!";
    public static String fileName = "TEST123";
    
    public DownloadFile(){
    	
    }
    
    public DownloadFile(String fileName){
    	this.fileName = fileName;
    }
/*    public DownloadFile(String filePath,String fileName){
    	this.fileName = fileName;
    	this.filePath = filePath;
    }*/
    
    
    public static void downLoad(String newFileName, String sFTPWorkingDirPath){
    	 //String SFTPHOST = EnvironmentUtility.atlas().serverName();//"Injeaijapp02";
    		String SFTPHOST ="Injeaijapp02";
		  int    SFTPPORT = 22;
		  String SFTPUSER = EnvironmentUtility.atlas().userName();
		  String SFTPPASS = EnvironmentUtility.atlas().passWord();
//		  String SFTPWORKINGDIR = "/var/log/outbound3PL/";

		  Session     session     = null;
		  Channel     channel     = null;
		  ChannelSftp channelSftp = null;

		  try{
			  JSch jsch = new JSch();
			  session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
			  session.setPassword(SFTPPASS);
			  java.util.Properties config = new java.util.Properties();
			  config.put("StrictHostKeyChecking", "no");
			  session.setConfig(config);
			  session.connect();
			  channel = session.openChannel("sftp");
			  channel.connect();
			  channelSftp = (ChannelSftp)channel;
			  channelSftp.cd(sFTPWorkingDirPath);
			  byte[] buffer = new byte[1024];
			  BufferedInputStream bis = new BufferedInputStream(channelSftp.get(newFileName));
			  
			  File newFile = new File("C:/" + newFileName);
			  OutputStream os = new FileOutputStream(newFile);
			  
			  BufferedOutputStream bos = new BufferedOutputStream(os);
			  int readCount;
			  
			  //System.out.println("Getting: " + theLine);
			  while( (readCount = bis.read(buffer))>0) {
				  System.out.println("Writing: " );
				  bos.write(buffer, 0, readCount);
			  }
			  bis.close();
			  bos.close();
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
    }
    
	public static void main(String[] args) {
		  
		 

	}


}
