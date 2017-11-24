package com.youli.testwebview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView wv;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv= (WebView) findViewById(R.id.wv);

        showDialog();

      //  wv.setWebViewClient(new WebViewClient());
        loadUrl(wv);
    }



    private void loadUrl(WebView wv) {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        // 加载数据
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }
            }
        });
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://www.baidu.com");
      //  wv.loadUrl("http://192.168.191.1:82");
       // wv.loadUrl("http://192.168.31.3:82");
      //  wv.loadUrl("http://web.youli.pw:86");
    }



    private void showDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(android.R.drawable.ic_dialog_info);
        progressDialog.setTitle("加载提示");
        progressDialog.setMessage("信息加载中，请稍后。。。");
        progressDialog.show();
    }



//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == event.KEYCODE_BACK) && wv.canGoBack()) {
//            wv.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("您确定退出吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
