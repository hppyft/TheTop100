package com.example.thetop100

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thetop100.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(mBinding.root)

        mBinding.linksList.adapter = LinkAdapter(createDummyList())

    }

    private fun createDummyList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        for (x in 0..100)
            list.add(Pair("Um repositorio", "Aqui vai o link"))
        return list
    }

}
