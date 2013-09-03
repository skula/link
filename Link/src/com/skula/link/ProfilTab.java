package com.skula.link;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

public class ProfilTab extends Fragment implements ActionBar.TabListener {

	private Fragment mFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from fragment1.xml
		getActivity().setContentView(R.layout.profiltab_layout);
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
