import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.lang.*;

// C:\Program Files\Java\postgresql-42.2.24.jar path

public class A1083331_checkpoint2_dbReader{

    //Description :To store the height of the map
    public  int Height = 0;
    //Description :To store the height of the map
    public  int Width = 0;
    //Description :To store the locations of the obstacle
    public  ArrayList<Integer[]> obstacleList = new ArrayList<Integer[]>() ;
    //Description :To store the style of each obstacle 
    public HashMap<Integer,String> styleHashMap = new HashMap<Integer,String>();



    /*********************************The TODO********************************
         * TODO (2) In this part, you need to read the size of map and the locations of the obstacle from the database. 
         * Hint1: For the detailed information to connect the database,you could check the document.
         * Hint2: We use postgresql for database system this time. You need to use postgresql.jar to successfully load the driver.
         *       （postgresql.jar located at /home/driver） 
         * Hint3: If you want to get the size of map,you could use this query "SELECT height,width FROM map_size WHERE map_id="+map_id".
         * Hint4: If you want to get the coordinate of the obstacle and the number of style of the obstacle,you could use this query "SELECT 
         *        obstacle_info.x_coordinate,obstacle_info.y_coordinate,obstacle_info.obstacle_type FROM obstacle_info WHERE obstacle_info.
         *        map_id="+map_id"
         * Hint5: If you want to get all style of the obstacle, you could use this query "SELECT obstacle_type,display FROM obstacle_style " 
    **********************************The End of the TODO**********************/

    public  void readDB(int map_id){

        /********************************************************************************************
            START OF YOUR CODE
        ********************************************************************************************/
        final String protocol="jdbc:postgresql://140.127.220.226:5432/oopiickp";
		Connection conn=null;
		try{
			conn=DriverManager.getConnection(protocol,"fallckp","2021OOPIIpwd");
			Statement s =conn.createStatement();
			ResultSet rs=null; 
			rs=s.executeQuery("SELECT height,width FROM map_size WHERE map_id="+map_id);
			while (rs.next()) {		 
				int height=rs.getInt("height");
				int width=rs.getInt("width");
				setWidth(width);
				setHeight(height);
			}
			rs.close();
			rs=s.executeQuery("SELECT x_coordinate,y_coordinate,obstacle_type FROM obstacle_info WHERE map_id="+map_id);
			while (rs.next()) {
				int x=rs.getInt("x_coordinate");
				int y=rs.getInt("y_coordinate");
				int ot=rs.getInt("obstacle_type");
				Integer[] temp= new Integer[3];
				temp[0]=x;
				temp[1]=y;
				temp[2]=ot;
				obstacleList.add(temp);
			}
			rs.close();
			rs=s.executeQuery("SELECT obstacle_type,display FROM obstacle_style");
			while (rs.next()) {
				int ot_num=rs.getInt("obstacle_type");
				String dis=rs.getString("display");
				styleHashMap.put(ot_num,dis);
			}  
			rs.close();	
			conn.close();
		}
		catch(SQLException err){
			System.out.println(err.getMessage());
			System.exit(0);
		}
		catch(Exception err){
			System.out.println("Fatal error,please try again.");
			System.exit(0);
		}
        /********************************************************************************************
            END OF YOUR CODE
        ********************************************************************************************/   
    }

    public void setWidth(int Width){
        this.Width = Width;
    }

    public void setHeight(int Height){
        this.Height = Height;
    }

    public void setobstacleList(ArrayList<Integer[]> obstacleList){
        this.obstacleList=obstacleList;
    }

    public void setstyleHashMap(HashMap<Integer,String> styleHashMap){
        this.styleHashMap= styleHashMap;
    }

    public int getWidth(){
        return Width;
    }

    public int getHeight(){
        return Height;
    }

    public ArrayList<Integer[]> getobstacleList(){
        return obstacleList;
    }

    public HashMap<Integer,String> getstyleHashMap(){
        return styleHashMap;
    }
}
