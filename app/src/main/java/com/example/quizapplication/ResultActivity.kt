package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private var mUsername:String? = null
    private var mCorrectAnswer:Int? = null
    private var mTotalAnswers:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        mUsername = intent.getStringExtra(Constants.USER_NAME)
        mCorrectAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        mTotalAnswers = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        tv_name.text =mUsername
        tv_score.text ="Your Score is $mCorrectAnswer out of $mTotalAnswers"


        btn_finish.setOnClickListener { view ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}