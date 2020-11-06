package com.okariastudio.whatdidyoumissduringthenight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okariastudio.whatdidyoumissduringthenight.api.ApiInterface
import com.okariastudio.whatdidyoumissduringthenight.api.getApiClient
import com.okariastudio.whatdidyoumissduringthenight.models.Article
import com.okariastudio.whatdidyoumissduringthenight.hide.API_KEY
import com.okariastudio.whatdidyoumissduringthenight.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var layoutManager : RecyclerView.LayoutManager
    private var articles : MutableList<Article> = mutableListOf()
    private lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false

        loadJson()
    }

    private fun loadJson(){
        val apiInterface : ApiInterface = getApiClient().create(ApiInterface::class.java)
        val country = "fr"
        //TODO("Add function to switch the country manually")

        val call : Call<News> = apiInterface.getNews(country, API_KEY)
        call.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful){
                    if(articles.isNotEmpty()){
                        articles.clear()
                    }

                    articles = response.body()?.articles as MutableList<Article>
                    adapter = MyAdapter(articles,this@MainActivity)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(this@MainActivity,"Aucun résultat",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}