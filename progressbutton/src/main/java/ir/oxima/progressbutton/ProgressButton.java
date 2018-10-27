package ir.oxima.progressbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class ProgressButton extends View {

    private Paint paint;
    private Paint paintProgress;
    private Paint paintText;

    private int height;
    private int wight;
    private int centerX;
    private int centerY;
    private float progress;

    private RectF rectF;
    private RectF rectFProgress;

    private int mColor;
    private float mCornerRadius;
    private float mStroke;
    private float mTextSize;
    private int mProgress;
    private String mText;
    private String mFontPath;

    private ButtonState mButtonState;


    public ProgressButton(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        initAttributes(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mColor);

        paintProgress = new Paint();
        paintProgress.setAntiAlias(true);
        paintProgress.setStyle(Paint.Style.FILL_AND_STROKE);
        paintProgress.setColor(mColor);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setColor(mColor);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        wight = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingBottom() - getPaddingTop();
        centerX = wight / 2;
        centerY = (height / 2) - (int) ((paintText.descent() + paintText.ascent()) / 2);

        paint.setStrokeWidth(mStroke);
        paintProgress.setStrokeWidth(mStroke);
        paintProgress.setAlpha((int) ((1.0f - 90) * 255));

        paintText.setTextSize(mTextSize);
        if (mFontPath != null){
            paintText.setTypeface(Utilities.getTypeface(getContext(),mFontPath));
        }


        rectF = new RectF(mStroke, mStroke, wight - mStroke, height - mStroke);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView(canvas);
    }

    private void drawView(Canvas canvas) {
        canvas.drawText(mText, centerX, centerY, paintText);
        canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, paint);
        setProgress(mProgress);
        rectFProgress = new RectF(mStroke, mStroke, progress, height - mStroke);
        canvas.drawRoundRect(rectFProgress, mCornerRadius, mCornerRadius, paintProgress);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.ProgressButton);
        if (attr == null) {
            return;
        }
        try {
            mColor = attr.getColor(R.styleable.ProgressButton_pb_color, Color.parseColor("#ffffff"));
            mCornerRadius = attr.getDimension(R.styleable.ProgressButton_pb_corener_redius, 0);
            mStroke = attr.getDimension(R.styleable.ProgressButton_pb_stroke, 3);
            mProgress = attr.getInteger(R.styleable.ProgressButton_pb_progress, 0);
            mTextSize = attr.getDimension(R.styleable.ProgressButton_pb_text_size, 12);
            mText = attr.getString(R.styleable.ProgressButton_pb_text);
            mFontPath = attr.getString(R.styleable.ProgressButton_pb_fontpath);

        } finally {
            attr.recycle();
        }
    }

    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    public void setState(ButtonState buttonState) {
        setState(buttonState,buttonState.toString());
    }

    public void setState(ButtonState buttonState,String text) {
        mButtonState = buttonState;
        mText = text;
        switch (mButtonState) {
            case Connecting:
                mProgress = 0;
                break;
            case Cancel:
                mProgress = 0;
                break;
            case Pause:
                break;
            case Error:
                mProgress = 0;
                break;
            case Progress:
                break;
            case Complete:
                mProgress = 0;
                break;
        }
        invalidate();
    }

    public ButtonState getState() {
        return mButtonState;
    }

    public void setProgress(final int percent) {
        this.mProgress = percent;
        progress = (percent / 100f) * (wight - mStroke);
        postInvalidate();
    }

    public void setText(String input){
        mText = input;
        invalidate();
    }
}
