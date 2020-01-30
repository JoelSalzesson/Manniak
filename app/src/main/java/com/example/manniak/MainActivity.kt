package com.example.manniak

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.manniak.audycje.GetStuff
import com.example.manniak.audycje.ManniakPage
import com.example.manniak.audycje.ManniakStreamMp3
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import pl.droidsonroids.jspoon.Jspoon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab2.setOnClickListener { view ->
            Snackbar.make(view, "second", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val htmlContent =
                ("<aside id=\"box-sounds\" class=\"clearfix list-files\">                       \n" +
                        "dupa dupa" +
                        "   <script>\n" +
                        "\t\tjQuery(function (\$) {\n" +
                        "    //some javascript bloat                                          \n" +
                        "\t\t});\n" +
                        "\t</script>\n" +
                        "\t<div id='player-main-audycja_2422547' class=\"player-main-audycja\"></div>\n" +
                        "</aside>")

            val jspoon = Jspoon.create()
            val htmlAdapter = jspoon.adapter<Page>(Page::class.java)

            val page = htmlAdapter.fromHtml(htmlContent)
            Log.d("page", "l3 " + page.l3)
            Log.d("page", "l4 " + page.l4)
            Log.d("page", "l5 " + page.l5)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

//            val executor = ThreadPoolExecutor()

            GetStuff.page().enqueue(object : Callback<ManniakPage> {
                override fun onFailure(call: Call<ManniakPage>, t: Throwable) {
                    Log.d("nok", t.message)
                }

                override fun onResponse(call: Call<ManniakPage>, response: Response<ManniakPage>) {
                    Log.d("ok", "response: " + response.body()?.posts?.get(0)?.title)
                    Log.d("ok", "response: " + response.body()?.posts?.get(0)?.url)

                    response.body()?.posts?.get(0)?.url?.let {

                        object : Thread() {
                            override fun run() {
                                val document =
                                    Jsoup.connect("https://www.polskieradio.pl/" + it).get()
//                                var xxx = document.getElementsByTag("script")
//                                for(item in document.getElementsByTag("script")){
//                                    Log.d("jsoup", "response: " + item.text())
//                                }
                                val content = document.getElementById("box-sounds")
//                                Log.d("jsoup", "response: \n" + content.getElementsByTag("script"))

                                val regex = "source: '(.*)'".toRegex()
                                val result = regex.find(content.getElementsByTag("script").html())
                                val theUrl = result?.groups?.get(1)?.value
                                Log.d("jsoup", "response: " + theUrl)

                                theUrl.let {
                                    //                                    val mediaPlayer = MediaPlayer.create(this@MainActivity, Uri.parse("https://static.prsa.pl"+it))
//                                    mediaPlayer.start()

                                    val intent = Intent(android.content.Intent.ACTION_VIEW)
                                    intent.setDataAndType(
                                        Uri.parse("https://static.prsa.pl" + it),
                                        "audio/mp3"
                                    )
                                    startActivity(intent)
                                }
                            }
                        }.start()

                        GetStuff.stream(it).enqueue(object : Callback<ManniakStreamMp3> {
                            override fun onResponse(
                                call: Call<ManniakStreamMp3>,
                                response: Response<ManniakStreamMp3>
                            ) {
                                Log.d("ok l1", "response: " + response.body()?.l1)
                                Log.d("ok l2", "response: " + response.body()?.l2)
                                Log.d("ok l3", "response: " + response.body()?.l3)
                                Log.d("ok url", "response: " + response.body()?.mp3Url)

//                            response.body()?.mp3Url?.let {
//                                val mediaPlayer = MediaPlayer.create(this@MainActivity, Uri.parse("https://www.polskieradio.pl/"+response.body()?.mp3Url))
//                                mediaPlayer.start()
//                            }
                            }

                            override fun onFailure(call: Call<ManniakStreamMp3>, t: Throwable) {
                                Log.d("nok", t.message)
                            }

                        })
                    }
                }

            })
        }
    }
}
