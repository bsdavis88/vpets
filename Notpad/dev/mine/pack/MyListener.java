package dev.mine.pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class MyListener implements ActionListener{
	protected MyFrame frame;
	protected JFileChooser chooser;
	protected JTextArea myTextArea;

	public void getFrame(MyFrame frame){
		this.frame = frame;
		chooser = new JFileChooser();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.getFileButton1()){
			if (chooser.showOpenDialog(frame.getFileButton1()) == JFileChooser.APPROVE_OPTION){
				String string = "";
				Scanner sc;
				try {
					File file = chooser.getSelectedFile();
					sc = new Scanner(file);
			
					while(sc.hasNext()){
						string += sc.nextLine();
						string += "\r\n";
						this.myTextArea = frame.getTextArea();
						myTextArea.setText(string);
					}
				}catch (FileNotFoundException ex) {
					System.out.println("File not found!");
				}
			}
		}else if (e.getSource() == frame.getFileButton2()){
			if (chooser.showSaveDialog(frame.getFileButton1()) == JFileChooser.APPROVE_OPTION){
				try{
					String string = "";
					Scanner sc;
					sc = new Scanner(frame.getTextArea().getText());
					while(sc.hasNext()){
						string += sc.nextLine();
						string += "\r\n";
					}
					sc.close();
					File file = chooser.getSelectedFile();
					FileWriter write = new FileWriter(file, false);
					BufferedWriter writer = new BufferedWriter(write);
					writer.write(string);
					writer.close();
				}catch(Exception ex2){
				
				}
			}			
		}else{
			System.out.println("No!");
		}

	}
}
