package com.ab.abwedding.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.abwedding.base.FragmentBase;

public class FoodFragment extends FragmentBase {

	private static FoodFragment fdDtlFragment;
	
	/**
	 * get FoodDetailFragment instance
	 * @param args
	 * @return
	 */
	public static FoodFragment getFragment(Bundle args) {
		if (fdDtlFragment != null) {
			fdDtlFragment.setArguments(args);
		} else {
			fdDtlFragment = new FoodFragment();
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
		// TODO Auto-generated method stub
		return null;
	}

}
