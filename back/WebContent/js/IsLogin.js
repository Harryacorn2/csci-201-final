/**
 * Checks if logged in or not
 */

function isIt() {
	let request = new XMLHttpRequest();
	request.open('GET', '/Backend/IsLoggedIn');
//	request.open('GET', '/IsLoggedIn');
	request.onload = function() {
		var r = request.response;
		if (r == "false"){
			window.location.href = "login.html";
			request.responseType = 'text';
		}
		console.log(r);
	};
	request.send();
}

function loadUserData() {
	var xhttp = new XMLHttpRequest();
	var url = "/Backend/UserData";
	xhttp.open("GET", url, true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onreadystatechange = function() {
		document.getElementById("page").innerHTML = this.responseText;
	}
	xhttp.send();
}