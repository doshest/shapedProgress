package com.dosh.androidshapeprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.dosh.androidshapeprogressbar.utils.DensityUtil;
import com.dosh.androidshapeprogressbar.utils.PercentStyle;

public class ShapedProgressbar extends RelativeLayout {


	


	private ImageView imageView;
	private ShapedSquareBarView bar;
	private boolean opacity = false;
	private boolean greyscale;
	private boolean isFadingOnProgress = false;

	public ShapedProgressbar(Context context) {
		super(context);
		initProgressBar(context);
	}
	
	public ShapedProgressbar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initProgressBar(context);
	}
	public ShapedProgressbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initProgressBar(context);
	}
	private void initProgressBar(Context context){
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.progressbar_layout, this, true);
		bar = (ShapedSquareBarView) findViewById(R.id.squareProgressBar1);
        imageView = (ImageView) findViewById(R.id.imageView1);
		bar.bringToFront();
	}
	
	public void setImage(int image){
		this.imageView.setImageResource(image);
	}
	
	public void setImageScaleType(ScaleType scaleType){
		this.imageView.setScaleType(scaleType);
	}
	
	public void setProgress(double progress){
		//TODO
		bar.setProgress(progress);
		if (opacity) {
			if (isFadingOnProgress) {
				setOpacity(100 - (int) progress);
			} else {
				setOpacity((int) progress);
			}
		} else {
			setOpacity(100);
		}
	}
	
	public void setHoloColor(int androidHoloColor){
		this.bar.setColor(getContext().getResources().getColor(androidHoloColor));
	}
	
	public void setColor(String colorString){
		this.bar.setColor(Color.parseColor(colorString));
	}
	
	public void setColorRGB(int r,int g,int b){
		this.bar.setColor(Color.rgb(r, g, b));
	}
	
	public void setColor(int color){
		this.bar.setColor(color);
	}
	
	public void setWidth(int width){
		int padding = DensityUtil.dp2Px(width, getContext());
		this.imageView.setPadding(padding, padding, padding, padding);
		
		bar.setWidthInDp(width);
	}
	
	private void setOpacity(int progress) {
		imageView.setAlpha((int) (2.55 * progress));
	}
	
	public void setCanOpacity(boolean opacity) {
		this.opacity = opacity;
		setProgress(bar.getProgress());
	}
	
	public void setImageGrayscale(boolean greyscale) {
		this.greyscale = greyscale;
		if (greyscale) {
			ColorMatrix matrix = new ColorMatrix();
			matrix.setSaturation(0);
			imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
		} else {
			imageView.setColorFilter(null);
		}
	}
	

	public boolean isOpacity() {
		return opacity;
	}


	public boolean isGreyscale() {
		return greyscale;
	}


	public void drawOutline(boolean drawOutline) {
		bar.setOutline(drawOutline);
	}
	
	

	public boolean isOutline() {
		return bar.isOutline();
	}


	public void drawStartline(boolean drawStartline) {
		bar.setStartline(drawStartline);
	}


	public boolean isStartline() {
		return bar.isStartline();
	}
	
	public void showProgress(boolean showProgress) {
		bar.setShowProgress(showProgress);
	}

	/**
	 * If the progress text inside of the image is enabled.
	 * 
	 * @return true if it is or not.
	 */
	public boolean isShowProgress() {
		return bar.isShowProgress();
	}
	
	
	public void setPercentStyle(PercentStyle percentStyle) {
		bar.setPercentStyle(percentStyle);
	}

	/**
	 * Returns the {@link PercentStyle} of the percent text. Maybe returns the
	 * default value, check {@link #setPercentStyle(PercentStyle)} fo that.
	 * 
	 * @return the percent style of the moment.
	 */
	public PercentStyle getPercentStyle() {
		return bar.getPercentStyle();
	}

	/**
	 * If the progress hits 100% then the progressbar disappears if this flag is
	 * set to <code>true</code>. The default is set to false.
	 * 
	 * @param removeOnFinished
	 *            if it should disappear or not.
	 * @since 1.4.0
	 */
	public void setClearOnHundred(boolean clearOnHundred) {
		bar.setClearOnHundred(clearOnHundred);
	}
	
	  /**
     * Set an image resource directly to the ImageView.
     *
     * @param bitmap the {@link android.graphics.Bitmap} to set.
     */
    public void setImageBitmap(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    /**
     * Set the status of the indeterminate mode. The default is false. You can
     * still configure colour, width and so on.
     *
     * @param indeterminate true to enable the indeterminate mode (default true)
     * @since 1.6.0
     */
    public void setIndeterminate(boolean indeterminate) {
        bar.setIndeterminate(indeterminate);
    }

    /**
     * Returns the status of the indeterminate mode. The default status is false.
     *
     * @since 1.6.0
     */
    public boolean isIndeterminate() {
        return bar.isIndeterminate();
    }
    
    /**
     * Draws a line in the center of the way the progressbar has to go.
     *
     * @param drawCenterline
     *            true if it should or not.
     * @since 1.6.0
     */
    public void drawCenterline(boolean drawCenterline) {
        bar.setCenterline(drawCenterline);
    }

    /**
     * If the centerline is enabled or not.
     *
     * @return true if centerline is enabled.
     * @since 1.6.0
     */
    public boolean isCenterline() {
        return bar.isCenterline();
    }

	/**
	 * Returns the {@link ImageView} that the progress gets drawn around.
     *
     * @return the main ImageView
	 * @since 1.6.0
	 */
	public ImageView getImageView(){
		return imageView;
	}
	
	

}
