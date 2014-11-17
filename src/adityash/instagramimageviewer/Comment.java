package adityash.instagramimageviewer;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
	String created_time;
	String text;
	User from;
	
	public Comment() {
	}
	
	public Comment(JSONObject json) {
		try {
			this.created_time = json.getString("created_time");
			this.text = json.getString("text");
			this.from = new User(json.getJSONObject("from"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
