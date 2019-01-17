package com.example.amandahinchman_dominguez.databasepatterncomparisons

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.sqldelight.android.AndroidSqliteDriver
import db.yello.Hurricane_sql_delight
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class MainFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val driver = AndroidSqliteDriver(Database.Schema, context as Context, "hurricane.db")
        val database = Database(driver)

        var inputStream: InputStream? = null

        try {
            inputStream = (context as Context).assets.open("atlantic.csv")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()

        database.hurricanesQueries.transaction {
            try {
                while (line != null) {
                    val tokens = line.split(",")
                    if (tokens.isNotEmpty()) {
                        val impl = Hurricane_sql_delight.Impl(tokens[0], tokens[1], tokens[2],
                            tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9],
                            tokens[10].toLong(), tokens[11].toLong(), tokens[12].toLong(), tokens[13].toLong(),
                            tokens[14].toLong(), tokens[15].toLong(), tokens[16].toLong(), tokens[17].toLong(),
                            tokens[18].toLong(), tokens[19].toLong(), tokens[20].toLong(), tokens[21].toLong())
                        database.hurricanesQueries.insert_row(impl)
                    }
                    line = reader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

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