package PortHub.testPagesJaunesAPI;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PagesJaunes {

	public static void main(String[] args) throws IOException {
		JSONObject json = new JSONObject();

		// Recovering the result of the request and transformation into a JSON
		// object
		String retourDeLaPage = get("plombier", "rennes");
		json = jsonParser(retourDeLaPage);

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
				+ "&app_key=" + key + "&return_urls=true";

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