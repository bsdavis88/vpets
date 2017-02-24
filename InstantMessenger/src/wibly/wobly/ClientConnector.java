package wibly.wobly;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientConnector extends JFrame implements ActionListener{

	private int width,height,startx,starty;
	private JTextField ipField, portField;
	private JLabel ipLabel, portLabel;
	private JButton button;
	private JPanel topPanel, centerPanel, bottomPanel;
	private String ip;
	private int port;

	public ClientConnector(int width, int height, int startx, int starty){
		this.width = width;
		this.height = height;
		this.startx = startx;
		this.starty = starty;
		
		ip = "localhost";
		port = 6789;
		
		ipField = new JTextField(ip);
		portField = new JTextField(Integer.toString(port));
		ipLabel = new JLabel ("IP Address: ");
		portLabel = new JLabel("            Port : ");
		button = new JButton("CONNECT");
		topPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		
		this.setLayout(new GridLayout(3,1));
		
		this.add(topPanel);
		this.add(centerPanel);
		this.add(bottomPanel);
		
		ipField.setPreferredSize(new Dimension(100,25));
		portField.setPreferredSize(new Dimension(100,25));
		
		topPanel.add(ipLabel);
		topPanel.add(ipField);
		centerPanel.add(portLabel);
		centerPanel.add(portField);
		bottomPanel.add(button);
		
		button.addActionListener(this);
		
		this.setTitle("Client");
		this.setLocation(startx, starty);
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ip = ipField.getText();
		port = Integer.parseInt(portField.getText());
		
		if (!ip.equals("") && !(port == 0)){
			Client client = new Client(640,480,940,200,ip,port);
			this.dispose();
		}
	}

}
