package com.visa.checkout.integration.nativesampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.visa.checkout.ManualCheckoutSession;
import com.visa.checkout.Profile;
import com.visa.checkout.PurchaseInfo;
import com.visa.checkout.VisaCheckoutSdk;
import com.visa.checkout.VisaPaymentSummary;

import static android.content.ContentValues.TAG;

public class CustomButtonFragment extends Fragment {
    private View mView;
    private ManualCheckoutSession.ManualCheckoutLaunchHandler launchHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_custom_button, container);
        setUpCustomButton(ConfigureVisaPaymentInfo.getProfile(), ConfigureVisaPaymentInfo.getPurchaseInfo());
        return mView;
    }

    private void setUpCustomButton(final Profile profile, final PurchaseInfo purchaseInfo) {
        final ImageView customButton = mView.findViewById(R.id.custom_button);
        customButton.setBackground(VisaCheckoutSdk.getCardArt(this.getActivity().getApplicationContext()));

        VisaCheckoutSdk.initManualCheckoutSession(this.getActivity(), profile, purchaseInfo, new ManualCheckoutSession() {
            @Override
            public void onReady(ManualCheckoutLaunchHandler manualCheckoutLaunchHandler) {
                CustomButtonFragment.this.launchHandler = manualCheckoutLaunchHandler;
            }

            @Override
            public void onResult(VisaPaymentSummary visaPaymentSummary) {
                if (VisaPaymentSummary.PAYMENT_SUCCESS.equalsIgnoreCase(
                        visaPaymentSummary.getStatusName())) {
                    Log.d(TAG, "Success");
                } else if (VisaPaymentSummary.PAYMENT_CANCEL.equalsIgnoreCase(
                        visaPaymentSummary.getStatusName())) {
                    Log.d(TAG, "Canceled");
                } else if (VisaPaymentSummary.PAYMENT_ERROR.equalsIgnoreCase(
                        visaPaymentSummary.getStatusName())) {
                    Log.d(TAG, "Error");
                } else if (VisaPaymentSummary.PAYMENT_FAILURE.equalsIgnoreCase(
                        visaPaymentSummary.getStatusName())) {
                    Log.d(TAG, "Generic Unknown failure");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpCustomButton(profile, purchaseInfo);
                    }
                });
            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (launchHandler != null) {
                    launchHandler.launch();
                }
            }
        });
    }
}
