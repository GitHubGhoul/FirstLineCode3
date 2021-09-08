package com.wxd.firstlinecode.net

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityWebBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding
    private val xml = "<apps>\n" +
            "\t<app>\n" +
            "\t\t<id>1</id>\n" +
            "\t\t<name>Google Maps</name>\n" +
            "\t\t<version>1.0</version>\n" +
            "\t</app>\n" +
            "\t<app>\n" +
            "\t\t<id>2</id>\n" +
            "\t\t<name>Chrome</name>\n" +
            "\t\t<version>2.1</version>\n" +
            "\t</app>\n" +
            "\t<app>\n" +
            "\t\t<id>3</id>\n" +
            "\t\t<name>Google Play</name>\n" +
            "\t\t<version>2.3</version>\n" +
            "\t</app>\n" +
            "</apps>";

    private val json =
        "[{'id':'5','version':'5.5','name':'Clash of class'}," +
                "{'id':'6','version':'7.5','name':'Boom Beach'}," +
                "{'id':'7','version':'3.5','name':'Clash Royale'}]";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.baidu.com")
        binding.sent.setOnClickListener {

        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXmlWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    // 开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    // 完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.e("WebActivity", "id is $id")
                            Log.e("WebActivity", "name is $name")
                            Log.e("WebActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXmlWithSax(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.contentHandler = handler
            // 开始执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.e("WebActivity", "id is $id")
                Log.e("WebActivity", "name is $name")
                Log.e("WebActivity", "version is $version")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<JsonData>>() {}.type
        val appList = gson.fromJson<List<JsonData>>(jsonData, typeOf)
        for (app in appList) {
            Log.e("WebActivity", "id is ${app.id}")
            Log.e("WebActivity", "name is ${app.name}")
            Log.e("WebActivity", "version is ${app.version}")
        }
    }

    suspend fun getBaiduRequest() {
        try {
            val response = request("https://www.baidu.com/")
            showResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

    suspend fun request(address: String): String {
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    continuation.resume(response)
                }

                override fun onError(e: Exception) {
                    continuation.resumeWithException(e)
                }

            })
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.show.text = response
        }
    }
}