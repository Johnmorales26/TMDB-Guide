package com.johndev.tmdb_guide

import android.view.View
import com.johndev.tmdb_guide.common.entities.ResultPerson

interface OnPersonListener {

    fun OnClick(person: ResultPerson, imgView: View, name: View)

}