package com.johndev.tmdb_guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.johndev.tmdb_guide.databinding.FragmentHomeBinding
import com.johndev.tmdb_guide.databinding.FragmentNotConectedBinding

class NotConectedFragment : Fragment() {

    private var _binding: FragmentNotConectedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotConectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}