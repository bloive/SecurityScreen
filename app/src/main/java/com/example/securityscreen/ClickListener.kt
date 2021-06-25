package com.example.securityscreen

interface ClickListener {
    fun onNumberClick(index: Int)
    fun onFingerPrintClick()
    fun onBackClick()
}

interface FingerPrintClickListener {
    fun onFingerPrintClick()
}

interface BackClickListener {
    fun onBackClick()
}