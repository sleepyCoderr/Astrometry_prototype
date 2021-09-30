import com.google.gson.annotations.SerializedName;

public class Data {

	
	@SerializedName("request-json")
	private API_Key apikey;
	
	public Data(API_Key apikey){

		this.apikey=apikey;
	};



}
