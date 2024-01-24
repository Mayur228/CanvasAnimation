package com.example.canvasanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator

class AnimatedSquareView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val originalSize = 100f
    private val scaledHeight = 50f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.BLUE
        startAnimation()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = (width - originalSize) / 2
        val centerY = (height - originalSize) / 2
        canvas.drawRect(centerX, centerY, centerX + originalSize, centerY + originalSize, paint)
    }

    private fun startAnimation() {
        val scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", scaledHeight / originalSize)
        val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 1f)

        scaleDownY.duration = 500
        scaleDownY.interpolator = DecelerateInterpolator()

        scaleUpY.duration = 500
        scaleUpY.interpolator = DecelerateInterpolator()

        val animatorSet = AnimatorSet()

        animatorSet.play(scaleDownY)
        // Delay the scaling up animation by 2000 milliseconds (2 seconds)
        animatorSet.play(scaleUpY).after(2000)

        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.duration = 500 // Set the overall duration of the AnimatorSet

        // Repeat the entire AnimatorSet infinitely
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animatorSet.start()
            }
        })

        animatorSet.start()
    }
}
