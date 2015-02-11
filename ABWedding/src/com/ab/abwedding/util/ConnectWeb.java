package com.ab.abwedding.util;

import android.content.Context;

import com.ab.abwedding.interfaces.AsyncCallback;

public class ConnectWeb {

    
	/**
	 * connect for getting the member list
	 * 
	 * @param ac	:if it is necessary to do any function before or after connecting, then set AsyncCallbac instance.
	 * @param usrId	:userID
	 * @param key	:key for identifying couple
	 * @return true
	 */
	public static boolean getMemberlist(Context context, AsyncCallback ac, String usrId, String key) {
		new AsyncPost(context, ac).execute(WebConst.URL_MEMBER_LIST, usrId, key);
		return true;
	}
	
}
