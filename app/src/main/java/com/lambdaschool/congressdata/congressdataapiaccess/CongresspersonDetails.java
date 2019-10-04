
package com.lambdaschool.congressdata.congressdataapiaccess;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CongresspersonDetails {

    // SerializedName("member_id")
    private String memberId;
    // SerializedName("first_name")
    private String firstName;
    // SerializedName("middle_name")
    private String middleName;
    // SerializedName("last_name")
    private String lastName;
    // SerializedName("suffix")
    private String suffix;
    // SerializedName("date_of_birth")
    private String dateOfBirth;
    // SerializedName("gender")
    private String gender;
    // SerializedName("url")
    private String url;
    // SerializedName("times_topics_url")
    private String timesTopicsUrl;
    // SerializedName("times_tag")
    private String timesTag;
    // SerializedName("govtrack_id")
    private String govtrackId;
    // SerializedName("cspan_id")
    private String cspanId;
    // SerializedName("votesmart_id")
    private String votesmartId;
    // SerializedName("icpsr_id")
    private String icpsrId;
    // SerializedName("twitter_account")
    private String twitterAccount;
    // SerializedName("facebook_account")
    private String facebookAccount;
    // SerializedName("youtube_account")
    private String youtubeAccount;
    // SerializedName("crp_id")
    private String crpId;
    // SerializedName("google_entity_id")
    private String googleEntityId;
    // SerializedName("rss_url")
    private String rssUrl;
    // SerializedName("in_office")
    private boolean inOffice;
    // SerializedName("current_party")
    private String currentParty;
    // SerializedName("most_recent_vote")
    private String mostRecentVote;
    // SerializedName("last_updated")
    private String lastUpdated;
    // SerializedName("roles")
    private List<Role> roles = null;

    public CongresspersonDetails(String memberId, String firstName, String middleName, String lastName, String suffix, String dateOfBirth, String gender, String url, String timesTopicsUrl, String timesTag, String govtrackId, String cspanId, String votesmartId, String icpsrId, String twitterAccount, String facebookAccount, String youtubeAccount, String crpId, String googleEntityId, String rssUrl, boolean inOffice, String currentParty, String mostRecentVote, String lastUpdated, List<Role> roles) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.url = url;
        this.timesTopicsUrl = timesTopicsUrl;
        this.timesTag = timesTag;
        this.govtrackId = govtrackId;
        this.cspanId = cspanId;
        this.votesmartId = votesmartId;
        this.icpsrId = icpsrId;
        this.twitterAccount = twitterAccount;
        this.facebookAccount = facebookAccount;
        this.youtubeAccount = youtubeAccount;
        this.crpId = crpId;
        this.googleEntityId = googleEntityId;
        this.rssUrl = rssUrl;
        this.inOffice = inOffice;
        this.currentParty = currentParty;
        this.mostRecentVote = mostRecentVote;
        this.lastUpdated = lastUpdated;
        this.roles = roles;
    }

    public CongresspersonDetails() {
        this.memberId = "member_id";
        this.firstName = "first_name";
        this.middleName = "middle_name";
        this.lastName = "last_name";
        this.suffix = "suffix";
        this.dateOfBirth = "date_of_birth";
        this.gender = "gender";
        this.url = "url";
        this.timesTopicsUrl = "times_topics_url";
        this.timesTag = "times_tag";
        this.govtrackId = "govtrack_id";
        this.cspanId = "cspan_id";
        this.votesmartId = "votesmart_id";
        this.icpsrId = "icpsr_id";
        this.twitterAccount = "twitter_account";
        this.facebookAccount = "facebook_account";
        this.youtubeAccount = "youtube_account";
        this.crpId = "crp_id";
        this.googleEntityId = "google_entity_id";
        this.rssUrl = "rss_url";
        this.inOffice = false;
        this.currentParty = "current_party";
        this.mostRecentVote = "most_recent_vote";
        this.lastUpdated = "last_updated";
        this.roles = new ArrayList<Role>();
    }

    public CongresspersonDetails(JSONObject jsonObject) {
        this.memberId = CongressDao.getStringFromJson(jsonObject,"member_id");
        this.firstName = CongressDao.getStringFromJson(jsonObject,"first_name");
        this.middleName = CongressDao.getStringFromJson(jsonObject,"middle_name");
        this.lastName = CongressDao.getStringFromJson(jsonObject,"last_name");
        this.suffix = CongressDao.getStringFromJson(jsonObject,"suffix");
        this.dateOfBirth = CongressDao.getStringFromJson(jsonObject,"date_of_birth");
        this.gender = CongressDao.getStringFromJson(jsonObject,"gender");
        this.url = CongressDao.getStringFromJson(jsonObject,"url");
        this.timesTopicsUrl = CongressDao.getStringFromJson(jsonObject,"times_topics_url");
        this.timesTag = CongressDao.getStringFromJson(jsonObject,"times_tag");
        this.govtrackId = CongressDao.getStringFromJson(jsonObject,"govtrack_id");
        this.cspanId = CongressDao.getStringFromJson(jsonObject,"cspan_id");
        this.votesmartId = CongressDao.getStringFromJson(jsonObject,"votesmart_id");
        this.icpsrId = CongressDao.getStringFromJson(jsonObject,"icpsr_id");
        this.twitterAccount = CongressDao.getStringFromJson(jsonObject,"twitter_account");
        this.facebookAccount = CongressDao.getStringFromJson(jsonObject,"facebook_account");
        this.youtubeAccount = CongressDao.getStringFromJson(jsonObject,"youtube_account");
        this.crpId = CongressDao.getStringFromJson(jsonObject,"crp_id");
        this.googleEntityId = CongressDao.getStringFromJson(jsonObject,"google_entity_id");
        this.rssUrl = CongressDao.getStringFromJson(jsonObject,"rss_url");
        this.inOffice = CongressDao.getBooleanFromJson(jsonObject,"in_office");
        this.currentParty = CongressDao.getStringFromJson(jsonObject,"current_party");
        this.mostRecentVote = CongressDao.getStringFromJson(jsonObject,"most_recent_vote");
        this.lastUpdated = CongressDao.getStringFromJson(jsonObject,"last_updated");
        this.roles = Role.getRolesFromJSON(CongressDao.getJSONArrayFromJson(jsonObject, "roles"));
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getUrl() {
        return url;
    }

    public String getTimesTopicsUrl() {
        return timesTopicsUrl;
    }

    public String getTimesTag() {
        return timesTag;
    }

    public String getGovtrackId() {
        return govtrackId;
    }

    public String getCspanId() {
        return cspanId;
    }

    public String getVotesmartId() {
        return votesmartId;
    }

    public String getIcpsrId() {
        return icpsrId;
    }

    public String getTwitterAccount() {
        return twitterAccount;
    }

    public String getFacebookAccount() {
        return facebookAccount;
    }

    public String getYoutubeAccount() {
        return youtubeAccount;
    }

    public String getCrpId() {
        return crpId;
    }

    public String getGoogleEntityId() {
        return googleEntityId;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public boolean isInOffice() {
        return inOffice;
    }

    public String getCurrentParty() {
        return currentParty;
    }

    public String getMostRecentVote() {
        return mostRecentVote;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
