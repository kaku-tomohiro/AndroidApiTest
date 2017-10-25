package com.example.daron.qiitaclient.client

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by daron on 2017/10/25.
 */
data class Test(
        val test: String
)

interface TestClient{
    @GET("api/v1/test")
    fun get(): Observable<Test>
}