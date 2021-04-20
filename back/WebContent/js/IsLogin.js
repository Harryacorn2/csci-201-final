/**
 * Checks if logged in or not
 */

const endpoint = "/IsLoggedIn";

function isIt(){
	if(gapi.auth2.getAuthInstance().isSignedIn.get()){
		continue;
	}
	else{
		window.location.href = "login.html";
	}
}
