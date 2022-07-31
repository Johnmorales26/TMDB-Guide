package com.johndev.tmdb_guide.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.common.utils.Constans.IMAGES_URL
import com.johndev.tmdb_guide.detailsActorModel.viewModel.ActorViewModel
import java.lang.NumberFormatException
import java.util.*

fun main() {
    //getProductionCompaniesList("[ProductionCompanies(id=290, logo_path=/Q8mw2AOQQc8Qg0uNwLWq86DVZv.png, name=Ingenious Media, origin_country=GB), ProductionCompanies(id=7076, logo_path=/8BFxn9NUWSgp0ndih569Gm62xiC.png, name=Chernin Entertainment, origin_country=US), ProductionCompanies(id=22213, logo_path=/qx9K6bFWJupwde0xQDwOvXkOaL8.png, name=TSG Entertainment, origin_country=US)]")
    //getSpokenLanguajesList("[SpokenLanguages(english_name=String, iso_639_1=String, name=String), SpokenLanguages(english_name=String, iso_639_1=String, name=String)]")
}

fun getImageResource(imgPath: String) = IMAGES_URL + imgPath

fun validateNumberID(id: String): Boolean {
    return try {
        id.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun getGenresList(genresString: String) : MutableList<Genre> {
    var modifyText = genresString
    val genreList: MutableList<Genre> = mutableListOf()
    val values: MutableList<String> = mutableListOf("[", "]")
    values.forEach {
        modifyText = modifyText.replace(it, "")
    }
    modifyText += ","
    modifyText.split("),").forEach {
        var index = 0
        var modText = it
        modText = modText.replace("Genre(", "")
        modText = modText.replace("id=", "")
        modText = modText.replace("name=", "")
        modText = modText.trim()
        var id: Int = 0
        var genre: String = ""
        modText.split(",").forEach { values ->
            when(index) {
                0 -> {
                    val isNumber = try {
                        values.trim().toInt()
                        true
                    } catch (e: NumberFormatException) {
                        false
                    }
                    if (isNumber) {
                        id = values.trim().toInt()
                    }
                }
                1 -> {
                    genre = values.toString().trim()
                }
            }
            index++
        }
        genreList.add(Genre(id, genre))
    }
    genreList.removeLast()
    return genreList
}

fun getProductionCompaniesList(languajesString: String): MutableList<ProductionCompanies> {
    val productionLanguajes: MutableList<ProductionCompanies> = mutableListOf()
    var modifyText = languajesString
    val values: MutableList<String> = mutableListOf("[", "]")
    values.forEach {
        modifyText = modifyText.replace(it, "")
    }
    modifyText += ","
    modifyText.split("),").forEach {
        var index = 0
        var modText = it
        modText = modText.replace("ProductionCompanies(", "")
        modText = modText.replace("id=", "")
        modText = modText.replace("name=", "")
        modText = modText.replace("logo_path=", "")
        modText = modText.replace("origin_country=", "")
        modText = modText.trim()
        var id: Int = 0
        var name: String = ""
        var logo_path= ""
        var origin_country= ""
        modText.split(",").forEach { values ->
            when(index) {
                0 -> {
                    val isNumber = try {
                        values.trim().toInt()
                        true
                    } catch (e: NumberFormatException) {
                        false
                    }
                    if (isNumber) {
                        id = values.trim().toInt()
                    }
                }
                1 -> {
                    logo_path = values.toString().trim()
                }
                2 -> {
                    name = values.toString().trim()
                }
                3 -> {
                    origin_country = values.toString().trim()
                }
            }
            index++
        }
        productionLanguajes.add(ProductionCompanies(id = id, logo_path = logo_path, name = name, origin_country = origin_country))
    }
    productionLanguajes.removeLast()
    println(productionLanguajes.size)
    return productionLanguajes
}

fun getSpokenLanguajesList(languajesString: String) : MutableList<SpokenLanguages> {
    val languajesList: MutableList<SpokenLanguages> = mutableListOf()
    var modifyText = languajesString
    val values: MutableList<String> = mutableListOf("[", "]")
    values.forEach {
        modifyText = modifyText.replace(it, "")
    }
    modifyText += ","
    modifyText.split("),").forEach {
        var index = 0
        var modText = it
        modText = modText.replace("SpokenLanguages(", "")
        modText = modText.replace("english_name=", "")
        modText = modText.replace("iso_639_1=", "")
        modText = modText.replace("name=", "")
        modText = modText.trim()
        var english_name: String = ""
        var iso_639_1: String = ""
        var name: String = ""
        modText.split(",").forEach { values ->
            when(index) {
                0 -> { english_name = values.toString().trim() }
                1 -> { iso_639_1 = values.toString().trim() }
                2 -> { name = values.toString().trim() }
            }
            index++
        }
        languajesList.add(SpokenLanguages(english_name, iso_639_1, name))
    }
    languajesList.removeLast()
    return languajesList
}

fun transformMAtoME(movieModel: MoviesAPI): MoviesEntity {
    return MoviesEntity(
        adult = movieModel.adult, backdrop_path = movieModel.backdrop_path, budget = movieModel.budget,
        genres = movieModel.genres.toString(), homepage = movieModel.homepage, id = movieModel.id,
        imdb_id = movieModel.imdb_id, original_language = movieModel.original_language, original_title = movieModel.original_title,
        overview = movieModel.overview, popularity = movieModel.popularity, poster_path = movieModel.poster_path,
        production_companies = movieModel.production_companies.toString(), production_countries = movieModel.production_countries.toString(),
        release_date = movieModel.release_date, revenue = movieModel.revenue, spoken_languages = movieModel.spoken_languages.toString(),
        status = movieModel.status, tagline = movieModel.tagline, title = movieModel.title, video = movieModel.video,
        vote_average = movieModel.vote_average, vote_count = movieModel.vote_count
    )
}

fun hideKeyBoard(context: Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}