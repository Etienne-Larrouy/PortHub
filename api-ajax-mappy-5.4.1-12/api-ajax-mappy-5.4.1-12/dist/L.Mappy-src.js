/*! L.Mappy 5.4.1 2016-11-16 */
(function(L) {

'use strict';

var random_string = function(length) {
    var str;
    str = '';
    while (str.length < length) {
        str += Math.random().toString(36)[2];
    }
    return str;
};

var object_to_uri = function(obj) {
    var data, key, value;
    data = [];
    for (key in obj) {
        value = obj[key];
        data.push(window.encodeURIComponent(key) + '=' + window.encodeURIComponent(value));
    }
    return data.join('&');
};

var translations = {
    fr: {
        'layers.traffic':           'Trafic',
        'layers.transport':         'Transports en commun',
        'layers.photo':             'Vue aérienne',
        'layers.hybrid':            'Afficher le nom des lieux',
        'trafficLegend.trafficAt':  'Trafic à {hours}:{minutes}',
        'trafficLegend.slow':       'ralenti',
        'trafficLegend.blocked':    'fermé',

        'vehicle.comcar':           'Petite voiture',
        'vehicle.midcar':           'Voiture de taille moyenne',
        'vehicle.sedcar':           'Routière',
        'vehicle.luxcar':           'Grande routière',
        'vehicle.caravancar':       'Véhicule avec caravane',
        'vehicle.2-lt3.5':          'Poids lourd, PTAC < 3.5T (2 essieux)',
        'vehicle.2-lt12':           'Poids lourd, PTAC < 12T (2 essieux)',
        'vehicle.2-gt12':           'Poids lourd, PTAC > 12T (2 essieux)',
        'vehicle.2-gt12a':          'Poids lourd, PTAC > 12T, articulé (2 essieux)',
        'vehicle.3-lt3.5':          'Poids lourd, PTAC < 3.5T (3 essieux ou plus)',
        'vehicle.3-lt12':           'Poids lourd, PTAC < 12T (3 essieux ou plus)',
        'vehicle.3-gt12':           'Poids lourd, PTAC > 12T (3 essieux ou plus)',
        'vehicle.3-gt12a':          'Poids lourd, PTAC > 12T, articulé (3 essieux ou plus)',
        'vehicle.mot':              'Moto',
        'vehicle.van':              'Camping car',
        'vehicle.2-coa':            'Bus 2 essieux',
        'vehicle.3-coa':            'Bus 3 essieux',
        'vehicle.ped':              'Piéton',
        'vehicle.bik':              'Vélo',
        'vehicle.bike':             'Vélo en libre-service',
        'vehicle.pub_tp':           'Transports en commun',
        'bikelegend.title':         'Pistes cyclables'
    },
    en: {
        'layers.traffic':           'Traffic',
        'layers.transport':         'Public transports',
        'layers.photo':             'Satellite photos',
        'layers.hybrid':            'Display locations names',
        'trafficLegend.trafficAt':  'Traffic at {hours}:{minutes}',
        'trafficLegend.slow':       'slow',
        'trafficLegend.blocked':    'congested',

        'vehicle.comcar':           'Compact car',
        'vehicle.midcar':           'Medium-size car',
        'vehicle.sedcar':           'Sedan car',
        'vehicle.luxcar':           'Luxury car',
        'vehicle.caravancar':       'Vehicle with caravan',
        'vehicle.2-lt3.5':          'Truck, GVWR < 3.5T (2 axles)',
        'vehicle.2-lt12':           'Truck, GVWR < 12T (2 axles)',
        'vehicle.2-gt12':           'Truck, GVWR > 12T (2 axles)',
        'vehicle.2-gt12a':          'Truck, GVWR > 12T, articulated (2 axles)',
        'vehicle.3-lt3.5':          'Truck, GVWR < 3.5T (3 axles or more)',
        'vehicle.3-lt12':           'Truck, GVWR < 12T (3 axles or more)',
        'vehicle.3-gt12':           'Truck, GVWR > 12T (3 axles or more)',
        'vehicle.3-gt12a':          'Truck, GVWR > 12T, articulated (3 axles or more)',
        'vehicle.mot':              'Motorbike',
        'vehicle.van':              'Campervan',
        'vehicle.2-coa':            'Bus 2 axles',
        'vehicle.3-coa':            'Bus 3 axles',
        'vehicle.ped':              'Pedestrian',
        'vehicle.bik':              'Bicycle',
        'bikelegend.title':         'Bicycle paths'
    },
    nl: {
        'layers.traffic':           'Verkeer',
        'layers.transport':         'Openbaar vervoer',
        'layers.photo':             'Luchtfoto\'s',
        'layers.hybrid':            'Locaties weergeven namen',
        'trafficLegend.trafficAt':  'Verkeer op {hours}:{minutes}',
        'trafficLegend.slow':       'langzaam',
        'trafficLegend.blocked':    'vastgelopen',

        'vehicle.comcar':           'Kleine auto',
        'vehicle.midcar':           'Middelgrote auto',
        'vehicle.sedcar':           'Grote auto',
        'vehicle.luxcar':           'Luxeauto',
        'vehicle.caravancar':       'Voertuig met caravan',
        'vehicle.2-lt3.5':          'Vrachtwagen, MTM < 3,5 T (2 assen)',
        'vehicle.2-lt12':           'Vrachtwagen, MTM < 12 T (2 assen)',
        'vehicle.2-gt12':           'Vrachtwagen, MTM > 12 T (2 assen)',
        'vehicle.2-gt12a':          'Vrachtwagen, MTM > 12 T, met oplegger (2 assen)',
        'vehicle.3-lt3.5':          'Vrachtwagen, MTM < 3,5 T (3 of meer assen)',
        'vehicle.3-lt12':           'Vrachtwagen, MTM < 12 T (3 of meer assen)',
        'vehicle.3-gt12':           'Vrachtwagen, MTM > 12 T (3 of meer assen)',
        'vehicle.3-gt12a':          'Vrachtwagen, MTM > 12 T, met oplegger (3 of meer assen)',
        'vehicle.mot':              'Motor',
        'vehicle.van':              'Camping car',
        'vehicle.2-coa':            'Bus 2 assen',
        'vehicle.3-coa':            'Bus 3 assen',
        'vehicle.ped':              'Voetganger',
        'vehicle.bik':              'Fiets',
        'bikelegend.title':         'Fietspaden'
    }
};

L.Mappy = {
    version: '5.4.1',
    _domain: 'mappy.net',
    _token: 'g0ztPgTHGBpIzhtEqb8IVksxgvRO/VCtTwFgtyNXgrE1AkVLgdBHwFgwpQ55cR5jtxZX0O5W1nY=',
    _clientId: null,
    _https: document.location.protocol.substr(0, 5) === 'https',
    _locale: 'fr_FR',
    _imgPath: 'images/',

    getText: function(key) {
        return translations[this._locale.substr(0, 2)][key] || '';
    },

    setLocale: function(locale) {
        if (locale !== 'fr_FR' && locale !== 'en_GB' && locale !== 'fr_BE' && locale !== 'nl_BE') {
            throw new Error('This locale is not available');
        }

        this._locale = locale;
    },

    setImgPath: function(path) {
        this._imgPath = path;
    },

    getImgPath: function() {
        return this._imgPath;
    },

    getLocale: function() {
        return this._locale;
    },

    setToken: function(token) {
        throw new Error('setToken is deprecated in favor of setClientId (refer to documentation)');
    },

    _getToken: function() {
        return this._token;
    },

    setClientId: function(clientId) {
        this._clientId = clientId;

        if (this._clientId) {
            // Ping mothership with API version and clientId
            var pingUrl = 'http' + (L.Mappy._getHttps() ? 's': '') + '://log.' + L.Mappy._getDomain() + '/log/1.0/ping/api-leaflet/' + this._clientId + '/' +  L.Mappy.version;
            setTimeout(function() {
                (new Image()).src = pingUrl;
            }, 1000);
        }
    },

    _getClientId: function() {
        return this._clientId;
    },

    enableHttps: function() {
        this._https = true;
    },

    disableHttps: function() {
        this._https = false;
    },

    _getHttps: function() {
        return this._https;
    },

    _getDomain: function() {
        return this._domain;
    },

    _checkClientId: function() {
        if (!this._getClientId()) {
            throw new Error('ClientId is mandatory (refer to documentation).');
        }
    },

    JSONP: function(options) {

        var callback, done, head, params, script;
        options = options ? options : {};
        params = {
              data: options.data || {},
              error: options.error || L.Util.falseFn,
              success: options.success || L.Util.falseFn,
              url: options.url || ''
        };
        if (params.url.length === 0) {
            throw new Error('MissingUrl');
        }
        done = false;

        callback = params.data[options.callback_name || 'callback'] = 'jsonp_' + random_string(15);
        window[callback] = function(data) {
            params.success(data);
            // return delete window[callback];
            window[callback] = null;
        };

        script = window.document.createElement('script');
        script.src = params.url;
        script.src += params.url.indexOf('?' === -1) ? '?' : '&';
        script.src += object_to_uri(params.data);
        script.async = true;
        script.onerror = function() {
            return params.error('server_error');
        };
        script.onload = script.onreadystatechange = function() {
            if (!done && (!this.readyState || this.readyState === 'loaded' || this.readyState === 'complete')) {
                done = true;
                script.onload = script.onreadystatechange = null;
                if (script && script.parentNode) {
                    return script.parentNode.removeChild(script);
                }
            }
        };
        head = head || window.document.getElementsByTagName('head')[0];
        return head.appendChild(script);
    },

    /**
     * CORS request using XMLHttpRequest or XDomainRequest (basic implementation)
     *
     * @see https://developer.mozilla.org/en-US/docs/Web/API/XDomainRequest
     * @see http://msdn.microsoft.com/en-us/library/ie/cc288060(v=vs.85).aspx
     *
     * @param options.url {String}
     * @param options.data {Object}
     * @param options.success {Function}
     * @param options.error {Function}
     */
    cors: function (options) {
        var req;

        if (window.XDomainRequest) {
            req = new XDomainRequest();
            if (options.data) {
                req.open('GET', options.url + '?' + object_to_uri(options.data));
            } else {
                req.open('GET', options.url);
            }
            if (options.error) {
                req.onerror = options.error;
            }
            req.onload = function() {
                options.success(JSON.parse(req.responseText));
            };
            req.send();

        } else if(window.XMLHttpRequest) {
            req = new XMLHttpRequest();
            if (options.data) {
                req.open('GET', options.url + '?' + object_to_uri(options.data), true);
            } else {
                req.open('GET', options.url, true);
            }
            req.onerror = function () {
                if (options.error) {
                    options.error('server_error');
                }
            };
            req.onreadystatechange = function() {
                if (req.readyState === 4) {
                    if (req.status >= 200 && req.status < 400) {
                        options.success(JSON.parse(req.responseText));
                    } else if (req.status >= 400 && req.status < 500) {
                        options.error('no_result');
                    }
                }
            };
            req.send();
        } else {
            options.error('cors_not_supported');
        }
    }
};

L.Util.debounce = function (func, timeout) {
    var timeoutID;
    timeout = timeout || 300;
    return function () {
        var scope = this , args = arguments;
        clearTimeout( timeoutID );
        timeoutID = setTimeout( function () {
            func.apply( scope , Array.prototype.slice.call( args ) );
        }, timeout);
    };
};

var Attribution = L.Control.Attribution.extend({
    options: {
        scale: true,
        position: 'bottomleft',
        'prefix': '&copy; ' +
            '<a href="http://corporate.mappy.com/conditions-dutilisation/copyright/" title="Mappy" target="_blank">Mappy</a> '
    },

    _layers: [],

    onAdd: function (map) {

        map.on('layeradd', function(evt) {
            if (evt.layer instanceof L.Mappy.TileLayer) {
                this._layers.push(evt.layer);
                evt.layer.on('attributionsrefresh', this._refreshAttributions, this);
            }
        }, this);
        map.on('layerremove', function(evt) {
            for (var i = this._layers.length - 1; i >= 0; i--) {
                if (this._layers[i] === evt.layer) {
                    this._layers.splice(i, 1);
                    evt.layer.off('attributionsrefresh', this._refreshAttributions, this);
                    this._refreshAttributions();
                }
            }
        }, this);

        var container = L.Control.Attribution.prototype.onAdd.call(this, map);

        if (this.options.scale) {
            L.control.scale({ imperial: false, position: this.options.position }).addTo(map);
        }

        return container;
    },

    addTo: function (map) {
        this._map = map;

        var container = this._container = this.onAdd(map),
            pos = this.getPosition(),
            corner = map._controlCorners[pos];

        L.DomUtil.addClass(container, 'leaflet-control');

        corner.appendChild(container);

        return this;
    },

    /* Remove the option of removing the attributions */
    removeFrom: L.Util.falseFn, // 0.7
    remove: L.Util.falseFn, // 0.8

    clearAttributions: function() {
        this._attributions = {};
        this._update();
    },


    /**
     * add on map attributions of all layers (overlay/viewmode)
    **/
    _refreshAttributions: function() {
        this.clearAttributions();
        var attribs = [];
        for (var i = this._layers.length - 1; i >= 0; i--) {
            attribs = attribs.concat(this._layers[i].getAttributions());
        }

        for (var j = 0; j < attribs.length; j++) {
            this.addAttribution(attribs[j]);
        }
    }
});

L.Mappy.Control = L.Control.extend({
    options: {
        position: 'topright',
        singleToolBox: false
    },

    statics: {
        mainContainer: null
    },

    _buttons: {},
    _tooltips: {},

    addTo: function (map) {
        this._buttons = {};
        this._tooltips = {};
        L.Control.prototype.addTo.call(this, map);
        L.DomUtil.addClass(this._container, 'mappy-control');
        return this;
    },

    removeFrom: function (map) {
        var pos = this.getPosition(),
            corner = map._controlCorners[pos];

        if (!this.options.singleToolBox || (this.options.singleToolBox && L.Mappy.Control.mainContainer)) {
            corner.removeChild(this._container);
            this._container = null;
        }
        if (this.options.singleToolBox && L.Mappy.Control.mainContainer) {
            L.Mappy.Control.mainContainer = null;
        }
        this._map = null;

        if (this.onRemove) {
            this.onRemove(map);
        }

        return this;
    },

    _createContainer: function (className) {
        this._container = L.DomUtil.create('div', 'mappy-control-' + className);
        return this._container;
    },

    _getContainer: function (name) {
        if (!this.options.singleToolBox) {
            return L.DomUtil.create('div', 'mappy-control-' + name);
        }
        if (!L.Mappy.Control.mainContainer) {
            L.Mappy.Control.mainContainer = L.DomUtil.create('div', 'mappy-control-main');
        }
        return L.Mappy.Control.mainContainer;
    },

    _createBasicButton: function (html, className) {
        var btn = L.DomUtil.create('a', 'mappy-button mappy-button-' + className, this._container);
        btn.innerHTML = html || '';
        btn.href = '#';

        L.DomEvent
            .on(btn, 'mousedown', L.DomEvent.stop)
            .on(btn, 'dblclick', L.DomEvent.stop)
            .on(btn, 'click', L.DomEvent.stop);

        return btn;
    },

    _createButton: function (html, title, className, fn) {
        var btn = this._createBasicButton(html, className);
        this._initTooltip(title, className, btn);

        L.DomEvent
            .on(btn, 'click', fn, this)
            .on(btn, 'click', this._refocusOnMap, this);

        return btn;
    },

    _createSwitch: function (html, title, title2, className, fn, fn2, delay) {
        var btn = this._createBasicButton(html, className);
        this._initTooltip(title, className, btn, delay);

        L.DomEvent
            .on(btn, 'click', function (evt) {
                if (!L.DomUtil.hasClass(btn, 'mappy-button-active')) {
                    this.activateButton(btn);
                    this._updateTooltipContent(title2, className);
                    fn.call(this, evt);
                } else {
                    this.deactivateButton(btn);
                    this._updateTooltipContent(title, className);
                    fn2.call(this, evt);
                }
                this._refocusOnMap();
            }, this);

        return btn;
    },

    _initTooltip: function (title, className, btn, delay) {
        if (!title) {
            return;
        }

        if (L.Browser.touch) {
            L.DomEvent
                .on(btn, 'click', function () {
                    this._closeTooltips();
                    this._createTooltip(title, className, btn);

                    if (L.Mappy.layerTooltip) {
                        clearInterval(L.Mappy.layerTooltip);
                    }

                    L.Mappy.layerTooltip = setTimeout(L.bind(function() {
                        this._closeTooltip(className);
                    }, this), 2000);

                }, this);
        } else {
            L.DomEvent
                .on(btn, 'mouseenter', function () {
                    this._closeTooltips();
                    this._createTooltip(title, className, btn);
                }, this)
                .on(btn, 'mouseleave', function () {
                    this._closeTooltip(className, delay);
                }, this);
        }

    },

    _createTooltip: function(title, id, btn) {
        if (!this._tooltips[id]) {
            var tooltip = L.DomUtil.create('div', 'mappy-tooltip', this._container);
            if (typeof title == 'string') {
                tooltip.innerHTML = title;
            } else {
                tooltip.appendChild(title);
            }

            this._tooltips[id] = tooltip;
        } else {
            this._container.appendChild(this._tooltips[id]);
        }

        this._tooltips[id].style.top = btn.offsetTop + (btn.offsetHeight - this._tooltips[id].offsetHeight) / 2 + 'px';
    },

    _updateTooltipContent: function(title, id) {
        if (this._tooltips[id] && title) {
            if (typeof title == 'string') {
                this._tooltips[id].innerHTML = title;
            } else {
                this._tooltips[id].appendChild(title);
            }
        }
    },

    _closeTooltip: function(id, delay) {
        this._removing = setTimeout(L.bind(function() {
            if (this._tooltips[id].parentNode) {
                this._container.removeChild(this._tooltips[id]);
            }
        }, this), delay || 0);
    },

    _closeTooltips: function() {
        clearTimeout(this._removing);
        for (var tooltip in this._tooltips) {
            if (this._tooltips[tooltip].parentNode) {
                this._tooltips[tooltip].parentNode.removeChild(this._tooltips[tooltip]);
            }
        }
    },

    activateButton: function (button) {
        L.DomUtil.addClass(button, 'mappy-button-active');
        button.setAttribute('data-active', 1);
    },

    deactivateButton: function (button) {
        L.DomUtil.removeClass(button, 'mappy-button-active');
        button.setAttribute('data-active', 0);
    }
});

var Geolocation = L.Mappy.Control.extend({
    options: {
        geolocationMarker: false,
        locateOptions: {}
    },

    _defaultLocateOptions: {
        setView: true,
        maxZoom: 15,
        timeout: 5 * 1000,
        maximumAge: 30 * 1000
    },

    onAdd: function (map) {
        this.options.locateOptions = L.extend(this._defaultLocateOptions, this.options.locateOptions);
        this._container = L.DomUtil.create('div', 'mappy-control-geolocation');
        this._geolocButton = this._createButton('<span>?</span>', null, 'geolocation', this.activateGeolocation);
        map.on('locationfound', this.onGeolocationSuccess, this);
        map.on('locationerror', this.onGeolocationError, this);
        map.on('moveend zoomend', this.deactivate, this);

        return this._container;
    },

    activateGeolocation: function () {
        L.DomUtil.removeClass(this._geolocButton, 'geolocation-error');
        L.DomUtil.addClass(this._geolocButton, 'searching');
        this._map.fire('locationsearch');
        this._map.locate(this.options.locateOptions);
    },

    onRemove: function (map) {
        map.off('locationfound', this.onGeolocationSuccess, this);
    },

    onGeolocationSuccess: function (location) {
        L.DomUtil.removeClass(this._geolocButton, 'searching');
        this.activateButton(this._geolocButton);

        if (!this.options.geolocationMarker) {
            return;
        }

        if (!this.geolocationMarker) {
            var iconImg = 'marker_geolocation' + (L.Browser.retina ? '_x2' : '') + '.png';
            var icon = new L.DivIcon({
                className:   'geolocationMarker',
                iconSize:    [30, 30],
                iconAnchor:  [15, 15],
                html: '<img src="' + L.Mappy.getImgPath() + iconImg + '" />'
            });

            this.geolocationMarker = L.marker(location.latlng, {
                icon: icon
            }).addTo(this._map);
        } else {
            this.geolocationMarker.setLatLng(location.latlng);
        }
    },

    onGeolocationError: function (locationEvent) {
        L.DomUtil.removeClass(this._geolocButton, 'searching');
        L.DomUtil.addClass(this._geolocButton, 'geolocation-error');
        this._map.on('moveend zoomend', L.bind(function () {
            L.DomUtil.removeClass(this._geolocButton, 'geolocation-error');
        }, this));
    },

    deactivate: function() {
        this.deactivateButton(this._geolocButton);
    }
});

L.Control.Layers = L.Mappy.Control.extend({
    options: {
        autoZIndex: true,
        traffic: true,
        publicTransport: true,
        viewMode: true,
        trafficLegend: true
    },

    initialize: function (baseLayers, overlays, options) {
        L.setOptions(this, options);

        this._buttons = {};
        this.trafficLegend = null;
        this._layers = {};
        this._lastZIndex = 0;

        for (var i in baseLayers) {
            this._addLayer(baseLayers[i], i);
        }

        for (i in overlays) {
            this._addLayer(overlays[i], i, true);
        }
    },

    _addLayer: function (layer, name, overlay) {
        var id = L.stamp(layer);

        this._layers[id] = {
            layer: layer,
            name: name,
            overlay: overlay
        };

        if (this.options.autoZIndex && layer.setZIndex) {
            this._lastZIndex++;
            layer.setZIndex(this._lastZIndex);
        }
    },

    onAdd: function (map) {
        this._container = this._getContainer('layers');

        this._initLayout();
        this._initActiveButton();

        map
            .on('layeradd', this._onLayerChange, this)
            .on('layerremove', this._onLayerChange, this);


        return this._container;
    },

    onRemove: function (map) {
        map
            .off('layeradd', this._onLayerChange, this)
            .off('layerremove', this._onLayerChange, this);
        if (this.options.trafficLegend) {
            this.trafficLegend.hide();
        }
    },

    _initLayout: function () {
        if (this.options.traffic !== false) {
            this._buttons.traffic = this._createSwitch('<span>\'</span>', L.Mappy.getText('layers.traffic'), null, 'traffic', this.activateTraffic, this.deactivateTraffic);
        }

        if (this.options.publicTransport !== false) {
            this._buttons.publicTransport = this._createSwitch('<span>7</span>', L.Mappy.getText('layers.transport'), null, 'transport', this.activatePublicTransport, this.deactivatePublicTransport);
        }

        if (this.options.trafficLegend) {
            this.trafficLegend = L.control.TrafficLegend();
        }

        if (this.options.viewMode !== false) {
            var tooltip = L.DomUtil.create('div'),
                span = L.DomUtil.create('span', null, tooltip),
                checkbox = this._createCheckbox(tooltip),
                label = L.DomUtil.create('label', null, tooltip);
            this._buttons.hybrid = checkbox;

            span.innerHTML = L.Mappy.getText('layers.photo');
            label.innerHTML = L.Mappy.getText('layers.hybrid');
            label.setAttribute('for', 'mappy-hybrid');

            this._buttons.aerial = this._createSwitch('<span>#</span>', tooltip, null, 'aerial', function () {
                this._map.setViewmode('photo');
            }, function () {
                this._map.setViewmode('standard');
                this._map.removeOverlay('hybrid');
            }, 500);

            L.DomEvent
                .on(tooltip, 'mouseenter', this._onAerialTooltipMouseEnter, this)
                .on(tooltip, 'mouseleave', this._onAerialTooltipMouseLeave, this)
                .on(checkbox, 'click', this._onAerialLabelClick, this);
        }
    },

    _initActiveButton: function () {
        var overlay = this._map.getTilelayer('overlay');
        if (overlay) {
            this._addOverlay(overlay.options.name);
        }
        this._updateViewMode(this._map.getTilelayer().options.name);
    },

    _onLayerChange: function (e) {
        var obj = this._layers[L.stamp(e.layer)];

        if (!obj) { return; }

        var type = obj.overlay ?
            (e.type === 'layeradd' ? 'overlayadd' : 'overlayremove') :
            (e.type === 'layeradd' ? 'baselayerchange' : null);

        if (obj.overlay) {
            if (type === 'overlayremove') {
                this._removeOverlay(obj.name);
            } else {
                this._addOverlay(obj.name);
            }
        } else {
            this._updateViewMode(obj.name);
        }

        if (type) {
            this._map.fire(type, obj);
        }
    },

    _removeOverlay: function (name) {
        if (name === 'traffic') {
            if (this._buttons.traffic) {
                this.deactivateButton(this._buttons.traffic);
            }
            if (this.trafficLegend) {
                this.trafficLegend.hide();
            }
        } else if (name === 'public_transport' && this._buttons.publicTransport) {
            this.deactivateButton(this._buttons.publicTransport);
        } else if (name === 'hybrid' && this._buttons.hybrid) {
            this._buttons.hybrid.checked = false;
        }
    },

    _addOverlay: function (name) {
        if (name === 'traffic') {
            if (this._buttons.traffic) {
                this.activateButton(this._buttons.traffic);
            }
            if (this.trafficLegend) {
                this.trafficLegend.show(this._map);
            }
        } else if (name === 'public_transport' && this._buttons.publicTransport) {
            this.activateButton(this._buttons.publicTransport);
        }

        if (this._buttons.hybrid && name === 'hybrid') {
            this._buttons.hybrid.checked = true;
        }
    },

    _updateViewMode: function (name) {
        if (!this._buttons.aerial) {
            return;
        }

        if (name === 'standard') {
            this.deactivateButton(this._buttons.aerial);
        } else {
            this.activateButton(this._buttons.aerial);
        }
    },

    activateTraffic: function (event) {
        this._map.setOverlay('traffic');
        if (this.trafficLegend) {
            this.trafficLegend.show(this._map);
        }
    },

    deactivateTraffic: function () {
        this._map.removeOverlay('traffic');
        if (this.trafficLegend) {
            this.trafficLegend.hide();
        }
    },

    activatePublicTransport: function () {
        this._map.setOverlay('public_transport');
    },

    deactivatePublicTransport: function () {
        this._map.removeOverlay('public_transport');
    },

    // IE7 bugs out if you create a checkbox dynamically, so you have to do it this hacky way
    _createCheckbox: function (parent) {
        var html = '<input type="checkbox" name="mappy-hybrid-checkbox" id="mappy-hybrid" />';

        var htmlFragment = document.createElement('div');
        htmlFragment.innerHTML = html;

        return parent.appendChild(htmlFragment.firstChild);
    },

    _onAerialTooltipMouseEnter: function (evt) {
        clearTimeout(this._removing);
    },

    _onAerialTooltipMouseLeave: function (evt) {
        this._closeTooltip('aerial', 500);
    },

    _onAerialLabelClick: function (evt) {
        if (this._buttons.hybrid && this._buttons.hybrid.checked) {
            if (this._map.getTilelayer().options.name !== 'photo') {
                this._map.setViewmode('photo');
            }
            this._map.setOverlay('hybrid');
        } else {
            this._map.removeOverlay('hybrid');
        }
    },

    getTrafficLegend: function () {
        return this.trafficLegend;
    }
});
var Legend = L.Control.extend({
    options: {
        position: 'bottomright'
    },

    initialize: function(options) {
        this.options = L.extend({}, this.options, options);
    },

    onAdd: function(map) {
        this._container = L.DomUtil.create('div', 'mappy-bike-legend');

        var left = L.DomUtil.create('div', 'mappy-bike-legend-col mappy-bike-legend-left', this._container);
        var right = L.DomUtil.create('div', 'mappy-bike-legend-col mappy-bike-legend-right', this._container);

        var bicyclePathBox = L.DomUtil.create('span', 'mappy-bike-legend-bicyclepath-box', left);

        var title = L.DomUtil.create('p', 'mappy-bike-legend-title', right);
        title.innerHTML = L.Mappy.getText('bikelegend.title');

        return this._container;
    }
});

var Logo = L.Control.extend({
    options: {
        position: 'topleft'
    },

    onAdd: function(map) {
        this._container = L.DomUtil.create('div', 'mappy-control-logo');
        var url = L.Mappy.getImgPath() + (L.Browser.retina ? 'api-logo-2x.png' : 'api-logo.png');
        this._container.style.backgroundImage = 'url("' + url + '")';
        return this._container;
    },

    /* Remove the option of removing the logo */
    removeFrom: L.Util.falseFn, // 0.7
    remove: L.Util.falseFn // 0.8
});
L.Mappy.TileLayer = L.TileLayer.extend({
    options: {
        minZoom: 0,
        maxZoom: 19,
        tileSize: 256,
        subdomains: '1234'
    },

    tileUrl: 'http{h}://map{s}.{domain}/map/1.0/slab/{m}/256/{z}/{x}/{y}',

    descrUrl: 'http{h}://map1.{domain}/map/1.0/multi-descr/{m}/256/{z}/{t}',

    descrInfos: {},

    attributions: [],
    layerItems: [],

    initialize: function (name, zIndex, options) {
        options = L.setOptions(this, L.extend({
            h: L.Mappy._getHttps() ? 's': '',
            m: name,
            name: name,
            zIndex: zIndex,
            domain: L.Mappy._getDomain()
        }, options));

        if (name !== 'photo' && options.detectRetina && L.Browser.retina) {
            this.options.m += '_hd';
        }

        // Cachebust URLs to prevent caching of traffic tiles
        if (name === 'traffic') {
            this.tileUrl += '?refresh={refresh}';
            options.refresh = {
                toString: function() {
                    return Math.random();
                }
            };
        }

        L.TileLayer.prototype.initialize.call(this, this.tileUrl, options);
    },

    onAdd: function(map) {
        L.TileLayer.prototype.onAdd.call(this, map);
        this.on('load', this._onLoad);

        if (this.options.name === 'traffic') {
            this.interval = window.setInterval(L.bind(this.redraw, this), 120000);
        }
    },

    onRemove: function(map) {
        L.TileLayer.prototype.onRemove.call(this, map);
        this.off('load', this._onLoad);

        if (this.options.name === 'traffic') {
            window.clearInterval(this.interval);
        }
    },

    getAttributions: function() {
        return this.attributions;
    },

    _onLoad: L.Util.debounce(function() {
        var bounds = this._map.getPixelBounds();
        var min = bounds.min.clone();
        var max = bounds.max.clone();

        // Retrieve tile x & y values.
        min = min.divideBy(this.options.tileSize)._floor();
        max = max.divideBy(this.options.tileSize)._floor();
        var tilesCoords = [];

        // We got it !
        for (var x = min.x; x <= max.x; x++) {
            for (var y = min.y; y <= max.y; y++) {
                tilesCoords.push(x + ',' + y);
            }
        }

        if (tilesCoords.length > 0) {
            this._requestDescr(tilesCoords);
        }
    }, 500),

    _refreshAttributions: function(tiles) {
        if (!this._map) {
            return;
        }
        this.attributions = [];
        this.layerItems = [];
        for (var i = 0; i < tiles.length; i++) {
            var key = this.options.m + '/' + this._map.getZoom() + '/' + tiles[i].replace(',', '/');
            if (this.descrInfos[key]) {
                for (var j = 0; j < this.descrInfos[key].copyrights.length; j++) {
                    this.attributions.push(this.descrInfos[key].copyrights[j].name);
                }
                this.layerItems = this.layerItems.concat(this.descrInfos[key].items);
            }
        }
        this.fire('attributionsrefresh');
    },

    _requestDescr: function(tiles) {

        // TODO cors

        var zoomLevel = this._map.getZoom();

        // Define tiles to request
        var reqTiles = [];
        for (var i = 0; i < tiles.length; i++) {
            var key = this.options.m + '/' + zoomLevel + '/' + tiles[i].replace(',', '/');
            if (!this.descrInfos[key]) {
                reqTiles.push(tiles[i]);
            }
        }

        if (reqTiles.length > 0) {
            var requestUrl = L.Util.template(this.descrUrl, L.extend({
                z: zoomLevel,
                t: reqTiles.join(';')
            }, this.options));

            // Request multi-descr
            L.Mappy.cors({
                url: requestUrl,
                success: L.bind(function(response) {
                    for (var i = 0; i < response.length; i++) {
                        this.descrInfos[this.options.m + '/' + response[i].sid] = response[i];
                    }
                    this._refreshAttributions(tiles);
                }, this)
            });
        }
        else {
            this._refreshAttributions(tiles);
        }
    }
});

var Tooltip = L.Class.extend({
    
    options: {
        width: 'auto',
        minWidth: '',
        maxWidth: '',
        showDelay: 500,
        hideDelay: 500,
        mouseOffset: L.point(0, 20),
        fadeAnimation: true,
        trackMouse: false
    },

    initialize: function (options) {
        L.setOptions(this, options);

        this._createTip();
    },

    _createTip: function () {
        this._map = this.options.map;

        if (!this._map) {
            throw new Error('No map configured for tooltip');
        }

        this._container = L.DomUtil.create('div', 'leaflet-tooltip');
        L.DomUtil.addClass(this._container, 'mappy-tooltip-transport');

        this._container.style.position = 'absolute';
        this._container.style.width = this._isNumeric(this.options.width) ? this.options.width + 'px' : this.options.width;
        this._container.style.minWidth = this._isNumeric(this.options.minWidth) ? this.options.minWidth + 'px' : this.options.minWidth;
        this._container.style.maxWidth = this._isNumeric(this.options.maxWidth) ? this.options.maxWidth + 'px' : this.options.maxWidth;

        if (this.options.html) {
            this.setHtml(this.options.html);
        }

        if (this.options.target) {
            this.setTarget(this.options.target);
        }

        this._map._tooltipContainer.appendChild(this._container);
    },
    
    isVisible: function () {
        return this._showing;
    },

    setTarget: function (target) {
        if (target._icon) {
            target = target._icon;
        }

        if (target === this._target) {
            return;
        }

        if (this._target) {
            this._unbindTarget(this._target);
        }

        this._bindTarget(target);

        this._target = target;
    },

    _bindTarget: function (target) {
        L.DomEvent
            .on(target, 'mouseover', this._onTargetMouseover, this)
            .on(target, 'mouseout', this._onTargetMouseout, this)
            .on(target, 'mousemove', this._onTargetMousemove, this);
    },

    _unbindTarget: function (target) {
        L.DomEvent
            .off(target, 'mouseover', this._onTargetMouseover, this)
            .off(target, 'mouseout', this._onTargetMouseout, this)
            .off(target, 'mousemove', this._onTargetMousemove, this);
    },

    setHtml: function (html) {
        if (typeof html === 'string') {
            this._container.innerHTML = html;   
        } else {
            while (this._container.hasChildNodes()) {
                this._container.removeChild(this._container.firstChild);
            }
            this._container.appendChild(this._content);         
        }
        
        this._sizeChanged = true;
    },

    setPosition: function (point) {
        var mapSize = this._map.getSize(),
            container = this._container,
            containerSize = this._getElementSize(this._container);

        point = point.add(this.options.mouseOffset);
        
        if (point.x + containerSize.x > mapSize.x) {
            container.style.left = 'auto';
            container.style.right = (mapSize.x - point.x) + 'px';
        } else {
            container.style.left = point.x + 'px';
            container.style.right = 'auto';
        }
        
        if (point.y + containerSize.y > mapSize.y) {
            container.style.top = 'auto';
            container.style.bottom = (mapSize.y - point.y + 2*(this.options.mouseOffset.y)) + 'px';
        } else {
            container.style.top = point.y + 'px';
            container.style.bottom = 'auto';
        }
    },

    remove: function () {
        this._container.parentNode.removeChild(this._container);
        delete this._container;

        if (this._target) {
            this._unbindTarget(this._target);
        }
    },

    show: function (point, html) {
        if (Tooltip.activeTip && Tooltip.activeTip != this) {
            Tooltip.activeTip._hide();
        }
        Tooltip.activeTip = this;     
        
        if (html) {
            this.setHtml(html);
        }

        this.setPosition(point);

        if (this.options.showDelay) {
            this._delay(this._show, this, this.options.hideDelay);
        } else {
            this._show();
        }
    },

    _show: function () {
        this._container.style.display = 'inline-block';     

        // Necessary to force re-calculation of the opacity value so transition will run correctly
        // if (window.getComputedStyle) {
            // window.getComputedStyle(this._container).opacity;
        // }

        L.DomUtil.addClass(this._container, 'leaflet-tooltip-fade');

        this._showing = true;           
    },

    hide: function () {
        if (this.options.hideDelay) {
            this._delay(this._hide, this, this.options.hideDelay);
        } else {
            this._hide();
        }       
    },

    _hide: function () {    
        if (this._timeout) {
            clearTimeout(this._timeout);
        }
        
        L.DomUtil.removeClass(this._container, 'leaflet-tooltip-fade');
        this._container.style.display = 'none';
        
        this._showing = false;

        if (Tooltip.activeTip === this) {
            delete Tooltip.activeTip;
        }
    },

    _delay: function (func, scope, delay) {
        var me = this;

        if (this._timeout) {
            clearTimeout(this._timeout);
        }
        this._timeout = setTimeout(function () {
            func.call(scope);
            delete me._timeout;
        }, delay);
    },

    _isNumeric: function (val) {
        return !isNaN(parseFloat(val)) && isFinite(val);
    },

    _getElementSize: function (el) {        
        var size = this._size;

        if (!size || this._sizeChanged) {
            size = {};

            el.style.left = '-999999px';
            el.style.right = 'auto';
            el.style.display = 'inline-block';
            
            size.x = el.offsetWidth;
            size.y = el.offsetHeight;
            
            el.style.left = 'auto';
            el.style.display = 'none';
            
            this._sizeChanged = false;
        }
        return size;
    },

    _onTargetMouseover: function (e) {
        var point = this._map.mouseEventToContainerPoint(e);

        this.show(point);
    },

    _onTargetMousemove: function (e) {
        L.DomEvent.stopPropagation(e);

        if (this.options.trackMouse) {
            var point = this._map.mouseEventToContainerPoint(e);        
            this.setPosition(point);
        }
    },

    _onTargetMouseout: function (e) {
        this.hide();
    }
});

L.Map.addInitHook(function () {
    this._tooltipContainer = L.DomUtil.create('div', 'leaflet-tooltip-container', this._container);
});

// Tooltip = function (options) {
//     return new Tooltip(options);
// };

(function () {
    var originalOnAdd = L.Marker.prototype.onAdd,
        originalOnRemove = L.Marker.prototype.onRemove,
        originalSetIcon = L.Marker.prototype.setIcon;

    L.Marker.include({

        getTooltip: function () {
            return this._tooltip;
        },
        
        onAdd: function (map) {
            originalOnAdd.call(this, map);

            if (this.options.tooltip) {
                this._tooltip = new Tooltip(L.extend(this.options.tooltip, {target: this, map: map}));
            }
        },

        onRemove: function (map) {
            if (this._tooltip) {
                this._tooltip.remove();
            }
            originalOnRemove.call(this, map);
        },

        setIcon: function (icon) {          
            originalSetIcon.call(this, icon);
            
            if (this._tooltip) {
                this._tooltip.setTarget(this._icon);
            }
        }
    }); 
})();
L.Control.TrafficLegend = L.Mappy.Control.extend({
    options: {
        position: 'bottomright'
    },

    _initLayout: function () {
        this._container = L.DomUtil.create('div', 'mappy-traffic-legend clearfix');

        var title = L.DomUtil.create('p', 'mappy-traffic-legend-title', this._container);

        var table = L.DomUtil.create('table', '', this._container);
        var tr = L.DomUtil.create('tr', '', table);
        L.DomUtil.create('td', 'orange', tr);
        L.DomUtil.create('td', 'red', tr);
        L.DomUtil.create('td', 'darkred', tr);
        L.DomUtil.create('td', 'black', tr);

        var leftText = L.DomUtil.create('p', 'left', this._container);
        leftText.innerHTML = L.Mappy.getText('trafficLegend.slow');

        var rightText = L.DomUtil.create('p', 'right', this._container);
        rightText.innerHTML = L.Mappy.getText('trafficLegend.blocked');

        return this._container;
    },

    show: function (map) {
        if (this._map) {
            this.refresh();

            if (this._container) {
                L.DomUtil.addClass(this._container.parentElement, 'leaflet-control-with-traffic');
            }
            return;
        }

        this.addTo(map);
        if (this._container) {
            L.DomUtil.addClass(this._container.parentElement, 'leaflet-control-with-traffic');
        }
    },

    hide: function () {
        if (this._container) {
            L.DomUtil.removeClass(this._container.parentElement, 'leaflet-control-with-traffic');
        }

        if (this._map) {
            this.removeFrom(this._map);
            this._map = null;
        }
    },

    onAdd: function () {
        if (!this._container) {
            this._initLayout();
        }
        this.refresh();
        this.interval = window.setInterval(L.bind(this.refresh, this), 120000);

        return this._container;
    },

    onRemove: function () {
        window.clearInterval(this.interval);
    },

    refresh: function () {
        var now = new Date();
        this._container.querySelector('p.mappy-traffic-legend-title').innerHTML = L.Util.template(L.Mappy.getText('trafficLegend.trafficAt'), {
            hours: now.getHours(),
            minutes: (now.getMinutes() < 10 ? '0' : '') + now.getMinutes()
        });
    }
});

L.control.TrafficLegend = function (options) {
    return new L.Control.TrafficLegend(options);
};


L.Control.Zoom = L.Mappy.Control.extend({
    options: {
        zoomSlider: false
    },

    onAdd: function (map) {
        this._container = this._getContainer('zoom');
        this._initLayout();

        return this._container;
    },

    onRemove: function (map) {
        map
            .off('zoomlevelschange', this._updateSize, this)
            .off('zoomend zoomlevelschange', this._updateKnobValue, this);
    },

    _initLayout: function () {
        var additionalClass = (this.options.zoomSlider) ? ' mappy-button-zoom-full' : '';
        this._buttons.zoomIn = this._createButton('<span>+</span>', null, 'zoom-in' + additionalClass, function(e) {
            this._map.zoomIn(e.shiftKey ? 3 : 1);
        });

        if (this.options.zoomSlider) {
            this._buttons.slider = this._createSlider();
            this._buttons.knob = new Knob(this._buttons.slider.knob, 8, 8);

            this._map.whenReady(this._initKnob, this)
                .whenReady(this._initEvents, this)
                .whenReady(this._updateSize, this)
                .whenReady(this._updateKnobValue, this);
        }

        this._buttons.zoomOut = this._createButton('<span>-</span>', null, 'zoom-out' + additionalClass, function(e) {
            this._map.zoomOut(e.shiftKey ? 3 : 1);
        });
    },

    _createSlider: function () {
        var ui = {};

        ui.bar  = L.DomUtil.create('div', 'mappy-slider', this._container);
        ui.wrap = L.DomUtil.create('div', 'mappy-slider-wrap', ui.bar);
        ui.body = L.DomUtil.create('div', 'mappy-slider-body', ui.wrap);
        ui.knob = L.DomUtil.create('div', 'mappy-slider-knob');

        L.DomEvent.disableClickPropagation(ui.bar);
        L.DomEvent.disableClickPropagation(ui.knob);

        return ui;
    },

    _initKnob: function () {
        this._buttons.knob.enable();
        this._buttons.slider.body.appendChild(this._buttons.slider.knob);
    },

    _initEvents: function () {
        this._map
            .on('zoomlevelschange', this._updateSize, this)
            .on('zoomend zoomlevelschange', this._updateKnobValue, this);

        L.DomEvent.on(this._buttons.slider.body, 'click', this._onSliderClick, this);

        this._buttons.knob.on('dragend', this._updateMapZoom, this);
    },

    _onSliderClick: function (e) {
        var first = (e.touches && e.touches.length === 1 ? e.touches[0] : e),
            y = L.DomEvent.getMousePosition(first, this._buttons.slider.body).y;

        this._buttons.knob.setPosition(y);
        this._updateMapZoom();
    },

    _zoomLevels: function () {
        var zoomLevels = this._map.getMaxZoom() - this._map.getMinZoom() + 1;
        return zoomLevels < Infinity ? zoomLevels : 0;
    },

    _toZoomLevel: function (value) {
        return value + this._map.getMinZoom();
    },

    _toValue: function (zoomLevel) {
        return zoomLevel - this._map.getMinZoom();
    },

    _updateSize: function () {
        var steps = this._zoomLevels();

        this._buttons.slider.body.style.height = 8 * steps + 'px';
        this._buttons.knob.setSteps(steps);
    },

    _updateMapZoom: function () {
        this._map.setZoom(this._toZoomLevel(this._buttons.knob.getValue()));
    },

    _updateKnobValue: function () {
        this._buttons.knob.setValue(this._toValue(this._map.getZoom()));
    }

});

var Knob = L.Draggable.extend({
    initialize: function (element, stepHeight, knobHeight) {
        L.Draggable.prototype.initialize.call(this, element, element);
        this._element = element;

        this._stepHeight = stepHeight;
        this._knobHeight = knobHeight;

        this.on('predrag', function () {
            this._newPos.x = 0;
            this._newPos.y = this._adjust(this._newPos.y);
        }, this);
    },

    _adjust: function (y) {
        var value = Math.round(this._toValue(y));
        value = Math.max(0, Math.min(this._maxValue, value));
        return this._toY(value);
    },

    // y = k*v + m
    _toY: function (value) {
        return this._k * value + this._m;
    },

    // v = (y - m) / k
    _toValue: function (y) {
        return (y - this._m) / this._k;
    },

    setSteps: function (steps) {
        var sliderHeight = steps * this._stepHeight;
        this._maxValue = steps - 1;

        // conversion parameters
        // the conversion is just a common linear function.
        this._k = -this._stepHeight;
        this._m = sliderHeight - (this._stepHeight + this._knobHeight) / 2;
    },

    setPosition: function (y) {
        L.DomUtil.setPosition(this._element,
            L.point(0, this._adjust(y)));
    },

    setValue: function (v) {
        this.setPosition(this._toY(v));
    },

    getValue: function () {
        return this._toValue(L.DomUtil.getPosition(this._element).y);
    }
});

L.Mappy.Map = L.Map.extend({

    /**
     * Map layers (overlay/viewmode) - traffic/public_transport
     * .viewmode (standard/photo/hybrid)
     * .overlay (traffic/public_transport)
     */
    _tileLayers: {},

    _tooltip: null,

    disabledActions: [],

    /**
     *
     * @param element
     * @param options
     */
    initialize: function(element, options) {
        options = options || {};

        if (options.clientId) {
            L.Mappy.setClientId(options.clientId);
        }
        L.Mappy._checkClientId();

        // Take zoomControl out of Leaflet map init to handle it our way
        var zoomControlOptions = (options.zoomControl !== undefined) ? options.zoomControl : true;
        options.zoomControl = false;

        // Take attributionControl out of Leaflet map init to handle it our way
        var attributionControlOptions = (options.attributionControl !== undefined) ? options.attributionControl : {};
        options.attributionControl = false;

        // Limit y-drag to map size
        options.maxBounds = options.maxBounds || L.latLngBounds(L.latLng(-90, -100000), L.latLng(90, 100000));
        options.worldCopyJump = (options.worldCopyJump !== undefined) ? options.worldCopyJump : true;

        // Limit zoom animation to 3 levels
        options.zoomAnimationThreshold = (options.zoomAnimationThreshold !== undefined) ? options.zoomAnimationThreshold : 3;

        this.baseLayers = {
            'standard': new L.Mappy.TileLayer('standard', 1, options.tileLayerOptions),
            'photo': new L.Mappy.TileLayer('photo', 1, options.tileLayerOptions)
        };

        var publicTransportLayerOptions = {
            minZoom: (options.tileLayerOptions && options.tileLayerOptions.minZoom > 6) ? options.tileLayerOptions.minZoom : 6
        };
        this.overlays = {
            'public_transport': new L.Mappy.TileLayer('public_transport', 2, L.extend({}, options.tileLayerOptions, publicTransportLayerOptions)),
            'traffic': new L.Mappy.TileLayer('traffic', 2, options.tileLayerOptions),
            'hybrid': new L.Mappy.TileLayer('hybrid', 2, options.tileLayerOptions)
        };

        L.Map.prototype.initialize.call(this, element, options);

        this.attributionControl = new Attribution(attributionControlOptions).addTo(this);

        if (options.logoControl !== false) {
            this.logoControl = new Logo(options.logoControl || {}).addTo(this);
        }

        if (! options.viewmode || !this.baseLayers[options.viewmode]) {
            this.addLayer(this.baseLayers.standard);
        } else {
            this.addLayer(this.baseLayers[options.viewmode]);
        }

        if (zoomControlOptions !== false) {
            this.zoomControl = L.control.zoom(zoomControlOptions || {}).addTo(this);
        }

        if (options.layersControl === undefined || options.layersControl) {
            this.layersControl = L.control.layers(this.baseLayers, this.overlays, options.layersControl || {}).addTo(this);
        }

        if (options.geolocationControl && ('geolocation' in navigator)) {
            this.geolocationControl = new Geolocation(options.geolocationControl).addTo(this);
        }

        if (options.legendControl) {
            this.legendControl = new Legend(options.legendControl).addTo(this);
        }

        if (options.tooltip !== false) {
            this.manageTooltip();
        }
    },

    manageTooltip: function () {
        this._tooltip = new Tooltip({
            map: this,
            showDelay: 0,
            hideDelay: 0
        });

        // bind events
        this.on('mousemove', this._handleMousemove);

        // hide background items' tooltip on drag/zoom
        this.on('move', this._tooltip.hide, this._tooltip);
        this.on('zoomstart', this._tooltip.hide, this._tooltip);

        if (!L.Browser.touch) {
            L.DomUtil.addClass(this._container, 'no-touch');
        }
    },

    /**
     * Sets the background map layer (viewmode). Available viewmodes : standard, photo & hybrid.
     *
     * @param {string} name
     */
    setViewmode: function(name) {
        name = name || 'standard';
        this._setTileLayer(this.baseLayers, name);
        if (this.baseLayers[name]) {
            this.fire('viewmode-' + name, this.baseLayers[name]);
        }
    },

    /**
     * Sets the overlay map layer. Available overlays : traffic & public_transport.
     * If nothing is specified, remove the current overlay.
     *
     * @param name
     */
    setOverlay: function(name) {
        this._setTileLayer(this.overlays, name);
        this.fire('overlay-' + (name ? name : 'disabled'), (this.overlays[name] ? this.overlays[name] : null));
    },

    /**
     * Sets the overlay map layer. Available overlays : traffic & public_transport.
     * If nothing is specified, remove the current overlay.
     *
     * @param name
     */
    removeOverlay: function(name) {
        this.removeLayer(this.overlays[name]);
        this.fire('overlay-disabled', (this.overlays[name] ? this.overlays[name] : null));
    },

    /**
     * Disable user interactions
     */
    disableInteractions: function() {
        var actions = ['dragging', 'touchZoom', 'scrollWheelZoom', 'doubleClickZoom', 'boxZoom', 'keyboard'];
        this.disabledActions = [];

        for (var i = 0; i < actions.length; i++) {
            if (this[actions[i]] && this[actions[i]].enabled()) {
                this.disabledActions.push(actions[i]);
                this[actions[i]].disable();
            }
        }
    },

    /**
     * Enable previously disabled interactions
     */
    enableInteractions: function() {
        for (var i = 0; i < this.disabledActions.length; i++) {
            this[this.disabledActions[i]].enable();
        }
    },

    /**
     * Returns the active Tilelayer of specified type
     *
     * @param type
     */
    getTilelayer: function(type) {
        var layers = type === "overlay" ? this.overlays : this.baseLayers;
        for (var layerName in layers) {
            if (this.hasLayer(layers[layerName])) {
                return layers[layerName];
            }
        }
        return null;
    },

    /**
     * Sets a map layer (overlay/viewmode) on map
     *
     * @param layer     type of layer to create (viewmode, overlay)
     * @param name      name of the viewmode
    **/
    _setTileLayer: function(layers, name) {
        if (name === 'hybrid') {
            this.addLayer(layers[name]);
        } else {
            // remove all layers except for hybrid (but remove hybrid if name is undefined)
            for (var layerName in layers) {
                if (this.hasLayer(layers[layerName]) && (layerName !== 'hybrid' || !name )) {
                    this.removeLayer(layers[layerName]);
                }
            }
            if (layers[name]) {
                this.addLayer(layers[name]);
            }
        }
        return this;
    },

    /**
     * handle actions depending on cursor position (bind on mousemove event)
     *
     * @param event     "mousemove" leaflet event
    **/
    _handleMousemove: function(event) {

        var items = this.getTilelayer();
        if (!items) {
            return;
        }
        items = items.layerItems;
        var overlay = this.getTilelayer('overlay');
        if (overlay) {
            items = items.concat(overlay.layerItems);
        }

        var i = 0,
            found = false;

        while (i < items.length && !found) {
            var box = items[i].box;
            if (box.minx < event.latlng.lng && event.latlng.lng < box.maxx &&
                box.miny < event.latlng.lat && event.latlng.lat < box.maxy) {

                if (!this._tooltip.isVisible()) {
                    this._showItemTooltip(items[i]);
                }
                found = true;
            }
            i++;
        }

        if (!found) {
            this._tooltip.hide();
        }
    },

    _showItemTooltip: function(item) {
        var baseUrl = 'http' + (L.Mappy._getHttps() ? 's': '') + '://logotc.' + L.Mappy._getDomain() + '/pictos/web/desktop/';

        var transportLabels = {
            'M' :   'metro',
            'S' :   'rer',
            'T' :   'train',
            'TY' :  'tram'
        };

        var lines = [];
        var itemLine = item.properties.description.line;
        itemLine = itemLine instanceof Array ? itemLine : [itemLine]; // Thx Lbx...
        for (var j = 0; j < itemLine.length; j++) {
            if (!lines[itemLine[j].type]) {
                lines[itemLine[j].type] = [];
            }
            lines[itemLine[j].type].push(itemLine[j].num);
        }

        var description = '';

        for (var type in lines) {
            if (lines.hasOwnProperty(type)) {
                var lineType = (transportLabels[type] || type);
                var icons = ['<img src="' + baseUrl + 'modes/' + lineType + '.png" />'];

                for (var line in lines[type]) {
                    if (lines[type].hasOwnProperty(line)) {
                        var lineName = (lineType === 'tram') ? 't' + lines[type][line].toLowerCase() : lines[type][line].toLowerCase();
                        icons.push('<img src="' + baseUrl + 'lines/stif_' + lineType + '_' + lineName + '.png" />');
                    }
                }

                description += '</p><p>' + icons.join('');
            }
        }

        var popupPosition = this.latLngToContainerPoint(L.latLng((item.box.maxy + item.box.miny)/2, (item.box.maxx + item.box.minx)/2));
        this._tooltip.show(popupPosition, '<div><p><span>' + item.properties.description.label + '</span>' + description + '</p></div>');
    },

    addLegendControl: function(options) {
        options = options || this.options.legendControl || {};

        if (this.legendControl) return;

        this.legendControl = new Legend(options.legendControl).addTo(this);
    },

    removeLegendControl: function() {
        if (this.legendControl) {
            this.legendControl.remove();
            this.legendControl = null;
        }
    }

});

L.Handler.MarkerDrag.prototype._onDrag = function () {
    var marker  = this._marker,
        shadow  = marker._shadow,
        iconPos = L.DomUtil.getPosition(marker._icon),
        topLeft = marker._map.containerPointToLayerPoint([0, 0]),
        bottomRight = marker._map.containerPointToLayerPoint(marker._map.getSize());

    // Limit marker position to map bounds
    var newPos = new L.Point(
        Math.max(topLeft.x + 5, Math.min(iconPos.x, bottomRight.x - 5)),
        Math.min(bottomRight.y - 5, Math.max(iconPos.y, topLeft.y + 15))
    );
    L.DomUtil.setPosition(marker._icon, newPos);

    var latlng = marker._map.layerPointToLatLng(newPos);

    // update shadow position
    if (shadow) {
        L.DomUtil.setPosition(shadow, newPos);
    }

    marker._latlng = latlng;

    marker
        .fire('move', {latlng: latlng})
        .fire('drag');
};

L.Mappy.Route = L.FeatureGroup.extend({

    initialize: function(routes, routeId, alternative) {
        L.FeatureGroup.prototype.initialize.call(this);

        this.polylineStepIdx = [];
        this._markers = [];
        var actions = routes.route[routeId || 0].actions.action;
        for (var i = 0; i < actions.length; i++) {
            if (actions[i].type === 'waypoint') {
                this._createStepMarker(L.latLng(actions[i].y, actions[i].x), 'step');
                this.polylineStepIdx.push(parseInt(actions[i]['polyline-index'], 10));
            }
        }

        this.colorDictionary = routes['color-dictionary'].color;
        if (!this.colorDictionary.length) {
            this.colorDictionary = [ this.colorDictionary ];
        }
        this.alternative = !!alternative;
        this.route = routes.route[routeId || 0]['polyline-definition'];
        this.points = L.polyline(this.route.polyline).getLatLngs();
        this._createShapes();
        this._createStepMarker(this.points[0], 'start');
        this._createStepMarker(this.points[this.points.length-1], 'end');
    },

    getMarkers: function () {
        return this._markers;
    },

    _createShapes: function() {
        
        var roadSection, 
            bikeFacility, 
            type,
            roadSections = this.route['road-section-collection']['road-sections'];

        // Create shape shadow
        L.polyline(this.points, {
            color: '#FFF',
            weight: 9,
            opacity: 0.58
        }).addTo(this);

        if (roadSections instanceof Array) {
            for (var i = 0; i < roadSections.length; i++) {
                if (roadSections[i].type === 'bike-facility') {
                    bikeFacility = roadSections[i]['road-section'].split(' ');
                } else {
                    roadSection = roadSections[i]['road-section'].split(' ');
                }
            }
        } else {
            roadSection = roadSections['road-section'].split(' ');
        }

        var sections = this._parseSection(roadSection);

        for (var j = 0; j < sections.length; j++) {
            L.polyline(sections[j].polyline, {
                color: sections[j].color,
                opacity: 0.58,
                weight: 5
            }).addTo(this);

            var found = false;
            for (var k = 0; k < this.polylineStepIdx.length; k++) {
                if (this.polylineStepIdx[k] === sections[j].sliceEnd) {
                    found = true;
                }
            }

            if (sections[j].sliceEnd < this.points.length-1 && !found) {
                L.marker(this.points[sections[j].sliceEnd], {
                    icon: new L.DivIcon({
                        className:   'mappy-connexion-marker',
                        iconSize:    [10, 10],
                        iconAnchor:  [5, 5]
                    })
                }).addTo(this);
            }
        }

        this._applyBikeFacility(bikeFacility);
    },

    _parseSection: function(section) {
        var sections = [];
        var sliceStart = 0, sliceEnd, color, type, colorValue;
        for (var i = 0; i < section.length; i += 2) {
            sliceEnd = parseInt(section[i + 1], 10);
            color = "";

            for (var colorIdx in this.colorDictionary) {
                if (this.colorDictionary[colorIdx].value === section[i] &&
                    (this.alternative || this.colorDictionary[colorIdx].type.indexOf("-alt") === -1)) {

                    color = this.colorDictionary[colorIdx].color;
                    type = this.colorDictionary[colorIdx].type;
                    colorValue = this.colorDictionary[colorIdx].value;
                }
            }

            var sectionPolyline = this.points.slice(sliceStart, sliceEnd + 1);
            sliceStart = sliceEnd;

            sections.push({
                polyline: sectionPolyline,
                color: color,
                type: type,
                sliceEnd: sliceEnd,
                colorValue: colorValue
            });
        }

        return sections;
    },

    _applyBikeFacility: function(bikeFacility) {
        if (bikeFacility) {
            var sections = this._parseSection(bikeFacility);

            for (var i = 0; i < sections.length; i++) {
                if (sections[i].type === 'bike-facility' && sections[i].colorValue !== '0') {
                    L.polyline(sections[i].polyline, {
                        color: sections[i].color,
                        weight: 7,
                        opacity: 1
                    }).addTo(this);
                }
            }
        }
    },

    _createStepMarker: function(latLng, type) {
        var src = L.Mappy.getImgPath() + 'roadbook-step-' + type + '.png';
        var marker = L.marker(latLng, {
            icon: new L.DivIcon({
                className:   'iti-marker mappy-route-' + type,
                html: '<img src="' + src + '" />',
                iconSize:    [40, 50],
                iconAnchor:  [20, 50]
            })
        });
        marker.addTo(this);
        this._markers.push({
            'type': type,
            'marker': marker,
            'latLng': latLng
        });
    }
});

L.Mappy.route = function (routes, routeId, alternative) {
    return new L.Mappy.Route(routes, routeId, alternative);
};

L.Mappy.Vehicles = [
    'comcar',
    'midcar',
    'sedcar',
    'luxcar',
    'caravancar',
    '2-lt3.5',
    '2-lt12',
    '2-gt12',
    '2-gt12a',
    '3-lt3.5',
    '3-lt12',
    '3-gt12',
    '3-gt12a',
    'mot',
    'van',
    '2-coa',
    '3-coa',
    'ped',
    'bik',
    'bike',
    'pub_tp'
];

var routeErrors = [
    'nostart',
    'noend',
    'nostartprovider',
    'noendprovider',
    'nostarttoendprovider',
    'nonetworkconnexion'
];

L.Mappy.Services = {

    localeParameters: {
        fr_FR: {
            favoriteCountry:    250,
            language:           'fre'
        },
        en_GB: {
            favoriteCountry:    826,
            language:           'eng'
        },
        fr_BE: {
            favoriteCountry:    56,
            language:           'fre'
        },
        nl_BE: {
            favoriteCountry:    56,
            language:           'dut'
        }
    },

    _decodePolyline : function(encoded)
    {
        var len = encoded.length,
            tmp = [],
            decoded = [],
            index = 0,
            lat = 0,
            lng = 0,
            decodeNextPoint = function() {
                var b,
                    shift = 0,
                    result = 0;
                do {
                    //get binary encodings
                    b = encoded.charCodeAt(index++) - 63;
                    //binary shift
                    result |= (b & 0x1f) << shift;
                    //move to next chunk
                    shift += 5;
                } while (b >= 0x20); //see if another binary value
                //if negative, flip bits & return
                return (((result & 1) > 0) ? ~(result >> 1) : (result >> 1));
            };

        while (index < len) {
            tmp.push(decodeNextPoint());
        }

        for(var i = 0; i < tmp.length ; i +=2)
        {
            lat += tmp[i] * 1e-5;
            lng += tmp[i+1] * 1e-5;
            decoded.push([lat, lng]);
        }

        return decoded;
    },

    geocode: function(query, successCallback, failureCallback) {
        L.Mappy._checkClientId();

        if (query instanceof L.LatLng) {
            query = query.lat + ',' + query.lng;
        } else if (query instanceof Array) {
            query = query[0] + ',' + query[1];
        }

        L.Mappy.JSONP({
            url: 'http' + (L.Mappy._getHttps() ? 's': '') + '://axe.' + L.Mappy._getDomain() + '/1v1/loc/get.aspx',
            data: {
                'opt.format': 'json',
                'opt.namedPlaceSearch': 1,
                'opt.interactive': 1,
                'opt.language':         this.localeParameters[L.Mappy.getLocale()].language,
                'opt.favoriteCountry':  this.localeParameters[L.Mappy.getLocale()].favoriteCountry,
                'opt.xmlOutput': '3v0',
                'auth': L.Mappy._getToken(),
                'fullAddress': query
            },
            success: function(response) {
                if (!response.kml.Document) {
                    return failureCallback('no_result');
                }
                var placemark = response.kml.Document.Placemark;
                placemark = placemark instanceof Array ? placemark : [placemark];
                if (placemark.length === 0) {
                    return failureCallback('no_result');
                }
                successCallback(placemark);
            },
            error: failureCallback
        });
    },

    route: function(steps) {
        L.Mappy._checkClientId();

        var options = {}, successCallback, failureCallback;

        if (typeof arguments[1] === 'function') {
            successCallback = arguments[1];
            failureCallback = arguments[2];
        } else {
            for (var name in arguments[1]) {
                options[name] = arguments[1][name];
            }
            successCallback = arguments[2];
            failureCallback = arguments[3];
        }

        this._requestRoute(steps, options, successCallback, failureCallback);
    },

    _requestRoute: function(steps, options, successCallback, failureCallback) {
        var step, elt;
        var data = L.extend(options || {}, {
            'clientid': L.Mappy._getClientId(),
            'wt':       'json',
            'from':     '',
            'to':       '',
            'vehicle':  options.vehicle || 'comcar'
        });

        var requestPath = options.vehicle === 'pub_tp' || options.vehicle === 'bike' ? 'route' : 'route_vehicle';

        data = this._getNormalizedVehicleOptions(data, requestPath === 'route');

        for (var i = 0; i < steps.length; i++) {
            step = L.latLng(steps[i]);

            if (i === 0) {
                elt = 'from';
            } else if (i === (steps.length - 1)) {
                elt = 'to';
            } else {
                elt = 'stops';
                data.stops = data.stops || '';
            }

            data[elt] += (elt === 'stops' && i > 1 ? ';' : '') + step.lng + ',' + step.lat;
        }

        this._request(
            'cors',
            'http' + (L.Mappy._getHttps() ? 's': '') + '://routemm.' + L.Mappy._getDomain() + '/' + requestPath + '/roadbook',
            data,
            successCallback,
            failureCallback
        );
    },

    _request: function(type, url, data, successCallback, failureCallback) {
        L.Mappy[type]({
            url: url, 
            data: data, 
            success: L.bind(function(results) {
                // Force array
                if (! results.routes.route.length) {
                    results.routes.route = [results.routes.route];
                }

                if (results.routes.route.length === 0) {
                    return failureCallback('no_result');
                }

                if (! results.routes.route[0].actions) {
                    var error = routeErrors.indexOf(results.routes.route[0].stats.error) > -1 ?
                        results.routes.route[0].stats.error : 'no_result';
                    return failureCallback(error);
                }

                // Decode polylines
                for (var i = 0; i < results.routes.route.length; i++) {
                    var polyline = this._decodePolyline(results.routes.route[i]['polyline-definition'].polyline);
                    results.routes.route[i]['polyline-definition'].polyline = polyline;
                }

                successCallback(results);
            }, this),
            error: failureCallback
        });
    },

    _getNormalizedVehicleOptions: function(data, isRouteService) {

        var routeParams, params = {};
        
        routeParams = {
            'from': 'from',
            'to': 'to',
            'stops': 'stops',
            'clientid': 'clientid',
            'infotraffic': 'rb.infotraffic',
            'date': 'rt.date',
            'time': 'rt.time',
            'sens': 'rt.sens',
            'notoll': 'rt.notoll',
            'cost': 'rt.cost',
            'lang': 'rb.lang',
            'axl': 'rb.axl',
            'gas': 'rb.gas',
            'nbroutes': 'rt.nbroutes',
            'cvn': 'rb.cvn',
            'wt': 'wt',
            'vehicle': 'rb.veh',
            'criteria': 'criteria'
        };

        if(isRouteService) {
            routeParams = {
                'from': 'from',
                'to': 'to',
                'stops': 'stops',
                'clientid': 'clientid',
                'date': 'date',
                'time': 'time',
                'sens': 'sens',
                'nbroutes': 'nbroutes',
                'wt': 'wt',
                'vehicle': 'transport_mode',
                'criteria': 'criteria'
            };
        }

        for (var name in data) {
            if (routeParams[name]) {
                params[routeParams[name]] = data[name];
            }
        }

        // Essieux
        if (/^2-/.test(data.vehicle)) {
            params['rb.veh'] = data.vehicle.replace(/^(2-)/, '');
            params['rb.axl'] = '2ax';
        }

        if (/^3-/.test(data.vehicle)) {
            params['rb.veh'] = data.vehicle.replace(/^(3-)/, '');
            params['rb.axl'] = '3ax';
        }

        // Caravan car
        if (data.vehicle === 'caravancar') {
            params['rb.veh'] = 'midcar';
            params['rb.cvn'] = '1';
        }

        return params;
    }
};


}(window.L));