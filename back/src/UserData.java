

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sub = (String) session.getAttribute("sub");
		System.out.println("SUB: " + sub);
		String body = "";
		User u = User.getUserFromDB(sub);
			if (u != null) {
				System.out.println("User: " + u.Username);
				body += "<p style=\"text-align: center;\">Username: "+u.Username+"<br>\n"
						+ "                    Rating: "+u.Rating+"<br>\n</p>";
			}
		response.getWriter().append(body);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
