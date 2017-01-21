package PortHub.testPagesJaunesAPI;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;



public class PagesJaunes {

	public static void main(String[] args) throws IOException {
		JSONObject json = new JSONObject();

		// Recovering the result of the request and transformation into a JSON
		// object
		String retourDeLaPage = get("garage", "rennes");
		json = jsonParser(retourDeLaPage);

		JSONObject context = (JSONObject) JSONValue.parse(json.get("context").toString());
		JSONObject result = (JSONObject) JSONValue.parse(context.get("results").toString());
		
		int nbResultat = (Integer.parseInt(result.get("last_listing").toString()));
		
		System.out.println(nbResultat);
		JSONObject search_results = (JSONObject) JSONValue.parse(json.get("search_results").toString());
		JSONArray listings = (JSONArray) JSONValue.parse(search_results.get("listings").toString());

		//tring listing = bite.get("listing_id").toString();
		//JSONObject inscription =(JSONObject) JSONValue.parse(bite.get(0).toString());

		boolean canAdd = true;
		ArrayList<Location> listzer= new ArrayList<Location>();		
			for(int i = 0; i < nbResultat ;i++){
				try
				{
					Location location = new Location();
					JSONObject listing_object = (JSONObject) JSONValue.parse(listings.get(i).toString());
					JSONArray inscription = (JSONArray) JSONValue.parse(listing_object.get("inscriptions").toString());
					JSONObject inscription_object = (JSONObject) JSONValue.parse(inscription.get(0).toString());
					String latitude = (inscription_object.get("latitude").toString());
					String longitude = (inscription_object.get("longitude").toString());
					String name = listing_object.get("merchant_name").toString();
					System.out.println(name);
					
					System.out.println("latitude = " + latitude);
					System.out.println("longitude = " +longitude+"\n");
					
					for(int j = 0 ; j<listzer.size() ; j++)
					{
						if(!name.equals(listzer.get(j).getName()))
						{
							canAdd= true;
						}
						else
						{
							canAdd = false;
							break;
						}
					}
					
					location.setLatitude(Float.parseFloat(latitude));
					location.setLongitude(Float.parseFloat(longitude));
					location.setName(name);
					
					if(canAdd)
						listzer.add(location);
				}
				catch(NullPointerException e)
				{
					System.out.println("garage a bite");
					continue;
				}
			}
			
			Set<Location> hs = new HashSet<>();
			hs.addAll(listzer);
			listzer.clear();
			listzer.addAll(hs);
	      
	      for(int i = 0; i < listzer.size() ;i++){
	    	  System.out.println(listzer.get(i).toString());
	      }
		//((Object) json).getJSONObject("context").getJSONObject("results");
		
		
		
		
		System.out.println("48.088414");
		    
		    
		// Export in a JSON file
		 FileWriter file = new
		 FileWriter("C:\\Users\\cleme\\Desktop\\file1.json");
		 file.write(json.toString());
		 System.out.println("Successfully Copied JSON Object to File...");
		 System.out.println("\nJSON Object: " + json);
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
	public static String get(String what, String where) throws IOException {
		String id = "d140a6f6";
		String key = "26452728b034374bccb462e880bfb0e5";
		String url = "https://api.apipagesjaunes.fr/pros/find?what=" + what + "&where=" + where + "&app_id=" + id
				+ "&app_key=" + key;

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
}