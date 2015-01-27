package com.ab.abwedding.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ab.abwedding.R;
import com.ab.abwedding.dummy.MenuContent;
import com.ab.abwedding.interfaces.AsyncCallback;
import com.ab.abwedding.util.AsyncPost;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private MenuContent.MenuItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText(mItem.content);
		}

		((Button) rootView.findViewById(R.id.btn_fragment_detail))
		.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AsyncPost as = new AsyncPost(new AsyncCallback() {
					
					@Override
					public void onProgressUpdate(int progress) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onPreExecute() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onPostExecute(String result) {
						// TODO Auto-generated method stub
						Log.i("connect result", result);
						
					}
					
					@Override
					public void onCancelled() {
						// TODO Auto-generated method stub
						
					}
				});
				as.execute(AsyncPost.GET_MEMBER_LIST, "", "");
			}
		});
		
		return rootView;
	}
}
