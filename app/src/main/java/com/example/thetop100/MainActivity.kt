package com.example.thetop100

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        configList()
    }

    private fun configList() {
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
        mBinding.linksList.layoutManager = layoutManager

        val call = RetrofitConfig().gitHubService().listRepos()
        call.enqueue(object : Callback<List<Repo>?> {
            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro ao baixar repositorios", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                response.body()?.let {
                    mBinding.linksList.adapter = LinkAdapter(this@MainActivity, it)
                }
            }

        })
    }
}
