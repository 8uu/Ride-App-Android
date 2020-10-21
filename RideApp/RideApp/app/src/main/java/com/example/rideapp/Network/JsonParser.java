package com.example.rideapp.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static HttpResponse parseJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<Item> judete = getItemListFromJson(jsonObject
                    .getJSONArray("judete"));
            return new HttpResponse(judete);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Item> getItemListFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        List<Item> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Item item = getItemFromJson(array
                    .getJSONObject(i));
            if (item != null) {
                results.add(item);
            }
        }
        return results;
    }

    private static Item getItemFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String nume = object.getString("nume");
        String prescurtare = object.getString("prescurtare");
        List<Localitati> localitati = getLocalitatiFromJson(object
                .getJSONArray("localitati"));
        return new Item(nume, prescurtare, localitati);
    }

    private static List<Localitati> getLocalitatiFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        List<Localitati> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Localitati localitati = getLocalitateFromJson(array
                    .getJSONObject(i));
            if (localitati != null) {
                results.add(localitati);
            }
        }
        return results;
    }

    private static Localitati getLocalitateFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String nume = object.getString("nume");
        String diacritice = object.getString("diacritice");
        List<Strazi> strazi = getStraziFromJson(object
                .getJSONArray("strazi"));
        return new Localitati(nume, diacritice, strazi);
    }

    private static List<Strazi> getStraziFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        List<Strazi> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Strazi strada = getStradaFromJson(array
                    .getJSONObject(i));
            if (strada != null) {
                results.add(strada);
            }
        }
        return results;
    }

    private static Strazi getStradaFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String name = object.getString("nume");
        int numar = object.getInt("numar");
        int cod = object.getInt("cod");
        return new Strazi(name, numar, cod);
    }
}
