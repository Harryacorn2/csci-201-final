import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.javanet.NetHttpTransport;


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

		String idToken = request.getParameter("id_token");
		
		
		
		
		
		
		
		
//		NetHttpTransport transport = new NetHttpTransport();
//		GsonFactory jsonFactory = new GsonFactory();  // For some reason half of these libraries dont actually exist.
//		
//		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
//			    // Specify the CLIENT_ID of the app that accesses the backend:
//			    .setAudience(Collections.singletonList("633532092157-5at1c44d3f4qif55icb4nrjgokiu6vb9.apps.googleusercontent.com"))
//			    // Or, if multiple clients access the backend:
//			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
//			    .build();
//
//			// (Receive idTokenString by HTTPS POST)
//
//			GoogleIdToken idToken = null;
//			try {
//				idToken = verifier.verify(request.getParameter("id_token"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			if (idToken != null) {
//			  Payload payload = idToken.getPayload();
//
//			  // Print user identifier
//			  String userId = payload.getSubject();
//			  System.out.println("User ID: " + userId);
//
//			  // Get profile information from payload
//			  String email = payload.getEmail();
//			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//			  String name = (String) payload.get("name");
//			  String pictureUrl = (String) payload.get("picture");
//			  String locale = (String) payload.get("locale");
//			  String familyName = (String) payload.get("family_name");
//			  String givenName = (String) payload.get("given_name");
//			  String sub = (String) payload.get("sud");
//
//			  // Use or store profile information
//			  // ...
//			  System.out.println(name);
//			  System.out.println(sub);
//
//			} else {
//			  System.out.println("Invalid ID token.");
//			}
	}

}
