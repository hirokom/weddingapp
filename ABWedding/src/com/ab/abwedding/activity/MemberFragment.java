package com.ab.abwedding.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.abwedding.base.FragmentBase;

public class MemberFragment extends FragmentBase {

	private static MemberFragment mbrDtlFragment;
	
	/**
	 * get MemberDetailFragment instance
	 * @param args
	 * @return
	 */
	public static MemberFragment getFragment(Bundle args) {
		if (mbrDtlFragment != null) {
			//TODO 事前のArgumentを削除する
			mbrDtlFragment.setArguments(args);
		} else {
			mbrDtlFragment = new MemberFragment();
			mbrDtlFragment.setArguments(args);
		}
		return mbrDtlFragment;
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
