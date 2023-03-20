package com.example.patronuschallenge.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class FocusView : View {
    lateinit var transparentPaint: Paint
    lateinit var semiBlackPaint: Paint
    private val path = Path()

    constructor(context: Context?) : super(context) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initPaints()
    }

    private fun initPaints() {
        transparentPaint = Paint()
        transparentPaint.color = Color.TRANSPARENT
        transparentPaint.strokeWidth = 10f
        semiBlackPaint = Paint()
        semiBlackPaint.color = Color.TRANSPARENT
        semiBlackPaint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.reset()
        path.addCircle(
            (canvas.width / 2).toFloat(),
            (canvas.height / 2).toFloat(),
            (width / 2).toFloat(),
            Path.Direction.CW
        )
        path.fillType = Path.FillType.INVERSE_EVEN_ODD
        canvas.drawCircle(
            (canvas.width / 2).toFloat(),
            (canvas.height / 2).toFloat(),
            (width / 2).toFloat(),
            transparentPaint
        )
        canvas.drawPath(path, semiBlackPaint)
        canvas.clipPath(path)
        canvas.drawColor(Color.parseColor("#ffffff"))
    }
}