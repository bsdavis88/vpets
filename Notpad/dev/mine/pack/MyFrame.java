package dev.mine.pack;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	protected JMenuBar menuBar;
	protected JScrollPane myScrollPane;
	protected JTextArea myTextArea;

	public MyFrame(int width, int height, MyListener listener){
		menuBar = new MyMenu(listener);
		myTextArea = new JTextArea(320,240);
		myScrollPane = new JScrollPane(myTextArea);
	
		this.setSize(width,height);
		this.setTitle("Notepad");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setJMenuBar(menuBar);
		this.add(myScrollPane);
		
		this.setVisible(true);
	}
	
	public JMenuItem getFileButton1(){
		return ((MyMenu) menuBar).getFileButton1();
	}
	
	public JMenuItem getFileButton2(){
		return ((MyMenu) menuBar).getFileButton2();
	}
	
	public JTextArea getTextArea(){
		return myTextArea;
	}
}
