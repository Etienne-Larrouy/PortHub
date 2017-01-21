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

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
    var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
    var="jqueryJs" />
<script src="${jqueryJs}"></script>
</head>
 <body>

    <div id="example-map-1" style="width:100%; height: 900px;"></div>
    <div id="feedback"></div>

<script>
   L.Mappy.setImgPath("/images");
    // Création de la carte
    var exampleMap1 = new L.Mappy.Map("example-map-1", {
        clientId: 'dri_24hducode',
        center: [51.5,-0.09],
        zoom: 7
    });

    var circle = L.circle([51.508, -0.11], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 500
    }).addTo(exampleMap1);

    // Création d'un layer contenant les marqueurs à afficher
    var mLayer = L.layerGroup().addTo(exampleMap1);
    // Création d'un marqueur qu'on ajoute au layer
    var marker = L.marker([51.5, -0.09]).addTo(exampleMap1);

    // Désactivation des interactions utilisateurs

    exampleMap1.on('click', function (e) {

        var coords = {};
        coords["lat"] = e.latlng.lat;
        coords["lng"] =  e.latlng.lng;


        $.ajax({
            type : "POST",
            url: "${home}search/api/getCLickCoords",
            contentType : "application/json",
            data : JSON.stringify(coords),
            dataType : 'json',
            success : function(data) {
                console.log("SUCCESS: ", data);
            addPoint(data);
        },
        });
    });

    function addPoint(data) {
        var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
        $('#feedback').html(json);

        exampleMap1.removeLayer(marker);

        marker = L.marker([parseFloat(data.result.lat),parseFloat(data.result.lng)]).addTo(exampleMap1);
    }


</script>

</body>
</html>
