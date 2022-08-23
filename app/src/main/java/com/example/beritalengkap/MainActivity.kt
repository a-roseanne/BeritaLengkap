package com.example.beritalengkap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter()
        adapter.notifyDataSetChanged()
        recycleView.layoutManager = LinearLayoutManager(this)
        showLoading(true)
        getNews()

    }

    private fun getNews() {
        val listNews = ArrayList<News>()
        val baseUrl = "http://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=6ff10779cca24cf6ad498aa75ba7ace2"
        AndroidNetworking.get(baseUrl)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("articles")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val itemNews = News()
                        itemNews.title = jsonObject.getString("author")
                        itemNews.description  = jsonObject.getString("description")
                        itemNews.photo = jsonObject.getString("urlToImage")
                        listNews.add(itemNews)
                    }
                    adapter.setData(listNews)
                    showLoading(false)
                }

                override fun onError(e: ANError) {
                    Log.d("Exception", e.message.toString())
                }

            })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}