package com.bn.qa.webservice.xpath;

import java.io.File;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathReader {
    
    private File xmlFile = null;
    private org.xml.sax.InputSource inStream = null;;
    private Document xmlDocument;
    private XPath xPath;
    
    public XPathReader(File xmlFile) {
        this.xmlFile = xmlFile;
        initObjectsFromFile();
    }
    
    public XPathReader(String xmlString) {

        inStream = new org.xml.sax.InputSource();
	    inStream.setCharacterStream(new java.io.StringReader(xmlString));
	     
	    initObjectsFromStream();
    }
    
    
    private void initObjectsFromFile(){        
        try {
            xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);         
            xPath =  XPathFactory.newInstance().newXPath();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }       
    }
    
    private void initObjectsFromStream(){        
        try {
            xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);            
            xPath =  XPathFactory.newInstance().newXPath();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }       
    }
    
    
    public Object read(String expression, QName returnType){
        try {
            XPathExpression xPathExpression = xPath.compile(expression);
	        return xPathExpression.evaluate(xmlDocument, returnType);
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args)
    {
    	XPathReader reader = new XPathReader("c:\\getRecommendations.xml");
		        
    		// To get a child element's value.'
	       // String expression = "/response/recommendations/recommendation/recommendedProducts/product[1]/EAN";
	        String expression = "//recommendedProducts/product[1]/EAN";
	        System.out.println(reader.read(expression, XPathConstants.STRING) + "\n");
	        
	        expression = "//recommendedProducts/product/EAN";
	        NodeList allEINs = (NodeList)reader.read(expression,XPathConstants.NODESET);
	        traverse(allEINs);
	        
	        // To get an entire node
	        expression = "/response/recommendations/recommendation/recommendedProducts/product[1]";
	        NodeList thirdProject = (NodeList)reader.read(expression,XPathConstants.NODESET);
	        traverse(thirdProject);
	        
	        
    }
    
    private static void traverse(NodeList rootNode){
        for(int index = 0; index < rootNode.getLength();
		index ++){
            Node aNode = rootNode.item(index);
            if (aNode.getNodeType() == Node.ELEMENT_NODE){
                NodeList childNodes = aNode.getChildNodes();            
                if (childNodes.getLength() > 0){
					System.out.println("Node Name-->" + 
					aNode.getNodeName() +
						" , Node Value-->" + 
                    aNode.getTextContent());
				}
                traverse(aNode.getChildNodes());                
	        }
		}        
    }
}

