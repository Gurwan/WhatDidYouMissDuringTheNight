package com.okariastudio.whatdidyoumissduringthenight

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.okariastudio.whatdidyoumissduringthenight.models.Article
import org.ocpsoft.prettytime.PrettyTime
import org.w3c.dom.Text
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MyAdapter(private val articles : List<Article>,private val context : Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (source, author, title, description, _, urlToImage, publishedAt) = articles[position]
        val requestOptions = RequestOptions()
        //options to load the image
        requestOptions.placeholder(ColorDrawable(Color.parseColor("#faca5f")))
        requestOptions.error(ColorDrawable(Color.parseColor("#ff0000")))
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()
        requestOptions.timeout(3000)
        // Glide is an image loading framework
        Glide.with(context)
                .load(urlToImage)
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView)
        holder.title.text = title
        holder.desc.text = description
        holder.source.text = source.name
        val p = PrettyTime(Locale(Locale.getDefault().toString().toLowerCase()))
        try {
            holder.time.text = p.format(SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'", Locale.FRANCE).parse(publishedAt))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        holder.published_ad.text = publishedAt
        holder.author.text = author
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class MyViewHolder(itemView : View,onItemClickListener : OnItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView
        var desc: TextView
        var author: TextView
        var published_ad: TextView
        var source: TextView
        var time: TextView
        var imageView: ImageView
        var progressBar: ProgressBar
        var onItemClickListener: OnItemClickListener?
        override fun onClick(v: View) {
            onItemClickListener?.onItemClick(v, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
            author = itemView.findViewById(R.id.author)
            published_ad = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.img)
            progressBar = itemView.findViewById(R.id.progress_load_photo)
            this.onItemClickListener = onItemClickListener
        }
    }

}

