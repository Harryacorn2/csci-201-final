import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;



@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public redirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append(request.getParameter("id_token"));
//		System.out.println(request.getParameter("id_token"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String token = request.getParameter("id_token");
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();

		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));
		
		System.out.println(header);
		System.out.println(payload);
		
	}

}
