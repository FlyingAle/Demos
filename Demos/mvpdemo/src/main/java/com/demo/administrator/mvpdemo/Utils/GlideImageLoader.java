package com.demo.administrator.mvpdemo.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {

  @Override
  public void displayImage(Context context, Object path, ImageView imageView) {
    Glide.with(context).load(path).into(imageView);
    Glide.with(context).load(path).into(new SimpleTarget<Drawable>() {
      @Override
      public void onResourceReady(@NonNull Drawable resource,
          @Nullable Transition<? super Drawable> transition) {

      }
    });
  }
}
