package com.demo.hys.myutillibrary.baseLibrarys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create By Hys ${Date}
 */
public abstract class BaseHttpActivity extends AppCompatActivity {

  protected Unbinder unbinder;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    unbinder = ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  protected abstract int getLayout();
  protected abstract void initDatas();
  protected abstract void initViews();
  protected abstract void initEvents();
}
