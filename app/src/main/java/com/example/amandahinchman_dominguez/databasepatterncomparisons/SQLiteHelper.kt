package com.example.amandahinchman_dominguez.databasepatterncomparisons

import android.content.ContentValues
import android.content.Context
import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.*

class SQLiteHelper(
    context: Context,
    private var manager: AssetManager) :
    SQLiteOpenHelper(context, table, null, version) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { /** no upgrades for now */ }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableStatement)
        var inputStream: InputStream? = null

        try {
            inputStream = manager.open("atlantic.csv")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        db?.beginTransaction()

        try {
            while (line != null) {
                val tokens = line.split(",")
                if (tokens.isNotEmpty()) {
                    val values = getContentValues(tokens.size, tokens)
                    db?.insert(table, null, values)
                }
                line = reader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        db?.setTransactionSuccessful()
        db?.endTransaction()
    }


    companion object {
        const val table = "hurricanes"
        const val version = 1

        const val ID = "id"
        const val NAME = "name"
        const val DATE = "dateCol"
        const val TIME = "timeCol"
        const val EVENT = "event"
        const val STATUS = "status"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val MAXIMUM_WIND = "maximumWind"
        const val MINIMUM_PRESSURE = "minimumPressure"
        const val LOW_WIND_NE = "lowWindNE"
        const val LOW_WIND_SE = "lowWindSE"
        const val LOW_WIND_SW = "lowWindSW"
        const val LOW_WIND_NW = "lowWindNW"
        const val MODERATE_WIND_NE = "moderateWindNE"
        const val MODERATE_WIND_SE = "moderateWindSE"
        const val MODERATE_WIND_SW = "moderateWindSW"
        const val MODERATE_WIND_NW = "moderateWindNW"
        const val HIGH_WIND_SE = "highWindSE"
        const val HIGH_WIND_NE = "highWindNE"
        const val HIGH_WIND_SW = "highWindSW"
        const val HIGH_WIND_NW = "highWindNW"

        val createTableStatement = """
            CREATE TABLE $table(
            $ID STRING,
            $NAME STRING,
            $DATE STRING,
            $TIME STRING,
            $EVENT STRING,
            $STATUS STRING,
            $LATITUDE STRING,
            $LONGITUDE STRING,
            $MAXIMUM_WIND STRING,
            $MINIMUM_PRESSURE STRING,
            $LOW_WIND_NE INTEGER,
            $LOW_WIND_SE INTEGER,
            $LOW_WIND_SW INTEGER,
            $LOW_WIND_NW INTEGER,
            $MODERATE_WIND_NE INTEGER,
            $MODERATE_WIND_SE INTEGER,
            $MODERATE_WIND_SW INTEGER,
            $MODERATE_WIND_NW INTEGER,
            $HIGH_WIND_SE INTEGER,
            $HIGH_WIND_NE INTEGER,
            $HIGH_WIND_SW INTEGER,
            $HIGH_WIND_NW INTEGER)
        """.trimIndent()
    }

    private fun getContentValues(cap: Int, tokens: List<String>): ContentValues {
        return ContentValues(cap).apply {
            put(ID, tokens[0].trim())
            put(NAME, tokens[1].trim())
            put(DATE, tokens[2].trim())
            put(TIME, tokens[3].trim())
            put(EVENT, tokens[4].trim())
            put(STATUS, tokens[5].trim())
            put(LATITUDE, tokens[6].trim())
            put(LONGITUDE, tokens[7].trim())
            put(MAXIMUM_WIND, tokens[8].trim())
            put(MINIMUM_PRESSURE, tokens[9].trim())
            put(LOW_WIND_NE, tokens[10].trim().toInt())
            put(LOW_WIND_SE, tokens[11].trim().toInt())
            put(LOW_WIND_NW, tokens[12].trim().toInt())
            put(LOW_WIND_SW, tokens[13].trim().toInt())
            put(MODERATE_WIND_NE, tokens[14].trim().toInt())
            put(MODERATE_WIND_SE, tokens[15].trim().toInt())
            put(MODERATE_WIND_NW, tokens[16].trim().toInt())
            put(MODERATE_WIND_SW, tokens[17].trim().toInt())
            put(HIGH_WIND_NE, tokens[18].trim().toInt())
            put(HIGH_WIND_SE, tokens[19].trim().toInt())
            put(HIGH_WIND_NW, tokens[20].trim().toInt())
            put(HIGH_WIND_SW, tokens[21].trim().toInt())
        }
    }
}
