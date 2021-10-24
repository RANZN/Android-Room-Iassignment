package com.ranzan.android_room_iassignment.RECYCLERVIEW

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ranzan.android_room_iassignment.R
import com.ranzan.android_room_iassignment.room.DatabaseEntity


class RecyclerListAdapter(
    var list: MutableList<DatabaseEntity>,
    private val popUpDialog: PopUpDialog
) :
    RecyclerView.Adapter<RecyclerListAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setData(list[position], popUpDialog)

    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private val tvTitle: TextView = view.findViewById<TextView>(R.id.tvTitle)
        private val tvDesc: TextView = view.findViewById<TextView>(R.id.tvDesc)
        private val popupmenu: ImageView = view.findViewById(R.id.popupMenu)
        private lateinit var pop: PopUpDialog
        private lateinit var databaseEntity: DatabaseEntity
        fun setData(databaseEntity: DatabaseEntity, popUpDialog: PopUpDialog) {
            this.databaseEntity = databaseEntity
            pop = popUpDialog
            tvTitle.text = databaseEntity.title
            tvDesc.text = databaseEntity.desc
            popupmenu.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val popupMenu = PopupMenu(view.context, v)
            popupMenu.inflate(R.menu.menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }

        override fun onMenuItemClick(p0: MenuItem?): Boolean {
            when (p0?.itemId) {
                R.id.edit -> {
                    pop.onEdit(databaseEntity.id)
                }
                R.id.delete -> {
                    pop.onDelete(databaseEntity.id)
                }
            }
            return true
        }
    }
}