package dev.mine.pack;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	protected JMenu menu1;
	protected JMenuItem item1A;
	protected JMenuItem item2A;
	protected JMenu subMenu;
	protected JMenuItem item1B;
	protected MyListener listener;
	
	public MyMenu(MyListener listener){
		item1A = new JMenuItem("Open");
		item2A = new JMenuItem("Save");
		item1B = new JMenuItem("Print");
		subMenu = new JMenu("Options");
		menu1 = new JMenu("File");
		subMenu.add(item1B);
		menu1.add(item1A);
		menu1.add(item2A);
		menu1.add(subMenu);
		this.add(menu1);
		this.listener = listener;
		
		item1A.addActionListener(listener);
		item2A.addActionListener(listener);
	}
	
	public JMenuItem getFileButton1(){
		return item1A;
	}
	
	public JMenuItem getFileButton2(){
		return item2A;
	}
}