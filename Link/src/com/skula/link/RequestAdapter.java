package com.skula.link;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skula.link.models.Request;

public class RequestAdapter extends ArrayAdapter<Request> {
	private Context context;
	private Request data[] = null;

	public RequestAdapter(Context context, int layoutResourceId, Request[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Request request = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.request_list_layout, parent, false);
		
		TextView idTextView = (TextView) rowView.findViewById(R.id.request_list_id);
		idTextView.setText(request.getId());
		TextView typeTextView = (TextView) rowView.findViewById(R.id.request_list_type);
		typeTextView.setText(request.getType());
		TextView labelTextView = (TextView) rowView.findViewById(R.id.request_list_label);
		labelTextView.setText(request.getLabel());
		
		return rowView;
	}
}