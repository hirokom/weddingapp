package com.ab.abwedding.activity;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ab.abwedding.R;
import com.ab.abwedding.data.CommonData;
import com.ab.abwedding.interfaces.AsyncCallback;
import com.ab.abwedding.util.BizConst;
import com.ab.abwedding.util.ConnectWeb;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
/**
 * Login Activity
 * 
 * @author ishikawatatsuya
 */
// Show the Up button in the action bar.
// getSupportActionBar().setDisplayHomeAsUpEnabled(false);
// create progress dialog
// get login button and set function
/**
	 * on click function of login button
	 */
// progressDialog.show();
/**
	 * action of connecting to web server
	 */
// ここでサーバからメンバーリストや食事リスト、アドレスなどのデータを取得して
// データを保存しておく。
// 配列の処理
// 各オブジェクトの処理
// "name"フィールド
// "mail"フィールド
// TODO set dummy data
/**
 * Login Activity
 * 
 * @author ishikawatatsuya
 */
public class LoginActivity extends Activity {

	private EditText edtTxtName;
	private EditText edtTxtKey;
	private Button btnLogin;
	private ProgressDialog progressDialog = null;

	private String SAMPLE_D = "[{		  'name' : { 'first' : '太郎', 'last' : '技評' },		  'mail' : 'taro@example.jp', 		  'todo' : { 'work' : 'hogehoge', 'limit' : '2012/02/13' }		}, 		{		  'name' : { 'first' : '次郎', 'last' : '技術' }, 		  'mail' : 'jiro@example.jp', 		  'todo' : { 'work' : 'hogehoge', 'limit' : '2012/02/15' }		},		{		  'name' : { 'first' : '花子', 'last' : '評論' }, 		  'mail' : 'hanako@example.jp', 		  'todo' : { 'work' : 'hogepiyo', 'limit' : '2012/02/28' }		}] ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Show the Up button in the action bar.
		// getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		edtTxtName = (EditText) findViewById(R.id.edtTxt_name);
		edtTxtKey = (EditText) findViewById(R.id.edtTxt_key);
		// create progress dialog
		progressDialog = new ProgressDialog(getApplicationContext());
		progressDialog.setMessage(getResources().getText(R.string.connecting));
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(true);
		// get login button and set function
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(loginBtnOCL);

	}

	/**
	 * on click function of login button
	 */
	private OnClickListener loginBtnOCL = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if ("".equals(edtTxtName.getText().toString())
					|| "".equals(edtTxtKey.getText().toString())) {
				Log.i("blank", "blank");
				Toast.makeText(getApplicationContext(),
						getString(R.string.vali_nessesary), Toast.LENGTH_LONG)
						.show();
				return;
			} else {
				// progressDialog.show();
				ConnectWeb.getMemberlist(getApplicationContext(), ac,
						edtTxtName.getText().toString(), edtTxtKey.getText()
								.toString());
			}
		}
	};

	/**
	 * action of connecting to web server
	 */
	private AsyncCallback ac = new AsyncCallback() {

		@Override
		public void onPreExecute() {
			Log.i("connect", "start!");
		}

		@Override
		public void onPostExecute(String result) {
//			Log.i("connect result", result);
			progressDialog.cancel();
			JsonFactory factory = new JsonFactory();
			JsonParser parser;

			String name = null;
			int answer;

			try {

				// ここでサーバからメンバーリストや食事リスト、アドレスなどのデータを取得して
				// TODO データを保存しておく。
				parser = factory.createParser(SAMPLE_D);
				// 配列の処理
				if (parser.nextToken() == JsonToken.START_ARRAY) {
					while (parser.nextToken() != JsonToken.END_ARRAY) {
						// 各オブジェクトの処理
						if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
							while (parser.nextToken() != JsonToken.END_OBJECT) {
								parser.nextToken();
								// "name"フィールド
								if ("name".equals(name)) {
									System.out.println(name + ": ");
									while (parser.nextToken() != JsonToken.END_OBJECT) {

										if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
											System.out.print("    "
													+ parser.getText() + ": ");
											name = parser.getText();
										} else if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
											System.out
													.println(parser.getText());
										}
										answer = BizConst.INVITATION_ACCEPT;
										CommonData.setMember(name, answer);
									}
								}
								// "mail"フィールド
								else if ("mail".equals(name)) {
									System.out.println(name + ": "
											+ parser.getText());
								} else {
									parser.skipChildren();
								}
							}
						} else {
							parser.skipChildren();
						}
					}
				} else {
					parser.skipChildren();
				}

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}

			// TODO set dummy data
			CommonData.setAddress("東京都 大田区 一丁目八番19号");
			CommonData.setPostalCode("123-4567");

			startActivity(new Intent(LoginActivity.this, ItemListActivity.class));
			finish();
			return;
		}

		@Override
		public void onCancelled() {
			Toast.makeText(getApplicationContext(),
					getString(R.string.connet_error), Toast.LENGTH_LONG).show();
			Log.e("connect false", "error");
			progressDialog.cancel();

			startActivity(new Intent(LoginActivity.this, ItemListActivity.class));
			finish();
			return;
		}

		@Override
		public void onProgressUpdate(int progress) {
		}

	};

}
