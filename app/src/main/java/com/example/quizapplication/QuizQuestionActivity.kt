package com.example.quizapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import kotlin.math.log10

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList :ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int =0
    private var mUsername:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUsername = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()

        // Log.i("000888", "onCreate: ${questionsList.size}")

        setQuestion()

        tvOption1.setOnClickListener{ view ->
            onClick(view)
        }
        tvOption2.setOnClickListener{ view ->
            onClick(view)
        }
        tvOption3.setOnClickListener{ view ->
            onClick(view)
        }
        tvOption4.setOnClickListener{ view ->
            onClick(view)
        }
    }

    fun setQuestion(){
        val question: Question? = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()
        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit.text = "FINISH"
        }
        else{
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = (mCurrentPosition)
        tvProgress.text = "$mCurrentPosition"+"/"+progressBar.max
        tvQuestion.text = question!!.question
        ivFlag.setImageResource(question!!.image)

        tvOption1.text = question!!.optionOne
        tvOption2.text = question!!.optionTwo
        tvOption3.text = question!!.optionThree
        tvOption4.text = question!!.optionFour

        btnSubmit.setOnClickListener { view ->
            onClick(view)
        }

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, tvOption1)
        options.add(1, tvOption2)
        options.add(2, tvOption3)
        options.add(3, tvOption4)


        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)


        }

    }

    override fun onClick(v: View?) {
        Log.d("000777", "onClick: "+v?.id)
        when(v?.id){
            R.id.tvOption1 -> {
                selectedOptionView(tvOption1,1)
            }
            R.id.tvOption2 -> {
                selectedOptionView(tvOption2,2)
            }
            R.id.tvOption3 -> {
                selectedOptionView(tvOption3,3)
            }
            R.id.tvOption4 -> {
                selectedOptionView(tvOption4,4)
            }
            R.id.btnSubmit -> {
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else -> {
                        val intent:Intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUsername)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                        startActivity(intent)
                        finish()

                           // Toast.makeText(this, "You have successfully completed the Quiz!",Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit.text = "FINISH"
                    }
                    else{
                        btnSubmit.text = "GO TO THE NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)


    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 -> tvOption1.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tvOption2.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tvOption3.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tvOption4.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

}

