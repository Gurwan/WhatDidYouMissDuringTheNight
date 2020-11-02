package com.okariastudio.whatdidyoumissduringthenight

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.okariastudio.whatdidyoumissduringthenight.models.Article
import org.w3c.dom.Text

class MyAdapter(var articles : List<Article>,var context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            init {
                val title = itemView.findViewById(R.id.title) as TextView
                val desc = itemView.findViewById(R.id.desc) as TextView
                val author = itemView.findViewById(R.id.author) as TextView
                val published_at = itemView.findViewById(R.id.publishedAt) as TextView
                val source = itemView.findViewById(R.id.source) as TextView
                val time = itemView.findViewById(R.id.time) as TextView
                val img = itemView.findViewById(R.id.img) as ImageView
                val progressBar = itemView.findViewById(R.id.progress_load_photo) as ProgressBar
            }
    }

}

