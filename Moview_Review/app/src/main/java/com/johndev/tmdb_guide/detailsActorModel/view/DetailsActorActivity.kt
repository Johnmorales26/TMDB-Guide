package com.johndev.tmdb_guide.detailsActorModel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.common.entities.KnownFor
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PERSON_DETAILS
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.detailsMovieModel.view.DetailsMovieActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.common.entities.MoviePopular
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.utils.getImageResource
import com.johndev.tmdb_guide.databinding.ActivityDetailsActorBinding
import com.johndev.tmdb_guide.detailsActorModel.viewModel.ActorViewModel

class DetailsActorActivity : AppCompatActivity(), OnPressedMovie {

    private lateinit var binding: ActivityDetailsActorBinding
    private lateinit var actorViewModel: ActorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsActorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupObservers()

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        recibeActor()
    }

    private fun setupViewModel() {
        actorViewModel = ViewModelProvider(this)[ActorViewModel::class.java]
    }

    private fun setupObservers() {
        actorViewModel.getResult().observe(this) { actor ->
            if (actor == null) {
                binding.apply {
                    tvTitle.text = "Actor No Disponible"
                }
            } else {
                binding.apply {
                    topAppBar.title = actor.name.toString().trim()
                    tvTitle.text = actor.name.toString().trim()
                    tvDepartment.text = actor.known_for_department.toString().trim()
                    //  Upadte Images
                    imgBackdropVisible.load(getImageResource(actor.profile_path.toString().trim())) {
                        crossfade(true)
                        scale(Scale.FILL)
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_broken_image)
                    }
                    //  Update Gender
                    when (actor.gender!!.toInt()) {
                        1 -> tvGender.text = "Femenino"
                        2 -> tvGender.text = "Masculino"
                    }
                    //  Popularity
                    tvPopularity.text = actor.popularity.toString().trim()
                    //  Conocido por
                    tvBiography.text = actor.biography.toString().trim()
                    //  Birthday and Deathday
                    tvBirthday.text = actor.birthday.toString().trim()
                    tvDeathday.text = actor.deathday.toString().trim()
                    //  Place Of Birth
                    tvPlaceOfBirth.text = actor.place_of_birth.toString().trim()
                }
            }
        }
    }

    private fun recibeActor() {
        val idActor = intent.getStringExtra(getString(R.string.key_actor_passed))
        getDetailsActorFromAPI(idActor.toString().trim())
        actorViewModel.consultActorByCode(idActor.toString().toInt())
    }

    private fun getDetailsActorFromAPI(url: String) {
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()
        val url = URL_BASE + API_PERSON_DETAILS + url + API_KEY_INDEX_SEARCH + API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val actorEntity = gson.fromJson(response, ActorEntity::class.java)
            actorViewModel.saveActor(actorEntity)
        }) {
            Toast.makeText(this, "No such actor exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    override fun OnMovieKnowPressed(movie: KnownFor) {
        val intent = Intent(this, DetailsMovieActivity::class.java).apply {
            putExtra(getString(R.string.key_movie_passed), movie.id.toString().trim())
        }
        startActivity(intent)
    }

    override fun OnMoviePressed(movie: DataRequestEntity, imgPhoto: View) {}
}