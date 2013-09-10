package com.skula.link;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.skula.link.models.Event;
import com.skula.link.models.Member;
import com.skula.link.services.DBService;

public class ProfilTab extends Fragment implements ActionBar.TabListener {

	private Fragment mFragment;
	private ListView listView;
	private DBService dbService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from fragment1.xml
		getActivity().setContentView(R.layout.profiltab_layout);
		
		this.dbService = new DBService(getActivity());
		//dbService.bouchon();
		this.listView = (ListView) getActivity().findViewById(R.id.profiltab_list);
		Event eventList[] = dbService.getEvents("1");
		EventAdapter adapter = new EventAdapter(getActivity(),
				R.layout.event_list_layout, eventList);
		listView.setAdapter(adapter);
		
		Member m = dbService.getAccount("1");
		TextView loginTv = (TextView) getActivity().findViewById(R.id.profiltab_login);
		loginTv.setText(m.getLogin());
		
		EditText statusTv = (EditText) getActivity().findViewById(R.id.profiltab_status);
		statusTv.setText(m.getStatus());
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mFragment = new ProfilTab();
		// Attach fragment1.xml layout
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// Remove fragment1.xml layout
		ft.remove(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
