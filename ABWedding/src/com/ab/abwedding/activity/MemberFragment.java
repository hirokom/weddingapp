package com.ab.abwedding.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;

public class MemberFragment extends FragmentBase implements OnClickListener{

	private static MemberFragment mbrDtlFragment;

	private View rootView;

	// 画面構成メンバ
	private Button save_btn;
	private EditText input_name,input_mess;
	private TextView output_name,output_text,list_comment,list_title;
	private ViewGroup vg;
	private TableLayout tl;

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
		rootView = inflater.inflate(R.layout.fragment_member_detail,
				container, false);

		// 構成オブジェクトのIDをすべて取得
		save_btn = (Button)rootView.findViewById(R.id.button1);
		input_name = (EditText)rootView.findViewById(R.id.editName);
		input_mess = (EditText)rootView.findViewById(R.id.editMessage);
		output_name = (TextView)rootView.findViewById(R.id.textView1);
		output_text = (TextView)rootView.findViewById(R.id.greeting_text);
		list_comment = (TextView)rootView.findViewById(R.id.textView2);
		list_title = (TextView)rootView.findViewById(R.id.textView4);
		vg = (ViewGroup)rootView.findViewById(R.id.TableLayout1);
		tl = (TableLayout)rootView.findViewById(R.id.TableLayout1);


		save_btn.setOnClickListener(this);

		// 出さないテキストは一旦非表示
		list_comment.setVisibility(View.GONE);
		list_title.setVisibility(View.GONE);

		return rootView;
	}

	@Override
	public void onClick(View v){
		// テキストの変更
		output_name.setText(input_name.getText());
		output_name.setTextColor(Color.RED);
		output_text.setText("さん、メッセージありがとうございます。");

		// ボタン、入力フィールドの非表示化
		save_btn.setVisibility(View.GONE);
		input_name.setVisibility(View.GONE);
		input_mess.setVisibility(View.GONE);

		// テキストの表示
		list_comment.setVisibility(View.VISIBLE);
		list_title.setVisibility(View.VISIBLE);
		list_title.setTextColor(Color.BLUE);

		// 参加者の取得
		String[][] user_list = new String[6][2];

		user_list[0][0] = "お名前　　　　　";
		user_list[0][1] = "メッセージ";
		user_list[1][0] = input_name.getText().toString();
		user_list[1][1] = input_mess.getText().toString();
		user_list[2][0] = "石川 達也";
		user_list[2][1] = "おめでとうございます！！";
		user_list[3][0] = "関口 太郎";
		user_list[3][1] = "結婚おめでとうございます。お幸せに！";
		user_list[4][0] = "土屋 司";
		user_list[4][1] = "眠い";
		user_list[5][0] = "高麗 浩士";
		user_list[5][1] = "モンストモンストモンストモンストモンストモンストモンストモンスト";


		// 表の表示
		for (int i = 0; i < 6; i++) {

			TableRow tr = new TableRow(getActivity());
		    TextView tv1 = new TextView(getActivity());
		    TextView tv2 = new TextView(getActivity());
		    Button btn = new Button(getActivity());


			tr.setId(i);
		    tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		    // 表のヘッダ
		    if(i==0){
		    	tr.setBackgroundResource(R.drawable.table_header);
		    	tv1.setBackgroundResource(R.drawable.table_header);
		    	tv2.setBackgroundResource(R.drawable.table_header);
			    tv1.setTextColor(Color.WHITE);
		        tv2.setTextColor(Color.WHITE);
		    // 表の中身
		    }else{
		    	tr.setBackgroundResource(R.drawable.table_detail);
		    	tv1.setBackgroundResource(R.drawable.table_detail);
		    	tv2.setBackgroundResource(R.drawable.table_detail);
			    tv1.setTextColor(Color.DKGRAY);
		        tv2.setTextColor(Color.DKGRAY);
		    }

		    // TEXTVIEWS
		    tv1.setText(user_list[i][0]);
		    tv1.setId(i);
		    tv1.setTextSize(15);
		    tv1.setPadding(10, 5, 10, 5);
		    tr.addView(tv1);

	        tv2.setText(user_list[i][1]);
	        tv2.setId(i+i);
	        tv2.setTextSize(15);
	        tv2.setPadding(10, 5, 10, 5);
	        tr.addView(tv2);

	        // Button
	        // 自分の行にだけ出す
	        if(i==1){
		        btn.setText("変更");
		        btn.setId(20);
		        btn.setHeight(20);
		        tr.addView(btn);
	        }

	        tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		    }

	}


}
