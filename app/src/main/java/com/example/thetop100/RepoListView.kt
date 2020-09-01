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
        val linkAdapter = LinkAdapter(this@RepoListView.activity!!, mViewModel.repos.value)
        mBinding.linksList.adapter = linkAdapter
        mViewModel.repos.observe(this, Observer() {
            linkAdapter.replaceItems(it)
        })
    }

    private fun configLoading() {
        mViewModel.loadingVisibility.observe(this, Observer() {
            if (it) {
                mBinding.linksList.visibility = View.GONE
                mBinding.progress.visibility = View.VISIBLE
            } else {
                mBinding.linksList.visibility = View.VISIBLE
                mBinding.progress.visibility = View.GONE
            }
        })
    }
}