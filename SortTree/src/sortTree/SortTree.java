package sortTree;

//package se.excosoft.flexdtd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;
import java.util.Map.Entry;
import java.text.Collator;

public class SortTree {


	   
public void Sort(Document doc,Node parent, int option){	
			
		   //removes text nodes
		   Node childNode = parent.getFirstChild();
	
		   while( childNode.getNextSibling()!=null ){ 
		   		Node currentNode = childNode;
		   		childNode = childNode.getNextSibling();
		   		if(currentNode.getNodeType() == Node.TEXT_NODE){
		   			parent.removeChild(currentNode);
		   		}  
		   		
		   }
		   	
		   if(childNode.getNodeType() == Node.TEXT_NODE){
			   parent.removeChild(childNode);
	   			
	   	   } 
			
		   Node result = doc.adoptNode(parent.cloneNode(true));
		   
		   //sets result node
		   
		   childNode = result.getFirstChild();
		   
		   while(result.hasChildNodes()){
			   result.removeChild(result.getFirstChild());
		   }
		   
		   
		   //appends to result if  first child is title 
		   childNode = parent.getFirstChild();
		   if(childNode.getNodeType() == Node.ELEMENT_NODE){
			   if(childNode.getNodeName() == "title" ){
				   result.appendChild(doc.adoptNode(childNode.cloneNode(true)));
			
			   }
		   }

		   
		   //sets the paragraph list
		   NodeList ParagraphList=((Element)parent).getElementsByTagName("paragraph");
		   
		   //sorts the paragraph
		   NodeList SortedParagraphs = SortParagraph(doc,ParagraphList, parent, option);
		  
		   //gets the section list
		   NodeList SectionList=((Element)parent).getElementsByTagName("section");
			
		   if(SectionList != null){
				
			   for(int i=0; i< SectionList.getLength(); i++){
				   Node ParentSection = SectionList.item(i);
				   this.Sort(doc,ParentSection, option);
			   }
				
	
			   NodeList SortedSections = SortSection(doc, SectionList, parent, option);

			   //for descending order sections are ordered first
			   if(option==1){ 
				   for (int i = 0; i < SortedSections.getLength(); i++) 
					   result.appendChild(SortedSections.item(i).cloneNode(true));
				
				   for (int i = 0; i < SortedParagraphs.getLength(); i++) 
					   result.appendChild(SortedParagraphs.item(i).cloneNode(true));
			 
			   }
			   // for every other option paragraph is ordered first
			   
			   else{
				   for (int i = 0; i < SortedParagraphs.getLength(); i++) 
					   result.appendChild(SortedParagraphs.item(i).cloneNode(true));
				 
				   for (int i = 0; i < SortedSections.getLength(); i++) 
					   result.appendChild(SortedSections.item(i).cloneNode(true));
			   }
			
			}
					   	
			Node ParentsChildNode = parent.getFirstChild();
			while(ParentsChildNode.getNextSibling() !=null ){ 
				Node ParentsNextChild = ParentsChildNode.getNextSibling();
				parent.removeChild(ParentsChildNode);
				ParentsChildNode = ParentsNextChild;	
			}
				   	
			ParentsChildNode = parent.getFirstChild();
			if (ParentsChildNode != null){
				if(ParentsChildNode.getNodeName()=="title")
					ParentsChildNode=ParentsChildNode.getNextSibling();
				parent.removeChild(ParentsChildNode);
			}
				
			Node NewChildNode = result.getFirstChild();
			parent.appendChild(doc.adoptNode(NewChildNode.cloneNode(true)));
			while(NewChildNode.getNextSibling() !=null ){ 
				NewChildNode = NewChildNode.getNextSibling();
				parent.appendChild(doc.adoptNode(NewChildNode.cloneNode(true)));
				
			}
						
		  }

//method for sorting a paragraph	   
private NodeList SortParagraph(Document doc,NodeList ParagraphList, Node parent, int option){
		   HashMap<Integer, String> hmap = new HashMap<>();
		   for(int i=0; i<ParagraphList.getLength(); i++){
			   Node NodeInParagraph=ParagraphList.item(i);
			   if(!NodeInParagraph.getParentNode().equals(parent)) continue;
			   hmap.put(i, NodeInParagraph.getTextContent()); 
			 }	
		   HashMap<Integer, String> Sortedmap = SortByValues(hmap, option);
		   Node NewNode=null;
		   Node result = doc.adoptNode(parent.cloneNode(true));
		   Node ChildNode = result.getFirstChild();
		   while(ChildNode.getNextSibling() !=null ){ 
		   		Node Nextchild = ChildNode.getNextSibling();
		   		result.removeChild(ChildNode);
		   		ChildNode = Nextchild;	
		   }
		   	
		    
		   ChildNode = result.getFirstChild();
		   if (ChildNode != null)
			   result.removeChild(ChildNode);
		   
		   Iterator<Entry<Integer, String>> iterator = Sortedmap.entrySet().iterator();
		   while(iterator.hasNext()){
			  HashMap.Entry<Integer, String> pair = (HashMap.Entry<Integer, String>)(iterator.next());										   	 
			   NewNode = ParagraphList.item((int)pair.getKey());
			   result.appendChild(doc.adoptNode(NewNode.cloneNode(true)));
		   }
		   
		   NodeList ResultList= result.getChildNodes();
		    
		   return ResultList;
}

//Method for sorting a section
private NodeList SortSection(Document doc,NodeList SectionList, Node parent, int option){
		   HashMap<Integer, String> hmap = new HashMap<>();
		   for(int i=0; i<SectionList.getLength(); i++){
			   Node NodeInSection=SectionList.item(i);
			   if(!NodeInSection.getParentNode().equals(parent)) continue;
			   hmap.put(i, NodeInSection.getTextContent());
			   
			 }	
	
		   HashMap<Integer, String> SortedMap = SortByValues(hmap, option);
		   Node NewNode=null;
		   Node result = doc.adoptNode(parent.cloneNode(true));
	
		   Node ChildNode = result.getFirstChild();
		   while(ChildNode.getNextSibling() !=null ){ 
		   		Node Nextchild = ChildNode.getNextSibling();
		   		result.removeChild(ChildNode);
		   		ChildNode = Nextchild;	
		   }
		   	
		    
		   ChildNode = result.getFirstChild();
		 
		   if (ChildNode != null)
			   result.removeChild(ChildNode);
		  
		   Iterator<Entry<Integer, String>> iterator = SortedMap.entrySet().iterator();
		   while(iterator.hasNext()){
			   Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>)(iterator.next());										   	 
			   NewNode = SectionList.item((int)pair.getKey());
			   result.appendChild(doc.adoptNode(NewNode.cloneNode(true)));
		   }
		   
		   NodeList ResultList= result.getChildNodes();
		   return ResultList;
}
	   
//Method for Sorting a hash map by values
private static HashMap<Integer, String> SortByValues(HashMap<Integer, String> map, int option) { 
		
		   List<HashMap.Entry<Integer, String>> list = new LinkedList<HashMap.Entry<Integer, String>>(map.entrySet());
	       
		   //this is for descending CASEINSENSITIVE order
		   if(option==1){
	    	   Collections.sort(list, Collections.reverseOrder (new Comparator <HashMap.Entry<Integer, String>>(){
	               public int compare( HashMap.Entry<Integer, String> ObjectOne, HashMap.Entry<Integer, String> ObjectTwo )
	               {
	            	   Collator collator = Collator.getInstance(Locale.ENGLISH);
	            	   collator.setStrength(Collator.SECONDARY); 
	            	   return collator.compare((ObjectOne.getValue()),(ObjectTwo.getValue()));
	               }
	           }));
	       }
		   
		   //this is for CASESENSITIVE order
	       else if(option==2){	
	    	   Collections.sort(list,  new Comparator <HashMap.Entry<Integer, String>>(){
	               public int compare( HashMap.Entry<Integer, String> ObjectOne, HashMap.Entry<Integer, String> ObjectTwo )
	               {
	            	  return (ObjectOne.getValue()).compareTo((ObjectTwo.getValue()));
	               }
	           } );
	       }
		   
		   //this is for HANDLENUMBERS option
	       else if(option==3){
	    	   Collections.sort(list, new Comparator<HashMap.Entry<Integer, String>>() {
	               public int compare(HashMap.Entry<Integer, String> ObjectOne, HashMap.Entry<Integer, String> ObjectTwo) {
	            	   Collator collator = Collator.getInstance(Locale.ENGLISH);
	            	   collator.setStrength(Collator.SECONDARY);
	            	   
	                   String o1StringPart = (ObjectOne.getValue()).replaceAll("[0-9.]+", "");
	                   String o2StringPart = (ObjectTwo.getValue()).replaceAll("[0-9.]+", "");
	                 
	                   if(o1StringPart.equalsIgnoreCase(o2StringPart))
	                	   return Double.compare((extractNumber(ObjectOne.getValue())), (extractNumber(ObjectTwo.getValue())));
	                   return collator.compare((ObjectOne.getValue()),((ObjectTwo.getValue())));
	               }

	               private double extractNumber(String s) {
	            	   
	            	   String NumbersPart=s.replaceAll("[^0-9.0-9]+", "");
	            	   if(NumbersPart.startsWith(".") || NumbersPart.isEmpty()) return 0;
	                   return Double.parseDouble(NumbersPart);
	                 
           	    }
	           });
	       }
		   
		   // this is for ascending CASEINSENSITIVE order
	       else{
	    	   Collections.sort(list,  new Comparator <HashMap.Entry<Integer, String>>(){
	               public int compare( HashMap.Entry<Integer, String> ObjectOne, HashMap.Entry<Integer, String> ObjectTwo )
	               {
	            	   Collator collator = Collator.getInstance(Locale.ENGLISH);
	            	   collator.setStrength(Collator.SECONDARY); 
	            	   return collator.compare((ObjectOne.getValue()),(ObjectTwo.getValue()));
	               }
	           } );
	       }

	       // using LinkedHashMap to preserve the insertion order while copying the sorted list in HashMap
	       
	       HashMap<Integer, String> SortedHashMap = new LinkedHashMap<Integer, String>();
	       for (HashMap.Entry<Integer, String> entry : list)
	          SortedHashMap.put(entry.getKey(), entry.getValue());
	       return SortedHashMap;
	       
	  }
	 
}