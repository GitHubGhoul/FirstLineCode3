package com.wxd.firstlinecode.uiwidget

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wxd.firstlinecode.R

sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)
class LeftViewHolder(view: View) : MsgViewHolder(view) {
    val leftMsg: TextView = view.findViewById(R.id.leftMsg)
}
class RightViewHolder(view: View) : MsgViewHolder(view) {
    val rightMsg: TextView = view.findViewById(R.id.rightMsg)
}