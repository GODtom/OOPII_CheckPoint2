import java.util.*;
import java.lang.*;


public class A1083331_checkpoint2{

    // To store the id of the map that user inputted
    public static int map_id = 0;


    /*********************************The Past TODO********************************
         * Past TODO (1)Fill in and use the following functions to achieve the requirements from the documentation.
         * Hint1: You need to get the filename from the Command Line Arguments.
         * Hint2: Also, you need to pass in the correct parameters to finish those methods.
    **********************************The End of the TODO**********************/
    public static void main(String args[]){
    	try{
	    	int map_id=0;
	        map_id=Integer.parseInt(args[0]);
		
	        A1083331_checkpoint2_dbReader dbReader = new A1083331_checkpoint2_dbReader();

	        A1083331_checkpoint2_mapDrawer mapDrawer = new A1083331_checkpoint2_mapDrawer();

	        dbReader.readDB(map_id);

	        mapDrawer.drawMap(dbReader.getWidth(),dbReader.getHeight(),dbReader.getobstacleList(),dbReader.getstyleHashMap());
        }
        catch(NumberFormatException e){
        	 System.out.println("Must input Integer,please try again.");
        	 System.exit(0);
        }
        catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println("Missing input,please try again.");
        	System.exit(0);
        }
        catch(Exception e){
        	System.out.println("Fatal error,please try again.");
        	System.exit(0);        	
        }
    }
}