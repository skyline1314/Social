package com.skyline.social.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.skyline.social.R;


public class CircleAvatar extends ImageView {
	private int backgroudcolor= Color.TRANSPARENT;
	private boolean isShowYellow = false;
	private boolean isShowCircle = false;//是否显示头像外面的圆圈
	private int outCircleColor;//头像外面圆圈的颜色
	private int outStrokeWidth;//头像外面圆圈粗细
	public CircleAvatar(Context context) {
		super(context);
	}

	public CircleAvatar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleAvatar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public void isShowYellowCircle(boolean b){
		isShowYellow = b;
	}

	/**
	 * 头像外圆圈
	 * @param color 圆圈颜色 0xffffffff类型
	 * @param StrokeWidth 圆圈粗细
	 */
	public void setAvatarOutCircle(int color, int StrokeWidth) {
		isShowCircle = true;
		outCircleColor = color;
		outStrokeWidth = StrokeWidth;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		if(isInEditMode())
			return;
		
		Drawable drawable = getDrawable();
		if (drawable == null || !(drawable instanceof BitmapDrawable)) {
			return; // couldn't resolve the URI
		}
		
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(backgroudcolor);		
		canvas.drawCircle(getWidth() * 0.5f, getHeight() * 0.5f, getWidth()
				* 0.5f, paint);
		if (isShowYellow){
			final Paint paintYellow = new Paint();
			paintYellow.setAntiAlias(true);
			paintYellow.setStyle(Style.FILL);
			paintYellow.setColor(getResources().getColor(R.color.yellow_f9));
			canvas.drawCircle(getWidth() * 0.5f, getHeight() * 0.5f, getWidth()
					* 0.5f, paintYellow);
		}


 		Drawable bgDrawable = getBackground();
		if(bgDrawable != null && (bgDrawable instanceof BitmapDrawable)){
			Bitmap bitmap = ((BitmapDrawable) bgDrawable).getBitmap();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final Rect destRect = new Rect(0, 0, getWidth(), getHeight());
			
			canvas.drawBitmap(bitmap, rect, destRect, paint);
		}
		
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final Rect destRect = new Rect(getPaddingLeft(), getPaddingTop(), getWidth()-getPaddingRight(), getHeight()-getPaddingBottom());
		

		final int color = 0xff424242;
		//final Paint paint = new Paint();
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(),
				null, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
						| Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
						| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
						| Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		
		int circler=(getWidth()-getPaddingLeft()-getPaddingRight())/2;
		int circlex=getPaddingLeft()+circler;
		int circley=getPaddingTop()+circler;
		canvas.drawCircle(circlex, circley, circler, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		if (!bitmap.isRecycled())
			canvas.drawBitmap(bitmap, rect, destRect, paint);

		if (isShowCircle) {
			final Paint paintOut = new Paint();
			paintOut.setAntiAlias(true);
			paintOut.setStyle(Style.STROKE);
			paintOut.setStrokeWidth(outStrokeWidth);
			paintOut.setColor(outCircleColor);
			canvas.drawCircle(getWidth() * 0.5f, getHeight() * 0.5f, getWidth()
					* 0.5f - outStrokeWidth / 2, paintOut);
		}
		canvas.restoreToCount(sc);
	}

	
	public void setBackgroundColor(int color) {
		backgroudcolor=color;		
		invalidate();
	}

}
