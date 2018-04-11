package com.atg_sync_services.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class JSONUtility {
	
	private ObjectMapper mapper = null;
	private BufferedReader fileReader = null;
	private JsonNode rootNode = null;
	private InputStream input = null;
	private String lastFieldName = "";
	private String path = "";
	private String m_json = "";
	public JSONUtility(){
		
	}
	
	public JSONUtility(JsonNode root){
		rootNode = root;
	}
	
	
	public JSONUtility(String json){
		mapper = new ObjectMapper();
		try {
			rootNode = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(String fileName){
		JsonFactory jfactory = new JsonFactory(); 
		
		/*** read from file ***/
		try {
			String json = new Scanner(new File(fileName))
		    .useDelimiter("\\A").next();
			
			System.out.println(json);
			mapper = new ObjectMapper();
			rootNode = mapper.readTree(json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JsonNode node(JsonNode node, String fieldName, Boolean recursive)	
	{
		JsonNode tempNode = null;
		Iterator<String> ite = node.getFieldNames();
		ArrayList<JsonNode> nodeList = null;
		
		while (ite.hasNext()&& tempNode==null) {
			String temp = ite.next();
			System.out.println(temp);
			if(temp.contentEquals(fieldName))
			{
				tempNode = node.get(temp);
				break;
			}
			else
			{
				if(recursive){
					tempNode = node.get(temp);
					tempNode = node(tempNode, fieldName,recursive);
				}
				
			}

		}
		
		return tempNode;
	}
	
	public static class Path{
		
		public Path(String path){
			
		}
		
		public static Boolean isArray(String path){
			
			Boolean array = false;
			if(path.indexOf("[")>-1){
				
				array = true;
			}

			return array;
		}
		
		public static String getPathNode(String path){
			
			String nodePath = "";
			
			if(path.indexOf("[")>-1){
				
				nodePath = path.substring(0,path.indexOf("["));
			}
			else
			{
				nodePath = path;
			}
			
			return nodePath;
		}
		
		public static int  getArrayIndex(String path){
			
			int index = 0;
			String nodePath = "";
			if(path.indexOf("[")>-1){
				
				nodePath = path.substring(path.indexOf("[")+1,path.indexOf("]"));
				index = Integer.valueOf(nodePath);
			}
			else
			{
				index = 0;
			}
			
			return index;
		}
		
	}
	
	private  LinkedHashMap<String, Boolean> getMappedPath(String path){
		Boolean stChild = true;
		LinkedHashMap<String, Boolean> pathList = new LinkedHashMap<String, Boolean>();
		Boolean atChild = false;
		int emptyCount = 0;

		String[] list = path.split("/");
		for(int i=0; i<list.length;i++){
			if(list[i].contentEquals("")){
				if(i>0){
					atChild = true;
				}
				
			}
			else
			{
				pathList.put(list[i], atChild);
				atChild = false;
			}

		}
		
		Iterator it = pathList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			lastFieldName = pairs.getKey().toString();
		}
		return pathList;

	}
	
	public JsonNode getParentOfLastNode(String path){
		
		JsonNode node = null;
		Boolean stChild = true;
		
		LinkedHashMap<String, Boolean> pathList = getMappedPath(path);
		int count = pathList.size();
		System.out.println(pathList.size());
		Iterator it = pathList.entrySet().iterator();
		node = rootNode;
		
		int i = 1;

		while (i < count) {
			Map.Entry pairs = (Map.Entry)it.next();
			Boolean isArray = Path.isArray(pairs.getKey().toString());
			String fieldName = Path.getPathNode(pairs.getKey().toString());
			boolean recursive = (Boolean)pairs.getValue();

			//if(node.isArray()){
			if(node.isArray()){
				int index = Path.getArrayIndex(pairs.getKey().toString());
				if(fieldName.contentEquals("") && index>0){
					
					Iterator<JsonNode> ite  = node.getElements();
					int iteIndex = 1;
					
					while(ite.hasNext()){
						JsonNode iteNode = ite.next();
						if(iteIndex == index){
							node = iteNode;
						}
						iteIndex++;
					}
				}
				else
				{
					List<JsonNode> listNodes = node.findValues(fieldName);			
					node = listNodes.get(index-1);	
				}
			}
			else
			{
				node = node(node,fieldName,recursive);
			}
			i++;
		}
				
		return node;
	}
	
	
	public String modifyNodeValue(String path, String value){
		
		String json = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){
			
			try {
				if(node!=null){
					
					((ObjectNode)node).put(lastFieldName, value);
					
					strWriter = new StringWriter();
					mapper.writeValue(strWriter, rootNode);
				}
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		m_json = strWriter.toString();
		System.out.println(m_json);
		return m_json;

	}
	
	public String modifyNodeValue(String path, int value){
		
		String json = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){
			
			try {
				if(node!=null){
					((ObjectNode)node).put(lastFieldName, value);
					strWriter = new StringWriter();
					mapper.writeValue(strWriter, rootNode);
				}
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		m_json = strWriter.toString();
		//System.out.println(m_json);
		return json;

	}
	
	public String modifyNodeValue(String path, long value){
		
		String json = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){
			
			try {
				if(node!=null){
					((ObjectNode)node).put(lastFieldName, value);
					strWriter = new StringWriter();
					mapper.writeValue(strWriter, rootNode);
				}
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		m_json = strWriter.toString();
		return json;

	}
	
	public String modifyNodeValue(String path, boolean value){
		
		String json = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){
			
			try {
				if(node!=null){
					((ObjectNode)node).put(lastFieldName, value);
					strWriter = new StringWriter();
					mapper.writeValue(strWriter, rootNode);
				}
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		m_json = strWriter.toString();
		return json;

	}
	
	public String modifyNodeValue(String path, double value){
		
		String json = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){
			
			try {
				if(node!=null){
					((ObjectNode)node).put(lastFieldName, value);
					strWriter = new StringWriter();
					mapper.writeValue(strWriter, rootNode);
				}
				
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		m_json = strWriter.toString();
		return json;
	}
	
	public String getNodeValue(String path){
		String value = "";
		this.path = path;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = null;
		node = getParentOfLastNode(path);
		if(node!=null){

			value = ((ObjectNode)node).get(lastFieldName).getTextValue();
			
		}
		
		return value;
	}
	
	public String getJSONString(){
		Writer strWriter = null;
		strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, rootNode);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strWriter.toString();
	}
	
	public static void main(String [] args)
	{
		JSONUtility jUtil = new JSONUtility();
		jUtil.load("C:\\Users\\Public\\Documents\\RFT\\ATGSyncService-QA-RFT\\testdata\\adressUS.json");
		jUtil.modifyNodeValue("//firstName", "Faruque");
	}

}
