package adityash.instagramimageviewer;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entry {
	String id;
	
	Image image;
	User user;
	String caption;
	String created_time;
	
	int commentCount;
	ArrayList<Comment> comments;
	
	int likeCount;
	ArrayList<User> likes;
	
	ArrayList<String> tags;
	
	public Entry() {
		this.comments = new ArrayList<Comment>();
		this.likes = new ArrayList<User>();
		this.tags = new ArrayList<String>();
	}
	
	public Entry(JSONObject json) {
		try {
			this.id = json.getString("id");
			this.image = new Image(json.getJSONObject("images").getJSONObject("standard_resolution"));
			this.user = new User(json.getJSONObject("user"));
			if(json.has("caption") && json.getJSONObject("caption") != null) {
				this.caption = json.getJSONObject("caption").getString("text");
			}
			this.created_time = json.getString("created_time");
			
			this.comments = new ArrayList<Comment>();
			JSONObject comments = json.getJSONObject("comments");
			if(comments != null) {
				this.commentCount = comments.getInt("count");
				JSONArray commentsData = comments.getJSONArray("data");
				if(commentsData != null) {
					for(int i = 0; i < commentsData.length(); i++) {
						this.comments.add(new Comment(commentsData.getJSONObject(i)));
					}
				}
			}

			this.likes = new ArrayList<User>();
			JSONObject likes = json.getJSONObject("likes");
			if(likes != null) {
				this.likeCount = likes.getInt("count");
				JSONArray likesData = likes.getJSONArray("data");
				if(likesData != null) {
					for(int i = 0; i < likesData.length(); i++) {
						this.likes.add(new User(likesData.getJSONObject(i)));
					}
				}
			}
			
			this.tags = new ArrayList<String>();
			JSONArray tags = json.getJSONArray("tags");
			if(tags != null) {
				for(int i = 0; i < tags.length(); i++) {
					this.tags.add(tags.getString(i));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
