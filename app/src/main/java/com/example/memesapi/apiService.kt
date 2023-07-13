package com.example.memesapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {

    @GET("templates")
    suspend fun getMemes():List<memesList>
    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.memegen.link/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}

//@GET("templates")
//suspend fun getMemes() : ArrayList<MemesResponse>
//
//companion object {
//    var apiService: APIService? = null
//    fun getInstance() : APIService{
//        if (apiService == null) {
//            apiService = Retrofit.Builder()
//                .baseUrl("https://api.memegen.link/")
//
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(APIService::class.java)
//        }
//        return apiService!!
//    }