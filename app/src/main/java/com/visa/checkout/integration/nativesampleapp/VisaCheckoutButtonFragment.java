package com.visa.checkout.integration.nativesampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visa.checkout.CheckoutButton;
import com.visa.checkout.VisaCheckoutSdk;
import com.visa.checkout.VisaPaymentSummary;

import static android.content.ContentValues.TAG;

public class VisaCheckoutButtonFragment extends Fragment {
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_visa_checkout_button, container);

        CheckoutButton checkoutButton = mView.findViewById(R.id.visaCheckoutButton);
        checkoutButton.init(getActivity(), ConfigureVisaPaymentInfo.getProfile(),
            ConfigureVisaPaymentInfo.getPurchaseInfo(),
           new VisaCheckoutSdk.VisaCheckoutResultListener() {
                @Override
                public void onButtonClick(LaunchReadyHandler launchReadyHandler) {
                    launchReadyHandler.launch();
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
                }
            });

        return mView;
    }
}
