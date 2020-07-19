package com.lambdaschool.congressdata.model


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.congressdata.importedjava.CongressDao

import java.util.ArrayList

object OverviewListRepository {
    val overviewList: LiveData<ArrayList<OfficialOverview>>
        get() {
            Log.i("Repository", "Retreiving Data")
            val listLiveData = MutableLiveData<ArrayList<OfficialOverview>>()

            val overviews = ArrayList<OfficialOverview>()
            val allMembers = CongressDao.getAllMembers()

            for (member in allMembers) {
                overviews.add(OfficialOverview(member))
            }

            listLiveData.value = overviews

            return listLiveData
        }
}
