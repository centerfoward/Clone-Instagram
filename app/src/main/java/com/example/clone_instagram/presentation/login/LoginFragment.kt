package com.example.clone_instagram.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clone_instagram.R

import com.example.clone_instagram.databinding.FragmentLoginBinding
import com.example.clone_instagram.utils.ResponseResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel : LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email,password)
        }

        loginObserve()

    }

    private fun loginObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { result ->
                when(result){
                    is ResponseResult.Failure -> {
                        Toast.makeText(context,"로그인에 실패했습니다",Toast.LENGTH_SHORT).show()
                    }
                    ResponseResult.Loading -> {
                        //TODO
                    }
                    is ResponseResult.Success -> {
                        Toast.makeText(context,"로그인에 성공했습니다",Toast.LENGTH_SHORT).show()
                        //TODO("성공하면 메인화면으로 넘어가는 로직 구현하기")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}