package com.example.clone_instagram.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.clone_instagram.R
import com.example.clone_instagram.databinding.FragmentRegisterBinding
import com.example.clone_instagram.utils.AssignState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()
            viewModel.register(email, password, confirm)
        }

        observeValidationState()
        observeRegisterResult()

    }

    private fun observeValidationState() { // 유효성 검증 상태 관리
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.validationState.collect { state ->
                    when (state) {
                        is RegisterViewModel.ValidationState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> Unit
                    }
                }
            }
        }
    }

    private fun observeRegisterResult() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect { result ->
                    when (result) {
                        is AssignState.Loading -> {
                            //TODO : 초기 상태
                        }

                        is AssignState.Wait -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is AssignState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "회원가입 성공: ${result.data?.email}",
                                Toast.LENGTH_SHORT
                            ).show()
                            // TODO: 홈 화면 이동 등 처리
                            findNavController().navigate(R.id.action_register_to_login)
                        }

                        is AssignState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "회원가입 실패: ${result.message.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
