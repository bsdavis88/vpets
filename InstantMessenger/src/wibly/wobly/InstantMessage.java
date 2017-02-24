package wibly.wobly;

public class InstantMessage {

	public static void main(String[] args){
		ClientConnector connect = new ClientConnector(320,240,960,200);
		Server server = new Server(640,480,300,200,6789);
	}
}
