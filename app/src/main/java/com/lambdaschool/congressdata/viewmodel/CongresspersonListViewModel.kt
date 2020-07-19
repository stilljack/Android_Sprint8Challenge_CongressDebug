package com.lambdaschool.congressdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.congressdata.model.OfficialOverview
import com.lambdaschool.congressdata.model.OverviewListRepository
import java.util.*

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
