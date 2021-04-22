import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.Vector;

@ServerEndpoint(value = "/WebSocket")
public class WebSocket {
	
	@OnOpen
	public void open(Session session) {
		System.out.println("New Session Detected\nRoom: "+getRoom(session)+"\n");
		
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		String text = "";
		text += "<tr align=\"center\">\n"
				+ "<td scope=\"col\">Time</td>\n"
				+ "<td scope=\"col\">"+message+"</td>\n"
				+ "</tr>\n";
		
		try {
			for (Session s : session.getOpenSessions()) {
				if (getRoom(s).equals(getRoom(session))) {
					s.getBasicRemote().sendText(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getRoom(Session session) {
		String room = session.getRequestURI().toString();
		int i = room.indexOf('?');
		room = room.substring(i+1);
		return room;
	}
}