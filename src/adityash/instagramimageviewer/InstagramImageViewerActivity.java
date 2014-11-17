package adityash.instagramimageviewer;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;


public class InstagramImageViewerActivity extends Activity {
	public static final String CLIENT_ID = "a1c7e18281724329b0cd36ad54a914c4";
	public static final String POPULAR_IMAGES_URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
	private ArrayList<Entry> entries;
	private InstagramArrayAdapter entiesAdapter;
	private PullToRefreshListView lvEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_image_viewer);
        setupAdapter();
        fetchImages();
    }

    private void setupAdapter() {
    	entries = new ArrayList<Entry>();
		lvEntries = (PullToRefreshListView) findViewById(R.id.lvEntries);
		entiesAdapter = new InstagramArrayAdapter(getBaseContext(), entries);
		lvEntries.setAdapter(entiesAdapter);
		
		lvEntries.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				fetchImages();
				lvEntries.onRefreshComplete();
			}
		});
	}

	private void fetchImages() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(POPULAR_IMAGES_URL, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					JSONArray entriesJSON = response.getJSONArray("data");
					for(int i = 0; i < entriesJSON.length(); i++) {
						entries.add(new Entry(entriesJSON.getJSONObject(i)));
					}
					entiesAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.instagram_image_viewer, menu);
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
    
    public void showComments(String id) {
    	Intent i = new Intent(getBaseContext(), UserCommentsActivity.class);
		i.putExtra("id", id);
		startActivity(i);
    }
}
