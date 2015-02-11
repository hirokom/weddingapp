package com.ab.abwedding.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.abwedding.dummy.MenuContent;

/**
 * Fragment base
 * 
 * @author ishikawatatsuya
 *
 */
public abstract class FragmentBase extends Fragment {

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	protected MenuContent.MenuItem mItem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = MenuContent.MENUS_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}

		subOnCreate(savedInstanceState);
	}

	/**
	 * onCreate function of each fragments sets.
	 * 
	 * @param savedInstanceState
	 */
	protected abstract void subOnCreate(Bundle savedInstanceState);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return subOnCreateView(inflater, container, savedInstanceState);

	}

	/**
	 * onCreateView function of each fragments sets.
	 * 
	 * @param inflater
	 * @param container
	 * @return
	 */
	protected abstract View subOnCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState);

}