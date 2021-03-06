package com.example.comicapp.screen.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.comicapp.R
import com.example.comicapp.data.model.Comic
import com.example.comicapp.screen.detail.DetailFragment
import com.example.comicapp.screen.home.adapter.ComicAdapter
import com.example.comicapp.utils.Constant.database
import com.example.comicapp.utils.OnItemComicClickListener
import com.example.comicapp.utils.extensions.hideBottomNavigationView
import com.example.comicapp.utils.extensions.replaceFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), OnItemComicClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var comicAdapter: ComicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initView(view)
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListener()
    }

    override fun onItemComicClick(comic: Comic?) {
        replaceFragment(R.id.layoutContainer, DetailFragment.newInstance(comic))
        hideBottomNavigationView(activity)
    }

    private fun initView(view: View) {
        initToolbar()

        view.rcvListComic.apply {
            this.layoutManager = GridLayoutManager(context, 3)
            comicAdapter = ComicAdapter(requireContext(), this@HomeFragment)
            this.adapter = comicAdapter
        }
    }

    private fun initData() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        getListComicsFromRealtimeDatabase()
        homeViewModel.listComics.observe(requireActivity(), Observer {
            comicAdapter.setData(it)
        })

//        write()
    }

    private fun initListener() {
        onEditTextSearchChange()
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbarHome)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun getListComicsFromRealtimeDatabase() {
        val mRef: DatabaseReference = database.getReference("list_comics")

        mRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listComic: MutableList<Comic> = mutableListOf()

                for (postSnapshot in dataSnapshot.children) {
                    val comic: Comic? = postSnapshot.getValue(Comic::class.java)
                    comic?.let { listComic.add(it) }
                }
                homeViewModel.setListComics(listComic)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting list comics failed, log a message
            }
        })
    }

    private fun onEditTextSearchChange() {
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                comicAdapter.sortData(s.toString())
            }

        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    private fun write() {
        /* Write comic to realtime database
        val chapter0 = Chapter(
            0, "Chapter 298.5", mutableListOf(
            )
        )

        val chapter1 = Chapter(
            1, "Chapter 299", mutableListOf(
            )
        )

        val chapter2 = Chapter(
            2, "Chapter 299.5", mutableListOf(
            )
        )

        val chapter3 = Chapter(
            3, "Chapter 300", mutableListOf(
            )
        )

        val chapter4 = Chapter(
            4, "Chapter 300.5", mutableListOf(
            )
        )

        val chapter5 = Chapter(
            5, "Chapter 301", mutableListOf(
            )
        )

        val chapter6 = Chapter(
            6, "Chapter 7", mutableListOf(
            )
        )

        val chapter7 = Chapter(
            7, "Chapter 8", mutableListOf(
            )
        )

        val chapter8 = Chapter(
            8, "Chapter 9", mutableListOf(
            )
        )

        val chapter9 = Chapter(
            9, "Chapter 10", mutableListOf(
            )
        )

        val comic = Comic(
            14,
            "?????u La ?????i L???c",
            "http://st.imageinstant.net/data/comics/107/dau-la-dai-luc.jpg",
            "???????ng Gia Tam Thi???u",
            "??ang ti???n h??nh",
            "?????u La ?????i L???c l?? m???t trong nh???ng t??c ph???m ?????c s???c c???a ???????ng Gia Tam Thi???u. T??c ph???m thu???c th??? lo???i truy???n Huy???n Hi???p, mang ?????n cho ?????c gi??? m???t c??i nh??n, m???t c???m nh???n m???i v??? th??? gi???i hi???p kh??ch huy???n ???o. C??u chuy???n v???i nh??n v???t ch??nh, con m???t th??? r??n, m???t th??? r??n tr??? th??nh t???u qu???, v?? th?? t??? ???? m???t, s???n s??ng l??i cu???n ng?????i ?????c ngay t??? nh???ng ch????ng ?????u ti??n.",
            mutableListOf(
                chapter0, chapter1, chapter2, chapter3, chapter4
            ),
            listCategories = mutableListOf()
        )

        val reference = database.getReference("list_comics")
        reference.child(comic.id.toString()).setValue(comic)
        */
        /* Write list categories to realtime database
        val reference = database.getReference("list_categories")
        val listCategories = listOf(
            Category(0, "Action"),
            Category(1, "Adventure"),
            Category(2, "Manhwa"),
            Category(3, "Manhua"),
            Category(4, "Romance"),
            Category(5, "Manga")
        )
        reference.setValue(listCategories)
        */
    }
}
