package com.example.quizapplication

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import kotlin.math.log10

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList :ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

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

        progressBar.progress = (mCurrentPosition)
        tvProgress.text = "$mCurrentPosition"+"/"+progressBar.max
        tvQuestion.text = question!!.question
        ivFlag.setImageResource(question!!.image)

        tvOption1.text = question!!.optionOne
        tvOption2.text = question!!.optionTwo
        tvOption3.text = question!!.optionThree
        tvOption4.text = question!!.optionFour

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
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)


    }

}

