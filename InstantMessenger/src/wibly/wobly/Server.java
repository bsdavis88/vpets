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

public class Server extends JFrame implements ActionListener{

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
	private String chatLog;
	private String outLog;
	private int port;

	public Server(int width, int height, int startx, int starty, int port){
		this.width = width;
		this.height = height;
		this.setTitle("Server");
		this.startx = startx;
		this.starty = starty;
		this.port = port;
		
		outLog = "";
		chatLog = "Waiting for Connection!\n";
		item = new JMenuItem("Exit");
		button = new JButton("Send");
		panel = new JPanel();
		menu = new JMenu("File");
		menuBar = new JMenuBar();
		label = new JLabel("Server : ");
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
		
		getClient();
	}

	public void getClient(){
		try{
			String clientSentence;
			ServerSocket welcomeSocket = new ServerSocket(6789);
			//while(true){
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				
				clientSentence = inFromClient.readLine();
				chatLog += "\n" + clientSentence;
				textArea.setText(chatLog);
				
				outToClient.writeChars("Message From Server!" + '\n');
				while(true){
					System.out.println(outLog);
					if (!outLog.equals("")){
						sendMessage(outToClient);
						outLog = "";
					}
					if (inFromClient.ready()){
						getMessage(inFromClient);
					}
				}
			//}
		}catch(Exception e){System.out.println("IOException!");}
	}

	public void sendMessage(DataOutputStream outToClient){
		
		try{
			outToClient.writeChars("Server: " + outLog + '\n');
		}catch(Exception e){System.out.println("IOException!");}
	}
	
	public void getMessage(BufferedReader inFromClient){
		try{
			chatLog += "\n" + inFromClient.readLine();
			textArea.setText(chatLog);
		}catch(Exception e){}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(item)){
			System.exit(0);
		}
		if (e.getSource().equals(button)){
			outLog = textField.getText();
			chatLog += "\n" + "Server: " + outLog;
			textArea.setText(chatLog);
		}
	}
}
