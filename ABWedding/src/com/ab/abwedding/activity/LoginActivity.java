package com.ab.abwedding.activity;

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
import com.ab.abwedding.interfaces.AsyncCallback;
import com.ab.abwedding.util.AsyncPost;

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
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(edtTxtName.getText().toString())
						|| "".equals(edtTxtKey.getText().toString())) {
					Log.i("blank", "blank");
					Toast.makeText(getApplicationContext(),
							getString(R.string.vali_nessesary),
							Toast.LENGTH_LONG);
					return;
				} else {
					// TODO connect server
					// progressDialog.show();

					AsyncPost ap = new AsyncPost(new AsyncCallback() {

						@Override
						public void onProgressUpdate(int progress) {
						}

						@Override
						public void onPreExecute() {
							Log.i("connect", "start!");
						}

						@Override
						public void onPostExecute(String result) {
							Log.i("connect result", result);
							progressDialog.cancel();

							startActivity(new Intent(LoginActivity.this,
									ItemListActivity.class));
							finish();
							return;
						}

						@Override
						public void onCancelled() {
							Toast.makeText(getApplicationContext(),
									getString(R.string.connet_error),
									Toast.LENGTH_LONG).show();
							Log.e("connect false", "error");
							progressDialog.cancel();

							startActivity(new Intent(LoginActivity.this,
									ItemListActivity.class));
							finish();
							return;
						}
					});
					startActivity(new Intent(LoginActivity.this,
							ItemListActivity.class));
					// ap.execute(AsyncPost.GET_MEMBER_LIST, "", "");

				}
			}
		});
	}
}
