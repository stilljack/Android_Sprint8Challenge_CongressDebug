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
import com.lambdaschool.congressdata.model.CongressPersonAll
import kotlinx.android.synthetic.main.congressperson_list_layout.view.*

import java.util.ArrayList

class OverviewListAdapter(private val dataList: List<CongressPersonAll.Result.Member> = listOf<CongressPersonAll.Result.Member>()) : androidx.recyclerview.widget.ListAdapter<CongressPersonAll.Result.Member,OverviewListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CongressPersonAll.Result.Member>() {
            override fun areItemsTheSame(oldItem: CongressPersonAll.Result.Member, newItem: CongressPersonAll.Result.Member): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CongressPersonAll.Result.Member, newItem: CongressPersonAll.Result.Member): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.district == newItem.district &&
                        oldItem.party == newItem.party &&
                        oldItem.state == newItem.state
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textListName: TextView =view.text_list_name
        var textListParty: TextView =view.text_list_party
        var textListState: TextView= view.text_list_state
        var cardParent: ViewGroup = view.card_view

    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.congressperson_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        val name ="${data.firstName} ${data.lastName}"
        holder.textListName.text = name
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
