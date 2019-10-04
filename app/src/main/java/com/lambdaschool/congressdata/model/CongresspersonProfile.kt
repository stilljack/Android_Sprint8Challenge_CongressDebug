package com.lambdaschool.congressdata.model

import android.graphics.Bitmap
import com.lambdaschool.congressdata.importedjava.Committee
import com.lambdaschool.congressdata.importedjava.CongressDao
import com.lambdaschool.congressdata.importedjava.CongresspersonDetails
import com.lambdaschool.congressdata.importedjava.Subcommittee


import org.json.JSONArray
import org.json.JSONException

import java.util.ArrayList

class CongresspersonProfile(
        val firstName: String,
        val middleName: String,
        val lastName: String,
        var party: String,
        val state: String,
        val id: String,
        val twitterAccount: String,
        val facebookAccount: String,
        val district: String,
        val phone: String, missedVotesPct: String,
        val office: String, votesWithPartyPct: String) {

    val displayName: String
    val location: String
    val missedVotesPct: Float
    private val votesWithPartyPct: Float
    var committees: ArrayList<String>? = null
        private set
    var subcommittees: ArrayList<String>? = null
        private set
    var image: Bitmap? = null

    // primary progress has been arbitrarily chosen to be red
    val primaryProgress: Float
        get() = if (this.party == REPUBLICAN) {
            this.votesWithPartyPct
        } else {
            100f - this.votesWithPartyPct - this.missedVotesPct
        }

    // secondary progress has been arbitrarily chosen to be blue
    val secondaryProgress: Float
        get() = if (this.party == DEMOCRAT) {
            this.votesWithPartyPct
        } else {
            100f - this.votesWithPartyPct - this.missedVotesPct
        }

    init {
        this.location = "$state - District $district"
        this.missedVotesPct = java.lang.Float.parseFloat(missedVotesPct)
        this.votesWithPartyPct = java.lang.Float.parseFloat(votesWithPartyPct)
        this.displayName = buildDisplayName()
        when (party) {
            "R" -> this.party =
                REPUBLICAN
            "D" -> this.party =
                DEMOCRAT
            "I" -> this.party =
                INDEPENDENT
        }
    }

    constructor(congresspersonDetails: CongresspersonDetails) : this(congresspersonDetails.firstName,
            congresspersonDetails.middleName,
            congresspersonDetails.lastName,
            congresspersonDetails.currentParty,
            congresspersonDetails.roles[0].state,
            congresspersonDetails.memberId,
            congresspersonDetails.twitterAccount,
            congresspersonDetails.facebookAccount,
            congresspersonDetails.roles[0].district,
            congresspersonDetails.roles[0].phone,
            java.lang.Double.toString(congresspersonDetails.roles[0].missedVotesPct),
            congresspersonDetails.roles[0].office,
            java.lang.Double.toString(congresspersonDetails.roles[0].votesWithPartyPct)) {

        //get correct role index
        var index = 0
        for (i in 0 until congresspersonDetails.roles.size) {
            if (congresspersonDetails.roles[i].congress == CongressDao.CONGRESS_NUMBER) {
                index = i
                break
            }
        }

        val committees = congresspersonDetails.roles[index].committees
        this.setCommittees(committees)
        val subcommittees = congresspersonDetails.roles[index].subcommittees
        this.setSubcommittees(subcommittees)
    }

    private fun buildDisplayName(): String {
        val nameBuilder = StringBuilder()
        nameBuilder.append(firstName).append(" ")
        if (middleName != "null") {
            nameBuilder.append(middleName).append(" ")
        }
        nameBuilder.append(lastName)
        return nameBuilder.toString()
    }

    fun setCommittees(committeesJson: JSONArray) {
        this.committees = parseJsonList(committeesJson,
            COMMITTEE_DETAIL_TARGET
        )
    }

    fun setCommittees(committees: List<Committee>) {
        this.committees = ArrayList()
        for (committee in committees) {
            this.committees!!.add(committee.name)
        }
    }

    fun setSubcommittees(subcommitteesJson: JSONArray) {
        this.committees = parseJsonList(subcommitteesJson,
            COMMITTEE_DETAIL_TARGET
        )
        this.subcommittees = this.committees
    }

    fun setSubcommittees(subcommittees: List<Subcommittee>) {
        this.subcommittees = ArrayList()
        for (subcommittee in subcommittees) {
            this.subcommittees!!.add(subcommittee.name)
        }
    }

    private fun parseJsonList(array: JSONArray, target: String): ArrayList<String> {
        val result = ArrayList<String>()

        for (i in 0 until array.length()) {
            try {
                result.add(array.getJSONObject(i).getString(target))
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        return result
    }

    companion object {

        val INDEPENDENT = "Independent"
        val REPUBLICAN = "Republican"
        val DEMOCRAT = "Democrat"
        val COMMITTEE_DETAIL_TARGET = "name"
    }
}
