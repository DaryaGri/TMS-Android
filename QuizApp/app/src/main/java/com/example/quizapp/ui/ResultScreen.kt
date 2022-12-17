package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentResultScreenBinding
import com.example.quizapp.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultScreen : Fragment() {

    private var _binding: FragmentResultScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {
        with (binding){
            currentTextView.text = viewModel.getRightAmount().toString()
            allTextView.text = viewModel.getQuestionsAmount().toString()
            commentTextView.text = viewModel.getComment()
            exitButton.setOnClickListener {
                requireActivity().finish()
            }
            restartButton.setOnClickListener {
                viewModel.restartQuiz()
                findNavController().popBackStack()
            }
            shareButton.setOnClickListener {
                val myIntent = Intent(Intent.ACTION_SEND);
                myIntent.type = "text/plain"
                myIntent.putExtra(Intent.EXTRA_TEXT, viewModel.getResultMessage())
                startActivity(Intent.createChooser(myIntent, "Share with"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}