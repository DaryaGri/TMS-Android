package com.example.quizapp.ui


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpViews()
    }

    private fun setUpViews() {
        binding.prevButton.setOnClickListener {
            viewModel.saveUserAnswer()
            viewModel.loadPreviousQuestion()
        }
        binding.allTextView.text = viewModel.getQuestionsAmount().toString()

        binding.radios.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.firstRB -> {
                    viewModel.userAnswer = 1
                }
                R.id.secondRB -> {
                    viewModel.userAnswer = 2
                }
                R.id.thirdRB -> {
                    viewModel.userAnswer = 3
                }
                R.id.fourthRB -> {
                    viewModel.userAnswer = 4
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpObservers() {
        viewModel.currentQuestionsId.observe(viewLifecycleOwner) { questionNumber ->
            viewModel.loadCurrentQuestion()
            binding.prevButton.isEnabled = questionNumber != 0
            if (questionNumber == viewModel.getQuestionsAmount() - 1) {
                binding.nextButton.text = "Finish"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    findNavController().navigate(R.id.action_quizFragment_to_resultScreen)
                }
            } else {
                binding.nextButton.text = "next"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    viewModel.loadNextQuestion()
                }
            }

            binding.currentTextView.text = (questionNumber + 1).toString()
            setUpAnswers(questionNumber)
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            binding.questionTextView.text = question

            loadUserAnswerIfExistAtQuestion(viewModel.currentQuestionsId.value!!)
        }
    }

    private fun loadUserAnswerIfExistAtQuestion(questionNumber: Int) {
        when (viewModel.userAnswers[questionNumber]) {
            1 -> {
                binding.radios.check(R.id.firstRB)
                viewModel.userAnswer = 1
            }
            2 -> {
                binding.radios.check(R.id.secondRB)
                viewModel.userAnswer = 2
            }
            3 -> {
                binding.radios.check(R.id.thirdRB)
                viewModel.userAnswer = 3
            }
            4 -> {
                binding.radios.check(R.id.fourthRB)
                viewModel.userAnswer = 4
            }
            else -> {
                binding.radios.clearCheck()
                viewModel.userAnswer = -1
            }
        }
    }

    private fun setUpAnswers(questionNumber: Int) {
        val answers = viewModel.loadAnswers(questionNumber)
        binding.firstRB.text = answers[0]
        binding.secondRB.text = answers[1]
        binding.thirdRB.text = answers[2]
        binding.fourthRB.text = answers[3]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}