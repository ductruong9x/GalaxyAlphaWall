package com.appfree.galaxyalpha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class StartActivity extends Activity {

    private Button btnset, btnrate, btnmore;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private String UNIT_ID="ca-app-pub-1857950562418699/1721637965";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        danhGia();
        btnset = (Button) findViewById(R.id.btnset);
        btnrate = (Button) findViewById(R.id.btnrate);
        btnmore = (Button) findViewById(R.id.btnmore);
        adView=(AdView)findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });

        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMoreApp = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri
                                .parse("https://play.google.com/store/apps/developer?id=.FreeVN"));
                startActivity(goMoreApp);
            }
        });
        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri
                        .parse("market://details?id=" + getPackageName()));
                startActivity(goToMarket);
            }
        });
    }
    public void danhGia() {
        SharedPreferences getPre = getSharedPreferences("setting", MODE_PRIVATE);
        int i = getPre.getInt("VOTE", 0);
        SharedPreferences pre;
        SharedPreferences.Editor edit;
        switch (i) {
            case 0:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", 1);
                edit.commit();
                break;
            case 1:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 2:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 3:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 4:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 5:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Vote Application");
                dialog.setMessage("You can vote for Quick Reboot");
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("market://details?id="
                                        + getPackageName()));
                        startActivity(goToMarket);
                        SharedPreferences pre = getSharedPreferences("setting", MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putInt("VOTE", 6);
                        edit.commit();
                    }
                });
                dialog.setNeutralButton("Do not show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pre = getSharedPreferences("setting",
                                MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putInt("VOTE", 6);
                        edit.commit();
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
                break;
        }
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(StartActivity.this);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle(R.string.title);
        dialog.setMessage(R.string.content);
        dialog.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                interstitialAd.show();
            }
        });
        dialog.setNeutralButton(R.string.more, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri
                        .parse("market://details?id=com.gamefree.choosecolor"));
                startActivity(goToMarket);

            }
        });
        dialog.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
    }
}
