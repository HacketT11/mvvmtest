package net.test.cloudmade.screens.search.ui.viewholders

import android.view.View
import kotlinx.android.synthetic.main.item_user.view.*
import net.test.cloudmade.data.user.User

class UserViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: User) = with(item) {
        itemView.loginEt.text = login
        itemView.idEt.text = id.toString()
        itemView.scoreEt.text = score.toString()
    }
}