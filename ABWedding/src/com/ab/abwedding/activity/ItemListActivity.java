package com.ab.abwedding.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;
import com.ab.abwedding.dummy.MenuContent;
import com.ab.abwedding.interfaces.AsyncCallback;
import com.ab.abwedding.util.AsyncPost;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	private ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// connect start
		initFunc();

		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	private void initFunc() {

		// create progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getResources().getText(R.string.connecting));
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(true);
		progressDialog.show();

		AsyncPost ap = new AsyncPost(new AsyncCallback() {

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
//				Log.i("connect result", result);
				progressDialog.cancel();
			}

			@Override
			public void onCancelled() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						getString(R.string.connet_error), Toast.LENGTH_LONG)
						.show();
				Log.e("connect false", "error");
				progressDialog.cancel();
			}
		});

		ap.execute(AsyncPost.GET_MEMBER_LIST, "userId", "key");
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle args = new Bundle();
			args.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			// TODO 他のFragmentが起動中は他を停止させないと無駄＆Map描画でエラー？
			FragmentManager fm = getSupportFragmentManager();

			if (MenuContent.MEMBER_ID.equals(id)) {
				replaceFragment(MemberFragment.getFragment(args));
			} else if (MenuContent.FOOD_ID.equals(id)) {
				replaceFragment(FoodFragment.getFragment(args));
			} else if (MenuContent.INTRODUCE_ID.equals(id)) {
				replaceFragment(IntroductionFragment.getFragment(args));
			} else if (MenuContent.ACCESS_ID.equals(id)) {
				replaceFragment(AccessFragment.getFragment(args));
			} else if (MenuContent.TEST_ID.equals(id)) {
				// TODO test用のため昔の書き方を残しておく
				ItemDetailFragment fragment = new ItemDetailFragment();
				fragment.setArguments(args);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			} else {
				// TODO go to Login Activity
			}

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);

		}
	}

	/**
	 * replace fragment
	 * @param fragment
	 */
	private void replaceFragment(FragmentBase fragment) {
		// TODO test用のタグ
		String TEST_TAG = "testTag";
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment, TEST_TAG)
				.commit();
	}
}
