package com.test.android.testapp.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.test.android.testapp.BuildConfig;
import com.test.android.testapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by basi on 7/2/17.
 */

public class StripeActivity extends AppCompatActivity {

    private static final String TAG = StripeActivity.class.getSimpleName();
    protected TextView cardName, amount;
    protected Stripe stripe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe);
        stripe = new Stripe();

    }

    public void submitCard(View view) {
        // TODO: replace with your own test key
        final String publishableApiKey = BuildConfig.DEBUG ?
                "pk_test_YsMG9CbdpwT7rabTeRVRW8EW" :
                getString(R.string.com_stripe_publishable_key);

        cardName = (TextView) findViewById(R.id.cardName);
        TextView cardNumberField = (TextView) findViewById(R.id.cardNumber);
        TextView monthField = (TextView) findViewById(R.id.month);
        TextView yearField = (TextView) findViewById(R.id.year);
        TextView cvcField = (TextView) findViewById(R.id.cvc);
        amount = (TextView) findViewById(R.id.amount);

//        Card card = new Card(cardNumberField.getText().toString().isEmpty() ? "4242424242424242" : cardNumberField.getText().toString(),
//                monthField.getText().toString().isEmpty() ? 01 : Integer.valueOf(monthField.getText().toString()),
//                yearField.getText().toString().isEmpty() ? 02 : Integer.valueOf(yearField.getText().toString()),
//                cvcField.getText().toString().isEmpty() ? "123" : cvcField.getText().toString());

        Card card = new Card("4242-4242-4242-4242", 12, 2018, "123");
        card.setName(cardName.getText().toString().isEmpty() ? "test" : cardName.getText().toString());
        card.setCurrency("inr");
//        if (!card.validateCard()) {
//            // Show errors
//            Log.d(TAG, "submitCard: error " );
//        }else{


        stripe.createToken(card, publishableApiKey, new TokenCallback() {
            public void onSuccess(Token token) {
                // TODO: Send Token information to your backend to initiate a charge

                new StripeCharge(token).execute();
                Toast.makeText(
                        getApplicationContext(),
                        "Token created: " + token.getId(),
                        Toast.LENGTH_LONG).show();
            }

            public void onError(Exception error) {
                Log.d("Stripe", error.getLocalizedMessage());
            }
        });
//        }

    }


    public class StripeCharge extends AsyncTask<String, Void, String> {
        Token token;

        private StripeCharge(Token token) {
            this.token = token;
        }

        @Override
        protected String doInBackground(String... params) {

            new Thread(
                    () -> postData(cardName, token, amount)
            ).start();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: " + s);
        }
    }

    private void postData(TextView cardName, Token token, TextView amount) {


        // Charge the user's card:
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("amount", "10");
        params.put("currency", "usd");
        params.put("description", "Example charge");
        params.put("source", token.getId());

        try {
            com.stripe.Stripe.apiKey="sk_test_9KH0Azdjq5hJSxXizSA36LtA";
            Charge charge = Charge.create(params);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

}
