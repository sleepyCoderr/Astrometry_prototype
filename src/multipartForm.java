
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.awt.image.BufferedImage;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

import java.util.Arrays;
import java.io.File;
import java.nio.charset.Charset;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.io.FileWriter;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

	
	public class multipartForm {

	public String generate_form(File file, String fileName,String session,BigInteger boundaryNum) {
	
	Gson gson = new Gson();
	

	LinkedHashMap<String,String> key_arg=new LinkedHashMap<String,String>();
	
	key_arg.put("allow_commercial_use","d");
	key_arg.put("allow_modifications","d");
	key_arg.put("publicly_visible", "y");
	key_arg.put("session", session.replace("\"",""));
	
	String jsonString = gson.toJson(key_arg);
//	
	String boundary=String.format("===============%s==",boundaryNum);

	String dataPre="--"+boundary+"\n"+"Content-Type:text/plain  \r\n"+
	"MIME-Version: 1.0\r\n"+"Content-disposition: form-data; name=\"request-json\"\r\n"+"\r\n"
	+ jsonString +"\n"+"--"+boundary+"\n"+"Content-Type: application/octet-stream\r\n" +
	"MIME-Version: 1.0\r\n"+
	"Content-disposition: form-data; name=\"file\"; filename="+fileName+"\r\n"+"\r\n";
	

	String dataPost="\n"+"--"+boundary+"--\n";
	
	String dataPreEncoded = URLEncoder.encode(dataPre,StandardCharsets.UTF_8);
	
	String dataPostEncoded= URLEncoder.encode(dataPost,StandardCharsets.UTF_8);
	
	String fullEncodedString = dataPreEncoded +file+dataPostEncoded;
	
	System.out.println(dataPre+dataPost);
	
	return fullEncodedString;

	}
	

	
	
}
