package com.dosh.androidshapeprogressbar;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.dosh.androidshapeprogressbar.utils.DensityUtil;
import com.dosh.androidshapeprogressbar.utils.PercentStyle;

@SuppressLint("ResourceAsColor")
public class ShapedSquareBarView extends View {
	private double progress;
    private Paint progressBarPaint;
    private Paint outlinePaint;
    private Paint textPaint;
    
    private float widthInDp = 10;
    private float strokewidth = 0;
    private Canvas canvas;
    
    private boolean outline = false;
    private boolean startline = false;
    private boolean showProgress = false;
    private boolean centerline = false;
    
    private PercentStyle percentSettings = new PercentStyle(Align.CENTER, 150,
            true);
    private boolean clearOnHundred = false;
    private boolean isIndeterminate = false;
    private int indeterminateCount = 1;

    private float indeterminateWidth = 20.0f;
    
	public ShapedSquareBarView(Context context) {
		super(context);
		initPaints(context);
	}
	public ShapedSquareBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaints(context);
	}

    @SuppressLint("ResourceAsColor")
	private void initPaints(Context context){
    	this.progressBarPaint = new Paint();
    	progressBarPaint.setColor(android.R.color.holo_green_dark);
    	progressBarPaint.setStrokeWidth(DensityUtil.dp2Px(widthInDp, context));
    	progressBarPaint.setAntiAlias(true);//for smooth
    	progressBarPaint.setStyle(Style.STROKE);
    	
    	this.outlinePaint = new Paint();
    	outlinePaint.setColor(android.R.color.black);
    	outlinePaint.setStrokeWidth(1);
    	outlinePaint.setAntiAlias(true);
    	outlinePaint.setStyle(Style.STROKE);
    	
    	this.textPaint = new Paint();
    	textPaint.setColor(android.R.color.black);
    	textPaint.setAntiAlias(true);
    	textPaint.setStyle(Style.STROKE);
    	
    }
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.canvas = canvas;
		
		strokewidth = DensityUtil.dp2Px(widthInDp, getContext());
		float scope = (canvas.getWidth() +canvas.getHeight())*2 - strokewidth;
		 if (isOutline()) {
	            drawOutline();
	        }

	        if (isStartline()) {
	            drawStartline();
	        }

	        if (isShowProgress()) {
	        	drawPersentage(percentSettings);
	        }

	        if (isCenterline()) {
	            drawCenterline(strokewidth);
	        }

	        if ((isClearOnHundred() && progress == 100.0) || (progress <= 0.0)) {
	            return;
	        }

	        if (isIndeterminate()) {
	            Path path = new Path();
	            DrawStop drawEnd = getDrawEnd((scope / 100) * Float.valueOf(String.valueOf(indeterminateCount)), canvas);

	            if (drawEnd.place == Place.TOP) {
	                path.moveTo(drawEnd.location - indeterminateWidth - strokewidth, strokewidth / 2);
	                path.lineTo(drawEnd.location, strokewidth / 2);
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.RIGHT) {
	                path.moveTo(canvas.getWidth() - (strokewidth / 2), drawEnd.location - indeterminateWidth);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth
	                        + drawEnd.location);
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.BOTTOM) {
	                path.moveTo(drawEnd.location - indeterminateWidth - strokewidth,
	                        canvas.getHeight() - (strokewidth / 2));
	                path.lineTo(drawEnd.location, canvas.getHeight()
	                        - (strokewidth / 2));
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.LEFT) {
	                path.moveTo((strokewidth / 2), drawEnd.location - indeterminateWidth
	                        - strokewidth);
	                path.lineTo((strokewidth / 2), drawEnd.location);
	                canvas.drawPath(path, progressBarPaint);
	            }

	            indeterminateCount++;
	            if (indeterminateCount > 100) {
	                indeterminateCount = 0;
	            }
	            invalidate();
	        } else {
	            Path path = new Path();
	            DrawStop drawEnd = getDrawEnd((scope / 100) * Float.valueOf(String.valueOf(progress)), canvas);

	            if (drawEnd.place == Place.TOP) {
	                if (drawEnd.location > (canvas.getWidth() / 2)) {
	                    path.moveTo(canvas.getWidth() / 2, strokewidth / 2);
	                    path.lineTo(drawEnd.location, strokewidth / 2);
	                } else {
	                    path.moveTo(canvas.getWidth() / 2, strokewidth / 2);
	                    path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth / 2);
	                    path.lineTo(canvas.getWidth() - (strokewidth / 2), canvas.getHeight() - strokewidth / 2);
	                    path.lineTo(strokewidth / 2, canvas.getHeight() - strokewidth / 2);
	                    path.lineTo(strokewidth / 2, strokewidth / 2);
	                    path.lineTo(drawEnd.location, strokewidth / 2);
	                }
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.RIGHT) {
	                path.moveTo(canvas.getWidth() / 2, strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth / 2
	                        + drawEnd.location);
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.BOTTOM) {
	                path.moveTo(canvas.getWidth() / 2, strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), canvas.getHeight());
	                path.moveTo(canvas.getWidth(), canvas.getHeight() - strokewidth / 2);
	                path.lineTo(drawEnd.location, canvas.getHeight()
	                        - (strokewidth / 2));
	                canvas.drawPath(path, progressBarPaint);
	            }

	            if (drawEnd.place == Place.LEFT) {
	                path.moveTo(canvas.getWidth() / 2, strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), strokewidth / 2);
	                path.lineTo(canvas.getWidth() - (strokewidth / 2), canvas.getHeight() - strokewidth / 2);
	                path.lineTo(0, canvas.getHeight() - strokewidth / 2);
	                path.moveTo(strokewidth / 2, canvas.getHeight() - strokewidth / 2);
	                path.lineTo((strokewidth / 2), drawEnd.location);
	                canvas.drawPath(path, progressBarPaint);
	            }
	        }
	}
	
	
	 private void drawStartline() {
	        Path outlinePath = new Path();
	        outlinePath.moveTo(canvas.getWidth() / 2, 0);
	        outlinePath.lineTo(canvas.getWidth() / 2, strokewidth);
	        canvas.drawPath(outlinePath, outlinePaint);
	    }

	    private void drawOutline() {
	        Path outlinePath = new Path();
	        outlinePath.moveTo(0, 0);
	        outlinePath.lineTo(canvas.getWidth(), 0);
	        outlinePath.lineTo(canvas.getWidth(), canvas.getHeight());
	        outlinePath.lineTo(0, canvas.getHeight());
	        outlinePath.lineTo(0, 0);
	        canvas.drawPath(outlinePath, outlinePaint);
	    }
	    
	    public double getProgress() {
	        return progress;
	    }

	    public void setProgress(double progress) {
	        this.progress = progress;
	        this.invalidate();
	    }

	    public void setColor(int color) {
	        progressBarPaint.setColor(color);
	        this.invalidate();
	    }

	    public void setWidthInDp(int width) {
	        this.widthInDp = width;
	        progressBarPaint.setStrokeWidth(DensityUtil.dp2Px(widthInDp, getContext()));
	        this.invalidate();
	    }

	    public boolean isOutline() {
	        return outline;
	    }

	    public void setOutline(boolean outline) {
	        this.outline = outline;
	        this.invalidate();
	    }

	    public boolean isStartline() {
	        return startline;
	    }

	    public void setStartline(boolean startline) {
	        this.startline = startline;
	        this.invalidate();
	    }
	    
	    public boolean isShowProgress() {
	        return showProgress;
	    }

	    public void setShowProgress(boolean showProgress) {
	        this.showProgress = showProgress;
	        this.invalidate();
	    }

	    public void setPercentStyle(PercentStyle percentSettings) {
	        this.percentSettings = percentSettings;
	        this.invalidate();
	    }

	    public PercentStyle getPercentStyle() {
	        return percentSettings;
	    }

	    public void setClearOnHundred(boolean clearOnHundred) {
	        this.clearOnHundred = clearOnHundred;
	        this.invalidate();
	    }

	    public boolean isClearOnHundred() {
	        return clearOnHundred;
	    }
	    
	    public boolean isCenterline() {
	        return centerline;
	    }

	    public void setCenterline(boolean centerline) {
	        this.centerline = centerline;
	        this.invalidate();
	    }

	    public boolean isIndeterminate() {
	        return isIndeterminate;
	    }

	    public void setIndeterminate(boolean indeterminate) {
	        isIndeterminate = indeterminate;
	        this.invalidate();
	    }


	    private void drawPersentage(PercentStyle settings){
	    	textPaint.setTextAlign(settings.getAlign());
	    	 if (settings.getTextSize() == 0) {
	             textPaint.setTextSize((canvas.getHeight() / 10) * 4);
	         } else {
	             textPaint.setTextSize(settings.getTextSize());
	         }
	    	 
	    	 String percentString = new DecimalFormat("###").format(getProgress());
	    	 
	    	 if (settings.isPercentSign()) {
	             percentString = percentString + percentSettings.getCustomText();
	         }
	    	 
	    	 textPaint.setColor(percentSettings.getTextColor());

	         canvas.drawText(
	                 percentString,
	                 canvas.getWidth() / 2,
	                 (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint
	                         .ascent()) / 2)), textPaint);
	    }
	    

	    private void drawCenterline(float strokewidth) {
	        float centerOfStrokeWidth = strokewidth / 2;
	        Path centerlinePath = new Path();
	        centerlinePath.moveTo(centerOfStrokeWidth, centerOfStrokeWidth);
	        centerlinePath.lineTo(canvas.getWidth() - centerOfStrokeWidth, centerOfStrokeWidth);
	        centerlinePath.lineTo(canvas.getWidth() - centerOfStrokeWidth, canvas.getHeight() - centerOfStrokeWidth);
	        centerlinePath.lineTo(centerOfStrokeWidth, canvas.getHeight() - centerOfStrokeWidth);
	        centerlinePath.lineTo(centerOfStrokeWidth, centerOfStrokeWidth);
	        canvas.drawPath(centerlinePath, outlinePaint);
	    }
	    
	    public DrawStop getDrawEnd(float percent, Canvas canvas) {
	        DrawStop drawStop = new DrawStop();
	        strokewidth = DensityUtil.dp2Px(widthInDp, getContext());
	        float halfOfTheImage = canvas.getWidth() / 2;

	        if (percent > halfOfTheImage) {
	            float second = percent - halfOfTheImage;

	            if (second > canvas.getHeight()) {
	                float third = second - canvas.getHeight();

	                if (third > canvas.getWidth()) {
	                    float forth = third - canvas.getWidth();

	                    if (forth > canvas.getHeight()) {
	                        float fifth = forth - canvas.getHeight();

	                        if (fifth == halfOfTheImage) {
	                            drawStop.place = Place.TOP;
	                            drawStop.location = halfOfTheImage;
	                        } else {
	                            drawStop.place = Place.TOP;
	                            drawStop.location = strokewidth + fifth;
	                        }
	                    } else {
	                        drawStop.place = Place.LEFT;
	                        drawStop.location = canvas.getHeight() - forth;
	                    }

	                } else {
	                    drawStop.place = Place.BOTTOM;
	                    drawStop.location = canvas.getWidth() - third;
	                }
	            } else {
	                drawStop.place = Place.RIGHT;
	                drawStop.location = strokewidth + second;
	            }

	        } else {
	            drawStop.place = Place.TOP;
	            drawStop.location = halfOfTheImage + percent;
	        }

	        return drawStop;
	    }

	    private class DrawStop {

	        private Place place;
	        private float location;

	        public DrawStop() {

	        }
	    }

	    public enum Place {
	        TOP, RIGHT, BOTTOM, LEFT
	    }
    
    
    




}
