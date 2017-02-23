package weby.camy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.opencv.highgui.VideoCapture;

public class CameraFrame extends JFrame implements ActionListener{

	CameraPanel cp;

	public CameraFrame(){
		System.loadLibrary("opencv_java249");
		VideoCapture list = new VideoCapture(0);
		cp = new CameraPanel();
		Thread thread = new Thread(cp);
		JMenuBar bar = new JMenuBar();
		JMenu camera = new JMenu("Camera");
		
		bar.add(camera);
		
		int i = 1;
		while (list.isOpened()){
			JMenuItem cam = new JMenuItem("Camera " + i);
			cam.addActionListener(this);
			camera.add(cam);
			list.release();
			list = new VideoCapture(i);
			i++;
		}
		thread.start();
		this.add(cp);
		this.setJMenuBar(bar);
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Brent's WebCam App");
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		CameraFrame cf = new CameraFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
