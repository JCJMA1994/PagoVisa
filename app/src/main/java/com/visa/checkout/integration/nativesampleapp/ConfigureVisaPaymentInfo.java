package com.visa.checkout.integration.nativesampleapp;

import com.visa.checkout.Environment;
import com.visa.checkout.Profile;
import com.visa.checkout.PurchaseInfo;
import com.visa.checkout.VisaConfigRequest;
import com.visa.checkout.VisaConfigResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * * Copyright © 2018 Visa. All rights reserved.
 */

public class ConfigureVisaPaymentInfo {

    public static Profile getProfile() {
        return new Profile.ProfileBuilder("MERCHANT-API-KEY",
            Environment.SANDBOX).setProfileName("PROFILE")
            .setDisplayName("Sample App")
            .setMerchantId("123")
            .setDataLevel(Profile.DataLevel.FULL)
            .setAcceptCanadianVisaDebit(true)
            .setEnableTokenization(true)
            .setCardBrands(new String[] {
                Profile.CardBrand.AMEX, Profile.CardBrand.VISA, Profile.CardBrand.MASTERCARD,
                Profile.CardBrand.ELO, Profile.CardBrand.ELECTRON
            })
            .setShippingCountries(new String[] {
                Profile.Country.US, Profile.Country.CA, Profile.Country.BR, Profile.Country.GB,
                Profile.Country.IN, Profile.Country.IE, Profile.Country.AU, Profile.Country.PL,
                Profile.Country.MX
            })
            .setBillingCountries(new String[] {
                Profile.Country.US, Profile.Country.CA, Profile.Country.BR, Profile.Country.GB,
                Profile.Country.IN, Profile.Country.IE, Profile.Country.AU, Profile.Country.PL,
                Profile.Country.MX
            })
            .setCountryCode(Profile.Country.US)
            .put("any key", "any data")
            .build();
    }

    public static PurchaseInfo getPurchaseInfo() {
        HashMap<String, String> data = new HashMap<>();
        data.put("key", "value");
        data.put("key1", "value1");

        return new PurchaseInfo.PurchaseInfoBuilder(new BigDecimal("10.23"),
            PurchaseInfo.Currency.USD).setShippingHandling(new BigDecimal("0"))
            .setTax(new BigDecimal("0"))
            .setDiscount(new BigDecimal("0"))
            .setMisc(new BigDecimal("0"))
            .setGiftWrap(new BigDecimal("0"))
            .setDescription("Gift Card Order")
            .setOrderId("234-SD355-343432")
            .setReviewMessage("Gift Card Order")
            .setMerchantRequestId("345345345dsfs434343423234234")
            .setSourceId("test-source-id")
            .setPromoCode("test-promo-code")
            .setShippingAddressRequired(true)
            .setUserReviewAction(PurchaseInfo.UserReviewAction.PAY)
            .setThreeDSSetup(true, false)
            .setCustomData(data)
            .setPrefillRequest(new VisaConfigRequest() {
                @Override
                public void handleConfigRequest(Object o, VisaConfigResponse visaConfigResponse) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("userFirstName", "First name");
                        jsonObject.put("userLastName", "Last name");
                        jsonObject.put("userEmail", "email");
                        jsonObject.put("userPhone", "phone");
                        visaConfigResponse.sendResponse(jsonObject);
                    } catch (JSONException e) {
                        visaConfigResponse.sendResponse(null);
                    }
                }
            })
            .put("any key", "any data")
            .build();
    }
}
