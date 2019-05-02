package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.constant.Operator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val STR_ZERO : String = "0"

        var currentVal : Int = 0
        var isCalc : Boolean
        /* ボタンの初期化が冗長な気がする */
        /* 数字ボタン valだと不変らしい */
        val btn0 : Button = findViewById(R.id.btn0)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)
        val btn4 : Button = findViewById(R.id.btn4)
        val btn5 : Button = findViewById(R.id.btn5)
        val btn6 : Button = findViewById(R.id.btn6)
        val btn7 : Button = findViewById(R.id.btn7)
        val btn8 : Button = findViewById(R.id.btn8)
        val btn9 : Button = findViewById(R.id.btn9)
        /* 数字ボタンの初期化が面倒だから配列に入れて処理してみる */
        var numBtnList :Array<Button>


        /* 計算ボタン */
        val btnadd : Button = findViewById(R.id.btnAdd)
        val btnSub : Button = findViewById(R.id.btnSub)
        val btnDiv : Button = findViewById(R.id.btnDiv)
        val btnMulti : Button = findViewById(R.id.btnMulti)
        /* 表示テキスト */
        val textArea : TextView = findViewById(R.id.display)

        fun getNumFromNumButton(btn : Button) :Int {
            return btn.text.toString().toInt()
        }

        fun pushNumButton(num: Int) : String {
            textArea.text = if (STR_ZERO.equals(textArea.text.toString()))
                textArea.text.toString() + num.toString() else textArea.text.toString() + num.toString()
            return textArea.text.toString()
        }
    }
}
