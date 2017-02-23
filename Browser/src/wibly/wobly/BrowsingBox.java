package wibly.wobly;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BrowsingBox extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JEditorPane webFrame;
	private TextListener textListener;
	private HyperListener hyperListener;	
	private LoadWeb webHandeler;
	private JScrollPane scrollPane;
	
	public BrowsingBox(String title){
		super(title);
		textField = new JTextField("http://google.com");
		webFrame = new JEditorPane();
		webHandeler = new LoadWeb(webFrame,textField);
		textListener = new TextListener(webHandeler);
		hyperListener = new HyperListener(webHandeler);
		scrollPane = new JScrollPane();
		
		webFrame.setEditable(false);	
		webFrame.addHyperlinkListener(hyperListener);
		textField.addActionListener(textListener);
		
		scrollPane.add(webFrame);
		scrollPane.setViewportView(webFrame);
		this.add(textField, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640,480);
		this.setVisible(true);
	}
}
