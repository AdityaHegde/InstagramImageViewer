package adityash.instagramimageviewer;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class UserCommentsActivity extends Activity {
	private ArrayList<Comment> comments;
	private CommentArrayAdapter commentsAdapter;
	private ListView lvComments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_comments);
		setAdapter();
		fetchComments();
	}

	private void setAdapter() {
		comments = new ArrayList<Comment>();
		commentsAdapter = new CommentArrayAdapter(this, comments);
		lvComments = (ListView) findViewById(R.id.lvUserComments);
		lvComments.setAdapter(commentsAdapter);
	}

	private void fetchComments() {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "https://api.instagram.com/v1/media/" + getIntent().getStringExtra("id") + "/comments?client_id=" + InstagramImageViewerActivity.CLIENT_ID;
		client.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					JSONArray commentsJSON = response.getJSONArray("data");
					for(int i = 0; i < commentsJSON.length(); i++) {
						comments.add(new Comment(commentsJSON.getJSONObject(i)));
					}
					commentsAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_comments, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
