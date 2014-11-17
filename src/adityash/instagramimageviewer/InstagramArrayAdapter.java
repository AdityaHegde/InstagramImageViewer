package adityash.instagramimageviewer;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InstagramArrayAdapter extends ArrayAdapter<Entry> {
	public InstagramArrayAdapter(Context context, ArrayList<Entry> entries) {
		super(context, R.layout.instagram_entry, entries);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.instagram_entry, parent, false);
	    }
		
		final Entry entry = getItem(position);
		
		TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
		ImageView image = (ImageView) convertView.findViewById(R.id.ivImage);
		TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
		ImageView profileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
		TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
		TextView tvComments = (TextView) convertView.findViewById(R.id.tvCommentCount);
		
		tvCaption.setText(entry.caption);
		image.getLayoutParams().height = entry.image.height;
		image.setImageResource(0);
		Picasso.with(getContext()).load(entry.image.url).placeholder(R.drawable.loading_placeholder).into(image);
		tvUsername.setText(entry.user.username);
		Picasso.with(getContext()).load(entry.user.profile_picture).resize(64, 64).into(profileImage);
		tvLikes.setText(String.valueOf(entry.likeCount));
		tvComments.setText(String.valueOf(entry.commentCount));
		
		tvComments.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), UserCommentsActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.putExtra("id", entry.id);
				getContext().startActivity(i);
			}
		});
		
		return convertView;
	}
}
