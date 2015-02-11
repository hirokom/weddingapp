package com.ab.abwedding.data;

import java.util.ArrayList;
import java.util.List;

/**
 * サーバから取得したデータを保持する。
 * ただし、基本的にデータはすべてサーバから取得するものとし、一時的に当クラスで保持しているだけでしか無い。
 * アプリの再起動、再開時にはデータ状態をチェックし、必要に応じてログイン画面へ戻す。
 * @author ishikawatatsuya
 *
 */
public class CommonData {

	/** member list */
	private static List<Member> ml = new ArrayList<Member>();
	/** postal code */
	private static String postalCode = "";
	/** address */
	private static String address = "";
	
	public static boolean setMember(String name,int answer) {
		Member m = new Member();
		m.setName(name);
		m.setAnswer(answer);
		return true;
	}
	public static List<Member> getMemberList() {
		return ml;
	}
	public static String getPostalCode() {
		return postalCode;
	}
	public static void setPostalCode(String postalCode) {
		CommonData.postalCode = postalCode;
	}
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		CommonData.address = address;
	}
}
