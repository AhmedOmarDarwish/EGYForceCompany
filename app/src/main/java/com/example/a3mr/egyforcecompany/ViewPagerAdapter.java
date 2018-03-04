package com.example.a3mr.egyforcecompany;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.TimerTask;

/**
 * Created by El3ashry on 2/17/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

   private Context context;
   private LayoutInflater layoutInflater;
   private Integer[] images = {R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.company_ph , null);
        ImageView img = (ImageView) view.findViewById(R.id.images);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setImageResource(images[position]);
        ViewPager vp = (ViewPager)container;
        vp.addView(view , null);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }
        }