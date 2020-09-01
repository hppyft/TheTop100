package com.example.thetop100

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thetop100.databinding.RepoListViewBinding

class RepoListView : Fragment() {

    private lateinit var mBinding: RepoListViewBinding
    private lateinit var mViewModel: RepoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = RepoListViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = this
        createViewModel()
        this.lifecycle.addObserver(mViewModel)
        configList()
        configLoading()
        configError()
        return mBinding.root
    }

    private fun createViewModel() {
        val service = RetrofitConfig.gitHubService()
        val dataSource = GitHubDataSource(service)
        val factory = ViewModelFactory(dataSource, activity!!.application)
        mViewModel = ViewModelProvider(this, factory).get(RepoListViewModel::class.java)
    }

    private fun configList() {
        val layoutManager = StaggeredGridLayoutManager(
            context!!.resources.getInteger(R.integer.number_of_repo_columns),
            StaggeredGridLayoutManager.VERTICAL
        )
        mBinding.linksList.layoutManager = layoutManager
        val linkAdapter = LinkAdapter(this@RepoListView.activity!!, mViewModel.getRepos().value)
        mBinding.linksList.adapter = linkAdapter
        mViewModel.getRepos().observe(this, Observer() {
            if (it.isEmpty()) {
                showError()
            } else {
                linkAdapter.replaceItems(it)
            }
        })
    }

    private fun configLoading() {
        mViewModel.getLoadingVisibility().observe(this, Observer() {
            if (it) {
                mBinding.linksList.visibility = View.GONE
                mBinding.progress.visibility = View.VISIBLE
            } else {
                mBinding.linksList.visibility = View.VISIBLE
                mBinding.progress.visibility = View.GONE
            }
        })
    }

    private fun configError() {
        mViewModel.getError().observe(this, Observer() {
            if (it) {
                showError()
            } else {
                hideError()
            }
        })
        mBinding.retryBtn.setOnClickListener {
            mViewModel.onRetryClicked()
        }
    }

    private fun showError() {
        mBinding.linksList.visibility = View.GONE
        mBinding.errorLayout.visibility = View.VISIBLE
    }

    private fun hideError() {
        mBinding.errorLayout.visibility = View.GONE
    }
}