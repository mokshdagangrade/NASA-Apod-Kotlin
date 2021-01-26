package com.example.nasaapod

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var title:TextView? = null
    var image:ImageView? = null
    var image_url:String? = null
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        requestQueue = Volley.newRequestQueue(this)
        val linearLayout = findViewById<LinearLayout>(R.id.layout_app)
        val animationDrawable = linearLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(2500)
        animationDrawable.start()
        fetchdata()

        image!!.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchdata() {
        val url =
            "https://api.nasa.gov/planetary/apod?api_key=ZlNn6a4JRQ7qGgvWIup4yCL4d2WoFibxcWKSCMsj"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(
                        response
                    )
                    title!!.text =
                        jsonObject.getString(
                            "title"
                        )
                    image_url = jsonObject.getString("url")
                    Picasso.get().load(image_url).into(image)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@MainActivity,
                    error.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            })

       // val requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(request)
    }
}