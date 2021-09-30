import com.google.gson.annotations.SerializedName;

public class API_Key {
	
	@SerializedName("apikey")
	private String apikey;
	
	public API_Key(String apikey){
		this.apikey=apikey;
	}

}
