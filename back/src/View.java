

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
		boolean isHostingThisSite = false;
		String site = "";
		
		HttpSession session = request.getSession();
		
		// If the user is a host
		if (session.getAttribute("isHost") != null) {
			
			// If the user was already hosting this site
			if (session.getAttribute("room") == request.getParameter("room") || request.getParameter("room") == null) {
				isHostingThisSite = true;
				
			// If the user is hosting a different site
			} else {
				
				// If he is creating a site (and he is a host)
				if (request.getParameter("sport") != null) {
					Room.remove((String) session.getAttribute("room"));
					Room r = new Room(request.getParameter("room"), request.getParameter("sport"), request.getParameter("des"));
					Room.add(r);
					session.setAttribute("room", r.room);
					isHostingThisSite = true;
				}
			}
			
		// If the user is not a host
		} else {
			
			// If he is creating a site (and he is not a host)
			if (request.getParameter("sport") != null) {
				Room r = new Room(request.getParameter("room"), request.getParameter("sport"), request.getParameter("des"));
				Room.add(r);
				session.setAttribute("room", r.room);
				session.setAttribute("isHost", true);
				isHostingThisSite = true;
				
			// If he pressed the View button and isnt a host
			} else if (request.getParameter("room") == null) {
				response.sendRedirect(request.getContextPath() + "/explore.html");
				return;
			}
		}
		
		if (isHostingThisSite) {
			site += "<html>\n"
					+ "	<head>\n"
					+ "		<meta charset=\"UTF-8\">\n"
					+ "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "		<title>About</title>\n"
					+ "		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"
					+ "		<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
					+ "		<script defer=\"\" src=\"js/view.js\"></script>\n"
					+ "		<script defer=\"\" src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script defer=\"\" src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script defer=\"\" src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script type=\"text/javascript\" src=\"js/IsLogin.js\"></script>\n"
					+ "		<style>\n"
					+ "			#looks{\n"
					+ "				display: inline-block;\n"
					+ "				height:200px;\n"
					+ "				overflow-y: scroll;\n"
					+ "			}\n"
					+ "		</style>\n"
					+ "		<script async=\"\" src=\"https://negbar.ad-blocker.org/chrome/adblocker-chromeglobalinjectjs.js\"></script>\n"
					+ "	</head>\n"
					+ "	\n"
					+ "	<body id=\"override\" onload=\"whenCalled()\">\n"
					+ "		<div id=\"mafs\">\n"
					+ "			<script>\n"
					+ "				var socket = new WebSocket(\"ws://localhost:8080/Backend/WebSocket?"+session.getAttribute("room")+"\");\n"
					+ "				function whenCalled(){\n"
					+ "					socket.onopen = function(event){\n"
					+ "					}\n"
					+ "					socket.onmessage = function(event){\n"
					+ "						var data = \"</tr>\\n\";\n"
					+ "						data += event.data;\n"
					+ "						var current = document.getElementById(\"messages\").innerHTML;\n"
					+ "						var newpage = current.replace(\"</tr>\\n\", data);\n"
					+ "						document.getElementById(\"messages\").innerHTML = newpage;\n"
					+ "					}\n"
					+ "					socket.onclose = function(event){\n"
					+ "						document.getElementById(\"cameBack\").innerHTML += \"Stream ended...\";\n"
					+ "					}\n"
					+ "					\n"
					+ "				}\n"
					+ "				\n"
					+ "				function sendMessage(){\n"
					+ "					var value = document.getElementById(\"messageArea\").value;\n"
					+ "					document.getElementById(\"messageArea\").value = \"\";\n"
					+ "					socket.send(value);\n"
					+ "					document.getElementById(\"messageArea\").focus();\n"
					+ "				}\n"
					+ "			</script>\n"
					+ "		</div>\n"
					+ "		\n"
					+ "		<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n"
					+ "            <a class=\"navbar-brand\" href=\"login.html\">PlayByPlaySports</a>\n"
					+ "            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\"\n"
					+ "                aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
					+ "                <span class=\"navbar-toggler-icon\"></span>\n"
					+ "            </button>\n"
					+ "            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
					+ "                <ul class=\"navbar-nav mr-auto\">\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"explore.html\">Explore</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"search.html\">Search</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"create.html\">Create</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"about.html\">User</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"login.html\">Login</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"/Backend/View\">View</a>\n"
					+ "                    </li>\n"
					+ "                </ul>\n"
					+ "            </div>\n"
					+ "        </nav>"
					+ "		<div class=\"container align-items-center justify-content-center\">\n"
					+ "			<div class=\"card mx-auto my-4\">\n"
					+ "				<div class=\"card-header text-center display-3\"> \n"
					+ "					"+session.getAttribute("room")+"\n"
					+ "				</div>\n"
					+ "				<div id=\"looks\">\n"
					+ "					<table id=\"messages\" class=\"card-body lead table\">\n"
					+ "						<tr>\n"
					+ "							<th scope=\"col\" style=\"text-align: center\">Time</th>\n"
					+ "							<th scope=\"col\" style=\"text-align: center\">Message</th>\n"
					+ "						</tr>\n"
					+ "					</table>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "		</div>\n"
					+ "		<div style=\"text-align: center\">\n"
					+ "			<h2>Enter Next Play</h2>\n"
					+ "			<br>\n"
					+ "			<form action=\"#\" method=\"get\" name=\"bigform\">\n"
					+ "				<textarea id=\"messageArea\" rows=\"2\" cols=\"25\" placeholder=\"...\" autofocus></textarea><br>\n"
					+ "				<button type=\"button\" onclick=\"sendMessage()\" style=\"padding-left: 50px;padding-right: 50px;padding-top: 5px;padding-bottom: 5px;\">Send</button>\n"
					+ "			</form>\n"
					+ "			\n"
					+ "			<button type=\"button\" onclick=\"closeRoom()\">Close Room</button>\n"
					+ "		</div>\n"
					+ "		<div id=\"cameBack\">\n"
					+ "		</div>\n"
					+ "	</body>\n"
					+ "</html>";
		} else {
			site += "<html>\n"
					+ "	<head>\n"
					+ "		<meta charset=\"UTF-8\">\n"
					+ "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "		<title>About</title>\n"
					+ "		<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"
					+ "		<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
					+ "		<script defer=\"\" src=\"js/view.js\"></script>\n"
					+ "		<script defer=\"\" src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script defer=\"\" src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script defer=\"\" src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
					+ "		<script type=\"text/javascript\" src=\"js/IsLogin.js\"></script>\n"
					+ "		<style>\n"
					+ "			#looks{\n"
					+ "				display: inline-block;\n"
					+ "				height:500px;\n"
					+ "				overflow-y: scroll;\n"
					+ "			}\n"
					+ "		</style>\n"
					+ "		<script async=\"\" src=\"https://negbar.ad-blocker.org/chrome/adblocker-chromeglobalinjectjs.js\"></script>\n"
					+ "	</head>\n"
					+ "	\n"
					+ "	<body id=\"override\" onload=\"whenCalled()\">\n"
					+ "		<div id=\"mafs\">\n"
					+ "			<script>\n"
					+ "				var socket = new WebSocket(\"ws://localhost:8080/Backend/WebSocket?"+request.getParameter("room")+"\");\n"
					+ "				function whenCalled(){\n"
					+ "					socket.onopen = function(event){\n"
					+ "					}\n"
					+ "					socket.onmessage = function(event){\n"
					+ "						var data = \"</tr>\\n\";\n"
					+ "						data += event.data;\n"
					+ "						var current = document.getElementById(\"messages\").innerHTML;\n"
					+ "						var newpage = current.replace(\"</tr>\\n\", data);\n"
					+ "						document.getElementById(\"messages\").innerHTML = newpage;\n"
					+ "					}\n"
					+ "					socket.onclose = function(event){\n"
					+ "						document.getElementById(\"cameBack\").innerHTML += \"Stream ended...\";\n"
					+ "					}\n"
					+ "					\n"
					+ "				}\n"
					+ "				function sendMessage(){\n"
					+ "					var value = document.getElementById(\"messageArea\").value;\n"
					+ "					document.getElementById(\"messageArea\").value = \"\";\n"
					+ "					socket.send(value);\n"
					+ "				}\n"
					+ "			</script>\n"
					+ "		</div>\n"
					+ "		\n"
					+ "		<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n"
					+ "            <a class=\"navbar-brand\" href=\"login.html\">PlayByPlaySports</a>\n"
					+ "            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\"\n"
					+ "                aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
					+ "                <span class=\"navbar-toggler-icon\"></span>\n"
					+ "            </button>\n"
					+ "            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
					+ "                <ul class=\"navbar-nav mr-auto\">\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"explore.html\">Explore</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"search.html\">Search</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"create.html\">Create</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"about.html\">User</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"login.html\">Login</a>\n"
					+ "                    </li>\n"
					+ "                    <li class=\"nav-item\">\n"
					+ "                        <a class=\"nav-link\" href=\"/Backend/View\">View</a>\n"
					+ "                    </li>\n"
					+ "                </ul>\n"
					+ "            </div>\n"
					+ "        </nav>"
					+ "		\n"
					+ "		<div class=\"container align-items-center justify-content-center\">\n"
					+ "			<div class=\"card mx-auto my-4\">\n"
					+ "				<div class=\"card-header text-center display-3\"> \n"
					+ "					"+request.getParameter("room")+"\n"
					+ "				</div>\n"
					+ "				<div id=\"looks\">\n"
					+ "					<table id=\"messages\" class=\"card-body lead table\" style=\"display: block; overflow-y:scroll\">\n"
					+ "						<tr>\n"
					+ "							<th scope=\"col\">Time</th>\n"
					+ "							<th scope=\"col\">Message</th>\n"
					+ "						</tr>\n"
					+ "					</table>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "		</div>\n"
					+ "		\n"
					+ "		<div id=\"cameBack\">\n"
					+ "		</div>\n"
					+ "		\n"
					+ "	</body>\n"
					+ "</html>";
		}
		
		response.getWriter().append(site);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
