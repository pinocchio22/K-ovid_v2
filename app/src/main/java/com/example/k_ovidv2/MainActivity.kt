package com.example.k_ovidv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var listView : ListView
    var adapter : Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listview)
        editText = findViewById(R.id.edittext)

    }

    fun onClickButton(view: View){
        val thread = Thread {
            var apiPublicMask = UrlJson(editText.text.toString())
            adapter = apiPublicMask.main()
            runOnUiThread {
                listView.adapter = adapter
            }
        }.start()
    }

}