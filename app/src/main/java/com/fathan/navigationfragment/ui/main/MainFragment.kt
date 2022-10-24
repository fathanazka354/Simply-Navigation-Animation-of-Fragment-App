package com.fathan.navigationfragment.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fathan.navigationfragment.R

class MainFragment : Fragment() {

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHeroes = requireActivity().findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)
        list.addAll(listHeroes)
        showRecyclerList()
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(requireActivity(), "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
        val mDetailCategoryFragment = DetailFragment()
        val mBundle = Bundle()
        mBundle.putParcelable(DetailFragment.EXTRA_NAME, hero)
        mDetailCategoryFragment.arguments = mBundle
        val mFragmentManager = parentFragmentManager
        val image = requireActivity().findViewById<ImageView>(R.id.img_item_photo)
        ViewCompat.setTransitionName(image, "hero_image")
        mFragmentManager.beginTransaction().apply {
            replace(R.id.container, mDetailCategoryFragment, DetailFragment::class.java.simpleName)
            addToBackStack(null)
            addSharedElement(image, "hero_image")
            commit()
        }
    }

    private val listHeroes: ArrayList<Hero>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listHero = ArrayList<Hero>()
            for (i in dataName.indices) {
                val hero = Hero(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listHero.add(hero)
            }
            return listHero
        }
    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}