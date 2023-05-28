package com.example.inventory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.inventory.databinding.FragmentSignUpBinding


class SignUp : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    private val viewModel : SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        val view = binding.root



        binding.gotosignin.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_signUp2_to_login)
        }

        binding.tryregis.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()

            viewModel.regis(username, password, name, email)
        }

        viewModel.regisStat.observe(viewLifecycleOwner, Observer { stats ->
            if (stats == "-") {
                val navController = view.findNavController()
                navController.navigate(R.id.action_signUp2_to_login)
            } else {
                Log.d("TAG", stats)
            }
        })

        return view
    }
}