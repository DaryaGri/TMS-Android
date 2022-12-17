package com.example.quizapp.model

import javax.inject.Inject

class Repo @Inject constructor() {
    val data = arrayListOf(
        "John's mom ____ him home from school most days.",
        "Anna and I ____ best friends since we were five.",
        "Last weekend, I ____ on a trip to York with my friends.",
        "What ____ at the moment?",
        "He definitely ____ on the team next week.")

    val answersVariants = arrayListOf(
        arrayListOf("drived", "driving", "drive", "drives"),//4
        arrayListOf("was", "been", "have been", "be"),//3
        arrayListOf("goes", "had gone", "went", "gone"),//3
        arrayListOf("do you do", "are you doing", "did you do", "is you do"),//2
        arrayListOf("will be", "was", "to be", "have been"),//1
    )

    val answersId = arrayListOf(4, 3, 3, 2, 1)

}