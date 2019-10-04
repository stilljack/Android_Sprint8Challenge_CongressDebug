package com.lambdaschool.congressdata.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.congressdata.activities.DetailsActivity
import com.lambdaschool.congressdata.model.OfficialOverview
import com.lambdaschool.congressdata.R

import java.util.ArrayList

class OverviewListAdapter(private val dataList: ArrayList<OfficialOverview> = arrayListOf<OfficialOverview>()) : androidx.recyclerview.widget.ListAdapter<OfficialOverview,OverviewListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OfficialOverview>() {
            override fun areItemsTheSame(oldItem: OfficialOverview, newItem: OfficialOverview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OfficialOverview, newItem: OfficialOverview): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.displayName == oldItem.displayName &&
                        oldItem.party == newItem.party &&
                        oldItem.state == newItem.state
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textListName: TextView
        var textListParty: TextView
        var textListState: TextView
        var cardParent: ViewGroup

        init {

            textListName = view.findViewById(R.id.text_list_name)
            textListParty = view.findViewById(R.id.text_list_party)
            textListState = view.findViewById(R.id.text_list_state)
            cardParent = view.findViewById(R.id.card_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.congressperson_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.textListName.text = data.displayName
        holder.textListParty.text = data.party
        holder.textListState.text = data.state

        holder.cardParent.tag = data.id
        holder.cardParent.setOnClickListener { view ->
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("id", view.tag.toString())
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
