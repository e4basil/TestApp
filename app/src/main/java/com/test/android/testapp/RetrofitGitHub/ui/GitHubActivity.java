package com.test.android.testapp.RetrofitGitHub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.test.android.testapp.R;
import com.test.android.testapp.RetrofitGitHub.adapter.GitHubRepoAdapter;
import com.test.android.testapp.RetrofitGitHub.connection.GitHubClient;
import com.test.android.testapp.RetrofitGitHub.model.GitHubRepo;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by basi on 27/12/16.
 */

public class GitHubActivity extends AppCompatActivity {

    private static final String TAG = GitHubActivity.class.getSimpleName();
    private GitHubRepoAdapter adapter = new GitHubRepoAdapter();
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_github);



        setdata();
    }

    private void setdata() {

        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);
        final EditText editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    getStarredRepos(username);
                }
            }
        });
    }


    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
    private void getStarredRepos(String username) {
        subscription = GitHubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }
                    @Override public void onNext(List<GitHubRepo> gitHubRepos) {
                        Log.d(TAG, "In onNext()");
                        adapter.setGitHubRepos(gitHubRepos);
                    }
                });
    }

}
