package com.appfree.galaxyalpha;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.appfree.galaxyalpha.adapter.WallAdapter;
import com.appfree.galaxyalpha.model.Wallpaper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.util.ArrayList;


public class MyActivity extends Activity {
    private ViewPager viewPager;
    private WallAdapter adapter;
    private ArrayList<Wallpaper> list = new ArrayList<Wallpaper>();
    private CirclePageIndicator indicator;
    private AdView adView;
    private String UNIT_ID="ca-app-pub-1857950562418699/1721637965";
    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        viewPager = (ViewPager) findViewById(R.id.vpMain);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        adView=(AdView)findViewById(R.id.adView);
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();

            }

        });
        adView.loadAd(new AdRequest.Builder().build());
        creartForder("Alpha");
        addWallpaper();
        adapter=new WallAdapter(this,list);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    private void addWallpaper() {
        list.add(new Wallpaper("wallpaper_01", R.drawable.wallpaper_01));
        list.add(new Wallpaper("wallpaper_02", R.drawable.wallpaper_02));
        list.add(new Wallpaper("wallpaper_03", R.drawable.wallpaper_03));
        list.add(new Wallpaper("wallpaper_04", R.drawable.wallpaper_04));
        list.add(new Wallpaper("wallpaper_05", R.drawable.wallpaper_05));
        list.add(new Wallpaper("wallpaper_06", R.drawable.wallpaper_06));
        list.add(new Wallpaper("wallpaper_07", R.drawable.wallpaper_07));
        list.add(new Wallpaper("wallpaper_08", R.drawable.wallpaper_08));

    }
    public void creartForder(String nameForder) {
        String path = Environment.getExternalStorageDirectory() + "/Wall/"
                + nameForder + "/";
        File checkForder;
        checkForder = new File(path);
        if (!checkForder.exists()) {
            checkForder.mkdirs();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
