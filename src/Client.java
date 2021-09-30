

import com.google.gson.Gson;
import java.nio.charset.Charset;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Base64;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.util.EntityUtils;



public class Client {
	
	multipartForm forms = new multipartForm();
	
	private String session;
	
	form dt = new form();// generates random number for boundary
	BigInteger boundary = dt.formData();
	
	String fileName = "1.png";
	String path = "C:\\Users\\user\\eclipse-workspace\\PrototypeAstro\\src\\"+fileName;
	
    writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
            true);
	
	public void send_request(String tail,String args) throws IOException {
		

		URL url = new URL ("http://nova.astrometry.net/api/"+tail);
		System.out.println("Uploading to "+url);
		
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		if (getSession() != null) {
		
		con.setRequestProperty("Content-Type","multipart/form-data ; boundary="+boundary.toString());
		}
		
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		OutputStream os = con.getOutputStream();
		byte[] input = args.getBytes("utf-8");
		os.write(input, 0, input.length);
		
		
		try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(con.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    
				    System.out.println(response);
			   
				    JsonElement jsonObjectAlt = JsonParser.parseString(response.toString());
					JsonObject jsonObject = jsonObjectAlt.getAsJsonObject();
					
					//System.out.println(jsonObject.get("session"));
	
					if (tail !="upload") {
					String session = jsonObject.get("session").toString();
					setSession(session);
					
				}
					
					System.out.println("end of request");		
		}
	}
	
	public void login() throws IOException {
		
		
		HashMap<String,String> json=new HashMap<String,String>();
		
		json.put("apikey","xewpjipcpovwiiql");	
		Gson gson = new Gson();
		
		String jsonInputString = gson.toJson(json);
		String urlencoded = URLEncoder.encode(jsonInputString,StandardCharsets.UTF_8);
	
		String urlParameters  = "request-json="+urlencoded;
		
		send_request("login",urlParameters);
	}
	
	public void upload() {

		try {
			
			//FileBody bin = new FileBody(new File(path));
			//String completed_form = forms.generate_form(bin,fileName,getSession(),boundary);
			
			MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
			reqEntity.addPart("json",bin);
			HttpEntity entity = reqEntity.build();
			System.out.println(entity);
			
//		    try {
//		        FileWriter myWriter = new FileWriter("C:\\Users\\user\\eclipse-workspace\\PrototypeAstro\\src\\data.txt");
//		        myWriter.write(fileData);
//		        myWriter.close();
//		      } catch (IOException e) {
//		        System.out.println("An error occurred.");
//		        e.printStackTrace();
//		      }
			
		   send_request("upload",entity.toString());
		    
		} catch (IOException e) {
			System.out.println(e);
		}	
	}

	
	
	  // Getter
	  public String getSession() {
	    return session;
	  }

	  // Setter
	public void setSession(String sess) {
			
		this.session = sess;
	}
	
}
