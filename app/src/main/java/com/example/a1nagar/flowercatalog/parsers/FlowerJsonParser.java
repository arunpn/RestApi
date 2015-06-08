package com.example.a1nagar.flowercatalog.parsers;

import com.example.a1nagar.flowercatalog.model.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlowerJsonParser {

    public static List<Flower> parseFeed(String content)  {

        try {
        JSONArray ar = new JSONArray(content);
        List<Flower> flowerList = new ArrayList<>();
        for (int i=0; i< ar.length(); i++) {

            JSONObject obj = ar.getJSONObject(i);
            Flower flower = new Flower();

            flower.setName(obj.getString("name"));
            flower.setCategory(obj.getString("category"));
            flower.setPrice(obj.getDouble("price"));
            flower.setInstructions(obj.getString("instructions"));
            flower.setPhoto(obj.getString("photo"));
            flower.setProductId(obj.getInt("productId"));
            flowerList.add(flower);

        }

        return flowerList;

    } catch (JSONException e) {
        e.printStackTrace();
            return null;
    }


}

}
