package com.lambdaschool.congressdata


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

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
