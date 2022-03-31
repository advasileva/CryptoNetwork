package Services

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class RequestSender {
    companion object {

        fun TryLogin(login: String, password: String) {
            Log.println(Log.DEBUG, Log.DEBUG.toString(), "GET $login $password")
            try {
                Log.println(Log.INFO, Log.DEBUG.toString(), "START")

                //URL("https://google.com/")?.readText()

                val url = URL("https://reqres.in/api/users?page=1")
                Thread.sleep(1_000)
                val connection = url.openConnection()
                Thread.sleep(1_000)
                BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {

                    }
                }
                Thread.sleep(1_000)
                Log.println(Log.DEBUG, Log.DEBUG.toString(), "SUCCESS")
            } catch (ex: Exception) {
                Log.println(Log.DEBUG, Log.DEBUG.toString(),
                    "EXC ${ex.message} ${ex.localizedMessage} ${ex.stackTrace}")
            }

//            var reqParam = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login, "UTF-8")
//            reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
//
//            val mURL = URL("https://cryptonetworkapi.azurewebsites.net/crypto-network/account/check?"+reqParam)
//            Log.println(Log.DEBUG, Log.DEBUG.toString(), "URL $mURL")
////            with(mURL.openConnection() as HttpURLConnection) {
////                // optional default is GET
////                requestMethod = "GET"
////
////                println("URL : $url")
////                println("Response Code : $responseCode")
////
////                BufferedReader(InputStreamReader(inputStream)).use {
////                    val response = StringBuffer()
////
////                    var inputLine = it.readLine()
////                    while (inputLine != null) {
////                        response.append(inputLine)
////                        inputLine = it.readLine()
////                    }
////                    it.close()
////                    Log.println(Log.DEBUG, Log.DEBUG.toString(), "Response $response")
////                }
////            }
        }
    }
}