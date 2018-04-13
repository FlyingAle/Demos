package com.demo.administrator.mvpdemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.demo.administrator.mvpdemo.Adapters.AndroidListAdapter.AndroidItemHolder;
import com.demo.administrator.mvpdemo.Beans.TodayDataBean.ResultsBean.AndroidBean;
import com.demo.administrator.mvpdemo.R;
import java.util.List;

public class AndroidListAdapter extends RecyclerView.Adapter<AndroidItemHolder> {

  private List<AndroidBean> list;
  private Context context;

  public AndroidListAdapter(List<AndroidBean> listBeans,Context context)
  {
    list = listBeans;
    this.context = context;
  }
  @NonNull
  @Override
  public AndroidItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(context).inflate(R.layout.item_main_list,parent,false);
    return new AndroidItemHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull AndroidItemHolder holder, int position) {
    holder.setTexts(list.get(position));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class AndroidItemHolder extends ViewHolder {
    @BindView(R.id.item_id)
    TextView itemId;
    @BindView(R.id.item_createAt)
    TextView itemCreateAt;
    @BindView(R.id.item_desc)
    TextView itemDesc;
    @BindView(R.id.item_publishedAt)
    TextView itemPublishedAt;
    @BindView(R.id.item_url)
    TextView itemUrl;
    @BindView(R.id.item_who)
    TextView itemWho;

    public AndroidItemHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

    public void setTexts(AndroidBean bean)
    {
      itemId.setText(bean.get_id());
      itemCreateAt.setText(bean.getCreatedAt());
      itemDesc.setText(bean.getDesc());
      itemPublishedAt.setText(bean.getPublishedAt());
      itemUrl.setText(bean.getUrl());
      itemWho.setText(bean.getWho());
    }
  }

}
