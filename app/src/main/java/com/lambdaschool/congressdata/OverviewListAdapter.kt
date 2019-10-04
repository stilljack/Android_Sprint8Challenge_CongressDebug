package com.lambdaschool.congressdata

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class OverviewListAdapter(private val dataList: ArrayList<OfficialOverview>) : RecyclerView.Adapter<OverviewListAdapter.ViewHolder>() {
    private var context: Context? = null

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

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): OverviewListAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.congressperson_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OverviewListAdapter.ViewHolder, position: Int) {
        val data = dataList[position]

        holder.textListName.text = data.displayName
        holder.textListParty.text = data.party
        holder.textListState.text = data.state

        holder.cardParent.tag = data.id
        holder.cardParent.setOnClickListener { view ->
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", view.tag.toString())
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
