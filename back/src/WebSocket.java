import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint(value = "/WebSocket")
public class WebSocket {
	private static Vector<Session> sV = new Vector<Session>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println();
		sV.add(session);
		
		
	}
}
