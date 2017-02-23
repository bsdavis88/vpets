package wibly.wobly;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class HyperListener implements HyperlinkListener{

	private LoadWeb webHandeler;

	public HyperListener(LoadWeb webHandeler){
		this.webHandeler = webHandeler;
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
			webHandeler.loadingWeb(e.getURL().toString());
		}
	}

}
