package com.example.sinanerestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sinanerestaurant.databinding.FragmentPictureBinding
import com.squareup.picasso.Picasso


private const val ARG_PICTURE = "picture"

class PictureFragment : Fragment() {

    private lateinit var binding : FragmentPictureBinding
    private var picture: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            picture = it.getString(ARG_PICTURE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(picture?.ifEmpty { null }).into(binding.pictureview)
    }


    companion object {


        @JvmStatic
        fun newInstance(picture: String) =
            PictureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PICTURE, picture)

                }
            }
    }
}