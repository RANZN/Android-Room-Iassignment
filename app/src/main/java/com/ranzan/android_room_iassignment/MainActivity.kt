package com.ranzan.android_room_iassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranzan.android_room_iassignment.RECYCLERVIEW.PopUpDialog
import com.ranzan.android_room_iassignment.RECYCLERVIEW.RecyclerListAdapter
import com.ranzan.android_room_iassignment.room.DatabaseDAO
import com.ranzan.android_room_iassignment.room.DatabaseEntity
import com.ranzan.android_room_iassignment.room.LocalDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), PopUpDialog {
    private lateinit var listAdapter: RecyclerListAdapter
    private lateinit var dao: DatabaseDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao = LocalDatabase.getDataBase(this).getDataBase()

        dao.getList().observe(this, Observer {
            val list = it
            setRecyclerView(list)
        })

        addBtn.setOnClickListener {
            val fragment = AddDataFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment, "Fragment").addToBackStack("Fragment").commit()
        }

    }

    private fun setRecyclerView(list: MutableList<DatabaseEntity>) {
        listAdapter = RecyclerListAdapter(list!!, this)
        recyclerViewList.run {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onEdit(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val fragment = AddDataFragment.newInstance()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment, "Fragment")
            .addToBackStack("Fragment").commit()
    }

    override fun onDelete(pos: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.delete(dao.getData(pos))
        }
    }
}