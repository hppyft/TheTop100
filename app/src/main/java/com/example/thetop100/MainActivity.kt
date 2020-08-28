package com.example.thetop100

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thetop100.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(mBinding.root)

        val call = RetrofitConfig().gitHubService().listRepos()
        call.enqueue(object : Callback<List<Repo>?>{
            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                mBinding.linksList.adapter = LinkAdapter(createDummyList())
            }

            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                response.body()?.let {
                    mBinding.linksList.adapter = LinkAdapter(it)
                }
            }

        })

//        mBinding.linksList.adapter = LinkAdapter(createDummyList())

    }

    private fun createDummyList(): List<Repo> {
        val list = mutableListOf<Repo>()
        for (x in 0..100)
            list.add(Repo("Um repositorio", "Aqui vai o link"))
        return list
    }
}
