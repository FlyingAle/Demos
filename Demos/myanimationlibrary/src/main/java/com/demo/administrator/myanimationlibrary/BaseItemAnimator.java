package com.demo.administrator.myanimationlibrary;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SimpleItemAnimator;

public class BaseItemAnimator extends SimpleItemAnimator {

  @Override
  public boolean animateRemove(ViewHolder holder) {
    return false;
  }

  @Override
  public boolean animateAdd(ViewHolder holder) {
    return false;
  }

  @Override
  public boolean animateMove(ViewHolder holder, int fromX, int fromY, int toX, int toY) {
    return false;
  }

  @Override
  public boolean animateChange(ViewHolder oldHolder, ViewHolder newHolder, int fromLeft,
      int fromTop, int toLeft, int toTop) {
    return false;
  }

  @Override
  public void runPendingAnimations() {

  }

  @Override
  public void endAnimation(ViewHolder item) {

  }

  @Override
  public void endAnimations() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
