package com.example.comicapp.screen.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comicapp.R
import com.example.comicapp.data.model.Category
import com.example.comicapp.data.model.Comic
import com.example.comicapp.screen.classify.adapter.ListCategoryAdapter
import com.example.comicapp.screen.classify.adapter.ListComicByCategoryAdapter
import com.example.comicapp.screen.detail.DetailFragment
import com.example.comicapp.utils.Constant
import com.example.comicapp.utils.OnItemCategoryClickListener
import com.example.comicapp.utils.OnItemComicClickListener
import com.example.comicapp.utils.extensions.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_classify.view.*

class ClassifyFragment : Fragment(), OnItemCategoryClickListener, OnItemComicClickListener {
    private lateinit var listCategoryAdapter: ListCategoryAdapter
    private lateinit var listComicByCategoryAdapter: ListComicByCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_classify, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    override fun onItemCategoryClick(category: Category?) {
        getListComicsByCategoryFromRealtimeDatabase(category?.name)
    }

    override fun onItemComicClick(comic: Comic?) {
        replaceFragment(R.id.layoutContainer, DetailFragment.newInstance(comic))

        val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationMain)
        bottomNav?.visibility = View.GONE
    }

    private fun initView(view: View) {
        view.rcvListCategory.apply {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            listCategoryAdapter = ListCategoryAdapter(requireContext(), this@ClassifyFragment)
            this?.adapter = listCategoryAdapter
        }

        view.rcvListComicByCategory.apply {
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            listComicByCategoryAdapter = ListComicByCategoryAdapter(context, this@ClassifyFragment)
            this?.adapter = listComicByCategoryAdapter
        }
    }

    private fun initData() {
        getListCategoriesFromRealtimeDatabase()
    }

    private fun getListCategoriesFromRealtimeDatabase() {
        val reference: DatabaseReference = Constant.database.getReference("list_categories")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listCategories: MutableList<Category> = mutableListOf()

                for (postSnapshot in dataSnapshot.children) {
                    val category: Category? = postSnapshot.getValue(Category::class.java)
                    category?.let { listCategories.add(it) }
                }

                listCategoryAdapter.setData(listCategories)
                getListComicsByCategoryFromRealtimeDatabase(listCategories[0].name)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting list comics failed, log a message
            }
        })
    }

    private fun getListComicsByCategoryFromRealtimeDatabase(categoryStr: String?) {
        val reference: DatabaseReference = Constant.database.getReference("list_comics")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listComic: MutableList<Comic> = mutableListOf()

                for (postSnapshot in dataSnapshot.children) {
                    val comic: Comic? = postSnapshot.getValue(Comic::class.java)
                    comic?.let {
                        it.listCategories.forEach { category ->
                            if (category.name == categoryStr) {
                                listComic.add(it)
                            }
                        }
                    }
                }

                listComicByCategoryAdapter.setData(listComic)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting list comics failed, log a message
            }
        })
    }

    companion object {
        fun newInstance() = ClassifyFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
        }
    }

}
