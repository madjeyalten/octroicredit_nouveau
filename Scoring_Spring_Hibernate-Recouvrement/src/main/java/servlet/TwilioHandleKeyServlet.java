package servlet;

import java.io.IOException;
import java.util.HashMap;


public class TwilioHandleKeyServlet{
//	public void service(HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		String digits = request.getParameter("Digits");
//		TwiMLResponse twiml = new TwiMLResponse();
//		// Check if the user pressed "1" on their phone
//		if (digits != null && digits.equals("1")) {
//			// Connect 310 555 1212 to the incoming caller.
//			Dial dial=null;
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
//		} else {
//			// If they didn't press 1, redirect them to the TwilioServlet
//			response.sendRedirect("/twiml");
//			return;
//		}
//		response.setContentType("application/xml");
//		response.getWriter().print(twiml.toXML());
//	}
}
