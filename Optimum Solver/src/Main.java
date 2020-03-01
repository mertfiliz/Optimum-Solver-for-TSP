import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		 File file = new File("gsp_turkiye.txt");          // File file = new File("gsp_dunya.txt"); 
		 Scanner cities_Name = new Scanner(file);
		 Scanner positions = new Scanner(file);
		 Scanner scan = new Scanner(System.in);
		 
		 int n, k;
		 int temp_n;		 
		 
		 List<String> sehirler = new ArrayList<String>();  // all cities will be added to "sehirler" ArrayList 
	     List<Double> enlem = new ArrayList<Double>();     // all latitudes will be added to "enlem" ArrayList 
	     List<Double> boylam = new ArrayList<Double>();	   // all longitudes will be added to "boylam" ArrayList      
	     List<Double> distances = new ArrayList<Double>(); // all calculated distances will be added to "distances" ArrayList
	     
		 System.out.println("How many city do you wanna travel? (Optimum Solver)");
		 n = scan.nextInt(); 			// n = First n city from the text file.
		 city[] cities = new city[n];   
		 
		 final long startTime = System.currentTimeMillis(); // Starts the timer which we'll use to find the program's execution time.
		 
		 temp_n = n;
		 
		 for(int i=0; i<n; i++) {
			 cities[i] = new city(); //using city class.			
		 }
   
        String line = cities_Name.nextLine();
        k = 0;
       
        // Getting first "n" city names.
        while (cities_Name.hasNextLine() && temp_n>0) {
       	cities[k++].city_Name = line;
	       	for(int i = 0; i<4; i++){ // Every 4 line city name changes.
		       	if(cities_Name.hasNextLine())
		       		line = cities_Name.nextLine();
	       	}	
	       	temp_n--;
       }         
       cities_Name.close();
       
       line = positions.nextLine();
       line = positions.nextLine();
       
       k = 0;
       temp_n = n;
       	
       //Getting first "n" longitude and latitude;
       while(positions.hasNextLine() && temp_n>0) {       	 
	       	cities[k].longitude = Double.parseDouble(line);
	       	line = positions.nextLine();
	       	cities[k++].latitude = Double.parseDouble(line);
      	 	
	       	for(int i = 0 ; i < 3 ; i++){
      			if(positions.hasNextLine())
      				line = positions.nextLine();
	       	}
	       	temp_n--;
       }        
       positions.close(); 
       
       for(int i=0; i<n; i++) {
		   sehirler.add(cities[i].city_Name);   // All cities with given order according to the text file.
		   enlem.add(cities[i].latitude); 		// All latitudes with given order according to the text file
		   boylam.add(cities[i].longitude);     // All longitudes with given order according to the text file
       }
      
       String initial_city = cities[0].city_Name;
       double initial_longitude = cities[0].longitude;
       double initial_latitude = cities[0].latitude;
       
       sehirler.add("Istanbul, Turkey"); // Final City
       
       String final_city = cities[0].city_Name;
       double final_longitude = cities[0].longitude;
       double final_latitude = cities[0].latitude;                     
          
       double distance = 0;
       double total_distance = 0;
       
       // Distance calculation
       for(int i=1; i<sehirler.size() - 1 ;i++) {
    	   distance = CalculateDistance(initial_longitude, enlem.get(i), initial_latitude, boylam.get(i));  
    	   initial_longitude = boylam.get(i);
    	   initial_latitude = enlem.get(i);
       } 
     
       
       System.out.println("[OPTIMUM SOLVER] for the first " + n + " cities: ");  
       
       
       Permute.permute(sehirler,1,distance,total_distance,n); // calling Permute permute.   
       final long endTime = System.currentTimeMillis();
       System.out.println("\r\nTotal Execution time: " + (endTime - startTime) +" ms" );
}
	// Distance Calculation with given formula
	public static double CalculateDistance(double initial_en, double initial_boy, double en, double boy) {
		double cal1 = (Math.cos(Math.toRadians(initial_boy)) * Math.cos(Math.toRadians(boy))) + (Math.sin(Math.toRadians(initial_boy)) * Math.sin(Math.toRadians(boy)));
		double cal2 = Math.cos(Math.toRadians(initial_en)) * Math.cos(Math.toRadians(en));
		double cal3 = Math.sin(Math.toRadians(initial_en)) * Math.sin(Math.toRadians(en));
		double cal4 = (cal1 * cal2) + cal3;
		double cal5 = Math.acos(cal4) * 6400;
		double distance = cal5;		
		return distance;		
	}
 	 
       
} 
      
	


