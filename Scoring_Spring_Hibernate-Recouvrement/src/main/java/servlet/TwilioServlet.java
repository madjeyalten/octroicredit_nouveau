package servlet;

import javax.faces.context.FacesContext;

import java.io.IOException;
import java.util.HashMap;

import com.otv.bean.AlertBean;
//import com.twilio.sdk.verbs.Dial;
//import com.twilio.sdk.verbs.Gather;
//import com.twilio.sdk.verbs.TwiMLResponse;
//import com.twilio.sdk.verbs.TwiMLException;
//import com.twilio.sdk.verbs.Say;
//import com.twilio.sdk.verbs.Play;

public class TwilioServlet {
//	public void service(HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		
//		//début
//		Dial dial=null;
//		dial = new Dial("+33973596648");
//		String digits = request.getParameter("Digits");
//		TwiMLResponse twiml = new TwiMLResponse();
//		// Check if the user pressed "1" on their phone
//		if (digits != null && digits.equals("1")) {
//			// Connect 310 555 1212 to the incoming caller.
//			try {
//				dial = new Dial("+33973596648");
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			// If the above dial failed, say an error message.
//			Say say = new Say(
//					"The call failed, or the remote party hung up. Goodbye.");
//			try {
//				twiml.append(dial);
//				twiml.append(say);
//			} catch (TwiMLException e) {
//				e.printStackTrace();
//			}
//		}
//		//fin
//		// Create a dict of people we know.
//		HashMap<String, String> callers = new HashMap<String, String>();
//		callers.put("+14158675309", "Curious George");
//		callers.put("+14158675310", "Boots");
//		callers.put("+14158675311", "Virgil");
//		String fromNumber = request.getParameter("From");
//		String knownCaller = callers.get(fromNumber);
//		String message;
//		
//		//récupération paramtres :
//		Long montantRetard=AlertBean.staticAmount;
//
//	
//		// Create a TwiML response and add our friendly message.
//		//TwiMLResponse twiml = new TwiMLResponse();
//		// Play an MP3 for incoming callers.
//		Play play = new Play("https://187d3381.ngrok.com/JavaServerFaces/jm.mp3");
//		 Gather gather = new Gather();
//		 gather.setTimeout(10000);
//		 gather.setAction("/twilio");
//		 gather.setNumDigits(1);
//		 gather.setMethod("POST");
//		 Say sayInGather = new Say("Bonjour, vous nous devez la somme de" +montantRetard+"euros.");
//		 sayInGather.setLanguage("FR");
//		 sayInGather.setVoice("woman");
//		 try {
//		 gather.append(sayInGather);
//		 //twiml.append(play);
//		 twiml.append(gather);
//		 twiml.append(dial);
//		 } catch (TwiMLException e) {
//		 e.printStackTrace();
//		 }
//		response.setContentType("application/xml");
//		response.getWriter().print(twiml.toXML());
//	}
}