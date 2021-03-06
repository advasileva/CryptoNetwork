package com.example.cryptonetwork.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptonetwork.databinding.FragmentAccountBinding
import com.example.cryptonetwork.ui.identification.IdentificationActivity

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAccount
        accountViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val loginButton: Button = binding.loginButton
        val intent = Intent()
        loginButton.text = intent.extras?.getString("result")
        loginButton.setOnClickListener {
            val intent = Intent(context, IdentificationActivity::class.java).apply {
                putExtra("keyIdentifier", 1)
                startActivity(this)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}