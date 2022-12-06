package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Repo

class QuizViewModel( private val repo : Repo) : ViewModel(){
    var currentQuestionsId = MutableLiveData <Int>(0)
    val currentQuestion = MutableLiveData <String>()
    var userAnswer = -1
    var userAnswers = Array<Int>(getQuestionsAmount()) { 0 }

    fun getQuestionsAmount() = repo.data.size

    fun loadNextQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! +1
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun loadPreviousQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! -1
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun loadCurrentQuestion() {
        currentQuestion.value = repo.data[currentQuestionsId.value!!]
    }

    fun saveUserAnswer() {
        userAnswers[currentQuestionsId.value!!] = userAnswer
    }

    fun loadAnswers(questionNumber: Int):ArrayList <String> {
        return repo.answersVariants[questionNumber]
    }
}