package com.ab.abwedding.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;

public class IntroductionFragment extends FragmentBase {

	private static IntroductionFragment itrdcDtlFragment;
	
	/**
	 * get IntroduceDetailFragment instance
	 * @param args
	 * @return
	 */
	public static IntroductionFragment getFragment(Bundle args) {
		if (itrdcDtlFragment != null) {
			itrdcDtlFragment.setArguments(args);
		} else {
			itrdcDtlFragment = new IntroductionFragment();
			itrdcDtlFragment.setArguments(args);
		}
		return itrdcDtlFragment;
	}
	
	@Override
	protected void subOnCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected View subOnCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		return null;
	}

}
