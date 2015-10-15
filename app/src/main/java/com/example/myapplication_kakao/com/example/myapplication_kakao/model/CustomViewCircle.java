package com.example.myapplication_kakao.com.example.myapplication_kakao.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.github.siyamed.shapeimageview.shader.CircleShader;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;


/**
 * Created by 상유 on 2015-10-16.
 */
public class CustomViewCircle extends com.android.volley.toolbox.NetworkImageView  {
    private final static boolean DEBUG = false;
    private ShaderHelper pathHelper;
    private CircleShader shader;

    public CustomViewCircle(Context context) {
        super(context);
        setup(context, null, 0);
    }

    public CustomViewCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs, 0);
    }

    public CustomViewCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup(context, attrs, defStyle);
    }

    private void setup(Context context, AttributeSet attrs, int defStyle) {
        getPathHelper().init(context, attrs, defStyle);
    }

    protected ShaderHelper getPathHelper() {
        if(pathHelper == null) {
            pathHelper = createImageViewHelper();
        }
        return pathHelper;
    }

    public ShaderHelper createImageViewHelper() {
        shader = new CircleShader();
        return shader;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(getPathHelper().isSquare()) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    //Required by path helper
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        getPathHelper().onSizeChanged(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(DEBUG) {
            canvas.drawRGB(10, 200, 200);
        }

        if(!getPathHelper().onDraw(canvas)) {
            super.onDraw(canvas);
        }
    }

    public void setBorderColor(final int borderColor) {
        getPathHelper().setBorderColor(borderColor);
        invalidate();
    }

    public int getBorderWidth() {
        return getPathHelper().getBorderWidth();
    }

    public void setBorderWidth(final int borderWidth) {
        getPathHelper().setBorderWidth(borderWidth);
        invalidate();
    }

    public float getBorderAlpha() {
        return getPathHelper().getBorderAlpha();
    }

    public void setBorderAlpha(final float borderAlpha) {
        getPathHelper().setBorderAlpha(borderAlpha);
        invalidate();
    }

    public void setSquare(final boolean square) {
        getPathHelper().setSquare(square);
        invalidate();
    }
    public float getBorderRadius() {
        if(shader != null) {
            return shader.getBorderRadius();
        }
        return 0;
    }

    public void setBorderRadius(final float borderRadius) {
        if(shader != null) {
            shader.setBorderRadius(borderRadius);
            invalidate();
        }
    }
}
