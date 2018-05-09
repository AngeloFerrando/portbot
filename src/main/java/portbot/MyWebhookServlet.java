package portbot;

import java.net.MalformedURLException;
import java.net.URL;

import ai.api.model.Fulfillment;

//@WebServlet("/webhook")
public class MyWebhookServlet extends AIWebhookServlet {
  @Override
  protected void doWebhook(AIWebhookRequest input, Fulfillment output) {
	  System.out.println("Webhook call received");
	  try {
		URL url = URLServiceConverter.convert(input);
		output.setSpeech(url.toString());
		//output.setSpeech("You said: " + input.getResult().getFulfillment().getSpeech() + input.getResult().getAction().toLowerCase());
	  } catch (MalformedURLException e) {
		  output.setSpeech("Error on the server, contact the administrator");
	  }
  }
}
