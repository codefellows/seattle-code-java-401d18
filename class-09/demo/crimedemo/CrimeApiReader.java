import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CrimeApiReader {

  public static void main(String[] args) {
    try {
      URL url = new URL("http://nflarrest.com/api/v1/crime");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      System.out.println("about to send request");
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      System.out.println("request came back");
      String inputLine;
      //StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        System.out.println(inputLine);
      }
      in.close();
    } catch (IOException e) {
      // something went wrong with the internet
      System.out.println("The Internet is down, but please don't commit crimes");
      System.out.println(e);
    }
  }
}
