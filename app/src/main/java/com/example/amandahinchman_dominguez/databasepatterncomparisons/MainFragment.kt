package com.example.amandahinchman_dominguez.databasepatterncomparisons

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {

    lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = SQLiteHelper(context as Context, (context as Context).assets).writableDatabase

        fragment_recycler_view.apply {
            // adapter = DatabasePatternAdapter(dbPatterns)

        }
    }

    companion object {

        fun newInstance() = MainFragment().apply {
            arguments = Bundle()
        }
    }
}