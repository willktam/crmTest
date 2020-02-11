package org.skyve.bus.map.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.joshworks.restclient.http.HttpResponse;
import io.joshworks.restclient.http.Json;
import io.joshworks.restclient.http.RestClient;

public class PhotonGeocodeServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotonGeocodeServiceImpl.class);
	private static final String PHOTON_BASE_URL = "https://photon.komoot.de/api/";

	private RestClient client;

	public Point geocode(String address){

		HttpResponse<Json> result = getClient().get()
				.queryString("q", address)
				.queryString("limit", 1)
				.asJson();

		if (result != null) {
			LOGGER.debug("Status: {}", result.getStatus());
			LOGGER.debug("Body: {}", result.body().toString());

			JSONObject root = result.body().getObject();
			if (root != null && root.has("features")) {
				JSONArray features = root.getJSONArray("features");
				if (features != null && features.length() >= 1) {
					JSONObject feature = (JSONObject) features.get(0);
					if (feature != null && feature.has("geometry")) {
						JSONObject geometry = (JSONObject) feature.get("geometry");
						if ("Point".equals(geometry.getString("type"))) {
							JSONArray coords = geometry.getJSONArray("coordinates");
							if (coords != null) {
								GeometryFactory gf = new GeometryFactory();
								LOGGER.info("Found coordinates lng:{}, lat:{}", coords.get(0), coords.get(1));

								// add the result to the cache
								return gf.createPoint(new Coordinate((double) coords.get(0), (double) coords.get(1)));
							}
						}
					}
				}
			}
		}

		return null;
	}

	private RestClient getClient() {
		if(client == null) {
			client = RestClient.builder().baseUrl(PHOTON_BASE_URL).build();
		}
		
		return client;
	}
}
