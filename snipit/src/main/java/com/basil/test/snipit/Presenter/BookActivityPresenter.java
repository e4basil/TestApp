package com.basil.test.snipit.Presenter;

import com.basil.test.snipit.BookActivityView;
import com.basil.test.snipit.Repository.BookRepository;
import com.basil.test.snipit.models.Book;

import java.util.List;

/**
 * Created by basi on 13/4/17.
 */

public class BookActivityPresenter {


    private BookActivityView view;
    private BookRepository bookRepository;


    public BookActivityPresenter(BookActivityView view, BookRepository bookRepository) {
        this.view = view;
        this.bookRepository = bookRepository;
    }

    public void loadBooks() {

        List<Book> bookList = bookRepository.getBooks();
        view.displayBooks(bookList);
    }
}

