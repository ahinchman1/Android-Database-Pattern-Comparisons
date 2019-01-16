package com.example.amandahinchman_dominguez.databasepatterncomparisons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DatabasePatternViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_list_item, parent, false)) {

    private var dataBaseListItem: TextView? = null
    private var databasePattern: DatabasePattern? = null

    init {
        dataBaseListItem = itemView.findViewById(R.id.database_list_item)
    }

    fun bind(dbPattern: DatabasePattern) {
        databasePattern = dbPattern

        dataBaseListItem?.text = dbPattern.pattern
    }
}