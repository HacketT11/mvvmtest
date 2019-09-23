package net.test.cloudmade.screens.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repo.view.*
import net.test.cloudmade.R
import net.test.cloudmade.data.repositories.UserRepo

class UserRepoAdapter : RecyclerView.Adapter<UserRepoAdapter.UserRepoViewHolder>() {

    var items = listOf<UserRepo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    class UserRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserRepo) = with(item) {
            itemView.idTv.text = id.toString()
            itemView.nameTv.text = name
            itemView.descriptionTv.text = description
            itemView.languageTv.text = language
        }
    }
}