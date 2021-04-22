import java.util.Vector;

public class Room {
	public String room = "";
	public String sport = "";
	public String description = "";
	
	public static Vector<Room> openRooms = new Vector<Room>();
	
	Room (String r, String s, String des) {
		this.room = r;
		this.sport = s;
		this.description = des;
	}
	
	public static Room search(String r) {
		if (openRooms.isEmpty()) return null;
		for (var openRoom : openRooms) {
			if (openRoom.room.equals(r)) {
				return openRoom;
			}
		}
		return null;
	}
	
	public static boolean add(Room r) {
		for (var openRoom : openRooms) {
			if (openRoom.room.equals(r.room)) {
				return false;
			}
		}
		openRooms.add(r);
		return true;
	}
	
	public static boolean remove(String r) {
		if (openRooms.isEmpty()) return false;
		for (var openRoom : openRooms) {
			if (openRoom.room.equals(r)) {
				openRoom.close();
				return true;
			}
		}
		return false;
	}
	
	public void close() {
		openRooms.remove(this);
	}
}
