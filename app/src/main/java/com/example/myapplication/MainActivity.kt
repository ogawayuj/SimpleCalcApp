package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.constant.Operator
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val STR_ZERO : String = "0"
    private val STR_BRANK : String = ""
    private val STR_ERROR : String = "Error"
    private val INT_ZERO : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* 表示テキスト */
        val textArea : TextView = findViewById(R.id.display)
        textArea.text = STR_ZERO
        /* 被計算値の前方の値：「1+2」の1の方 */
        var forwardOperand : Int = INT_ZERO
        var selectedOperator : String = STR_BRANK
        var isClear : Boolean = false

        /* 数字ボタン : 初期化が面倒だから配列に入れて処理してみる */
        var listOfNumBtn : ArrayList<Button> = ArrayList()
        //NumBtnIdをもとに、
        listOf(R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9)
            .forEach { btnId -> listOfNumBtn.add(findViewById(btnId)) }
        listOfNumBtn.forEach{ btnInstance -> btnInstance.setOnClickListener {
                if(isClear){ textArea.text = STR_ZERO}
                var valOfNumBtn : Int = getNumFromTextView(btnInstance)
                textArea.text = pushNumButton(valOfNumBtn , textArea.text.toString())
            }
        }

        /* 四則演算子ボタン */
        var listOfOperatorBtn : ArrayList<Button> = ArrayList()
        listOf(R.id.btnAdd,R.id.btnSub,R.id.btnDiv,R.id.btnMulti)
            .forEach { btnId -> listOfOperatorBtn.add(findViewById(btnId))}
        listOfOperatorBtn.forEach { btnInstance -> btnInstance.setOnClickListener {
                selectedOperator = btnInstance.text.toString()
                forwardOperand = getNumFromTextView(textArea)
                isClear = true
            }
        }

        /* ＝ボタン */
        val btnEqual : Button = findViewById(R.id.btnEqual)
        btnEqual.setOnClickListener {
            if (selectedOperator.equals(STR_BRANK)) { return@setOnClickListener }
            var backwardOperand = getNumFromTextView(textArea)
            try {
                textArea.text = calculation(selectedOperator, forwardOperand, backwardOperand).toString()
                selectedOperator = STR_BRANK
            }catch (e : ArithmeticException) {
                textArea.text = e.message
                selectedOperator = STR_BRANK
            }
        }

        /* Clearボタン */
        val btnClear : Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            textArea.text = STR_ZERO
            selectedOperator = STR_BRANK
            forwardOperand = INT_ZERO
            isClear = false
        }
    }

    /**
     * @param operator(表示用の値)
     * @param forwardOperand(四則演算の前方部)
     * @param backwardOperand(四則演算の後方部)
     * @throws ArithmeticException (0除算した場合 除算かつ後方部が0)
     * */
    private fun calculation (operator : String, forwardOperand : Int, backwardOperand : Int) : Int {
        return if (operator.equals(Operator.ADD.label)) {
            forwardOperand + backwardOperand
        } else if (operator.equals(Operator.SUBTRACT.label)) {
            forwardOperand - backwardOperand
        } else if (operator.equals(Operator.MULTIPLE.label)) {
            forwardOperand * backwardOperand
        } else if (operator.equals(Operator.DIVIDE.label)) {
            //0除算なら処理終了
            if (backwardOperand == INT_ZERO){  throw ArithmeticException(STR_ERROR) }
            forwardOperand / backwardOperand
        } else {
            INT_ZERO
        }
    }

    /**
     * 数字ボタンをクリックした際の挙動
     * <br> 画面表示値で処理の制御を変える
     * <br> Error：数字ボタンの値を返却
     * <br> 0：数字ボタンの値を返却
     * <br> 0以上：表示値 と数字ボタンの値を結合したのち返却
     * */
    private fun pushNumButton(valOfPushedBtn : Int, currentTextArea : String) : String {
        if(STR_ERROR.equals(currentTextArea)){ return valOfPushedBtn.toString() }
        when (STR_ZERO.equals(currentTextArea)) {
            true  -> return valOfPushedBtn.toString()
            false -> return currentTextArea + valOfPushedBtn.toString()
        }
    }

    private fun getNumFromTextView(textView: TextView) :Int {
        return textView.text.toString().toInt()
    }

}
