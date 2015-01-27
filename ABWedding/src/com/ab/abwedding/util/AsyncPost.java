/**
 * Connecting Server Utility
 * @author ishikawatatsuya
 *
 */
package com.ab.abwedding.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ab.abwedding.interfaces.AsyncCallback;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncPost extends AsyncTask<String, Integer, String> {

	/** encode */
	private static final String ENCODE = "UTF-8";

	/**
	 *  getting member list from web server
	 *  set param1 "userId" param2 "key"
	 */
	public static String GET_MEMBER_LIST = "getMemberlist";
	public static String G_MENU_LIST = "getMenuList";
	
	/** getting XXXX URL */
//	private static final String URL_MEMBER_LIST = "http://www.golugolu.net/GolfCourse/SearchV13";
	private static final String URL_MEMBER_LIST = "http://localhost:80/weddingappserver";//http://192.168.0.5:8000/absampleapp/restget3";

	/** parameter key of userID */
	private static final String PARAM_USERID = "usrId";
	/** parameter key of key */
	private static final String PARAM_KEY = "key";

    private AsyncCallback asyncCallback = null;;

    public AsyncPost() {
    }

    public AsyncPost(AsyncCallback asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (this.asyncCallback != null)
        	this.asyncCallback.onPreExecute();
    }

    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.asyncCallback != null)
            this.asyncCallback.onProgressUpdate(values[0]);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (this.asyncCallback != null)
            this.asyncCallback.onPostExecute(result);
    }

    protected void onCancelled() {
        super.onCancelled();
        if (this.asyncCallback != null)
            this.asyncCallback.onCancelled();
    }
    
	/**
	 * connect and get the member list
	 * 
	 * @param usrId
	 * @param key
	 * @return
	 * @throws MalformedURLException
	 */
	public static String getMemberlist(String usrId, String key) {

		HttpPost httpPost = new HttpPost(URL_MEMBER_LIST);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("keyword", "神戸"));
		
		nameValuePair.add(new BasicNameValuePair(PARAM_USERID, usrId));
		nameValuePair.add(new BasicNameValuePair(PARAM_KEY, key));
		return post(httpPost, nameValuePair);
	}

	/**
	 * post some parameters
	 * 
	 * @param httppost
	 * @param nameValuePair
	 * @return
	 */
	private static String post(HttpPost httppost,
			List<NameValuePair> nameValuePair) {
		try {
			return post(httppost, new UrlEncodedFormEntity(nameValuePair,
					ENCODE));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * post some parameters
	 * 
	 * @param httppost
	 * @param nameValuePair
	 * @return
	 */
	private static String post(HttpPost httppost, Object entity) {

		String result = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// set limit of timeout
			httpclient.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));
			// set entity by exact class
			if (entity instanceof UrlEncodedFormEntity) {
				httppost.setEntity((UrlEncodedFormEntity) entity);
			} else if (entity instanceof MultipartEntity) {
				httppost.setEntity((MultipartEntity) entity);
			}
			// execute post connection
			HttpResponse response = httpclient.execute(httppost);
			// set the byte stream
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			response.getEntity().writeTo(byteArrayOutputStream);
			// check the connection result
			Log.i("status line",
					String.valueOf(response.getStatusLine().getStatusCode()));
			Log.i("get data",
					String.valueOf(response.getStatusLine().getStatusCode()));
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				result = byteArrayOutputStream.toString();
			} else {
				Log.e("post error", response.getStatusLine().toString());
				Log.e("post Error", response.getParams().toString());
				Log.e("post Error", response.getEntity().toString());
				Log.e("post Error", response.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected String doInBackground(String... params) {
		if (params == null || params.length == 0)
			return null;
		String result = null;
		if (GET_MEMBER_LIST.equals(params[0])) {
			if (params.length == 3)
				result = getMemberlist(params[1], params[2]);
		} else if (G_MENU_LIST.equals(params[0])) {

		}
		return result;
	}

	
	// public static enum ConnectType {GetMemberList(G_MEMBER_LIST),
	// GetMenuList(G_MENU_LIST);
	// private String _methodName;
	// private ConnectType(String methodName) {
	// this._methodName = methodName;
	// }
	// public String getMethodName() {
	// return _methodName;
	// }
	// }
	//
	// @Override
	// protected String doInBackground(String... params) {
	// // TODO Auto-generated method stub
	// if (params == null || params.length == 0)
	// return null;
	// String result = null;
	// ConnectType ct = ConnectType.valueOf(params[0]);
	// if (ct != null) {
	// Method m;
	// try {
	// m = AsyncPost.class.getMethod(ct.getMethodName(), params[0].getClass());
	// result = (String) m.invoke(this, null);
	// } catch (NoSuchMethodException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
}