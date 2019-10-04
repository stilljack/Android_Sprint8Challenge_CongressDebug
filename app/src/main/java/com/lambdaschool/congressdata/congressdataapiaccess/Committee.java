
package com.lambdaschool.congressdata.congressdataapiaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Committee {

    // SerializedName("name")
    private String name;
    // SerializedName("code")
    private String code;
    // SerializedName("api_uri")
    private String apiUri;
    // SerializedName("side")
    private String side;
    // SerializedName("title")
    private String title;
    // SerializedName("rank_in_party")
    private int rankInParty;
    // SerializedName("begin_date")
    private String beginDate;
    // SerializedName("end_date")
    private String endDate;

    public Committee(String name, String code, String apiUri, String side, String title, int rankInParty, String beginDate, String endDate) {
        this.name = name;
        this.code = code;
        this.apiUri = apiUri;
        this.side = side;
        this.title = title;
        this.rankInParty = rankInParty;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Committee(JSONObject jsonObject) {
        this.name = CongressDao.getStringFromJson(jsonObject, "name");
        this.code = CongressDao.getStringFromJson(jsonObject, "code");
        this.apiUri = CongressDao.getStringFromJson(jsonObject, "api_uri");
        this.side = CongressDao.getStringFromJson(jsonObject, "side");
        this.title = CongressDao.getStringFromJson(jsonObject, "title");
        this.rankInParty = CongressDao.getIntFromJson(jsonObject, "rank_in_party");
        this.beginDate = CongressDao.getStringFromJson(jsonObject, "begin_date");
        this.endDate = CongressDao.getStringFromJson(jsonObject, "end_date");
    }

    static ArrayList<Committee> getCommitteesFromJSON(JSONArray jsonArray) {
        ArrayList<Committee> committees = new ArrayList<Committee>();
        for(int i = 0; i < jsonArray.length(); ++i) {
            try {
                committees.add(new Committee(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return committees;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getApiUri() {
        return apiUri;
    }

    public String getSide() {
        return side;
    }

    public String getTitle() {
        return title;
    }

    public int getRankInParty() {
        return rankInParty;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
