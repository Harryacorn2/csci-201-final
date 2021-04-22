

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckRoom
 */
@WebServlet("/CheckRoom")
public class CheckRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckRoom() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = request.getParameter("room");
		Room room = Room.search(r);
		if (room != null) {
			response.getWriter().append("true");
		} else {
			response.getWriter().append("false");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
