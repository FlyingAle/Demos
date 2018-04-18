package com.demo.hys.myutillibrary.GodRecycleAdapter;

public interface Cell {

  public int getItemType();
  public BaseViewHolder onCreateViewHolder();
  public void onBinderViewHolder(BaseViewHolder holder,int position);
  public void releaseResource();

}
