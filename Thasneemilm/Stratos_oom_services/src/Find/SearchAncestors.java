package Find;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ancestors")
public class SearchAncestors {

	 List<String> allWords  =new ArrayList<String>();
		
		 List<File> allJavaFiles  =new ArrayList<File>();
		static String classconstraint1 = "class";
		static String classconstraint2 = "extends";
	    int count = 0;
		String immediateParent = null;
		
		
		@GET
	    @Path("/searchservice")
	    @Consumes( MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
	    public Response REST(@QueryParam("searchclass") String searchclass) {
	        StringBuilder stringBuilder = new StringBuilder();
	        try {
	        	
	        	File currentDir = new File("/home/thasneemilm/workspaceKepler"); // current directory
	        	System.out.println("\n");
	        	System.out.println("ALL Java Classes");
	    		///System.out.println("\n");
	    		displayDirectoryContents(currentDir);
	    		
	    		// see all java files
	    		for(File f: allJavaFiles){
	    			System.out.println(f.getName());
	    		}
	    		
	    		
	    		// read all word from all files
	    		readWord(allJavaFiles);
	    		/*for(String s : allWords ){
	    			System.out.println(s);
	    		}*/
	    		
	    		// total words captured
	    		System.out.println("Total number of words : "+allWords.size());
	    		System.out.println("Search class is : "+searchclass);
	    		
	    		//search for a word 
	    		
	    		//searchForAllSubclasses(searchclass);
	    		searchForAncestors(searchclass);
	    		System.out.println("Total number of ancestors : "+ count);
	        	
	    		
	    		
	        } catch (Exception e) {
	            System.out.println("Error Parsing: - ");
	        }
	        //System.out.println("Class to be searched : " + searchclass);
	 
	        // return HTTP response 200 in case of success
	        stringBuilder.append("Number Of Ancestors :").append(count);
	        return Response.status(200).entity(stringBuilder.toString()).build();
	        
	        
	        
	        
	    }	
		
		
		
		public  void displayDirectoryContents(File dir) throws FileNotFoundException {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					displayDirectoryContents(file);
				} else {
					if(file.getName().endsWith(".java")){	
						allJavaFiles.add(file);}
		             	}	
	         }
			
		}      //end of  displayDirectoryContents
		
		
		public void readWord(List<File> allJavaFiles2) throws FileNotFoundException{
			
			for(int i=0;i<allJavaFiles2.size();i++){
				Scanner fileScan= new Scanner(new DataInputStream(new FileInputStream(allJavaFiles2.get(i).getAbsoluteFile())));
				while (fileScan.hasNextLine()) {
			         String line = fileScan.nextLine();
			         String[] wordArray=line.split("\\W");
			         for(String s : wordArray ){
			        	 allWords.add(s); 
			          }
			      }
				
			}
				
		}
		
		
		public void searchForWord(String wordTosearch){
			//System.out.println("test");
			for(String s:allWords){
				if(s.contains(wordTosearch)){
					int index=allWords.indexOf(wordTosearch);
					System.out.println(index);
					//System.out.println(allWords.get(index + 8));
					
				    
				}
			}
			
		}
		
		/*public void searchForAllSubclasses(String parentclass){
			System.out.println("SubClasses for the Class - "+parentclass+" are :");
			for(int i=0; i<allWords.size();i++){
				if(allWords.get(i).contains(parentclass)){
					if((allWords.get(i-3).contains(classconstraint1) && allWords.get(i-1).contains(classconstraint2))){
						System.out.println(allWords.get(i-2));	
					}
					
					
				}
			}
			
		}*/
		
		
		public int searchForAncestors(String searchclass){
			
			//String immediateParent = null;
			//System.out.println("inside Ancestro");
			//System.out.println(" searching for ancestors :");
			for(int i=0; i<allWords.size();i++){
				if(allWords.get(i).contains(searchclass)){
					if((allWords.get(i-1).contains(classconstraint1) && allWords.get(i+1).contains(classconstraint2))){
						immediateParent = allWords.get(i+2);
						System.out.println("Immediate Parent of "+searchclass+" is "+immediateParent);
						count ++; 
						searchForAncestors(immediateParent);
						//System.out.println(count);
						}
				
				}
			}
			
		//searchForAncestors(immediateParent);	
		//System.out.println("total generation : ");
		return count;
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
