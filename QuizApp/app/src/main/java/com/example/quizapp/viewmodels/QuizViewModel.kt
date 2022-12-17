package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    var currentQuestionsId = MutableLiveData(0)
    val currentQuestion = MutableLiveData<String>()
    var userAnswer = -1
    var userAnswers = Array<Int>(getQuestionsAmount()) { 0 }

    fun getQuestionsAmount() = repo.data.size

    fun loadNextQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! + 1
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun loadPreviousQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! - 1
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun loadCurrentQuestion() {
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun saveUserAnswer() {
        userAnswers[currentQuestionsId.value!!] = userAnswer
    }

    fun loadAnswers(questionNumber: Int): ArrayList<String> {
        return repo.answersVariants[questionNumber]
    }

    fun getRightAmount(): Int {
        var correctAnswers = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == repo.answersId[i]) {
                correctAnswers++
            }
        }
        return correctAnswers
    }

    fun getComment(): String {
        val res = getRightAmount()
        return when {
            res == getQuestionsAmount() -> "Excellent!"
            res > getQuestionsAmount() * 2 / 3 -> "Good job!"
            res > getQuestionsAmount() / 3 -> "Practice makes perfect!"
            else -> "Try again!"
        }
    }

    fun restartQuiz() {
        currentQuestionsId.postValue(0)
        userAnswer = -1
        for (i in userAnswers.indices) {
            userAnswers[i] = -1
        }
    }

    fun getResultMessage(): String {
        return "Try english tenses quiz! I got ${getRightAmount()}/${getQuestionsAmount()}! Now your turn!"
    }
}