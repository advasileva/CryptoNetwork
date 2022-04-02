package com.example.cryptonetwork.ui.identification

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cryptonetwork.R
import com.example.cryptonetwork.databinding.FragmentAccountBinding
import com.example.cryptonetwork.databinding.FragmentLoginBinding
import org.json.JSONObject
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private var _bindingAcc: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }
        binding.buttonToregistr.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun handleLogin() {
        val login = binding.login.text.toString()
        val password = binding.password.text.toString()
        if (login.trim().length > 0 && password.trim().length > 0) {
            val simpleRunnable = SimpleRunnable(login, password)
            val threadWithRunnable = Thread(simpleRunnable)
            threadWithRunnable.start()
            binding.buttonLogin.text = "Checking..."
            threadWithRunnable.join()
            val text = simpleRunnable.Text
            if (text.length <= 0) {
                Toast.makeText(getActivity(), "Incorrect login or password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show()
                val intent = Intent()
                //intent.putExtra("result", text)
                //findNavController().navigate(R.id.navigation_account)
            }
        } else {
            Toast.makeText(getActivity(), "Enter login and password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SimpleRunnable(_login: String, _password: String) : Runnable {
    val login: String
    val password: String
    var Text: String = ""

    init {
        login = _login
        password = _password
    }

    public override fun run() {
        val url =
            "https://cryptonetworkapi.azurewebsites.net/crypto-network/account" +
                    "/check?login=$login&password=$password"
        Log.println(Log.DEBUG, Log.DEBUG.toString(), url)
        val connection = URL(url).openConnection() as HttpURLConnection
        var text = "test"
        try {
            connection.connect()
            text = connection.inputStream.use {
                it.reader().use() { reader -> reader.readText() }
            }
            Text = text
        } catch (ex: FileNotFoundException) {
            Log.println(Log.DEBUG, Log.DEBUG.toString(), "CATCHED")
        } finally {
            connection.disconnect()
        }
        Log.println(Log.DEBUG, Log.DEBUG.toString(), "GET $text")
    }
}

class Response(json: String) : JSONObject(json) {
    val type: String? = this.optString("type")
    val data = this.optJSONArray("data")
        ?.let { 0.until(it.length()).map { i -> it.optJSONObject(i) } } // returns an array of JSONObject
        ?.map { User(it.toString()) } // transforms each JSONObject of the array into Foo
}

class User(json: String) : JSONObject(json) {
    val username = this.optString("username")
    val wallets = this.optJSONArray("Wallets")
    val subscribes = this.optJSONArray("Subscribes")
    val subscribers = this.optJSONArray("Subscribers")
}