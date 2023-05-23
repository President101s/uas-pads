package com.example.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.inventory.databinding.FragmentLoginBinding
import com.example.inventory.databinding.FragmentTransactionBinding


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root


        binding.loginButton.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_login_to_inventory)
        }

        binding.gotosignup.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_login_to_signUp2)
        }
        return view
    }


}