package com.demo.hys.myutillibrary.GodRecycleAdapter;

public abstract class BaseCell<T> implements Cell {

  private T mDataCell;
  public BaseCell(T cell) {
    mDataCell = cell;
  }

  @Override
  public void releaseResource() {

  }
}
