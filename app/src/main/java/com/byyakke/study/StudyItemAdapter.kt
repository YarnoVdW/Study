package com.byyakke.study

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StudyItemAdapter(var trickList: MutableList<StudyItem>): RecyclerView.Adapter<StudyItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: StudyItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return trickList.size
    }

    fun addNewItem(item: StudyItem){
        trickList.add(item)

        notifyItemInserted(trickList.size-1)
    }
}