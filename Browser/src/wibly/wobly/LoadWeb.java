package wibly.wobly;

import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class LoadWeb {

	private JEditorPane webFrame;
	private JTextField textField;

	public LoadWeb(JEditorPane webFrame, JTextField textField){
		this.webFrame = webFrame;
		this.textField = textField;
	}

	public void loadingWeb(String url){
		
		try{
			webFrame.setPage(url);
			textField.setText(url);
		}catch(Exception e){
		
		}
	}
}
