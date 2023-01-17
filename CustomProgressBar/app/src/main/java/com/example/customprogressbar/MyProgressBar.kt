package com.example.customprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.Key.CUSTOM

class MyProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_PROGRESS_BAR_COLOR = Color.LTGRAY
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_CUSTOM_PROGRESS_BAR_COLOR = Color.BLUE
        private const val DEFAULT_BORDER_WIDTH = 6.0f

        const val MAX = 100
        var VALUE = 0
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mainProgressBarColor = DEFAULT_PROGRESS_BAR_COLOR
    private var borderProgressBarColor = DEFAULT_BORDER_COLOR
    private var customProgressBarColor = DEFAULT_CUSTOM_PROGRESS_BAR_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private var myWidth = 0
    private var myHeight = 0

    var max = MAX
        set(state) {
            field = state
            invalidate()
        }

    var myValue = VALUE
        set(state) {
            field = state
            invalidate()
        }

    init {
        setupAttributes(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawMainProgressBar(canvas)
        drawCustomProgressBar(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        myWidth = measuredWidth.coerceAtMost(measuredWidth)
        myHeight = measuredHeight.coerceAtMost(measuredHeight)
    }


    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putInt("max", max)
        bundle.putInt("myValue", myValue)
        bundle.putParcelable("superState", super.onSaveInstanceState())

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state

        if (viewState is Bundle) {
            max = viewState.getInt("max", MAX)
            myValue = viewState.getInt("myValue", VALUE)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }

    private fun drawMainProgressBar(canvas: Canvas) {
        paint.color = mainProgressBarColor
        paint.style = Paint.Style.FILL
        paint.strokeWidth = borderWidth
        val rect = Rect(0, 0, myWidth, myHeight)
        canvas.drawRect(rect, paint)

        paint.color = borderProgressBarColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawRect(rect, paint)
    }

    private fun drawCustomProgressBar(canvas: Canvas) {
        paint.color = customProgressBarColor
        paint.style = Paint.Style.FILL
        val myCustomHeight = myHeight * myValue / max
        val customRect = Rect(
            borderWidth.toInt(), myHeight - myCustomHeight,
            myWidth - borderWidth.toInt(), myHeight - borderWidth.toInt()
        )
        canvas.drawRect(customRect, paint)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.MyProgressBarView, 0, 0)
        mainProgressBarColor = typedArray.getColor(
            R.styleable.MyProgressBarView_mainProgressBarColor,
            DEFAULT_PROGRESS_BAR_COLOR)

        customProgressBarColor = typedArray.getColor(
            R.styleable.MyProgressBarView_customProgressBarColor, DEFAULT_CUSTOM_PROGRESS_BAR_COLOR)

        borderProgressBarColor = typedArray.getColor(
            R.styleable.MyProgressBarView_borderProgressBarColor, DEFAULT_BORDER_COLOR)

        borderWidth = typedArray.getDimension(
            R.styleable.MyProgressBarView_borderWidth, DEFAULT_BORDER_WIDTH)

        max = typedArray.getInt(R.styleable.MyProgressBarView_max, MAX)

        myValue = typedArray.getInt(R.styleable.MyProgressBarView_myValue, VALUE)

        typedArray.recycle()
    }
}