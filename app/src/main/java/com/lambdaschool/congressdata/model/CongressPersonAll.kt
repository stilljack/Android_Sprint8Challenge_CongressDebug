package com.lambdaschool.congressdata.model


import com.google.gson.annotations.SerializedName

data class CongressPersonAll(
    var status: String,
    var copyright: String,
    var results: List<Result>
) {
    data class Result(
        var congress: String,
        var chamber: String,
        @SerializedName("num_results")
        var numResults: Int,
        var offset: Int,
        var members: List<Member>
    ) {
        data class Member(
            var id: String,
            var title: String,
            @SerializedName("short_title")
            var shortTitle: String,
            @SerializedName("api_uri")
            var apiUri: String,
            @SerializedName("first_name")
            var firstName: String,
            @SerializedName("last_name")
            var lastName: String,
            @SerializedName("date_of_birth")
            var dateOfBirth: String,
            var gender: String,
            var party: String,
            @SerializedName("twitter_account")
            var twitterAccount: String?,
            @SerializedName("facebook_account")
            var facebookAccount: String?,
            @SerializedName("govtrack_id")
            var govtrackId: String?,
            @SerializedName("cspan_id")
            var cspanId: String?,
            @SerializedName("votesmart_id")
            var votesmartId: String?,
            @SerializedName("icpsr_id")
            var icpsrId: String?,
            @SerializedName("crp_id")
            var crpId: String?,
            @SerializedName("google_entity_id")
            var googleEntityId: String?,
            @SerializedName("fec_candidate_id")
            var fecCandidateId: String,
            var url: String,
            @SerializedName("rss_url")
            var rssUrl: String?,
            @SerializedName("in_office")
            var inOffice: Boolean,
            @SerializedName("dw_nominate")
            var dwNominate: Double?,
            var seniority: String,
            @SerializedName("next_election")
            var nextElection: String,
            @SerializedName("total_votes")
            var totalVotes: Int?,
            @SerializedName("missed_votes")
            var missedVotes: Int?,
            @SerializedName("total_present")
            var totalPresent: Int?,
            @SerializedName("last_updated")
            var lastUpdated: String,
            @SerializedName("ocd_id")
            var ocdId: String,
            var office: String?,
            var phone: String?,
            var fax: String?,
            var state: String,
            var district: String,
            @SerializedName("at_large")
            var atLarge: Boolean,
            var geoid: String,
            @SerializedName("missed_votes_pct")
            var missedVotesPct: Double,
            @SerializedName("votes_with_party_pct")
            var votesWithPartyPct: Double,
            @SerializedName("middle_name")
            var middleName: String?,
            @SerializedName("youtube_account")
            var youtubeAccount: String?,
            @SerializedName("leadership_role")
            var leadershipRole: String?,
            var suffix: String?,
            @SerializedName("contact_form")
            var contactForm: String?,
            @SerializedName("ideal_point")
            var idealPoint: Any?
        )
    }
}