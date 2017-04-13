package com.basil.test.snipit.Presenter;

import com.basil.test.snipit.BookActivityView;
import com.basil.test.snipit.Repository.BookRepository;
import com.basil.test.snipit.models.Book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by basi on 13/4/17.
 */
public class BookActivityPresenterTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldPassBooksToView() {

        // given

        BookActivityView view = new MockView();
        BookRepository bookRepository = new MockBookRepository();

        //  when

        BookActivityPresenter presenter = new BookActivityPresenter(view, bookRepository);
        presenter.loadBooks();

        //  then

        Assert.assertEquals(true, ((MockView) view).passed);
    }


    private class MockView implements BookActivityView {
        boolean passed;

        @Override
        public void displayBooks(List<Book> bookList) {
            if (bookList.size() == 3) passed = true;
        }
    }

    private class MockBookRepository implements BookRepository {

        @Override
        public List<Book> getBooks() {
            return Arrays.asList(new Book(), new Book(), new Book());
        }
    }
}