package Systems;

import java.sql.Statement;

import javax.swing.JTextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLiteConnection {

	public static Connection connector(){
	
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:root.sqlite");
			return con;
		}catch(Exception e){System.exit(1);}
		
		return null;
	}

	public static void printSet(ResultSet res, JTextArea textArea){
		String newText = "";
		
		try{
			while(res.next()){
				//System.out.println(res.getString("username") + " " + res.getString("password"));
				newText += res.getString("username") + " " + res.getString("password") + "\n";
			}
			textArea.setText(newText);
		}catch(Exception e){}
	}
	
	public static boolean testSet(ResultSet res, String username, String password){
		boolean pass = false;
		
		try{			
			while(res.next()){
				if (username.equals(res.getString("username")) && password.equals(res.getString("password"))){
					pass = true;
				}
			}
		}catch(Exception e){System.exit(1);}
		
		return pass;
	}
	
	public static ResultSet getUsers(Connection con){
		
		try{
			Statement state = (Statement) con.createStatement();
			ResultSet res = ((java.sql.Statement) state).executeQuery("SELECT username, password FROM users");
			return res;
		}catch(Exception e){System.exit(1);}
		
		return null;
	}
	
	public static void addUser(String username, String password, Connection con) {
		
		try{
			PreparedStatement prep = con.prepareStatement("INSERT INTO users values(?,?,?);");
			prep.setString(2, username);
			prep.setString(3, password);
			prep.execute();	
		}catch(Exception e){System.exit(1);}	
	}
	
	public static void removeUser(String username, String password, Connection con){
		try{
			PreparedStatement prep = con.prepareStatement("DELETE FROM users WHERE username = ?");
			prep.setString(1, username);
			prep.execute();			
		}catch(Exception e){System.exit(1);}
	}
}