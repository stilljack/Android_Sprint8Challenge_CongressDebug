package com.lambdaschool.congressdata.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.congressdata.model.CongresspersonProfile
import com.lambdaschool.congressdata.model.ProfileRepository

class CongresspersonProfileViewModel(var id: String?) : ViewModel() {
    var profile: LiveData<CongresspersonProfile>? = null
    get() {
        return if (field == null) {
            loadProfile()
        } else {
            field
        }
    }

    private fun loadProfile(): LiveData<CongresspersonProfile>? {
        profile = ProfileRepository.getProfile(id)
        return profile
    }
}
