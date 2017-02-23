package wibly.wobly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextListener implements ActionListener{
	private LoadWeb webHandeler;

	public TextListener(LoadWeb webHandeler){
		this.webHandeler = webHandeler;
	}

	public void actionPerformed(ActionEvent e) {
		webHandeler.loadingWeb(e.getActionCommand());
	}
}
