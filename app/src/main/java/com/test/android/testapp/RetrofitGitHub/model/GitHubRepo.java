package com.test.android.testapp.RetrofitGitHub.model;

import lombok.Data;

/**
 * Created by basi on 27/12/16.
 */

@Data
public class GitHubRepo {
    public int id;
    public String name;
    public String htmlUrl;
    public String description;
    public String language;
    public int stargazersCount;

    public GitHubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }
}
