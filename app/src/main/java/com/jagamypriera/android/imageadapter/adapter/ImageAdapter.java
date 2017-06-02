package com.jagamypriera.android.imageadapter.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.jagamypriera.android.imageadapter.R;
import com.jagamypriera.android.imageadapter.activity.ImageViewerActivity;
import com.jagamypriera.android.imageadapter.bundle.ImageListBundle;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.jagamypriera.android.imageadapter.Statics.DATA;

/**
 * Created by jagamypriera on 30/05/17.
 */

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> images=new ArrayList<>();
    private PhotoViewAttacher mAttacher;
    private boolean zoomable=true;
    public ImageAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * if true, the images can be zoomed but not clicked
     * @param zoomable
     * @return
     */
    public ImageAdapter setZoomable(boolean zoomable) {
        this.zoomable=zoomable;
        return this;
    }
    public ImageAdapter setImages(ArrayList<String> images){
        if(images.size()==0)return this;
        this.images=images;
        notifyDataSetChanged();
        return this;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.layout_image_view, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.img_image);
        final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        Glide.with(mContext).load(images.get(position)).fitCenter().dontAnimate().into(new ViewTarget<ImageView, GlideDrawable>(imageView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation anim) {
                this.view.setImageDrawable(resource);
                progressBar.setVisibility(View.GONE);
                if (zoomable)mAttacher = new PhotoViewAttacher(imageView);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                progressBar.setVisibility(View.GONE);
            }
        });
        container.addView(itemView);
        itemView.setOnClickListener(new ClickLister());
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
    private class ClickLister implements View.OnClickListener{
        public ClickLister() {}
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext, ImageViewerActivity.class);
            ImageListBundle bundle=new ImageListBundle(images);
            intent.putExtra(DATA,bundle);
            mContext.startActivity(intent);
        }
    }
}
