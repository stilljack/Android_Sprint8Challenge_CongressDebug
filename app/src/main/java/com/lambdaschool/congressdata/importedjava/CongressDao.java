package com.lambdaschool.congressdata.importedjava;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class CongressDao {
    private static final String BASE_URL = "https://api.propublica.org/congress/v1/";

    private static final String URL_MEMBERS_HOUSE_CALIFORNIA  = BASE_URL + "members/house/CA/current.json";
    public static final  String CONGRESS_NUMBER               = "115";
    private static final String URL_MEMBERS_HOUSE_ALL = BASE_URL + CONGRESS_NUMBER + "/house/members.json";
    private static final String URL_MEMBERS_SENATE_CALIFORNIA = BASE_URL + "members/senate/CA/current.json";
    private static final String URL_MEMBERS_SENATE_ALL        = BASE_URL + CONGRESS_NUMBER + "/senate/members.json";

    private static final String MEMBER_DETAILS                = BASE_URL + "members/";

    private static final String IMAGE_URL = "https://theunitedstates.io/images/congress/450x550/";

    private static final String JPG  = ".jpg";
    private static final String JSON = ".json";

    public interface OverviewListCallback {
        public void requestFinished(boolean success, ArrayList<CongresspersonOverview> list);
    }

    public interface MemberDetailsCallback {
        public void requestFinished(boolean success, ArrayList<CongresspersonOverview> list);
    }

    public static ArrayList<CongresspersonOverview> getAllMembers() {
        ArrayList<CongresspersonOverview> data = new ArrayList<>();

        try {
            String result = NetworkAdapter.httpGetRequest(URL_MEMBERS_HOUSE_ALL);
            if(result != null) {
                JSONObject dataObject    = new JSONObject(result);
                JSONArray  dataJsonArray = dataObject.getJSONArray("results").getJSONObject(0).getJSONArray("members");

                for (int i = 0; i < dataJsonArray.length(); ++i) {
                    CongresspersonOverview officialOverview = new CongresspersonOverview(dataJsonArray.getJSONObject(i));
                    data.add(officialOverview);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static CongresspersonDetails getMemberDetails(String memberId) {
        CongresspersonDetails congressPersonProfile;
        try {
            String result = NetworkAdapter.httpGetRequest(MEMBER_DETAILS + memberId + JSON);
            JSONObject dataObject    = new JSONObject(result);
            JSONObject dataJsonObject = dataObject.getJSONArray("results").getJSONObject(0);

            congressPersonProfile = new CongresspersonDetails(dataJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
            congressPersonProfile = new CongresspersonDetails();
        }
        return congressPersonProfile;
    }

    public static Bitmap getImage(String id) {
        return NetworkAdapter.getBitmapFromURL(IMAGE_URL + id + JPG);
    }

    public static int getIntFromJson(JSONObject jsonObject, String id) {
        int value = 0;
        try {
            value = jsonObject.getInt(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;

    }

    public static double getDoubleFromJson(JSONObject jsonObject, String id) {
        double value = 0;
        try {
            value = jsonObject.getDouble(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean getBooleanFromJson(JSONObject jsonObject, String id) {
        boolean value = false;
        try {
            value = jsonObject.getBoolean(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getStringFromJson(JSONObject jsonObject, String id) {
        String value = "";
        try {
            value = jsonObject.getString(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static JSONObject getJSONObjectFromJson(JSONObject jsonObject, String id) {
        JSONObject value = null;
        try {
            value = jsonObject.getJSONObject(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray getJSONArrayFromJson(JSONObject jsonObject, String id) {
        JSONArray value = null;
        try {
            value = jsonObject.getJSONArray(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}
