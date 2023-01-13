package by.sergey.customview

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class EmotionalFace(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 3.0f

        const val HAPPY = 0L
        const val SAD = 1L
        const val COLD = 2L
    }

    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR

    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var size = 0
    private val mouthPath = Path()

    var happinessState = HAPPY
        set(state) {
            field = state
            invalidate()
        }

    init {
        setupAttributes(attrs)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth.coerceAtMost(measuredHeight)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putLong("happinessState", happinessState)
        bundle.putParcelable("superState", super.onSaveInstanceState())

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state

        if (viewState is Bundle) {
            happinessState = viewState.getLong("happinessState", HAPPY)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
            mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
        } else if(happinessState == SAD){
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        } else{
            mouthPath.lineTo( size * 0.78f, size * 0.7f)
            paint.style = Paint.Style.STROKE
        }

        paint.color = mouthColor

        canvas.drawPath(mouthPath, paint)

    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView, 0, 0)

        happinessState =
            typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor =
            typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(
            R.styleable.EmotionalFaceView_borderColor,
            DEFAULT_BORDER_COLOR
        )
        borderWidth = typedArray.getDimension(
            R.styleable.EmotionalFaceView_borderWidth,
            DEFAULT_BORDER_WIDTH
        )

        typedArray.recycle()
    }


}