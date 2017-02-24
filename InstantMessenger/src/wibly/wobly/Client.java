package wibly.wobly;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener, Runnable{

	private int width,height;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel label;
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel panel;
	private JButton button;
	private JMenuItem item;
	private int startx, starty;
	private String ip;
	private int port;
	private String chatLog;
	private String outLog;
	private Thread thread;
	
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;

	public Client(int width, int height, int startx, int starty, String ip, int port){
		this.width = width;
		this.height = height;
		this.setTitle("Client");
		this.startx = startx;
		this.starty = starty;
		this.port = port;
		this.ip = ip;
		chatLog = "Connecting to Host at\n" + "IP: " + this.ip + "\nPort: " + this.port;
		outLog = "";
		
		item = new JMenuItem("Exit");
		button = new JButton("Send");
		panel = new JPanel();
		menu = new JMenu("File");
		menuBar = new JMenuBar();
		label = new JLabel("Client : ");
		textArea = new JTextArea(chatLog);
		scrollPane = new JScrollPane(textArea);
		textField = new JTextField();
		textArea.setEditable(false);
		menuBar.add(menu);
		textField.setPreferredSize(new Dimension(450,25));
		this.add(panel,BorderLayout.NORTH);
		panel.add(label);
		panel.add(textField);
		panel.add(button);
		this.add(scrollPane,BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		menu.add(item);
		item.addActionListener(this);
		button.addActionListener(this);
		
		this.setSize(width, height);
		this.setVisible(true);
		this.setLocation(startx,starty);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		getServer();
	}

	public void getServer(){
		try{
			thread = new Thread(this);
			Socket clientSocket = new Socket(ip, port);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			outToServer.writeChars("Message From Client!" + '\n');
			
			chatLog += "\n" + inFromServer.readLine();
			textArea.setText(chatLog);
			
			thread.start();

		}catch(Exception e){System.out.println("Exception!");}
	}

	public void getMessage(){
		while(true){
			try{
				if (inFromServer.ready()){
					chatLog += "\n" + inFromServer.readLine();
					textArea.setText(chatLog);
				}
				if (!outLog.equals("")){
					outToServer.writeChars("Client: " + outLog + '\n');
					outLog = "";
				}
			}catch(Exception e){System.out.println("Exception!");}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(item)){
			System.exit(0);
		}
		if (e.getSource().equals(button)){
			outLog = textField.getText();
			chatLog += "\n" + "Clinet: " + outLog;
			textArea.setText(chatLog);
		}
	}

	@Override
	public void run() {
		System.out.println("Yes!");
		getMessage();		
	}
}