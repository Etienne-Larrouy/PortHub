<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">


<head>

<!-- MAPPY LEAFLET CSS JS -->

<spring:url value="/resources/core/css/L.Mappy.css" var="mappyCss" />
<spring:url value="/resources/core/css/leaflet.css" var="leafletCss" />
<link rel="stylesheet" href="${leafletCss}" />
<link rel="stylesheet"  href="${mappyCss}" />

<spring:url value="/resources/core/js/leaflet.js"
	var="leafletJs" />
<script src="${leafletJs}"></script>
<spring:url value="/resources/core/js/L.Mappy-src.js" var="mappysrcJs" />
<script src="${mappysrcJs}"></script>
<spring:url value="/resources/core/js/L.Mappy.js"
	var="mappyJs" />
<script src="${mappyJs}"></script>


<title>Spring MVC 4 + Ajax Hello World</title>

<c:url var="home" value="/" scope="request" />


<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />


<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
</head>
 <body>
	<button onCLick="refresh()">Refresh</button>
	<div id="example-map-1" style="width:100%; height: 900px;"></div>
	<div id="feedback"></div>

<script>

	  var BatimentsIcon = L.Icon.extend({
	  options: {
		  iconSize:     [50, 50],
		  iconAnchor:   [0,0]
	}
	});

	var bankIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/bank.png'}),
	foodIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/food.png'}),
	garageIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/garage.png'}),
	iceIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/ice.png'}),
	manifIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/manif.png'}),
	radarIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/radar.png'}),
	sleepIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/sleep.png'}),
	trafficIcon = new BatimentsIcon({iconUrl: '/spring4ajax/resources/core/css/images/traffic.png'});

   L.Mappy.setImgPath("/spring4ajax/resources/core/css/images/");
	var  oldLat;
	var  oldLng;
	var idPlayer = 0;

	var getUrlParameter = function getUrlParameter(sParam) {
		var sPageURL = decodeURIComponent(window.location.search.substring(1)),
			sURLVariables = sPageURL.split('&'),
			sParameterName,
			i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true : sParameterName[1];
			}
		}
	};

	var idPlayer = getUrlParameter('p');
	if(idPlayer==undefined){
		while(true){
			alert('Erreur nom joueur');
		}
	}

	console.log("Player : " + idPlayer);

	$.ajax({
		type : "POST",
		url: "${home}search/api/initMap",
		contentType : "application/json",
		async : false,
		success : function(data) {
			console.log("SUCCESS: ", data);
			oldLat = data.result.departLatitude;
			oldLng = data.result.departLongitude;

			console.log("init");

		},
	});
	// Création de la carte
	var exampleMap1 = new L.Mappy.Map("example-map-1", {
		clientId: 'dri_24hducode',
		center: [oldLat,oldLng],
		zoom: 7
	});

	var move = {};
	move["lat"] = oldLat;
	move["lng"] =  oldLng;
	var newIconLayer = L.layerGroup();
	initIcons(exampleMap1, newIconLayer, move);

	// Création d'un layer contenant les marqueurs à afficher
	var mLayer = L.layerGroup().addTo(exampleMap1);
	// Création d'un marqueur qu'on ajoute au layer
	var marker = L.marker([oldLat, oldLng]).addTo(exampleMap1);

	// Désactivation des interactions utilisateurs
	var  total = 0;
	var radius = 5000;

		circle = L.circle([oldLat, oldLng], {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5,
			radius: radius
		}).addTo(exampleMap1).on("click", clickOnRange);


	function refresh() {
		$.ajax({
			type : "POST",
			url: "${home}search/api/getInfo",
			contentType : "application/json",
			async : false,
			success : function(data) {
				console.log("SUCCESS: ", data);
				oldLat = data.result.departLatitude;
				oldLng = data.result.departLongitude;
				if(data.result.list_Player[idPlayer].state){
					var move = {};
					move["lat"] = oldLat;
					move["lng"] =  oldLng;
					exampleMap1.removeLayer(newIconLayer);
					newIconLayer=undefined;
					newIconLayer=L.layerGroup();
					initIcons(exampleMap1, newIconLayer, move);
				}
				else{
				   console.log("Pas ton tour")
				}
			},
		});
	}

	function clickOnRange(e) {


		exampleMap1.removeLayer(newIconLayer);
		newIconLayer=undefined;
		newIconLayer=L.layerGroup();

		var move = {};
		move["lat"] = e.latlng.lat;
		move["lng"] =  e.latlng.lng;
		move["idPlayer"] =  idPlayer;

		initIcons(exampleMap1, newIconLayer, move);

		exampleMap1.removeLayer(marker);

		marker = L.marker([e.latlng.lat,e.latlng.lng]).addTo(exampleMap1);
		exampleMap1.removeLayer(circle);

		circle = L.circle([e.latlng.lat, e.latlng.lng], {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5,
			radius: radius
		}).addTo(exampleMap1).on("click", clickOnRange);


		var options = {
			vehicle: L.Mappy.Vehicles.comcar,
			cost: "length", // or "time" or "price"
			gascost: 1.0,
			gas: "petrol", // or diesel, lpg
			nopass: 0, // 1 pour un trajet sans col
			notoll: 0, // 1 pour un trajet sans péage
			infotraffic: 0 // 1 pour un trajet avec trafic
		};


		// On cherche les résultats pour "47 rue de charonne Paris"
		L.Mappy.Services.route([L.latLng(e.latlng.lat, e.latlng.lng), L.latLng(oldLat, oldLng)],
			options,
			// Callback de succès
			function(result) {
				L.Mappy.route(result.routes).addTo(exampleMap1);
				var summary = result.routes.route[0].summary;
				var roadbook = result.routes.route[0].actions.action;
				move["distance"] = (summary.length / 1000);
				console.log("Distance : " + move["distance"]);

			$.ajax({
				type : "POST",
				url: "${home}search/api/getCLickCoords",
				contentType : "application/json",
				data : JSON.stringify(move),
				dataType : 'json',
				success : function(data) {
					console.log("SUCCESS: ", data);
				},
			});
			},
			// Callback d'erreur
			function(errorType) {
				// Error during route calculation
			}
		);

		oldLat = parseFloat(e.latlng.lat);
		oldLng = parseFloat(e.latlng.lng);
	};

	function initIcons(map, newIconLayer, move) {

		var search = {}
		search["listTotal"]= $("#listTotal").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/test",
			data : JSON.stringify(move),
			timeout : 100000,
			success : function(data) {
			console.log("SUCCESSY", data);
	  	var databis = data.listTotal;

			var nb_banque=0, nb_nourriture=0, nb_garage=0, nb_dormir=0;

	  	for (var i in databis){
					move["proche"]="";
				if((databis[i].type=="banque")&&(nb_banque<10)&&(measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<radius)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: bankIcon}).addTo(newIconLayer);
						nb_banque=nb_banque+1;
						if((measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<50)){
							move["proche"]="banque";
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : "${home}search/api/proche",
								data : JSON.stringify(move),
								timeout : 100000,
								success : function(data) {
								console.log("proche sent")
							}
						});
				}
			}
				if((databis[i].type=="nourriture")&&(nb_nourriture<10)&(measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<radius)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: foodIcon}).addTo(newIconLayer);
						nb_nourriture=nb_nourriture+1;
						if((measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<50)){
							move["proche"]="nourriture";
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : "${home}search/api/proche",
								data : JSON.stringify(move),
								timeout : 100000,
								success : function(data) {
								console.log("proche sent")
							}
						});
				}
				}
				if((databis[i].type=="garage")&&(nb_garage<10)&(measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<radius)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: garageIcon}).addTo(newIconLayer);
						nb_garage=nb_garage+1;
						if((measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<50)){
							move["proche"]="garage";
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : "${home}search/api/proche",
								data : JSON.stringify(move),
								timeout : 100000,
								success : function(data) {
								console.log("proche sent")
							}
						});
				}
				}
				if((databis[i].type=="dormir")&&(nb_dormir<10)&(measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<radius)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: sleepIcon}).addTo(newIconLayer);
						nb_dormir=nb_dormir+1;
						if((measure(move["lat"],move["lng"],databis[i].latitude,databis[i].longitude)<50)){
							move["proche"]="dormir";
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : "${home}search/api/proche",
								data : JSON.stringify(move),
								timeout : 100000,
								success : function(data) {
								console.log("proche sent")
							}
						});
				}
				}
			}
				newIconLayer.addTo(map);
			}
		});
	}

	function measure(lat1, lon1, lat2, lon2){  // generally used geo measurement function
    var R = 6378.137; // Radius of earth in KM
    var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
    var dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
    Math.sin(dLon/2) * Math.sin(dLon/2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    var d = R * c;
    return d * 1000; // meters
}


</script>

</body>
</html>
