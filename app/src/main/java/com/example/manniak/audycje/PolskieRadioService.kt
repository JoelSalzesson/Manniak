package com.example.manniak.audycje

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PolskieRadioService {

    @GET("9,Trojka/328,Manniak-po-ciemku")
    fun getPage(): Call<ManniakPage>

    @GET
    fun getStream(@Url url: String): Call<ManniakStreamMp3>
}