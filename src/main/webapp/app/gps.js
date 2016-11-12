
var container = document.getElementById("data-view");
var latitude = document.getElementById("latitude");
var longitude = document.getElementById("longitude");
var accuracy = document.getElementById("accuracy");
var map = document.getElementById("map");

var geoOptions = {
	enableHighAccuracy: true,
	maximumAge: 30000,
	timeout: 27000,
};

function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.watchPosition(showPosition, geoError, geoOptions);
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
		showMap();
	}
	else {
		geoError();
	}
}

function geoError() {
	var noLocationText = "Not Available";
	latitude.innerHTML = noLocationText;
	longitude.innerHTML = noLocationText;
	accuracy.innerHTML = noLocationText;
}

function showMap() {
	var img = new Image();
    img.src = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=300x300&sensor=false";

    output.appendChild(img);
}

getLocation();
