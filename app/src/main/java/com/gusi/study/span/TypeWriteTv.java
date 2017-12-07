package com.gusi.study.span;

/**
 * @Author ylw  2017-12-07 17:08
 */
public class TypeWriteTv  {
  //private String text;
  //private int length;
  //public TypeWriteTv(Context context, @Nullable AttributeSet attrs) {
  //  super(context, attrs);
  //}
  //
  //
  //private void init(Context context, AttributeSet attrs, int defStyleAttr) {
  //  text = (String) getText();
  //  length = text.length();
  //  int speed = 100;
  //  boolean isEnableTypeWrite = true;
  //  boolean isEnableTtf = true;
  //  if (attrs != null) {
  //    TypedArray typedArray = context.getTheme().
  //        obtainStyledAttributes(attrs, R.styleable.TypeWrite, defStyleAttr, 0);
  //    int n = typedArray.getIndexCount();
  //    for (int i = 0; i < n; i++) {
  //      int attr = typedArray.getIndex(i);
  //      if (attr == R.styleable.TypeWrite_setSpeed) {
  //        speed = typedArray.getInteger(attr, 100);
  //
  //      } else if (attr == R.styleable.TypeWrite_isEnableTypeWrite) {
  //        isEnableTypeWrite = typedArray.getBoolean(attr, true);
  //
  //      } else if (attr == R.styleable.TypeWrite_isEnableTtf) {
  //        isEnableTtf = typedArray.getBoolean(attr, true);
  //
  //      }
  //    }
  //    typedArray.recycle();
  //  }
  //  if (isEnableTtf) {
  //    String fontName = "Heavy.ttf";
  //    super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
  //        "fonts/" + fontName), defStyleAttr);
  //  }
  //
  //  if (isEnableTypeWrite) {
  //    Flowable.interval(UPDATE_DELAY, speed, TimeUnit.MILLISECONDS)
  //        .take(length + 1)
  //        .map(new Function<Long, String>() {
  //          @Override
  //          public String apply(Long aLong) throws Exception {
  //            return text.substring(0, mIndex);
  //          }
  //        })
  //        .subscribeOn(Schedulers.io())
  //        .observeOn(AndroidSchedulers.mainThread())
  //        .subscribe(new Consumer<String>() {
  //          @Override
  //          public void accept(String str) throws Exception {
  //            mIndex++;
  //            setText(str);
  //          }
  //        });
  //  }
  //

  }
