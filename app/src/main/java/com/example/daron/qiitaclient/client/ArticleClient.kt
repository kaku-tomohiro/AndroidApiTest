package com.example.daron.qiitaclient.client

import com.example.daron.qiitaclient.model.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by daron on 2017/10/22.
 */


interface ArticleClient{
    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}