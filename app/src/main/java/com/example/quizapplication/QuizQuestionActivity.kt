package com.example.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz_question.*
import kotlin.math.log10

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val questionsList = Constants.getQuestions()

        Log.i("000888", "onCreate: ${questionsList.size}")

        val currentPosition = 1
        val question: Question? = questionsList[currentPosition-1]

        progressBar.progress = (currentPosition)
        tvProgress.text = "$currentPosition"+"/"+progressBar.max
        tvQuestion.text = question!!.question
        ivFlag.setImageResource(question.correctAnswer)
        tvOption1.text = questionsList[0].optionOne
        tvOption2.text = questionsList[0].optionTwo
        tvOption3.text = questionsList[0].optionThree
        tvOption4.text = questionsList[0].optionFour

    }
}