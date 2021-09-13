package com.example.k_ovidv2

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-13
 * @desc
 */
class UrlJson(addr: String){

    var addr: String

    init{
        this.addr = addr
    }

    var lats = ArrayList<String>()
    var lngs = ArrayList<String>()
    var name = ArrayList<String>()
    var adapter : Adapter = Adapter()

    val URL = "https://api.odcloud.kr/api/apnmOrg/v1/list?page=1&perPage=10&serviceKey=9PAc6aMn2DC3xdA7rYZn71Hxr3mT9V5E4qnnakQkwj44zVNrPfV%2FVLVnDsnf30wrZZ%2BD%2FS%2BWRTNinP7J8lMjeQ%3D%3D"

    fun main(): Adapter{

//        var text: String? = null
//        try {
//            text = URLEncoder.encode(addr, "UTF-8")
//        } catch (e: UnsupportedEncodingException) {
//            throw RuntimeException("검색어 인코딩 실패", e)
//        }

//        val url = "$URL?address=$text"
        val responseBody = get(URL)
        parseData(responseBody)

        return adapter
    }

    private operator fun get(apiUrl: String): String {
        var responseBody: String = ""
        try {
            val url = URL(apiUrl)
            val `in` = url.openStream()
            responseBody = readBody(`in`)

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return responseBody
    }

    private fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)

        try {
            BufferedReader(streamReader).use({ lineReader ->
                val responseBody = StringBuilder()

                var line: String? = lineReader.readLine()
                while ( line != null) {
                    responseBody.append(line)
                    line = lineReader.readLine()
                }
                return responseBody.toString()
            })
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }

    }

    private fun parseData(responseBody: String) {
        var storeName: String
        var remain_stat: String
        var start: String
        var end: String
        var lunchst: String
        var lunchend: String
        var add: String
        var tel: String

        var jsonObject = JSONObject()
        try {
            jsonObject = JSONObject(responseBody)
            val jsonArray = jsonObject.getJSONArray("data")

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                storeName = item.getString("orgnm")
//                remain_stat = item.getString("remain_stat")
                start = item.getString("sttTm")
                end = item.getString("endTm")
                lunchst = item.getString("lunchSttTm")
                lunchend = item.getString("lunchEndTm")
                add = item.getString("orgZipaddr")
                tel = item.getString("orgTlno")
                Log.d("data: ", jsonArray.toString())


                name.add(storeName)
//                lats.add(lat)
//                lngs.add(lng)
                adapter.addItem(storeName, start, end, lunchst, lunchend, add, tel )

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}