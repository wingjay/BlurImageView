package com.wingjay.blurimageview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wingjay.blurimageviewlib.BlurImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.blur_factor)
    EditText blurFactorEditText;

    @Bind(R.id.fast_blur_btn)
    Button fastBlurBtn;

    @Bind(R.id.blur_image_view)
    BlurImageView blurImageView;

    @Bind(R.id.full_blur_image_view)
    BlurImageView fullBlurImageView;

    boolean alreadyCompelete = false;

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
            "https://cdn-images-1.medium.com/freeze/max/60/1*cDmQ2XlMGowTZNIf4oOHjw.jpeg?q=20",
            "https://cdn-images-1.medium.com/freeze/max/60/1*hBp9i_LZHGwKfq7plvjWxQ.jpeg?q=20",
            "https://cdn-images-1.medium.com/freeze/max/30/1*yd_YDN3dVyrSp_o7YHOKPg.jpeg?q=20",
            "https://cdn-images-1.medium.com/freeze/max/60/1*hMQ9_nBW3LOHCk3rQSRSbw.jpeg?q=20"
    };

    String[] mediumNmUrl = {
            "https://cdn-images-1.medium.com/max/1600/1*cDmQ2XlMGowTZNIf4oOHjw.jpeg",
            "https://cdn-images-1.medium.com/max/2000/1*hBp9i_LZHGwKfq7plvjWxQ.jpeg",
            "https://cdn-images-1.medium.com/max/2000/1*yd_YDN3dVyrSp_o7YHOKPg.jpeg",
            "https://cdn-images-1.medium.com/max/2000/1*hMQ9_nBW3LOHCk3rQSRSbw.jpeg"
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @OnClick(R.id.fast_blur_btn)
    void doFastBlur() {
        if (!alreadyCompelete) {
            int blurFactor = getBlurFactor();
            fullBlurImageView.setBlurFactor(blurFactor);
            fullBlurImageView.setFullImageByUrl(mediumSmUrl[getResIndex()], mediumNmUrl[getResIndex()]);

            blurImageView.setBlurFactor(blurFactor);
            blurImageView.setBlurImageByUrl(mediumSmUrl[getResIndex()]);
            alreadyCompelete = true;
        } else {
            blurImageView.clear();
            fullBlurImageView.clear();
            currentIndex++;
            alreadyCompelete = false;
        }
    }

    int getBlurFactor() {
        if (TextUtils.isEmpty(blurFactorEditText.getText())) {
            return BlurImageView.DEFAULT_BLUR_FACTOR;
        }
        return Integer.parseInt(blurFactorEditText.getText().toString());
    }

    @Override
    protected void onDestroy() {
        blurImageView.cancelImageLoadForSafty();
        fullBlurImageView.cancelImageLoadForSafty();
        super.onDestroy();
    }

}
