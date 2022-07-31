package com.johndev.tmdb_guide.DetailsActor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.Actors.Actor
import com.johndev.tmdb_guide.Actors.KnownFor
import com.johndev.tmdb_guide.Constans
import com.johndev.tmdb_guide.Constans.API_KEY
import com.johndev.tmdb_guide.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.Constans.API_PERSON_DETAILS
import com.johndev.tmdb_guide.Constans.URL_BASE
import com.johndev.tmdb_guide.DetailsMovie.DetailsMovieActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.PopularMovies.MoviePopular
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ActivityDetailsActorBinding

class DetailsActorActivity : AppCompatActivity(), OnPressedMovie {

    private lateinit var binding: ActivityDetailsActorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsActorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recibeActor()
    }

    private fun recibeActor() {
        val idActor = intent.getStringExtra(getString(R.string.key_actor_passed))
        getDetailsActor(idActor.toString().trim())
    }

    private fun getDetailsActor(url: String) {
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()
        val url = URL_BASE + API_PERSON_DETAILS + url + API_KEY_INDEX_SEARCH + API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val actor = gson.fromJson(response, Actor::class.java)
            updateUI(actor)
        }) {
            Toast.makeText(this, "No such actor exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    private fun updateUI(actorModel: Actor) {
        val imagePosterPath = "${Constans.IMAGES_URL}${actorModel.profile_path}"
        binding.apply {
            tvTitle.text = actorModel.name.toString().trim()
            tvDepartment.text = actorModel.known_for_department.toString().trim()
            //  Upadte Images
            imgBackdropVisible.load(imagePosterPath) {
                crossfade(true)
                scale(Scale.FILL)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_broken_image)
            }
            //  Update Gender
            when (actorModel.gender!!.toInt()) {
                1 -> binding.tvGender.text = "Femenino"
                2 -> binding.tvGender.text = "Masculino"
            }
            //  Popularity
            binding.tvPopularity.text = actorModel.popularity.toString().trim()
            //  Conocido por
            binding.tvBiography.text = actorModel.biography.toString().trim()
            //  Birthday and Deathday
            binding.tvBirthday.text = actorModel.birthday.toString().trim()
            binding.tvDeathday.text = actorModel.deathday.toString().trim()
            //  Place Of Birth
            binding.tvPlaceOfBirth.text = actorModel.place_of_birth.toString().trim()
        }
    }

    override fun OnMovieKnowPressed(movie: KnownFor) {
        val intent = Intent(this, DetailsMovieActivity::class.java).apply {
            putExtra(getString(R.string.key_movie_passed), movie.id.toString().trim())
        }
        startActivity(intent)
    }

    override fun OnMoviePressed(movie: MoviePopular) {}
}