import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	public int UID;
	public String Username;
	public int Rating;
	public String SUB;
	
	User (String username, int rating, String sub) {
		this.Rating = rating;
		this.SUB = sub;
		this.Username = username;
	}
	
	User (int uid, String username, int rating, String sub) {
		this.UID = uid;
		this.Rating = rating;
		this.SUB = sub;
		this.Username = username;
	}
	
	public static User getUserFromDB(String sub) {
		User u;
		String query = "SELECT * FROM users WHERE SUB = \""+ sub +"\";";
		String url = "jdbc:mysql://172.90.225.8:3306/201Final";
		String username = "root";
		String password = "wwssadadba";
		
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(url, username, password);
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			result.next();
			u = new User(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4));
			

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return null;
		}
		return u;
	}
	
	public static boolean addUserToDB(User u) {
		String query = "INSERT INTO users (Username, Rating, SUB) VALUES (\""+u.Username+"\", "+u.Rating+", \""+u.SUB+"\");";
		String url = "jdbc:mysql://172.90.225.8:3306/201Final";
		String username = "root";
		String password = "wwssadadba";
		
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(url, username, password);
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return false;
		}
		return true;
	}
	
	public static boolean editUserInDB(String sub, User u) {
		String query = "UPDATE users SET Username = \""+u.Username+"\", Rating = "+u.Rating+", SUB = \""+u.SUB+"\" WHERE SUB = \""+sub+"\";";
		String url = "jdbc:mysql://172.90.225.8:3306/201Final";
		String username = "root";
		String password = "wwssadadba";
		
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(url, username, password);
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return false;
		}
		return true;
	}
}
