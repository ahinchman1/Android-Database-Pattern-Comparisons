package com.example.amandahinchmandominguez.formrefactorprototype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.amandahinchman_dominguez.databasepatterncomparisons.DatabasePattern
import com.example.amandahinchman_dominguez.databasepatterncomparisons.DatabasePatternViewHolder

class DatabasePatternAdapter(private val list: List<DatabasePattern>) :
    RecyclerView.Adapter<DatabasePatternViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabasePatternViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DatabasePatternViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: DatabasePatternViewHolder, position: Int) {
        val databasePattern: DatabasePattern = list[position]

        holder.bind(databasePattern)
    }

}