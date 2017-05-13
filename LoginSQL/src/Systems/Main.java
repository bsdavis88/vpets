package Systems;

import java.sql.Connection;

public class Main {

	public static Window window;

	public static void main(String[] args){
		
		window = new Window();
		//Connection con = SQLiteConnection.connector();
		//Data data = new Data(con);
	}
	
	public static void launchEditor(Connection con){
		Data data = new Data(con);	
	}
}
