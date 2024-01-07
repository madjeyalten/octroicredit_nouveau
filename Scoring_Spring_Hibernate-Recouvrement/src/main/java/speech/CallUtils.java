package speech;

import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.*;
import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import okhttp3.*;





public class CallUtils {
	/* Find your sid and token at twilio.com/user/account */
	public static final String ACCOUNT_SID = "AC2734f8c93b278c35889f6fdb3a7212ff";
	public static final String AUTH_TOKEN = "aa64f5650fb5b30ca65a4c7e669e2e19";

	/**
	 * @param args
	 * @throws TwilioRestException 
	 */
	
//	 public static void main(String[] args) {
//		 
//		 NexmoClient client = new NexmoClient.Builder()
//		  .apiKey("eee7bb0e")
//		  .apiSecret("jHM9cgRZfswsjKBg")
//		  .build();
//
//		String messageText = "Hello from Nexmokkk";
//		TextMessage message = new TextMessage("Nexmo", "33695544758", messageText);
//
//		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
//
//		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
//		    System.out.println(responseMessage);
//		}


//		String messageText = "Hello from Nexmo";
//		TextMessage message = new TextMessage("Nexmo", "33695544758", messageText);
//
//		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
//
//		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
//		    System.out.println(responseMessage);
//		}
//	    }
	 
//	public static void main(String[] args) throws TwilioRestException {
//		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//		Account account = client.getAccount();
//		// Use the Twilio-provided site for the TwiML response.
//		String Url="http://twimlets.com/message";
//		//Url = Url + "?Message%5B0%5D=mememememememememememe";
//		//Url="http://twimlets.com/message?Message[0]=https://187d3381.ngrok.com/JavaServerFaces";
//		SmsFactory messageFactory=client.getAccount().getSmsFactory();
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("Body", "test");
//		params.put("To", "+33695544758");
//		params.put("From", "33975188961");
//		params.put("Url", Url);
//		// Create an instance of the CallFactory class.
//		CallFactory callFactory = account.getCallFactory();
//
//		// FIXME décommenter ce bout de code
//		//Call call = callFactory.create(params);
//		sendSMS();
//	}
	
	
//	public static void main(String[] args) throws Exception {
//        String phoneNumber = "0022891985311";
//        String sender = "FINGICIEL";
//        String message = URLEncoder.encode("Envoi de SMS de la part de Fingiciel STOP au 36180", "UTF-8");
//        String login = "fingiciel";
//        String apiKey = "e33e234b63cff4f";
//        String smsData = "<DATA><MESSAGE><![CDATA[["+message+"]]></MESSAGE><TPOA>"+sender+"</TPOA><SMS><MOBILEPHONE>"+phoneNumber+"</MOBILEPHONE></SMS></DATA>";
//        String url = "https://api.allmysms.com/http/9.0/sendSms/sendSms/?login=" + login + "&apiKey=" + apiKey + "&smsData=" + smsData;
//        // Send GET request
//                URL client = new URL(url);
//                URLConnection conn = client.openConnection();
//                InputStream responseBody = conn.getInputStream();
//                // Convert in XML document
//
//                byte[] contents = new byte[1024];
//
//                int bytesRead=0;
//                String strFileContents = null;
//                while( (bytesRead = responseBody.read(contents)) != -1){
//                   strFileContents = new String(contents, 0, bytesRead);
//                }
//
//                responseBody.close();
//                System.out.println(strFileContents);
//                System.out.println("toto");
//    }
	        

			
		// System.out.println("toto");
		 
	        //Twilio.init("madjey.mensah@gmail.com", "Octobre20102010", ACCOUNT_SID);

//	        Message message = Message
//	                .creator(new PhoneNumber("+33760926533"), // to
//	                        new PhoneNumber("+1 844 976 0694 "), // from
//	                        "SMS de test effectué par Fingiciel")
//	                .create();
//
//	        System.out.println(message.getSid());
	        
//	        try {
//				callClient("00695544758");
//			} catch (TwilioRestException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	   // }
	 
	 
	 

	   
	
	public static void callClient(String numero) 
	{
//		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//		Account account = client.getAccount();
//		// Use the Twilio-provided site for the TwiML response.
//		String Url="http://twimlets.com/message";
//		//Url = Url + "?Message%5B0%5D=mememememememememememe";
//		//Url="http://twimlets.com/message?Message[0]=https://187d3381.ngrok.com/JavaServerFaces";
//		SmsFactory messageFactory=client.getAccount().getSmsFactory();
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("Body", "test");
//		params.put("To", "+33695544758");
//		params.put("From", "33975188961");
//		params.put("Url", "https://187d3381.ngrok.com/JavaServerFaces/twilio");
//		// Create an instance of the CallFactory class.
//		CallFactory callFactory = account.getCallFactory();
//
//		// FIXME décommenter ce bout de code
//		Call call = callFactory.create(params);
		//sendSMS();
	}
	
	 private void sendPost() throws Exception {
		 OkHttpClient httpClient = new OkHttpClient();

	        // form parameters
	        RequestBody formBody = new FormBody.Builder()
	                .add("username", "abc")
	                .add("password", "123")
	                .add("custom", "secret")
	                .build();

	        Request request = new Request.Builder()
	                .url("https://httpbin.org/post")
	                .addHeader("User-Agent", "OkHttp Bot")
	                .post(formBody)
	                .build();

	        try (Response response = httpClient.newCall(request).execute()) {

	            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

	            // Get response body
	            System.out.println(response.body().string());
	        }

	    }
	
	 public static void main(String[] args) throws Exception {
	{
		OkHttpClient httpClient = new OkHttpClient();
		
        		String basic = Credentials.basic("adam", "nadia");
        		
        		
        		Request request = new Request.Builder()
                .url("https://fingiciel.ngrok.io/fineract-provider/api/v1/clients")
                .addHeader("Fineract-Platform-TenantId", "default")  // add request headers
                .addHeader("Authorization", basic)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }
		// Use your account SID and authentication token instead
		// of the placeholders shown here.
//		String accountSID = "AC2734f8c93b278c35889f6fdb3a7212ff";
//		String authToken = "aa64f5650fb5b30ca65a4c7e669e2e19";
//
//		// Create an instance of the Twilio client.
//		TwilioRestClient client;
//		client = new TwilioRestClient(accountSID, authToken);
//
//		// Retrieve the account, used later to create an instance of the SmsFactory.
//		Account account = client.getAccount();
//
//		// Send an SMS message.
//		// Place the call From, To and Body values into a hash map. 
//		HashMap<String, String> smsParams = new HashMap<String, String>();
//		//smsParams.put("From", "33975188961"); 
//		smsParams.put("From", "33975188961"); 
//		smsParams.put("To", "33695544758");   // Use your own value for the second parameter.
//		smsParams.put("Body", "This is my SMS message.");
//
//		// Create an instance of the SmsFactory class.
//		SmsFactory smsFactory = account.getSmsFactory();
//
//		// Send the message.
//		Sms sms = smsFactory.create(smsParams);
	}
	
	
	

	 }
}
