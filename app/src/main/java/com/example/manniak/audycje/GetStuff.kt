package com.example.manniak.audycje

import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Call
import retrofit2.Retrofit

object GetStuff {

    fun page(): Call<ManniakPage> {
        val polskieRadioService = createService()
        return polskieRadioService.getPage()
    }

    fun stream(url: String): Call<ManniakStreamMp3> {
        val polskieRadioService = createService()
        return polskieRadioService.getStream(url)
    }

    private fun createService(): PolskieRadioService {
        return createRetrofit()
            .create(PolskieRadioService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.polskieradio.pl/")
            .addConverterFactory(JspoonConverterFactory.create())
            .build()
    }
}
