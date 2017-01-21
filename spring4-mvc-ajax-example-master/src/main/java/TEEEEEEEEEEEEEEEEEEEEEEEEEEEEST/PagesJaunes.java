package TEEEEEEEEEEEEEEEEEEEEEEEEEEEEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class PagesJaunes {

	public static void main(String[] args) throws IOException {

		ArrayList<Location> listTotal = new ArrayList<Location>();
		listTotal = finalList("rennes");
	}

	/**
	 * Return the complete arraylist of all the researches
	 * 
	 * @param town
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Location> finalList(String town) throws IOException {
		ArrayList<Location> listFood = new ArrayList<Location>();
		listFood = searchLocation(town, "nourriture");

		ArrayList<Location> listGarage = new ArrayList<Location>();
		listFood = searchLocation(town, "garage");

		ArrayList<Location> listSleep = new ArrayList<Location>();
		listFood = searchLocation(town, "dormir");

		ArrayList<Location> listBank = new ArrayList<Location>();
		listFood = searchLocation(town, "banque");

		ArrayList<Location> listTotal = new ArrayList<Location>();
		listTotal.addAll(listBank);
		listTotal.addAll(listFood);
		listTotal.addAll(listSleep);
		listTotal.addAll(listGarage);
		return listTotal;
	}

	/**
	 * Request the yellowPage for geolocalisation information (Ex: Restaurant in
	 * Rennes)
	 * 
	 * @param url
	 * @param what
	 * @param where
	 * @return response of yelloPage webSite
	 * @throws IOException
	 */
	public static String get(String what, String where, int page) throws IOException {
		String id = "d140a6f6";
		String key = "26452728b034374bccb462e880bfb0e5";
		String url = "https://api.apipagesjaunes.fr/pros/find?what=" + what + "&where=" + where + "&page=" + page
				+ "&app_id=" + id + "&app_key=" + key;

		String source = "";
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			source += inputLine;
		in.close();
		return source;
	}

	/**
	 * Take the yellowPage response and parse it into a JSON object.
	 * 
	 * @param yellowPageResponse
	 * @return JSON object++-+
	 */
	public static JSONObject jsonParser(String yellowPageResponse) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();

		try {
			json = (JSONObject) parser.parse(yellowPageResponse);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * put the information of the name, the latitude and the longitude into an
	 * arrayList of location object
	 * 
	 * @param list
	 * @param json
	 * @param page
	 * @param type
	 */
	public static void searchInformationJSON(ArrayList<Location> list, JSONObject json, int page, String type) {

		JSONObject context = (JSONObject) JSONValue.parse(json.get("context").toString());
		JSONObject result = (JSONObject) JSONValue.parse(context.get("results").toString());

		int nbResultat = (Integer.parseInt(result.get("last_listing").toString()));

		JSONObject search_results = (JSONObject) JSONValue.parse(json.get("search_results").toString());
		JSONArray listings = (JSONArray) JSONValue.parse(search_results.get("listings").toString());

		boolean canAdd = true;

		for (int i = 0; i < nbResultat / page; i++) {
			try {
				Location location = new Location();
				JSONObject listing_object = (JSONObject) JSONValue.parse(listings.get(i).toString());
				JSONArray inscription = (JSONArray) JSONValue.parse(listing_object.get("inscriptions").toString());
				JSONObject inscription_object = (JSONObject) JSONValue.parse(inscription.get(0).toString());
				String latitude = (inscription_object.get("latitude").toString());
				String longitude = (inscription_object.get("longitude").toString());
				String name = listing_object.get("merchant_name").toString();

				for (int j = 0; j < list.size(); j++) {
					if (!name.equals(list.get(j).getName())) {
						canAdd = true;
					} else {
						canAdd = false;
						break;
					}
				}

				location.setLatitude(Float.parseFloat(latitude));
				location.setLongitude(Float.parseFloat(longitude));
				location.setName(name);
				location.setType(type);

				if (canAdd)
					list.add(location);
			} catch (NullPointerException e) {
				System.out.println("Informations sur le garage non fournies");
				continue;
			}
		}
	}

	/**
	 * return an arraylist of all the locations
	 * 
	 * @param town
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Location> searchLocation(String town, String type) throws IOException {
		// Recovering the result of the request and transformation into a JSON
		// object
		JSONObject json1 = new JSONObject();
		String retourDeLaPage1 = get(type, town, 1);
		json1 = jsonParser(retourDeLaPage1);
		
		ArrayList<Location> location_list = new ArrayList<Location>();
		searchInformationJSON(location_list, json1, 1, type);

		JSONObject context = (JSONObject) JSONValue.parse(json1.get("context").toString());
		JSONObject pages = (JSONObject) JSONValue.parse(context.get("pages").toString());

		int nbPage = (Integer.parseInt(pages.get("page_count").toString()));

		for (int i = 2; i < nbPage; i++) {
			JSONObject json = new JSONObject();
			String retourDeLaPage2 = get(type, town, i);
			json = jsonParser(retourDeLaPage2);
			searchInformationJSON(location_list, json, i, type);

		}

		for (int i = 0; i < location_list.size(); i++) {
			System.out.println(location_list.get(i).toString());
		}
		return location_list;

	}
}