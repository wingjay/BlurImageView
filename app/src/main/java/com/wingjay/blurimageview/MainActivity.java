package com.wingjay.blurimageview;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wingjay.blurimageviewlib.BlurImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.fast_blur_btn)
  Button fastBlurBtn;

  @Bind(R.id.full_blur_image_view)
  BlurImageView blurImageView;

  @Bind(R.id.image_indicator)
  TextView imageIndicator;

  @Bind(R.id.about_author)
  TextView aboutAuthor;

  boolean alreadyLoad = false;

  int[] mediumSmRes = {
    R.drawable.medium_sm_1,
    R.drawable.medium_sm_2,
    R.drawable.medium_sm_3,
    R.drawable.medium_sm_4
  };

  int[] mediumNmRes = {
    R.drawable.medium_nm_1,
    R.drawable.medium_nm_2,
    R.drawable.medium_nm_3,
    R.drawable.medium_nm_4
  };

  String[] mediumSmUrl = {
    "http://upload-images.jianshu.io/upload_images/1825662-4c4e9bc7148749b7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
    "http://upload-images.jianshu.io/upload_images/1977600-c562e582d45a4dee.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
    "http://upload-images.jianshu.io/upload_images/1761761-704c9d7ed34d112b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
    "http://upload-images.jianshu.io/upload_images/2557965-b224163bc8a4a695.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62"
  };

  String[] mediumNmUrl = {
    "http://upload-images.jianshu.io/upload_images/1825662-4c4e9bc7148749b7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
    "http://upload-images.jianshu.io/upload_images/1977600-c562e582d45a4dee.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
    "http://upload-images.jianshu.io/upload_images/1761761-704c9d7ed34d112b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
    "http://upload-images.jianshu.io/upload_images/2557965-b224163bc8a4a695.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620"
  };

  int[] blurImageViewProgressBgColor = {
      Color.BLACK,
      Color.BLACK,
      Color.parseColor("#E29C45"),
      Color.parseColor("#E29C45"),
  };

  int[] blurImageViewProgressClor = {
      Color.WHITE,
      Color.parseColor("#789262"),
      Color.parseColor("#7BCFA6"),
      Color.parseColor("#519A73"),
  };


  int currentIndex = 0;
  int getResIndex() {
    if (currentIndex > 3) {
      currentIndex = currentIndex - 4;
    }
    return currentIndex;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    String aboutAuthorString = "<u>Find me here: wingjay (https://github.com/wingjay)</u>";
    aboutAuthor.setText(Html.fromHtml(aboutAuthorString));
    aboutAuthor.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wingjay"));
        startActivity(browserIntent);
      }
    });

  }

  @OnClick(R.id.fast_blur_btn)
  void doFastBlur() {
    if (!alreadyLoad) {
      customizeBlurImageView();

      int blurFactor = BlurImageView.DEFAULT_BLUR_FACTOR;
      blurImageView.setBlurFactor(blurFactor);
      blurImageView.setFullImageByUrl(mediumSmUrl[getResIndex()], mediumNmUrl[getResIndex()]);

      alreadyLoad = true;
      fastBlurBtn.setText("Click and Clear current image");
      imageIndicator.setText((getResIndex() + 1) + "/" + mediumNmUrl.length);
    } else {
      blurImageView.clear();

      currentIndex++;
      alreadyLoad = false;
      fastBlurBtn.setText("Click to load new Image");
    }
  }

  private void customizeBlurImageView() {
    blurImageView.setProgressBarBgColor(blurImageViewProgressBgColor[getResIndex()]);
    blurImageView.setProgressBarColor(blurImageViewProgressClor[getResIndex()]);
  }

  @Override
  protected void onDestroy() {
    blurImageView.cancelImageRequestForSafty();
    super.onDestroy();
  }

}
