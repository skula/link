package com.skula.link;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skula.link.enums.EventType;
import com.skula.link.models.Event;

public class EventAdapter extends ArrayAdapter<Event> {
	private Context context;
	private Event data[] = null;

	public EventAdapter(Context context, int layoutResourceId, Event[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Event e = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.event_list_layout, parent, false);

		String text = "<b>" + e.getDate() + "</b>: ";
		if(e.getType()==EventType.CHALLENGE){
			if(e.isAsk()){
				text += "vous avez défié <font color=\"red\">" + e.getLinkerLogin() + "</font>: </br><i>\"" + e.getLabel() + "\"</i>.";
			}else{
				text += "<font color=\"red\">" + e.getLinkerLogin() + "</font> vous a défié: </br></br><i>\"" + e.getLabel() + "\"</i>.";
			}
			text += " - " + e.getStatus() + ".";
		}else{
			text += "vous etes ami avec <font color=\"red\">" + e.getLinkerLogin() + "</font>.";
		}
		
		TextView labelTextView = (TextView) rowView.findViewById(R.id.event_list_text);
		labelTextView.setText(Html.fromHtml(text));
		return rowView;
	}
}
