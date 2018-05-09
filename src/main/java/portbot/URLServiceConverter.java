package portbot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.JsonElement;

import portbot.AIWebhookServlet.AIWebhookRequest;

public class URLServiceConverter {
	
	private static String SERVICE = "http://<the-service-to-call>";
	
	public static URL convert(AIWebhookRequest input) throws MalformedURLException {
		switch(input.getResult().getMetadata().getIntentName()){
			//case "open_boat": return openBoat(input);
			default: 
				return genericUrl(input);
				//return new URL(SERVICE + "/default");
		}
	}
	
	private static URL genericUrl(AIWebhookRequest input) throws MalformedURLException {
		HashMap<String, JsonElement> params = input.getResult().getParameters();
		String result = SERVICE + "/" + input.getResult().getMetadata().getIntentName();
		boolean first = true;
		for(String param : params.keySet()) {
			JsonElement value = params.get(param);
			result += (first ? "?" : "&") + param + "=" + value; 
			if(first) first = false;
		}
		return new URL(result);
	}
	
	private static URL openBoat(AIWebhookRequest input) throws MalformedURLException {
		String boatName = input.getResult().getStringParameter("boat_name");
		Date date = input.getResult().getDateParameter("date");
		return new URL(SERVICE + "?boat_name=" + boatName + "&date=" + date.getTime());
	}
}
