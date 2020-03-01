import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permute {
	
	// Printing data
	public static void print(List<String> arr, int n) throws FileNotFoundException, UnsupportedEncodingException {
		   PrintWriter writer = new PrintWriter("opt_gsp_turkiye_" + n + ".txt", "UTF-8"); 
		   writer.println("[OPTIMUM SOLVER] for the first " + n +" cities: ");
		   writer.println("\r\n");		   
		   writer.println(arr);	 	      
	       writer.close();		
	}	
	
	// All possible routes using recursive.
	public static void permute(List<String> arr, int k, double ds, double tds,int n) throws FileNotFoundException, UnsupportedEncodingException {
		for(int i = k; i<arr.size()-1;i++) {		
			Collections.swap(arr, i, k);
			tds += ds;			
			permute(arr,k+1,ds,tds,n);
			Collections.swap(arr, k, i);					
		}	
			
		if(k == arr.size() - 1) {	
			System.out.println(arr + "---" + tds +" KM");	
			Permute.print(arr,n);
			tds=0;
		}	
	}
}
