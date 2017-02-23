package dev.mine.pack;

public class Main{
	public static void main(String[] args){
		MyFrame myFrame;
		MyListener listener;
		listener = new MyListener();
		myFrame = new MyFrame(640,480,listener);
		listener.getFrame(myFrame);
	}
}
