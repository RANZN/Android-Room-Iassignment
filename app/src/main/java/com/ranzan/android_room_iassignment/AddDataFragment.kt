package com.ranzan.android_room_iassignment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ranzan.android_room_iassignment.room.DatabaseEntity
import com.ranzan.android_room_iassignment.room.LocalDatabase
import kotlinx.android.synthetic.main.fragment_add_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddDataFragment : Fragment(R.layout.fragment_add_data) {

    companion object {
        fun newInstance() =
            AddDataFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = LocalDatabase.getDataBase(requireContext()).getDataBase()
        val bundle = this.arguments
        val id = bundle?.getInt("id")
        if (id!! > 0) {
            addBtnToData.text = "Update Data"
            var data: DatabaseEntity? = null
            CoroutineScope(Dispatchers.IO).launch {
                data = dao.getData(id!!)
                getTitle.setText(data?.title)
                getDesc.setText(data?.desc)
            }
            addBtnToData.setOnClickListener {
                val add = DatabaseEntity(getTitle.text.toString(), getDesc.text.toString())
                add.id = id!!
                CoroutineScope(Dispatchers.IO).launch {
                    dao.update(add)
                }
                fragmentManager?.popBackStack()
            }

        } else {
            addBtnToData.setOnClickListener {
                val add = DatabaseEntity(getTitle.text.toString(), getDesc.text.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    dao.insert(add)
                }
                fragmentManager?.popBackStack()
            }
        }
    }
}