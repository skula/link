package com.skula.link;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skula.link.models.Member;

public class MemberAdapter extends ArrayAdapter<Member> {
	private Context context;
	private Member data[] = null;

	public MemberAdapter(Context context, int layoutResourceId, Member[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Member member = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.member_list_layout, parent, false);
		
		TextView idTextView = (TextView) rowView.findViewById(R.id.member_list_id);
		idTextView.setText(member.getId());
		
		TextView loginTextView = (TextView) rowView.findViewById(R.id.member_list_login);
		loginTextView.setText(member.getLogin());
		loginTextView.setTextColor(member.getGender().equals("F")?Color.RED:Color.BLUE);
		//TextView passwdTextView = (TextView) rowView.findViewById(R.id.member_list_passwd);
		//passwdTextView.setText(member.getPasswd());
		//TextView cityTextView = (TextView) rowView.findViewById(R.id.member_list_city);
		//cityTextView.setText(member.getCity());
		//TextView emailTextView = (TextView) rowView.findViewById(R.id.member_list_email);
		//emailTextView.setText(member.getEmail());
		TextView statusTextView = (TextView) rowView.findViewById(R.id.member_list_status);
		statusTextView.setText(member.getStatus());
		//TextView descriptionTextView = (TextView) rowView.findViewById(R.id.member_list_description);
		//descriptionTextView.setText(member.getDescription());
		//TextView genderTextView = (TextView) rowView.findViewById(R.id.member_list_gender);
		//genderTextView.setText(member.getGender());
		TextView pointsTextView = (TextView) rowView.findViewById(R.id.member_list_points);
		pointsTextView.setText(member.getPoints());
		TextView birthTextView = (TextView) rowView.findViewById(R.id.member_list_age);
		birthTextView.setText(member.getBirth());
		
		return rowView;
	}
}
