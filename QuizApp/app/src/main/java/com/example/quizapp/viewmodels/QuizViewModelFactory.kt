package com.example.quizapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.model.Repo

class QuizViewModelFactory constructor(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(this.repo) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}