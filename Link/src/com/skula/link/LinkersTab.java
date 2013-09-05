package com.skula.link;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ListView;

import com.skula.link.models.Member;
import com.skula.link.services.DBService;

public class LinkersTab extends Fragment implements ActionBar.TabListener {
	
	private Fragment mFragment;
	private ListView listView;
	private DBService dbService;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.linkerstab_layout);
		
		this.dbService = new DBService(getActivity());
		
		this.listView = (ListView) getActivity().findViewById(R.id.linkerstab_list);
		Member memberList[] = dbService.getMembers();
		MemberAdapter adapter = new MemberAdapter(getActivity(),
				R.layout.member_list_layout, memberList);
		listView.setAdapter(adapter);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new LinkersTab();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}
