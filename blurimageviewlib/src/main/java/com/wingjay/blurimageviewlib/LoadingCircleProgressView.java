package com.wingjay.blurimageviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * A circle progressBar which can change with the real loading progress ratio.
 */
public class LoadingCircleProgressView extends View {

  private float currentProgressRatio = 0.6f;

  private int defaultSize = 80;
  private int defaultStrokeWidth = 10;
  private int strokeWidth = defaultStrokeWidth;
  private int radius = (defaultSize) / 2  - defaultStrokeWidth;

  private Paint bgPaint;
  private RectF rectF;
  private Paint progressPaint;

  private int progressBgColor = Color.BLACK;
  private int progressColor = Color.WHITE;

  public LoadingCircleProgressView(Context context) {
    this(context, null);
  }

  public LoadingCircleProgressView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LoadingCircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    rectF = new RectF();

    bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    bgPaint.setStyle(Paint.Style.STROKE);
    bgPaint.setStrokeWidth(defaultStrokeWidth);
    progressPaint = new Paint(bgPaint);

    setupPaintColor();
  }

  private void setupPaintColor() {
    progressBgColor = 0XAA000000 | progressBgColor;
    progressColor = 0XEE000000 | progressColor;
    bgPaint.setColor(progressBgColor);
    progressPaint.setColor(progressColor);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
      width = Math.max(width, defaultSize);
    } else {
      width = defaultSize;
    }
    if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
      height = Math.max(height, defaultSize);
    } else {
      height = defaultSize;
    }
    setMeasuredDimension(width, height);

    width = Math.min(width, height);
    height = width;
    strokeWidth = Math.min(width / 5, defaultStrokeWidth);
    radius = (width - strokeWidth) / 2;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    bgPaint.setStrokeWidth(strokeWidth - 2);
    progressPaint.setStrokeWidth(strokeWidth);

    // bg Arc
    rectF.set(getHalfStrokeWidth(), getHalfStrokeWidth(),
        radius * 2 - getHalfStrokeWidth(), radius * 2 - getHalfStrokeWidth());
    canvas.drawArc(rectF, 0, 360, false, bgPaint);

    // progress Arc
    canvas.rotate(-90, radius, radius);
    canvas.drawArc(rectF, 0, getCurrentDegree(), false, progressPaint);

  }

  public void setCurrentProgressRatio(float currentProgressRatio) {
    if (currentProgressRatio < 0.0f) {
      currentProgressRatio = 0.f;
    }
    if (currentProgressRatio > 1.f) {
      currentProgressRatio = 1.f;
    }
    this.currentProgressRatio = currentProgressRatio;
    invalidate();
  }

  public void setProgressBgColor(int progressBgColor) {
    this.progressBgColor = progressBgColor;
    setupPaintColor();
    invalidate();
  }

  public void setProgressColor(int progressColor) {
    this.progressColor = progressColor;
    setupPaintColor();
    invalidate();
  }

  private int getHalfStrokeWidth() {
    return strokeWidth / 2;
  }

  private int getCurrentDegree() {
    return Math.min(360, (int) (currentProgressRatio * 360));
  }
}
