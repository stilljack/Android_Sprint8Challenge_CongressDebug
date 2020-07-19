
package com.lambdaschool.congressdata.importedjava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Role {

    // SerializedName("congress")
    private String congress;
    // SerializedName("chamber")
    private String chamber;
    // SerializedName("title")
    private String title;
    // SerializedName("short_title")
    private String shortTitle;
    // SerializedName("state")
    private String state;
    // SerializedName("party")
    private String party;
    // SerializedName("leadership_role")
    private String leadershipRole;
    // SerializedName("fec_candidate_id")
    private String fecCandidateId;
    // SerializedName("seniority")
    private String seniority;
    // SerializedName("district")
    private String district;
    // SerializedName("at_large")
    private boolean atLarge;
    // SerializedName("ocd_id")
    private String ocdId;
    // SerializedName("start_date")
    private String startDate;
    // SerializedName("end_date")
    private String endDate;
    // SerializedName("office")
    private String office;
    // SerializedName("phone")
    private String phone;
    // SerializedName("fax")
    private String fax;
    // SerializedName("contact_form")
    private String contactForm;
    // SerializedName("bills_sponsored")
    private int billsSponsored;
    // SerializedName("bills_cosponsored")
    private int billsCosponsored;
    // SerializedName("missed_votes_pct")
    private double missedVotesPct;
    // SerializedName("votes_with_party_pct")
    private double votesWithPartyPct;
    // SerializedName("committees")
    private List<Committee> committees = null;
    // SerializedName("subcommittees")
    private List<Subcommittee> subcommittees = null;

    public Role(String congress, String chamber, String title, String shortTitle, String state, String party, String leadershipRole, String fecCandidateId, String seniority, String district, boolean atLarge, String ocdId, String startDate, String endDate, String office, String phone, String fax, String contactForm, int billsSponsored, int billsCosponsored, double missedVotesPct, double votesWithPartyPct, List<Committee> committees, List<Subcommittee> subcommittees) {
        this.congress = congress;
        this.chamber = chamber;
        this.title = title;
        this.shortTitle = shortTitle;
        this.state = state;
        this.party = party;
        this.leadershipRole = leadershipRole;
        this.fecCandidateId = fecCandidateId;
        this.seniority = seniority;
        this.district = district;
        this.atLarge = atLarge;
        this.ocdId = ocdId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.office = office;
        this.phone = phone;
        this.fax = fax;
        this.contactForm = contactForm;
        this.billsSponsored = billsSponsored;
        this.billsCosponsored = billsCosponsored;
        this.missedVotesPct = missedVotesPct;
        this.votesWithPartyPct = votesWithPartyPct;
        this.committees = committees;
        this.subcommittees = subcommittees;
    }

    public Role(JSONObject jsonObject) {
        this.congress = CongressDao.getStringFromJson(jsonObject, "congress");
        this.chamber = CongressDao.getStringFromJson(jsonObject, "chamber");
        this.title = CongressDao.getStringFromJson(jsonObject, "title");
        this.shortTitle = CongressDao.getStringFromJson(jsonObject, "short_title");
        this.state = CongressDao.getStringFromJson(jsonObject, "state");
        this.party = CongressDao.getStringFromJson(jsonObject, "party");
        this.leadershipRole = CongressDao.getStringFromJson(jsonObject, "leadership_role");
        this.fecCandidateId = CongressDao.getStringFromJson(jsonObject, "fec_candidate_id");
        this.seniority = CongressDao.getStringFromJson(jsonObject, "seniority");
        this.district = CongressDao.getStringFromJson(jsonObject, "district");
        this.atLarge = CongressDao.getBooleanFromJson(jsonObject, "at_large");
        this.ocdId = CongressDao.getStringFromJson(jsonObject, "ocd_id");
        this.startDate = CongressDao.getStringFromJson(jsonObject, "start_date");
        this.endDate = CongressDao.getStringFromJson(jsonObject, "end_date");
        this.office = CongressDao.getStringFromJson(jsonObject, "office");
        this.phone = CongressDao.getStringFromJson(jsonObject, "phone");
        this.fax = CongressDao.getStringFromJson(jsonObject, "fax");
        this.contactForm = CongressDao.getStringFromJson(jsonObject, "contact_form");
        this.billsSponsored = CongressDao.getIntFromJson(jsonObject, "bills_sponsored");
        this.billsCosponsored = CongressDao.getIntFromJson(jsonObject, "bills_cosponsored");
        this.missedVotesPct = CongressDao.getDoubleFromJson(jsonObject, "missed_votes_pct");
        this.votesWithPartyPct = CongressDao.getDoubleFromJson(jsonObject, "votes_with_party_pct");
        this.committees = Committee.getCommitteesFromJSON(CongressDao.getJSONArrayFromJson(jsonObject, "committees"));
        this.subcommittees = Subcommittee.getSubCommitteesFromJSON(CongressDao.getJSONArrayFromJson(jsonObject, "subcommittees"));
    }

    static ArrayList<Role> getRolesFromJSON(JSONArray jsonArray) {
        ArrayList<Role> roles = new ArrayList<Role>();
        for(int i = 0; i < jsonArray.length(); ++i) {
            try {
                roles.add(new Role(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return roles;
    }

    public String getCongress() {
        return congress;
    }

    public String getChamber() {
        return chamber;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getState() {
        return state;
    }

    public String getParty() {
        return party;
    }

    public String getLeadershipRole() {
        return leadershipRole;
    }

    public String getFecCandidateId() {
        return fecCandidateId;
    }

    public String getSeniority() {
        return seniority;
    }

    public String getDistrict() {
        return district;
    }

    public boolean isAtLarge() {
        return atLarge;
    }

    public String getOcdId() {
        return ocdId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOffice() {
        return office;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getContactForm() {
        return contactForm;
    }

    public int getBillsSponsored() {
        return billsSponsored;
    }

    public int getBillsCosponsored() {
        return billsCosponsored;
    }

    public double getMissedVotesPct() {
        return missedVotesPct;
    }

    public double getVotesWithPartyPct() {
        return votesWithPartyPct;
    }

    public List<Committee> getCommittees() {
        return committees;
    }

    public List<Subcommittee> getSubcommittees() {
        return subcommittees;
    }
}
