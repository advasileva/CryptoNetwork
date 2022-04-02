package Services

import android.os.AsyncTask
import kotlinx.coroutines.*
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RequestSender {
    fun TryLogin(login: String?, password: String?) : AsyncTask<String, String, String>? {
        Log.println(
            Log.INFO, Log.DEBUG.toString(),
            "https://cryptonetworkapi.azurewebsites.net/crypto-network/account" +
                    "/check?login=$login&password=$password"
        )
        val url =
            "https://cryptonetworkapi.azurewebsites.net/crypto-network/account" +
                    "/check?login=$login&password=$password"

        return AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            val text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use {
                    it.reader().use() { reader -> reader.readText() }
                }
            } finally {
                connection.disconnect()
            }
            //text = URL(url.toString()).readText()
            return text
        }

        override fun onPostExecute(result: String?) {
            Log.println(Log.DEBUG, Log.DEBUG.toString(), "EXECUTED")
            super.onPostExecute(result)
            handleJson(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
        }
    }

    private fun handleJson(jsonString: String?) {
        Log.println(Log.DEBUG, Log.DEBUG.toString(), jsonString.toString())
    }
}