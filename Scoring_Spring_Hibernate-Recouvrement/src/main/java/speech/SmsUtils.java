package speech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;




//import javax.swing.plaf.SliderUI;
//
//import t2s.son.LecteurTexte;

public class SmsUtils {
	
	public static int solde(String apikey) {
        try {
			URL url = new URL("http://www.envoyersms.biz/api/v1/?method=credit&apikey=" + URLEncoder.encode(apikey, "ISO-8859-1"));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer results = new StringBuffer();
            String solde = br.readLine();
			br.close();
			return Integer.parseInt(solde);
		}
		catch(Exception exception) {
			return 0;
        }
	}
	
	public static void send(String apikey, String number, String msg, String sender, String msg_id) {
        try {
			URL url = new URL("http://www.envoyersms.biz/api/v1/?method=send");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			String postBody = "apikey=" + URLEncoder.encode(apikey, "ISO-8859-1") + "&" +
							  "number=" + URLEncoder.encode(number, "ISO-8859-1") + "&" +
							  "message=" + URLEncoder.encode(msg, "ISO-8859-1") + "&" +
							  "expediteur=" + URLEncoder.encode(sender, "ISO-8859-1") + "&" +
							  "msg_id=" + URLEncoder.encode(msg_id, "ISO-8859-1");

			conn.setRequestMethod("POST");

			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
            wr.write(postBody);
            wr.flush();
            wr.close();

			conn.connect();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer results = new StringBuffer();

            String oneline;
            while ( (oneline = br.readLine()) != null) {
              results.append(oneline);
            }

            br.close();
            System.out.println(URLDecoder.decode(results.toString(), "ISO-8859-1"));
        }
        catch(Exception exception) {
			System.out.println(exception.getMessage() + exception.getCause());
        }
    }
        
    public static void sendSms(String texte, String numero) throws IOException {
    	
//    	Voice voice = new Voice("jmarcmensah@gmail.com", "Cerruti39");
//    	voice. login();
//    	voice.sendSMS("33695544758", texte);
//    	voice.sendSMS("33751375289", texte);
//    	
//    	
//        String originNumber = "33695544758";
//        
//        String destinationNumber = "33751375289";
//
//        voice.call(originNumber, destinationNumber, "mobile");

        
    	String apikey = "ix3219z68n5hr8f5a858w5mnlgs28zcn";
    	//FIXME : décommenter ce bout de code
    	// Envoyer un SMS
		send(apikey, "33695544758", "Votre dossier est prêt. Merci de venir faire le retrait.", "Prefecture", "");
    	System.out.println("Sms envoyé");
    	System.out.println("Sms envoyé "+numero+" "+texte);
    }
    
public static String sendSMSNew(String email, String apikey, String subtype, String senderlabel, String message, String recipient) throws IOException{
        
        String result="";
        String urlParameters = "email=" + email+"&apikey="+apikey+"&message[type]=SMS&message[subtype]="+subtype+"&message[senderlabel]="+senderlabel+"&message[content]="+message+"&message[recipients]="+recipient;
                
         URL url = new URL("http://www.smsenvoi.com/httpapi/sendsms/");
         URLConnection conn = url.openConnection();

         conn.setDoOutput(true);

         OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

         writer.write(urlParameters);
         writer.flush();

         String line;
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

         while ((line = reader.readLine()) != null) {
             System.out.println(line);
             result=result.concat(line).concat("n");
         }
         writer.close();
         
         reader.close();    
         
         
      return result; 
     }
    

    public static  void sendSMSNouveau(String numero,Long montant)
    {
    	//FIXME : pour cette partie :
    	//numero="0022892324265";
    	 System.out.println("Envoi SMS");
    	 try{
    		 //FIXME : décommenter l'envoi réel du SMS
    		 System.out.println("Envoi SMS");
             sendSMSNew("jm_mensah@yahoo.fr","4889N93PT2RH4WW3LRZZ","premium","Recouvrement","Service de Recouvrement Nous vous rappelons que vous nous devez la somme de "+montant+" FCFA. Contactez nous pour régulariser votre impayé.", numero);
          
          } catch(IOException e){
              
              e.printStackTrace();
              
    }
    }
    
    public static void main (String [] arg){
    	new SmsUtils().sendSMSNouveau("0682526049",new Long("10"));
    }

}
