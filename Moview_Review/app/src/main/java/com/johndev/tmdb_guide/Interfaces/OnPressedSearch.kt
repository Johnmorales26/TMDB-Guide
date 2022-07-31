package com.johndev.tmdb_guide.Interfaces

import android.view.View
import com.johndev.tmdb_guide.common.entities.SearchData

interface OnPressedSearch {
    fun onSearchPressed(data: SearchData, imgPhoto: View, tvName: View)
}