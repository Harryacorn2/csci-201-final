import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;



@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public redirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("id_token");
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();

		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));
		
		System.out.println(header);
		System.out.println(payload);
		
		Gson gson = new Gson();
		Token tokenG = gson.fromJson(payload, Token.class);
		
		String sub = tokenG.sub;
		
		System.out.println(sub);
		
		HttpSession session = request.getSession();
		session.setAttribute("sub", sub);
		
		// Adding a user if one does not already exist
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		User u = User.getUserFromDB(sub);
		if (u == null) {
			String username = tokenG.email;
			int i = username.indexOf("@");
			username = username.substring(0, i);
			u = new User(username, 5, sub);
			User.addUserToDB(u);
		}
	}
}
