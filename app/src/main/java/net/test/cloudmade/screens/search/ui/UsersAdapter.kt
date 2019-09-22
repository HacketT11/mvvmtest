package net.test.cloudmade.screens.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.test.cloudmade.R
import net.test.cloudmade.data.user.User
import net.test.cloudmade.screens.search.ui.viewholders.BaseViewHolder
import net.test.cloudmade.screens.search.ui.viewholders.LoadingViewHolder
import net.test.cloudmade.screens.search.ui.viewholders.UserViewHolder
import java.lang.IllegalArgumentException

class UsersAdapter(private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    private companion object {
        const val TYPE_CONTENT = 0
        const val TYPE_LOADING = 1
    }

    var list = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemViewType(position: Int): Int {
        return if (isLastPosition(position)) TYPE_LOADING else TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CONTENT -> UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
            TYPE_LOADING -> LoadingViewHolder(inflater.inflate(R.layout.item_loading, parent, false))
            else -> throw(IllegalArgumentException())
        }
    }

    override fun getItemCount(): Int = if (list.isEmpty()) 0 else list.size + 1

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (!isLastPosition(position)) {
            holder.bind(list[position])
            holder.itemView.setOnClickListener { onItemClick(list[position].login) }
        }
    }

    private fun isLastPosition(position: Int) = position == list.size
}