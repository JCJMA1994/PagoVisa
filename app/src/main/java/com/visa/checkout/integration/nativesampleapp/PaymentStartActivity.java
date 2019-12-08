package com.visa.checkout.integration.nativesampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

/**
 * * Copyright © 2018 Visa. All rights reserved.
 */

public class PaymentStartActivity extends AppCompatActivity {

    private static final String TAG = PaymentStartActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_start);
        setUpTab();
    }

    private void setUpTab() {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab_VisaCheckoutButton");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Visa Checkout Button");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab_CustomButton");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Custom Button");
        host.addTab(spec);
    }
}
