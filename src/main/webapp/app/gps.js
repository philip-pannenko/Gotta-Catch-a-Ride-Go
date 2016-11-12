
var container = document.getElementById("data-view");
var latitude = document.getElementById("latitude");
var longitude = document.getElementById("longitude");
var accuracy = document.getElementById("accuracy");

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.watchPosition(showPosition);
	}
	else {
		container.innerHTML = "Geolocation is not supported by this browser.";
	}
}
function showPosition(position) {
	if (position && position.coords) {
		latitude.innerHTML = position.coords.latitude;
		longitude.innerHTML = position.coords.longitude;
		accuracy.innerHTML = position.coords.accuracy;
	}
}

// while(true) {
	getLocation();
// }
