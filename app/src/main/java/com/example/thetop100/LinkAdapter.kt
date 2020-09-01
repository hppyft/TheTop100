package com.example.thetop100

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thetop100.databinding.LinkItemBinding


class LinkAdapter(private val context: Context, private var list: List<Repo>?) :
    RecyclerView.Adapter<LinkAdapter.ViewHolder>(),AdapterItemsContract {

    class ViewHolder(val mBinding: LinkItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = LinkItemBinding.inflate(layoutInflator, parent, false)
        return ViewHolder(binding)
    }

    override fun replaceItems(list: List<*>) {
        this.list = list as List<Repo>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list!![position]
        holder.mBinding.repoName = "Repositório: ${item.name}"
        holder.mBinding.ownerName = "Autor: ${item.owner.login}"
        holder.mBinding.card.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url))
            context.startActivity(browserIntent)
        }
    }
}