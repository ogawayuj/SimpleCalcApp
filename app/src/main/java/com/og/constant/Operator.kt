package com.og.constant

enum class Operator (val value : String, val label : String) {
    /**
     * value:プログラム内での値
     * label:画面表示値
     * labelをもっているのは微妙？
     * */
    ADD("+", "+")
    , MULTIPLE("*","×")
    , SUBTRACT("-", "-")
    , DIVIDE("/", "÷")
}