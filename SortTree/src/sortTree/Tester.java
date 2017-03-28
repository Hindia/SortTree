package sortTree;

import static org.junit.Assert.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class Tester {

	@Test
	public synchronized void Ascendingtest() {
		    Node TestNode   = null;
		    Node SortedNode = null;
		 
		    
		    SortTree  object = new SortTree();
		    
			  
		    try {
		    	
					    File file = new File(System.getProperty("user.dir")+"\\SortTree.xml");
					    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					    Document doc = dBuilder.parse(file);
						XPath xpath = XPathFactory.newInstance().newXPath();
					    TestNode = (Node) xpath.evaluate("//*[@id='body']", doc, XPathConstants.NODE);
					    
					    
					    File sortedFile = new File(System.getProperty("user.dir")+"\\SortTreeascending.xml");
					    Document SortedDoc = dBuilder.parse(sortedFile);
						xpath = XPathFactory.newInstance().newXPath();
					    SortedNode = (Node) xpath.evaluate("//*[@id='body']", SortedDoc, XPathConstants.NODE);
						
					    
						   
						object.Sort(doc, TestNode,4);
						   
						
						Node child = TestNode.getFirstChild();
						//--- remove text nodes
						Node childNode = SortedNode.getFirstChild();
						

						while( childNode.getNextSibling()!=null ){ 
							Node currentNode = childNode;
							childNode = childNode.getNextSibling();
							if(currentNode.getNodeType() == Node.TEXT_NODE){
								SortedNode.removeChild(currentNode);
							}  
							   		
						}
							   	
						if(childNode.getNodeType() == Node.TEXT_NODE){
							SortedNode.removeChild(childNode);
						   			
						}
							
						Node SortedChild = SortedNode.getFirstChild();
							   
						if (child.getNodeType() == Node.ELEMENT_NODE) { 
							assertEquals(child.getTextContent(),SortedChild.getTextContent());
						}
							  
						
						while( child.getNextSibling()!=null ){ 
							child = child.getNextSibling();
							SortedChild =SortedChild.getNextSibling();
							if (child.getNodeType() == Node.ELEMENT_NODE) { 
								assertEquals(child.getTextContent(),SortedChild.getTextContent());
						    }
						        
						      
						 }

					   
			}
		    catch (ParserConfigurationException e) {
		    	System.out.println("error :"+e.getMessage());
				
		    }catch (Exception e) {
		    	System.out.println("error :"+ e.getMessage());
			
		    }
			
		 
	}
	
	@Test
	public synchronized void Descendingtest() {
		    Node TestNode   = null;
		    Node SortedNode = null;
		 
		    
		    SortTree  object = new SortTree();
		    
			  
		    try {
		    	
					    File file = new File(System.getProperty("user.dir")+"\\SortTree.xml");
					    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					    Document doc = dBuilder.parse(file);
						XPath xpath = XPathFactory.newInstance().newXPath();
					    TestNode = (Node) xpath.evaluate("//*[@id='body']", doc, XPathConstants.NODE);
					    				   
					    
					    File sortedFile = new File(System.getProperty("user.dir")+"\\SortTreeDescending.xml");
					    Document SortedDoc = dBuilder.parse(sortedFile);
						xpath = XPathFactory.newInstance().newXPath();
					    SortedNode = (Node) xpath.evaluate("//*[@id='body']", SortedDoc, XPathConstants.NODE);
						
					    object.Sort(doc, TestNode,1);
						   
						
						Node child = TestNode.getFirstChild();
						//--- remove text nodes
						Node childNode = SortedNode.getFirstChild();
						

						while( childNode.getNextSibling()!=null ){ 
							Node currentNode = childNode;
							childNode = childNode.getNextSibling();
							if(currentNode.getNodeType() == Node.TEXT_NODE){
								SortedNode.removeChild(currentNode);
							}  
							   		
						}
							   	
						if(childNode.getNodeType() == Node.TEXT_NODE){
							SortedNode.removeChild(childNode);
						   			
						}
							
						Node SortedChild = SortedNode.getFirstChild();
							   
						if (child.getNodeType() == Node.ELEMENT_NODE) { 
							assertEquals(child.getTextContent(),SortedChild.getTextContent());
						}
							  
						
						while( child.getNextSibling()!=null ){ 
							child = child.getNextSibling();
							SortedChild =SortedChild.getNextSibling();
							if (child.getNodeType() == Node.ELEMENT_NODE) { 
								assertEquals(child.getTextContent(),SortedChild.getTextContent());
							}
						        
						      
						}

					   
			}
		    catch (ParserConfigurationException e) {
		    	System.out.println("error :"+e.getMessage());
				
		    }catch (Exception e) {
		    	System.out.println("error :"+ e.getMessage());
			
		    }
			
		 
	}
	
	@Test
	public synchronized void CaseSensitivetest() {
		    Node TestNode   = null;
		    Node SortedNode = null;
		 
		    
		    SortTree  object = new SortTree();
		    
			  
		    try {
		    	
					    File file = new File(System.getProperty("user.dir")+"\\SortTree.xml");
					    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					    Document doc = dBuilder.parse(file);
						XPath xpath = XPathFactory.newInstance().newXPath();
					    TestNode = (Node) xpath.evaluate("//*[@id='body']", doc, XPathConstants.NODE);
					    
					    
					    File sortedFile = new File(System.getProperty("user.dir")+"\\SortTreeCaseSensitive.xml");
					    Document SortedDoc = dBuilder.parse(sortedFile);
						xpath = XPathFactory.newInstance().newXPath();
					    SortedNode = (Node) xpath.evaluate("//*[@id='body']", SortedDoc, XPathConstants.NODE);
						
					    object.Sort(doc, TestNode,2);
					    
						Node child = TestNode.getFirstChild();
						     //--- remove text nodes
					    Node childNode = SortedNode.getFirstChild();
						

					    while( childNode.getNextSibling()!=null ){ 
					    	Node currentNode = childNode;
					    	childNode = childNode.getNextSibling();
					    	if(currentNode.getNodeType() == Node.TEXT_NODE){
					    		SortedNode.removeChild(currentNode);
					    	}  
					    	
					    }
							   	
					    if(childNode.getNodeType() == Node.TEXT_NODE){
					    	SortedNode.removeChild(childNode);
					    	
					    }
							
					    Node SortedChild = SortedNode.getFirstChild();
					    
					    if (child.getNodeType() == Node.ELEMENT_NODE) { 
					    	assertEquals(child.getTextContent(),SortedChild.getTextContent());
					    }
							  
						
					    while( child.getNextSibling()!=null ){ 
					    	child = child.getNextSibling();
					    	SortedChild =SortedChild.getNextSibling();
					    	if (child.getNodeType() == Node.ELEMENT_NODE) { 
					    		assertEquals(child.getTextContent(),SortedChild.getTextContent());
					    	}
						        
						      
					    }

					   
			}
		    catch (ParserConfigurationException e) {
		    	System.out.println("error :"+e.getMessage());
				
		    }catch (Exception e) {
		    	System.out.println("error :"+ e.getMessage());
			
		    }
			
		 
	}
	
	@Test
	public synchronized void HandleNumberstest() {
		    Node TestNode   = null;
		    Node SortedNode = null;
		 
		    
		    SortTree  object = new SortTree();
		    
			  
		    try {
		    	
					    File file = new File(System.getProperty("user.dir")+"\\SortTree.xml");
					    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					    Document doc = dBuilder.parse(file);
						XPath xpath = XPathFactory.newInstance().newXPath();
					    TestNode = (Node) xpath.evaluate("//*[@id='body']", doc, XPathConstants.NODE);
					    
					    File sortedFile = new File(System.getProperty("user.dir")+"\\SortTreeHandleNumbers.xml");
					    Document SortedDoc = dBuilder.parse(sortedFile);
						xpath = XPathFactory.newInstance().newXPath();
					    SortedNode = (Node) xpath.evaluate("//*[@id='body']", SortedDoc, XPathConstants.NODE);
						
					    object.Sort(doc, TestNode,3);
						   
						
					    Node child = TestNode.getFirstChild();
						 
					    //--- removes text nodes
					    Node childNode = SortedNode.getFirstChild();
						

					    while( childNode.getNextSibling()!=null ){ 
					    	Node currentNode = childNode;
					    	childNode = childNode.getNextSibling();
					    	if(currentNode.getNodeType() == Node.TEXT_NODE){
					    		SortedNode.removeChild(currentNode);
					    	}  
							 
					    }
							   	
					    if(childNode.getNodeType() == Node.TEXT_NODE){
					    	SortedNode.removeChild(childNode);
							 
					    }
							
					    Node SortedChild = SortedNode.getFirstChild();
							   
					    if (child.getNodeType() == Node.ELEMENT_NODE) { 
					    	assertEquals(child.getTextContent(),SortedChild.getTextContent());
						}
							  
						
					    while( child.getNextSibling()!=null ){ 
					    	child = child.getNextSibling();
					    	SortedChild =SortedChild.getNextSibling();
					    	if (child.getNodeType() == Node.ELEMENT_NODE) { 
					    		assertEquals(child.getTextContent(),SortedChild.getTextContent());
					    	}
						        
						      
					    }

					   
			}
		    catch (ParserConfigurationException e) {
		    	System.out.println("error :"+e.getMessage());
				
		    }catch (Exception e) {
		    	System.out.println("error :"+ e.getMessage());
			
		    }
			
		 
	}

}


