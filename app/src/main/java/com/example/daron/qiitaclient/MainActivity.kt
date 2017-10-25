package com.example.daron.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar

import com.example.daron.qiitaclient.client.ArticleClient
import com.example.daron.qiitaclient.client.TestClient
import com.example.daron.qiitaclient.model.Article
import com.example.daron.qiitaclient.model.User
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //ArticleViewオブジェクトをセット
//        val articleView = ArticleView(applicationContext)
//        articleView.setArticle(Article("123",
//                "Kotlin入門",
//                "http://www.example.com/articles/123",
//                User("456", "たろう", "")))
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        val listAdapter = ArticleListAdapter(applicationContext)
//        listAdapter.articles = listOf(dummyArticle("kotlin入門", "たろう"),
//                dummyArticle("java入門", "じろう"))
//
        val listView: ListView = findViewById<ListView>(R.id.list_view)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this, listAdapter.articles[position])
            startActivity(intent)
        }

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit_test = Retrofit.Builder()
                .baseUrl("http://160.16.213.209/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val testClient = retrofit_test.create(TestClient::class.java)

        testClient.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.v("test_message", it.test)
                }, {
                    toast("エラー $it")
                    Log.e("API_ERROR", "error")
                    Log.e("API_ERROR", "$it")
                })




        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

        val queryEditText = findViewById<EditText>(R.id.query_edit_text)
        val searchButton = findViewById<Button>(R.id.search_button)
        Log.v("success", "success_build")

        searchButton.setOnClickListener {
            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.VISIBLE
                    }
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
                        Log.v("success", "success")
                    }, {
                        toast("エラー $it")
                        Log.e("API_ERROR", "$it")
                    })
        }

    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article("",
                    title,
                    "https://kotlinlang.org/",
                    User("",userName,""))

}
