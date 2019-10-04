package com.lambdaschool.congressdata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.lifecycle.ViewModel

import java.util.ArrayList

class CongresspersonListViewModel : ViewModel() {
    var overviewList: LiveData<ArrayList<OfficialOverview>>? = null
    get() {
        if (field == null) {
            loadList()
        }
        return field
    }

    private fun loadList(): LiveData<ArrayList<OfficialOverview>>? {
        overviewList = OverviewListRepository.overviewList
        return overviewList
    }
}
