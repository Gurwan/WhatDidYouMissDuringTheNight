package com.okariastudio.whatdidyoumissduringthenight

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.okariastudio.whatdidyoumissduringthenight.models.Article

class MyAdapter(var articles : List<Article>,var context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
        //return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return 0
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class MyViewHolder(itemView : View,onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
            init {
                //var title = itemView.findViewById(R.id.title)

            }
    }

}

interface OnItemClickListener {
    fun onItemClick(view : View, position: Int)
}

