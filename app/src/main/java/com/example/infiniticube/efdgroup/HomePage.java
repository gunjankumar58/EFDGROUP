package com.example.infiniticube.efdgroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
  private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        webView=(WebView)findViewById(R.id.wvIew);
       /* WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        webView.loadUrl("https://www.efd-group.com/m.login.php");
        webView.setWebViewClient(new MywebViewClient());*/

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new WebViewClient() {

            /* @Override
             public void onReceivedHttpAuthRequest(WebView view,
                                                   HttpAuthHandler handler, String host, String realm) {
                 handler.proceed("mobiloitte", "mobiloitte123");
             }
 */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return false;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {


                Log.e("TAG", "url : " + url);


            }
        });

        webView.loadUrl("https://www.efd-group.com/m.login.php");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.login) {
            webView.loadUrl("https://www.efd-group.com/m.login.php");
            // Handle the camera action
        } else if (id == R.id.bigprocess) {
            webView.loadUrl("https://www.efd-group.com/bip-progress.php");

        } else if (id == R.id.dashboard) {
            webView.loadUrl("https://www.efd-group.com/m.dashboard.php");
        } else if (id == R.id.etaaccount) {
            webView.loadUrl("https://www.efd-group.com/gta-elite-account-history.php");

        } else if (id == R.id.partnerbip) {
            webView.loadUrl("https://www.efd-group.com/partners-bip-progress.php");

        }
        else if (id == R.id.partnersgtaelite) {
            webView.loadUrl("https://www.efd-group.com/partners-gtaelite-progress.php");

        }
        else if (id == R.id.partnersptaprogress) {
            webView.loadUrl("https://www.efd-group.com/partners-pta-progress.php");

        }

        else if (id == R.id.signup) {
            webView.loadUrl("https://www.efd-group.com/m.signup.php");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class MywebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("https://www.efd-group.com/m.login.php")){
                return false;
            }

            else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }
        ProgressDialog pd = null;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pd = new ProgressDialog(HomePage.this);
            pd.setTitle("please wait...");
            pd.setMessage("Website is loading..");
            pd.setCancelable(false);
            pd.show();
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            pd.dismiss();
            super.onPageFinished(view, url);
        }
    }
    //https://www.youtube.com/watch?v=9cJPR87Fi5M    this is youtube url to open back key
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()== KeyEvent.ACTION_DOWN){
            switch(keyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(webView.canGoBack()){
                        webView.goBack();
                    }
                    else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
