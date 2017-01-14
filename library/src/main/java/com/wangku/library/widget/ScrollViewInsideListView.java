package com.wangku.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ScrollViewInsideListView extends ListView {

	public ScrollViewInsideListView(Context context) {
		super(context);
	}

	public ScrollViewInsideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollViewInsideListView(Context context, AttributeSet attrs,
									int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
