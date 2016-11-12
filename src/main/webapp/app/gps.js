
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
	zoom = 15;
	x = lat2tile(latitude.innerHTML, zoom);
	y = long2tile(longitude.innerHTML, zoom);
	map.innerHTML = '<iframe width="600" height="450" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/search?q='+latitude.innerHTML+','+longitude.innerHTML+'&key=AIzaSyDwMujYa5vx07JKm81BfzYKugsJd9SzUSM" allowfullscreen></iframe>';
	// map.innerHTML = '<img src="https://api.pitneybowes.com/location-intelligence/geomap/v1/tile/osm/'+zoom+'/'+x+'/'+y+'.png?api_key=xAAjYPYcG75SiICwPTfRtG4BdI65xyJ8">';
}

function long2tile(lon,zoom) { return (Math.floor((lon+180)/360*Math.pow(2,zoom))); }
function lat2tile(lat,zoom)  { return (Math.floor((1-Math.log(Math.tan(lat*Math.PI/180) + 1/Math.cos(lat*Math.PI/180))/Math.PI)/2 *Math.pow(2,zoom))); }

getLocation();
