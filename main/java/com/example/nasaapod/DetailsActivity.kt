package com.example.nasaapod

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
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

class DetailsActivity : AppCompatActivity() {
    var image: ImageView? = null
    var like1:ImageView? = null
    var save1:ImageView? = null
    var view1:ImageView? = null
    var explain: TextView? = null
    var photo:TextView? = null
    var like2:TextView? = null
    var like3:TextView? = null
    var save2:TextView? = null
    var save3:TextView? = null
    var view2:TextView? = null
    var view3:TextView? = null
    var image_url: String? = null
    var title: String? = null
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        title = intent.getStringExtra("title")
        image = findViewById(R.id.imageView)
        photo = findViewById(R.id.photo)
        explain = findViewById(R.id.explain)
        explain!!.setMovementMethod(ScrollingMovementMethod())
        like1 = findViewById(R.id.like1)
        like2 = findViewById(R.id.like2)
        like3 = findViewById(R.id.like3)
        save1 = findViewById(R.id.save1)
        save2 = findViewById(R.id.save2)
        save3 = findViewById(R.id.save3)
        view1 = findViewById(R.id.view1)
        view2 = findViewById(R.id.view2)
        view3 = findViewById(R.id.view3)
        requestQueue = Volley.newRequestQueue(this)
        fetchdata()
    }

    private fun fetchdata() {
        val url = "https://api.nasa.gov/planetary/apod?api_key=ZlNn6a4JRQ7qGgvWIup4yCL4d2WoFibxcWKSCMsj"

        val request = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { response ->
                    try {
                        val jsonObject = JSONObject(
                                response)
                        for (i in 0 until response.length) {
                            explain!!.text = jsonObject.getString("explanation")
                            image_url = jsonObject.getString("url")
                            Picasso.get().load(image_url).into(image)
                            like1!!.visibility = View.VISIBLE
                            like2!!.visibility = View.VISIBLE
                            like3!!.visibility = View.VISIBLE
                            save1!!.visibility = View.VISIBLE
                            save2!!.visibility = View.VISIBLE
                            save3!!.visibility = View.VISIBLE
                            view1!!.visibility = View.VISIBLE
                            view2!!.visibility = View.VISIBLE
                            view3!!.visibility = View.VISIBLE
                            photo!!.text = jsonObject.getString("copyright")
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                            this@DetailsActivity,
                            error.message,
                            Toast.LENGTH_SHORT)
                            .show()
                })
        requestQueue?.add(request)
    }
}