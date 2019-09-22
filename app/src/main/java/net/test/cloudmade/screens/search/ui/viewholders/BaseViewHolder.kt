package net.test.cloudmade.screens.search.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import net.test.cloudmade.data.user.User

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind(item: User) {}
}