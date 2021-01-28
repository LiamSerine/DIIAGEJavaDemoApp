package diiage.potherat.demo.demoapp3.dal.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;

import diiage.potherat.demo.demoapp3.model.Quote;

public interface QuoteRepository {
    PagingSource<Integer, Quote> getAll();
    LiveData<Quote> getById(Long id);
    Integer countQuote();
    Integer getCountAuthor();
    Quote getLastQuote();
    Long create(Quote quote);
    void update(Quote quote);
    void delete(Quote quote);
}
