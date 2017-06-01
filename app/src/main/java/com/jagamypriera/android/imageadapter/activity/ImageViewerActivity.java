package com.jagamypriera.android.imageadapter.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.jagamypriera.android.imageadapter.R;
import com.jagamypriera.android.imageadapter.adapter.ImageAdapter;
import com.jagamypriera.android.imageadapter.bundle.ImageListBundle;

import static com.jagamypriera.android.imageadapter.Statics.DATA;

/**
 * Created by jagamypriera on 30/05/17.
 */

public class ImageViewerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        ImageListBundle bundle=(ImageListBundle)getIntent().getSerializableExtra(DATA);
        ImageAdapter adapter=new ImageAdapter(this).setImages(bundle.images);
        pager.setAdapter(adapter);
        if (Build.VERSION.SDK_INT < 16) getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
