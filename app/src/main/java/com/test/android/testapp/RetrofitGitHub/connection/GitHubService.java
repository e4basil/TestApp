package com.test.android.testapp.RetrofitGitHub.connection;

import com.test.android.testapp.RetrofitGitHub.model.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by basi on 27/12/16.
 */

public interface GitHubService {

    @GET("users/{user}/starred")
    Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);
}
