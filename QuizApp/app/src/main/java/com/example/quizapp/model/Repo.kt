package com.example.quizapp.model

class Repo {
    val data = arrayListOf(
        "John's mom ____ him home from school most days.",
        "One day last year John ____ for his mom at the front door.",
        "Just before he got there, a tree branch ____ down on the car.",
        "The branch ____ John's mom inside the car.",
        "The firefighters ____ her.")
    val answersVariants = arrayListOf(
        arrayListOf("Drived", "Driving", "Drive", "Drives"),//4
        arrayListOf("Will wait", "Wait", "Waited", "Did wait"),//3
        arrayListOf("Will crash", "Crash", "Crashed", "Crashing"),//3
        arrayListOf("Trap", "Trapped", "Will trap", "Shall trap"),//2
        arrayListOf("Rescued", "Will rescued", "Rescue", "Rescuing"),//1
    )
    val answersId = arrayListOf(3, 2, 2, 1, 0)

}