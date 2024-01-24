package com.example.canvasanimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View

class BlinkingSquareView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val squareSize = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var isSquareVisible = true
    private val handler = Handler(Looper.getMainLooper())

    init {
        paint.color = Color.BLUE
        startBlinking()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isSquareVisible) {
            val centerX = (width - squareSize) / 2
            val centerY = (height - squareSize) / 2
            canvas.drawRect(centerX, centerY, centerX + squareSize, centerY + squareSize, paint)
        }
    }

    private fun startBlinking() {
        handler.post(object : Runnable {
            override fun run() {
                isSquareVisible = !isSquareVisible
                invalidate()
                handler.postDelayed(this, 500) // Toggle visibility every 500 milliseconds
            }
        })
    }
}
