package com.basil.test.snipit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.basil.test.snipit.Presenter.BookActivityPresenter;
import com.basil.test.snipit.models.Book;

import java.util.List;

public class BookActivity extends AppCompatActivity implements BookActivityView {

    private BookActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        presenter = new BookActivityPresenter(this, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void displayBooks(List<Book> bookList) {

    }
}
