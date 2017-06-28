package com.bn.qa.webservice.test;

import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/*
 * import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
*/


public class JSonTest {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException {
		
		HttpClient clientService = new DefaultHttpClient();
		HttpPost post = new HttpPost();
		post.setURI(new URI("http://10.1.162.170/ProductDataServices/rest/ProductData"));
		
		post.setHeader("Content-Type", "application/json");
		
		// This ensure that we drop the root element
		/*XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
		public HierarchicalStreamWriter createWriter(Writer writer) {
		return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
		}
		});
		
		xstream.setMode(XStream.NO_REFERENCES);*/
		
		
	}

}
