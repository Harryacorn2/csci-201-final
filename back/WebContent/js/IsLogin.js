/**
 * Checks if logged in or not
 */

const endpoint = "/IsLoggedIn";

function isIt(){
	gapi.auth2.getAuthInstance().isSignedIn.get();
}
