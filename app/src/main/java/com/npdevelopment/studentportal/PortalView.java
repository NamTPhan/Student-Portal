package com.npdevelopment.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PortalView extends AppCompatActivity {

    private WebView mViewPortal;
    private String mPassedUrl = "";
    private PortalObject mPortalObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_view);

        mViewPortal = findViewById(R.id.viewPortal);
        mViewPortal.setWebViewClient(new WebViewClient());
        mViewPortal.getSettings().setBuiltInZoomControls(true);
        mViewPortal.getSettings().setJavaScriptEnabled(true);
        mViewPortal.getSettings().setDomStorageEnabled(true);

        mPortalObject = getIntent().getParcelableExtra(PortalObjectAdapter.URL_KEY);
        mPassedUrl = mPortalObject.getmUrl();

        mViewPortal.loadUrl(mPassedUrl);
    }
}
