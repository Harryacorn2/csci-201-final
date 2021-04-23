

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetAllRooms")
public class GetAllRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetAllRooms() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String site = "";
		for (Room r : Room.openRooms) {
			System.out.println("Found Room: " + r.room);
			site += "<tbody>\n"
					+ "<tr align=\"center\">\n"
					+ "<td scope=\"col\"><a href=\"/Backend/View?room="+r.room+"\">"+r.room+"</a></td>\n"
					+ "<td scope=\"col\">"+r.sport+"</td>\n"
					+ "<td scope=\"col\">"+r.description+"</td>\n"
					+ "</tr>\n"
					+ "</tbody>\n";
		}
		System.out.println("------------\n" + site + "\n------------");
		response.getWriter().println(site);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
