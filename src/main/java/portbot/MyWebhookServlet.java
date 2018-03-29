package portbot;

import ai.api.model.Fulfillment;

//@WebServlet("/webhook")
public class MyWebhookServlet extends AIWebhookServlet {
  @Override
  protected void doWebhook(AIWebhookRequest input, Fulfillment output) {
    output.setSpeech("You said: " + input.getResult().getFulfillment().getSpeech() + input.getResult().getAction().toLowerCase());
  }
}
