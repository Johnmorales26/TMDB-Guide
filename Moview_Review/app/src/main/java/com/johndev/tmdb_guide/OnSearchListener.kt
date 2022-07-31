package com.johndev.tmdb_guide

import android.view.View
import com.johndev.tmdb_guide.common.entities.ResultSearch

interface OnSearchListener {

    fun onSearchListener(search: ResultSearch, view: View)

}