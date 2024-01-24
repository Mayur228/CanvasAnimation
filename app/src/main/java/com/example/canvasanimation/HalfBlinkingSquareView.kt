package com.example.canvasanimation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class HalfBlinkingSquareView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val squareSize = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000) // Animation duration: 1000 milliseconds

    init {
        paint.color = Color.BLUE
        startBlinking()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alpha = (animator.animatedFraction * 255).toInt()
        paint.alpha = alpha
        val centerX = (width - squareSize) / 2
        val centerY = (height - squareSize) / 2
        canvas.drawRect(centerX, centerY, centerX + squareSize, centerY + squareSize, paint)
    }

    private fun startBlinking() {
        animator.addUpdateListener {
            invalidate()
        }
        animator.reverse()
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
    }
}
