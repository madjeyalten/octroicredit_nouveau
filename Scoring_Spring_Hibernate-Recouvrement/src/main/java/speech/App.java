package speech;

import java.util.*;

import com.twilio.http.HttpMethod;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.sdk.*;



import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.*;
import com.twilio.sdk.client.TwilioRestClient;
import com.twilio.type.PhoneNumber;


public class App {
	
	/* Find your sid and token at twilio.com/user/account */
	public static final String ACCOUNT_SID = "AC2734f8c93b278c35889f6fdb3a7212ff";
	public static final String AUTH_TOKEN = "aa64f5650fb5b30ca65a4c7e669e2e19";
	

    
    public static void main(String[]args) {  
 
     TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN, "https://api.twilio.com:8443");   
      
      // Build the parameters   
      List params = new ArrayList();   params.add(new BasicNameValuePair("To", "+33695454758"));   
      params.add(new BasicNameValuePair("From", "+14158141829"));   
      params.add(new BasicNameValuePair("Body", "Your system is ready for the upcoming change to the Twilio API's SSL certificate. No further action is needed."));
      

      Message message = Message
      .creator(new PhoneNumber("+33695454758"), // to
              new PhoneNumber("+14158141829"), // from
              "Where's Wallace?")
      .create();
      
//      MessageFactory messageFactory = client.getAccount().getMessageFactory();   
//      Message message = messageFactory.create(params);
//      System.out.println(message.getSid()); 
      
      
     }

}
