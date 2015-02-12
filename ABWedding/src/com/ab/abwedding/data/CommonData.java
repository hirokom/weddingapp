package com.ab.abwedding.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.media.Image;

/**
 * サーバから取得したデータを保持する。
 * ただし、基本的にデータはすべてサーバから取得するものとし、一時的に当クラスで保持しているだけでしか無い。
 * アプリの再起動、再開時にはデータ状態をチェックし、必要に応じてログイン画面へ戻す。
 * @author ishikawatatsuya
 *
 */
public class CommonData {

	/** user本人のID */
	// 一旦これで。
	public static String usrid;
	/** member list */
	private static List<Member> ml = new ArrayList<Member>();
	/** postal code */
	private static String postalCode = "";
	/** address */
	private static String address = "";
	/** message content1 */
	private static String c1 = "幸せにしてあげてね！";
	/** message content2 */
	private static String c2 = "幸せでいてね^^";

	private static String[] coupleName = new String[2];

	private static Image[] coupleImage = new Image[2];

	private static Date[] coupleBirthday = new Date[2];

	private static String[] coupleHobby = new String[2];


	public static boolean setMember(String usrid,String name,String comment,int answer) {
		Member m = new Member();
		m.setUsrId(usrid);
		m.setName(name);
		m.setComment(comment);
		m.setAnswer(answer);

		ml.add(m);
		sortMember();

		return true;
	}
	// DB接続実装までのドライバ
	public static void setMemberList(){
		String json ="{{\"usrid\":\"abc\", \"name\":\"石川 達也\", \"comment\":\"おめでとうございます！！\", \"answer\":1 },"
					 +"{\"usrid\":\"efg\", \"name\":\"関口 太郎\", \"comment\":\"結婚おめでとうございます。お幸せに！\", \"answer\":1 },"
					 +"{\"usrid\":\"hij\", \"name\":\"土屋 司\", \"comment\":\"眠い！！\", \"answer\":1 },"
					 +"{\"usrid\":\"klm\", \"name\":\"高麗 浩士\", \"comment\":\"モンストモンストモンストモンスト\", \"answer\":1 },"
					 +"{\"usrid\":\"opq\", \"name\":\"豊川 陽\", \"comment\":\"\", \"answer\":1 }}";

//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			List<Member> ml = mapper.readValue(json, List.class);
//		} catch (JsonParseException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

		setMember("abc","石川 達也","おめでとうございます！！",1);
		setMember("efg","関口 太郎","結婚おめでとうございます。お幸せに！",1);
		setMember("hij","土屋 司","眠い。。。",1);
		setMember("klm","高麗 浩士","モンストモンストモンストモンストモンスト",1);
		setMember("opq","豊川 陽",null,1);
		sortMember();
	}
	public static List<Member> getMemberList() {
		return ml;
	}
	public static boolean containsUsrId(String usrid){
		for(Member m :ml){
			if(m.getUsrId()==usrid){
				return true;
			}
		}
		return false;
	}
	public static void sortMember(){
		Collections.sort(ml, new Comparator<Member>() {
			public int compare(Member A00, Member A01){
				String T00 = A00.getUsrId();
				String T01 = A01.getUsrId();

				if (T00 == null && T01 == null) {
		            return 0;   // arg0 = arg1
		        } else if (T00 == null) {
		            return 1;   // arg1 > arg2
		        } else if (T01 == null) {
		            return -1;  // arg1 < arg2
		        }
				return T00.compareTo(T01);
			}
		});
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
	public static String getC1() {
		return c1;
	}
	public static void setC1(String c1) {
		CommonData.c1 = c1;
	}
	public static String getC2() {
		return c2;
	}
	public static void setC2(String c2) {
		CommonData.c2 = c2;
	}
	public static String[] getCoupleName() {
		coupleName[0] = "やすし";
		coupleName[1] = "やすこ";
		return coupleName;
	}
	public static void setCoupleName(String[] coupleName) {
		CommonData.coupleName = coupleName;
	}
	public static Image[] getCoupleImage() {
		return coupleImage;
	}
	public static void setCoupleImage(Image[] coupleImage) {
		CommonData.coupleImage = coupleImage;
	}
	public static Date[] getCoupleBirthday() {
		Calendar cal = Calendar.getInstance();
		cal.set(1980, 1, 5);
		coupleBirthday[0] = cal.getTime();
		cal.set(1980,1,6);
		coupleBirthday[1] = cal.getTime();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
//		sdf.format(coupleBirthday[0]).toString();
		return coupleBirthday;
	}
	public static void setCoupleBirthday(Date[] couplebaithday) {
		CommonData.coupleBirthday = couplebaithday;
	}
	public static String[] getCoupleHobby() {
		coupleHobby[0] = "ゴルフ";
		coupleHobby[1] = "お菓子作り";
		return coupleHobby;
	}
	public static void setCouplehobby(String[] coupleHobby) {
		CommonData.coupleHobby = coupleHobby;
	}
}
