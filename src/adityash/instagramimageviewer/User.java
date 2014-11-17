package adityash.instagramimageviewer;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	String username;
	String profile_picture;
	String full_name;
	
	public User() {
	}
	
	public User(JSONObject json) {
		try {
			this.username = json.getString("username");
			this.profile_picture = json.getString("profile_picture");
			this.full_name = json.getString("full_name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
