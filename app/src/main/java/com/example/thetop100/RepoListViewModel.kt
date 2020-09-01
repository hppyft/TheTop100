package com.example.thetop100

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*

class RepoListViewModel(val repository: RepoDataSource, application: Application) :
    AndroidViewModel(application), LifecycleObserver {

    val loadingVisibility = MutableLiveData<Boolean>().apply { value = false }
    var repos = MutableLiveData<List<Repo>>().apply { value = emptyList() }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        loadingVisibility.postValue(true)
        repository.listAll({ list ->
            repos.postValue(list)
            if (list.isEmpty()) {
                //TODO caso n tenha nada
            }
            loadingVisibility.postValue(false)
        }, {
            //TODO caso de erro
            loadingVisibility.postValue(false)
        }
        )
    }

}