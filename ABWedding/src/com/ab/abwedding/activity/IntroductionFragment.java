package com.ab.abwedding.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;
import com.ab.abwedding.data.CommonData;

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

		View rootView = inflater.inflate(R.layout.fragment_introduction_detail,
				container, false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String birthday1 = sdf.format(CommonData.getCoupleBirthday()[0]).toString();
		String birthday2 = sdf.format(CommonData.getCoupleBirthday()[1]).toString();

		TextView nameh = (TextView)rootView.findViewById(R.id.TextViewn02);
		nameh.setText(CommonData.getCoupleName()[0]);

		TextView namew = (TextView)rootView.findViewById(R.id.TextViewn03);
		namew.setText(CommonData.getCoupleName()[1]);

		TextView birthdayh = (TextView)rootView.findViewById(R.id.TextViewb02);
		birthdayh.setText(birthday1);

		TextView birthdayw = (TextView)rootView.findViewById(R.id.TextViewb03);
		birthdayw.setText(birthday2);

		TextView hobbyh = (TextView)rootView.findViewById(R.id.TextViewh02);
		hobbyh.setText(CommonData.getCoupleHobby()[0]);

		TextView hobbyw = (TextView)rootView.findViewById(R.id.TextViewh03);
		hobbyw.setText(CommonData.getCoupleHobby()[1]);

		return rootView;
	}

}
