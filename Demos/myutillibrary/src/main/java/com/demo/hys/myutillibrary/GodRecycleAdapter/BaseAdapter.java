package com.demo.hys.myutillibrary.GodRecycleAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<C extends BaseCell> extends RecyclerView.Adapter<BaseViewHolder> {

  protected List<C> mDataCells;

  public BaseAdapter() {
    mDataCells = new ArrayList<>();
  }

  public void setDataCell(List<C> dataCell)
  {
    addAll(dataCell);
  }

  public List<C> getmDataCells() {
    return mDataCells;
  }

  public void add(C cell)
  {
    mDataCells.add(cell);
    int index = mDataCells.indexOf(cell);
    notifyItemChanged(index);
  }

  public void add(int index,C cell)
  {
    mDataCells.set(index,cell);
    notifyItemChanged(index);
  }

  public void remove(C cell)
  {
    int index = mDataCells.indexOf(cell);
    remove(index);
  }

  public void remove(int index)
  {
    mDataCells.remove(index);
    notifyItemRemoved(index);
  }

  public void addAll(List<C> dataCell)
  {
    if(dataCell == null || dataCell.size() == 0)
    {
      return;
    }
    mDataCells.addAll(dataCell);
    notifyItemRangeChanged(mDataCells.size() - dataCell.size(),mDataCells.size());
  }

  public void addAll(int index,List<C> datacell)
  {
    if(datacell == null || datacell.size() == 0)
    {
      return;
    }
    mDataCells.addAll(index,datacell);
    notifyItemRangeChanged(index,index + datacell.size());
  }

  public void clear()
  {
    mDataCells.clear();
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    for(C c:mDataCells)
    {
      if(viewType == c.getItemType())
      {
        return c.onCreateViewHolder();
      }
    }
    throw new RuntimeException("Wrong ViewType");
  }

  @Override
  public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
    super.onViewDetachedFromWindow(holder);
    int positon = holder.getAdapterPosition();
    if(positon < 0 || positon>= mDataCells.size())
    {
      return;
    }
    mDataCells.get(positon).releaseResource();
  }

  @Override
  public int getItemViewType(int position) {
    return mDataCells.get(position).getItemType();
  }

  @Override
  public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    mDataCells.get(position).onBinderViewHolder(holder,position);
  }

  @Override
  public int getItemCount() {
    return mDataCells == null ? 0:mDataCells.size();
  }

  /**
   * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
   * @param holder
   * @param position
   */
  protected abstract void onViewHolderBound(BaseViewHolder holder, int position);
}
