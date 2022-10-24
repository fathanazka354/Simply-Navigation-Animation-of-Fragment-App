package com.fathan.navigationfragment.ui.main

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.fathan.navigationfragment.R

class DetailFragment : Fragment() {
    lateinit var tvCategoryName: TextView
    lateinit var image: ImageView
    var description: Hero? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_judul)
        image = view.findViewById(R.id.imageView)

        if (arguments != null) {
            val categoryName = arguments?.getParcelable<Hero>(EXTRA_NAME)
            tvCategoryName.text = categoryName?.name.toString()
            categoryName?.photo?.let { image.setImageResource(it) }
        }

        ViewCompat.setTransitionName(image, "hero_image")
    }
    companion object {
        var EXTRA_NAME = "extra_name"
    }
}