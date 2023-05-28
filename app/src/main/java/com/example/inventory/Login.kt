package com.example.inventory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.inventory.databinding.FragmentLoginBinding

class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root

        binding.loginButton.setOnClickListener {
            val salesUsername = binding.theUser.text.toString()
            val salesPassword = binding.thePass.text.toString()

            viewModel.postlogin(salesUsername, salesPassword)
        }

        binding.gotosignup.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_login_to_signUp2)
        }

        // Observe the verification status
        viewModel.verificationStatus.observe(viewLifecycleOwner, Observer { verified ->
            if (verified) {
                val navController = view.findNavController()
                navController.navigate(R.id.action_login_to_inventory)
            } else {
                Log.d("TAG", "Salah")
            }
        })

        return view
    }
}