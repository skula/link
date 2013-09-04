package com.skula.link;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skula.link.models.Challenge;

public class ChallengeAdapter extends ArrayAdapter<Challenge> {
	private Context context;
	private Challenge data[] = null;

	public ChallengeAdapter(Context context, int layoutResourceId, Challenge[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Challenge challenge = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.challenge_list_layout, parent, false);
		
		TextView idTextView = (TextView) rowView.findViewById(R.id.challenge_list_id);
		idTextView.setText(challenge.getId());
		TextView askerLoginTextView = (TextView) rowView.findViewById(R.id.challenge_list_askerLogin);
		askerLoginTextView.setText(challenge.getAskerLogin());
		TextView makerLoginTextView = (TextView) rowView.findViewById(R.id.challenge_list_makerLogin);
		makerLoginTextView.setText(challenge.getMakerLogin());
		TextView statusTextView = (TextView) rowView.findViewById(R.id.challenge_list_status);
		statusTextView.setText(challenge.getStatus());
		TextView dateTextView = (TextView) rowView.findViewById(R.id.challenge_list_date);
		dateTextView.setText(challenge.getDate());
		TextView labelTextView = (TextView) rowView.findViewById(R.id.challenge_list_label);
		labelTextView.setText(challenge.getLabel());
		
		return rowView;
	}
}
