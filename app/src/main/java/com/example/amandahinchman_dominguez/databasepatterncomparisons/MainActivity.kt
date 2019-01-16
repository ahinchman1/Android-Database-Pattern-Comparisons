package com.example.amandahinchman_dominguez.databasepatterncomparisons

import android.support.v4.app.Fragment

class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = MainFragment.newInstance()

}
