package com.example.thetop100

import android.app.Application
import androidx.lifecycle.*

class RepoListViewModel(val repository: RepoDataSource, application: Application) :
    AndroidViewModel(application), LifecycleObserver {

    private val loadingVisibility = MutableLiveData<Boolean>().apply { value = false }
    private val repos = MutableLiveData<List<Repo>>().apply { value = emptyList() }
    private val error = MutableLiveData<Boolean>().apply { value = false }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun load() {
        loadingVisibility.postValue(true)
        error.postValue(false)
        repository.listAll({ list ->
            repos.postValue(list)
            if (list.isEmpty()) {
                error.postValue(true)
            }
            loadingVisibility.postValue(false)
        }, {
            error.postValue(true)
            loadingVisibility.postValue(false)
        }
        )
    }

    fun onRetryClicked(){
        load()
    }

    fun getLoadingVisibility():LiveData<Boolean> = loadingVisibility
    fun getRepos():LiveData<List<Repo>> = repos
    fun getError():LiveData<Boolean> = error

}