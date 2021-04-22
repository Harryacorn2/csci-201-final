

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public View() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String site = "";
		HttpSession session = request.getSession();
		if (session.getAttribute("isHost") != null || request.getParameter("sport") != null) {
			if (request.getParameter("sport") != null) {
				session.setAttribute("isHost", true);
			}
			
			site += "<html><head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "    <title>About</title>\n"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"
					+ "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
					+ "    <script defer=\"\" src=\"js/view.js\"></script>\n"
					+ "    <script defer=\"\" src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script defer=\"\" src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script defer=\"\" src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script type=\"text/javascript\" src=\"js/IsLogin.js\"></script>\n"
					+ "    <style></style>\n"
					+ "<script async=\"\" src=\"https://negbar.ad-blocker.org/chrome/adblocker-chromeglobalinjectjs.js\"></script></head>\n"
					+ "<body id=\"override\" onload=\"whenCalled()\">\n"
					+ "\n"
					+ "	<div id=\"mafs\">\n"
					+ "		<script>\n"
					+ "		    var socket = new WebSocket(\"ws://localhost:8080/Backend/WebSocket?"+request.getParameter("room")+"\");\n"
					+ "		    function whenCalled(){\n"
					+ "				socket.onopen = function(event){\n"
					+ "				}\n"
					+ "				socket.onmessage = function(event){\n"
					+ "					document.getElementById(\"messages\").innerHTML += event.data;\n"
					+ "				}\n"
					+ "				socket.onclose = function(event){\n"
					+ "					document.getElementById(\"cameBack\").innerHTML += \"Stream ended...\";\n"
					+ "				}\n"
					+ "				\n"
					+ "		     }\n"
					+ "		    function sendMessage(){\n"
					+ "		        var value = document.getElementById(\"messageArea\").value;\n"
					+ "		        document.getElementById(\"messageArea\").value = \"\";\n"
					+ "		    	socket.send(value);\n"
					+ "		    }\n"
					+ "    	</script>\n"
					+ "	</div>\n"
					+ "\n"
					+ "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n"
					+ "        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
					+ "            <span class=\"navbar-toggler-icon\"></span>\n"
					+ "        </button>\n"
					+ "        <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
					+ "            <ul class=\"navbar-nav mr-auto\">\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "            </ul>\n"
					+ "        </div>\n"
					+ "    </nav>\n"
					+ "    <div class=\"container align-items-center justify-content-center\">\n"
					+ "        <div class=\"card mx-auto my-4\">\n"
					+ "            <div class=\"card-header text-center display-3\"> \n"
					+ "                View\n"
					+ "            </div>\n"
					+ "            <table id=\"messages\" class=\"card-body lead table\">\n"
					+ "	            <tr>\n"
					+ "	                 <th scope=\"col\">Time</th>\n"
					+ "	                 <th scope=\"col\">Message</th>\n"
					+ "	            </tr>\n"
					+ "            </table>\n"
					+ "        </div>\n"
					+ "    </div>\n"
					+ "    <div style=\"text-align: center\">\n"
					+ "		<h2>Enter Next Play</h2>\n"
					+ "		<br>\n"
					+ "		<form action=\"#\" method=\"get\" name=\"bigform\">\n"
					+ "		    <textarea id=\"messageArea\" rows=\"2\" cols=\"25\" placeholder=\"...\"></textarea><br>\n"
					+ "		    <button type=\"button\" onclick=\"sendMessage()\">Submit</button>\n"
					+ "		</form>\n"
					+ "    </div>\n"
					+ "    <div id=\"cameBack\">\n"
					+ "    </div>\n"
					+ "</body></html>";
			
		} else {
			
			site += "<html><head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "    <title>About</title>\n"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"
					+ "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
					+ "    <script defer=\"\" src=\"js/view.js\"></script>\n"
					+ "    <script defer=\"\" src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script defer=\"\" src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script defer=\"\" src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
					+ "    <script type=\"text/javascript\" src=\"js/IsLogin.js\"></script>\n"
					+ "    <style></style>\n"
					+ "<script async=\"\" src=\"https://negbar.ad-blocker.org/chrome/adblocker-chromeglobalinjectjs.js\"></script></head>\n"
					+ "<body id=\"override\" onload=\"whenCalled()\">\n"
					+ "\n"
					+ "	<div id=\"mafs\">\n"
					+ "		<script>\n"
					+ "		    var socket = new WebSocket(\"ws://localhost:8080/Backend/WebSocket?"+request.getParameter("room")+"\");\n"
					+ "		    function whenCalled(){\n"
					+ "				socket.onopen = function(event){\n"
					+ "				}\n"
					+ "				socket.onmessage = function(event){\n"
					+ "					document.getElementById(\"messages\").innerHTML += event.data;\n"
					+ "				}\n"
					+ "				socket.onclose = function(event){\n"
					+ "					document.getElementById(\"cameBack\").innerHTML += \"Stream ended...\";\n"
					+ "				}\n"
					+ "				\n"
					+ "		     }\n"
					+ "		    function sendMessage(){\n"
					+ "		        var value = document.getElementById(\"messageArea\").value;\n"
					+ "		        document.getElementById(\"messageArea\").value = \"\";\n"
					+ "		    	socket.send(value);\n"
					+ "		    }\n"
					+ "    	</script>\n"
					+ "	</div>\n"
					+ "\n"
					+ "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n"
					+ "        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
					+ "            <span class=\"navbar-toggler-icon\"></span>\n"
					+ "        </button>\n"
					+ "        <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
					+ "            <ul class=\"navbar-nav mr-auto\">\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "                <li class=\"nav-item\">\n"
					+ "                    <a class=\"nav-link\" href=\"#\">...</a>\n"
					+ "                </li>\n"
					+ "            </ul>\n"
					+ "        </div>\n"
					+ "    </nav>\n"
					+ "    <div class=\"container align-items-center justify-content-center\">\n"
					+ "        <div class=\"card mx-auto my-4\">\n"
					+ "            <div class=\"card-header text-center display-3\"> \n"
					+ "                View\n"
					+ "            </div>\n"
					+ "            <table id=\"messages\" class=\"card-body lead table\">\n"
					+ "	            <tr>\n"
					+ "	                 <th scope=\"col\">Time</th>\n"
					+ "	                 <th scope=\"col\">Message</th>\n"
					+ "	            </tr>\n"
					+ "            </table>\n"
					+ "        </div>\n"
					+ "    </div>\n"
					+ "    <div id=\"cameBack\">\n"
					+ "    </div>\n"
					+ "</body></html>";
			
		}
		response.getWriter().append(site);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
