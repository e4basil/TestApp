package com.basil.test.network.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basil.test.network.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by basi on 5/4/17.
 */

public class Demo_RV_Adapter extends RecyclerView.Adapter<Demo_RV_Adapter.ViewHolder> {

    private static final String TAG = Demo_RV_Adapter.class.getSimpleName();
    private ArrayList<HashMap<String, String>> contactList;

    public Demo_RV_Adapter(Context context, ArrayList<HashMap<String, String>> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.demo_rv_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + contactList.size());
        return contactList == null ? 0 : contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
