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
	// Création de la carte
	var exampleMap1 = new L.Mappy.Map("example-map-1", {
		clientId: 'dri_24hducode',
		center: [51.5,-0.09],
		zoom: 7
	});

	var circle = L.circle([51.5, -0.09], {
		color: 'red',
		fillColor: '#f03',
		fillOpacity: 0.5,
		radius: 5000
	}).addTo(exampleMap1).on("click", clickOnRange);

	// Création d'un layer contenant les marqueurs à afficher
	var mLayer = L.layerGroup().addTo(exampleMap1);
	// Création d'un marqueur qu'on ajoute au layer
	var marker = L.marker([51.5, -0.09]).addTo(exampleMap1);

	// Désactivation des interactions utilisateurs
	var  total = 0;
	var  oldLat = 51.5;
	var  oldLng = -0.09;
	var radius = 5000;

	function clickOnRange(e) {

		var move = {};
		move["lat"] = e.latlng.lat;
		move["lng"] =  e.latlng.lng;

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
					addPoint(data);
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
	 initIcons(exampleMap1);

	function initIcons(map) {

		var search = {}
		search["listTotal"]= $("#listTotal").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/test",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
			console.log("SUCCESS", data.listTotal[0]);
	  	var databis = data.listTotal;
			var mLayer = L.layerGroup().addTo(map);
			var nb_banque=0, nb_nourriture=0, nb_garage=0, nb_dormir=0;
	  	for (var i in databis){
				if((databis[i].type=="banque")&&(nb_banque<15)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: bankIcon}).addTo(map);
						nb_banque=nb_banque+1;
				}
				if((databis[i].type=="nourriture")&&(nb_nourriture<15)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: foodIcon}).addTo(map);
						nb_nourriture=nb_nourriture+1;
				}
				if((databis[i].type=="garage")&&(nb_garage<15)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: garageIcon}).addTo(map);
						nb_garage=nb_garage+1;
				}
				if((databis[i].type=="dormir")&&(nb_dormir<15)){
						var marker = L.marker([databis[i].latitude, databis[i].longitude],{icon: sleepIcon}).addTo(map);
						nb_dormir=nb_dormir+1;
				}
			}
			}
		});
	}


</script>

</body>
</html>
