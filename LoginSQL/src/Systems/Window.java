package Systems;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Window extends JFrame{

	private ActionManager listener;
	private JButton button;
	private JLabel errorLabel, usernameLabel, passwordLabel;
	private JTextField usernameField, passwordField;
	private Connection con;

	public Window(){
	
		this.setTitle("SQLite Login");
		this.setSize(320,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		button = new JButton("Login");
		errorLabel = new JLabel("Error Goes Here");
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		usernameField = new JTextField("root");
		passwordField = new JTextField("password");
		listener = new ActionManager(button,errorLabel,usernameField,passwordField, con);
		
		errorLabel.setBounds(20,20,280,25);
		usernameLabel.setBounds(20,65,75,25);
		passwordLabel.setBounds(20,90,75,25);
		usernameField.setBounds(100,65,180,25);
		passwordField.setBounds(100,90,180,25);
		button.setBounds(100,120,75,25);
		
		errorLabel.setForeground(Color.RED);
		button.addActionListener(listener);
		
		this.add(errorLabel);
		this.add(button);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(usernameField);
		this.add(passwordField);
		
		this.setVisible(true);
		this.validate();
	}
}
