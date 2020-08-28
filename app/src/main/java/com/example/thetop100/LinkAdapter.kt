package com.example.thetop100

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thetop100.databinding.LinkItemBinding

class LinkAdapter(private val list: List<Repo>) :
    RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

    class ViewHolder(val mBinding: LinkItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = LinkItemBinding.inflate(layoutInflator, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBinding.text = list[position].name
    }
}