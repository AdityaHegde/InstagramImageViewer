package adityash.instagramimageviewer;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentArrayAdapter extends ArrayAdapter<Comment> {
	public CommentArrayAdapter(Context context, ArrayList<Comment> comments) {
		super(context, R.layout.user_comment, comments);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_comment, parent, false);
	    }
		
		Comment comment = getItem(position);
		
		TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
		ImageView profileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
		TextView tvComments = (TextView) convertView.findViewById(R.id.tvComments);
		
		tvUsername.setText(comment.from.username);
		Picasso.with(getContext()).load(comment.from.profile_picture).resize(64, 64).into(profileImage);
		tvComments.setText(comment.text);
		
		return convertView;
	}
}
