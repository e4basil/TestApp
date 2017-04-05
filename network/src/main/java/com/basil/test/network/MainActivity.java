package com.basil.test.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.basil.test.network.adapter.Demo_RV_Adapter;
import com.basil.test.network.model.ContactsData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<HashMap<String, String>> contactList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        contactList = new ArrayList<>();
        SetRecyclerview();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.androidhive.info/contacts/")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: "+response.body().string());
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: is not Successful");
                } else {

                    ContactsData contactsData = new Gson().fromJson(response.body().charStream(), ContactsData.class);
//                    try {
//
//                        JSONObject jsonObject = new JSONObject(response.body().charStream().toString());
//                        JSONArray contacts = jsonObject.getJSONArray("contacts");
//
//                        for (int i = 0; i < contacts.length(); i++) {
//                            JSONObject contact = contacts.getJSONObject(i);
//
//                            String id = contact.getString("id");
//                            String name = contact.getString("name");
//                            String email = contact.getString("email");
//                            String address = contact.getString("address");
//                            String gender = contact.getString("gender");
//
//                            JSONObject phone = contact.getJSONObject("phone");
//
//                            String mobile = phone.getString("mobile");
//                            String home = phone.getString("home");
//                            String office = phone.getString("office");
//
//                            HashMap<String, String> myContacts = new HashMap<String, String>();
//                            myContacts.put("id", id);
//                            myContacts.put("name", name);
//                            myContacts.put("email", email);
//                            myContacts.put("mobile", mobile);
//
//                            contactList.add(myContacts);
//                            recyclerView.notify();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }


    private void SetRecyclerview() {

        Demo_RV_Adapter mAdapter = new Demo_RV_Adapter(MainActivity.this, contactList);
        recyclerView.setAdapter(mAdapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
