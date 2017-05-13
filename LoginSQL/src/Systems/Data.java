package Systems;

import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Data extends JFrame{

	private ActionManager listener;
	private JButton get,add, remove;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField, passwordField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Connection con;

	public Data(Connection con){
	
		this.con = con;
	
		this.setTitle("SQLite Editor");
		this.setSize(320,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		textArea = new JTextArea("");
		scrollPane = new JScrollPane(textArea);
		get = new JButton("Get");
		add = new JButton("Add");
		remove = new JButton("Remove");
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		usernameField = new JTextField("");
		passwordField = new JTextField("");
		listener = new ActionManager(get,add,remove,textArea,usernameField,passwordField, con);
		
		scrollPane.setBounds(20,10,260,90);
		usernameLabel.setBounds(20,105,75,25);
		passwordLabel.setBounds(20,130,75,25);
		usernameField.setBounds(100,105,180,25);
		passwordField.setBounds(100,130,180,25);
		get.setBounds(25,160,60,25);
		add.setBounds(100,160,60,25);
		remove.setBounds(175,160,100,25);
		
		get.addActionListener(listener);
		add.addActionListener(listener);
		remove.addActionListener(listener);
		
		textArea.setEditable(false);
		
		this.add(scrollPane);
		this.add(get);
		this.add(add);
		this.add(remove);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(usernameField);
		this.add(passwordField);
		
		this.setVisible(true);
		this.validate();	
	}
}
