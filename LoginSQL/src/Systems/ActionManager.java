package Systems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ActionManager implements ActionListener{

	private JButton button,add,get,remove;
	private JLabel errorLabel;
	private JTextField usernameField, passwordField;
	private Connection con;
	private ResultSet res;
	private boolean editor;
	private JTextArea textArea;

	public ActionManager(JButton button, JLabel errorLabel, JTextField usernameField, JTextField passwordField, Connection con){
		this.button = button;
		this.errorLabel = errorLabel;
		this.usernameField = usernameField;
		this.passwordField = passwordField;
		this.con = con;
		editor = false;
	}
	
	public ActionManager(JButton get, JButton add, JButton remove, JTextArea textArea, JTextField usernameField, JTextField passwordField, Connection con){
		this.add = add;
		this.get = get;
		this.remove = remove;
		this.textArea = textArea;
		this.usernameField = usernameField;
		this.passwordField = passwordField;
		this.con = con;
		editor = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button) && !editor){
			con = SQLiteConnection.connector();
			res = SQLiteConnection.getUsers(con);
			errorLabel.setText("Gotcha!");
			if (SQLiteConnection.testSet(res, usernameField.getText(), passwordField.getText())){
				errorLabel.setText("Success!");
				Main.launchEditor(con);
				Main.window.dispose();
			}else{
				errorLabel.setText("Username or password incorrect!");
			}
		}else if (e.getSource().equals(get) && editor){
			res = SQLiteConnection.getUsers(con);
			SQLiteConnection.getUsers(con);
			SQLiteConnection.printSet(res,textArea);
		}else if (e.getSource().equals(add) && editor){
			SQLiteConnection.addUser(usernameField.getText(), passwordField.getText(), con);
		}else if (e.getSource().equals(remove) && editor){
			SQLiteConnection.removeUser(usernameField.getText(), passwordField.getText(), con);
		}
	}
}
