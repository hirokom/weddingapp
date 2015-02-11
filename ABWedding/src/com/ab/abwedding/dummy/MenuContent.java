package com.ab.abwedding.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Menu list information saved.
 * 
 * <p>
 * TODO: Now, menus are defined. but it has to change to get from web server.
 */
public class MenuContent {

	/** 参加者一覧 */
	public static String MEMBER_ID = "1";
	/** 食事一覧 */
	public static String FOOD_ID = "2";
	/** 新郎新婦紹介 */
	public static String INTRODUCE_ID = "3";
	/** アクセス */
	public static String ACCESS_ID = "4";
	/** テスト用 */
	public static String TEST_ID = "9";
	
	/**
	 * An array of menu items.
	 */
	public static List<MenuItem> MENUS = new ArrayList<MenuItem>();

	/**
	 * A map of menu, by ID.
	 */
	public static Map<String, MenuItem> MENUS_MAP = new HashMap<String, MenuItem>();

	static {
		// Add 3 sample items.
		// TODO change to string.xml define
		// TODO change to get data from web
		addItem(new MenuItem(MEMBER_ID, "参加者一覧"));
		addItem(new MenuItem(FOOD_ID, "お食事"));
		addItem(new MenuItem(INTRODUCE_ID, "新郎新婦紹介"));
		addItem(new MenuItem(ACCESS_ID, "アクセス"));
		addItem(new MenuItem(TEST_ID, "テスト用"));
	}

	/**
	 * add menu
	 * @param menu
	 */
	private static void addItem(MenuItem item) {
		MENUS.add(item);
		MENUS_MAP.put(item.id, item);
	}

	/**
	 * A menu representing a piece of content.
	 */
	public static class MenuItem {
		public String id;
		public String content;

		public MenuItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
