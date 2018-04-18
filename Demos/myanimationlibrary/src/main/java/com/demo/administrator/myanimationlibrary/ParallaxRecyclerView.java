package com.demo.administrator.myanimationlibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class ParallaxRecyclerView extends RecyclerView implements OnScrollListener{

  public ParallaxRecyclerView(Context context) {
    this(context,null);
  }

  public ParallaxRecyclerView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public ParallaxRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }


  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {

  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
      int totalItemCount) {

  }
}
