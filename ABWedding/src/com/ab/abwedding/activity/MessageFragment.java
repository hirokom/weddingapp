package com.ab.abwedding.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;
import com.ab.abwedding.data.CommonData;

public class MessageFragment extends FragmentBase {

	private static MessageFragment fdDtlFragment;

	/**
	 * get FoodDetailFragment instance
	 * @param args
	 * @return
	 */
	public static MessageFragment getFragment(Bundle args) {
		if (fdDtlFragment != null) {
			fdDtlFragment.setArguments(args);
		} else {
			fdDtlFragment = new MessageFragment();
			fdDtlFragment.setArguments(args);
		}
		return fdDtlFragment;
	}

	@Override
	protected void subOnCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected View subOnCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_message_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			TextView m1 = (TextView) rootView.findViewById(R.id.m1);
			m1.setText("From Dad:");
			TextView m2 = (TextView) rootView.findViewById(R.id.m2);
			m2.setText("From Mom:");
			TextView c1 = (TextView) rootView.findViewById(R.id.c1);
			c1.setText(CommonData.getC1());
			TextView c2 = (TextView) rootView.findViewById(R.id.c2);
			c2.setText(CommonData.getC2());
		}

		return rootView;
	}

}
