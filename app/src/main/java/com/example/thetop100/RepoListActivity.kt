package com.example.thetop100

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thetop100.databinding.RepoListActivityBinding


class RepoListActivity : AppCompatActivity() {

    private lateinit var mBinding: RepoListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = RepoListActivityBinding.inflate(layoutInflater, null, false)
        setContentView(mBinding.root)
        addFragmentTo(R.id.content_frame, createFragment())
    }

    private fun createFragment(): Fragment = RepoListView()
}
