package com.reliant.nest.test;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONKeyValue {
	public static void main(String[] args) {
		try {
			JSONArray jsonArray = new JSONArray("[{\"a\":1},{\"b\":2,\"c\":3},{\"d\":4},{\"e\":5,\"f\":7}]");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				Iterator<String> keys = json.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					System.out.println("Key :" + key + "  Value :" + json.get(key));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
