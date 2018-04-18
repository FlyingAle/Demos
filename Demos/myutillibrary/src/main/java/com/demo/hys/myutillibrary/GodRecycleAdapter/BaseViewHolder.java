package com.demo.hys.myutillibrary.GodRecycleAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BaseViewHolder extends RecyclerView.ViewHolder {

  private SparseArray<View> childViews;
  private View mItemView;
  private Context mContext;

  public BaseViewHolder(View itemView,Context context) {
    super(itemView);
    mItemView = itemView;
    mContext = context;
  }

  protected <V extends View>V getView(int res)
  {
    View view = childViews.get(res);
    if(view == null)
    {
      view = mItemView.findViewById(res);
      childViews.put(res,view);
      if(view == null)
      {
        throw new NullPointerException();
      }
    }
    return (V)view;
  }

  public BaseViewHolder setImageViewSrc(int res,String imgUrl)
  {
    ImageView view = getView(res);
    Glide.with(mContext).load(imgUrl).into(view);
    return this;
  }

  public BaseViewHolder setImageViewSrc(int[] reses,List<String> imgUrls)
  {
    for(int i=0;i<reses.length;i++)
    {
      ImageView imageView = getView(reses[i]);
      Glide.with(mContext).load(imgUrls.get(i)).into(imageView);
    }
    return this;
  }

  public BaseViewHolder setText(int res,String text)
  {
    TextView textView = getView(res);
    textView.setText(text);
    return this;
  }

  public BaseViewHolder setTest(int[] reses,List<String> text)
  {
    for(int i=0;i<reses.length;i++)
    {
      TextView textView = getView(reses[i]);
      textView.setText(text.get(i));
    }
    return this;
  }

  public BaseViewHolder setOnClickListener(int res,OnClickListener onClickListener)
  {
    View view = getView(res);
    view.setOnClickListener(onClickListener);
    return this;
  }
}
