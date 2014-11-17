package adityash.instagramimageviewer;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {
	String url;
	int width;
	int height;
	
	public Image() {
	}
	
	public Image(JSONObject json) {
		try {
			this.url = json.getString("url");
			this.width = json.getInt("width");
			this.height = json.getInt("height");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
