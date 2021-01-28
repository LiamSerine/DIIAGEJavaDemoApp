package diiage.potherat.demo.demoapp3.dal.room.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

@Dao
public interface QuoteDao extends QuoteRepository {
    @Query("SELECT * FROM Quote")
    PagingSource<Integer, Quote> getAll();
    @Query("SELECT * FROM Quote WHERE id = :id")
    LiveData<Quote> getById(Long id);
    @Query("SELECT Count(*) FROM Quote")
    Integer countQuote();
    @Query("SELECT COUNT(source) FROM (SELECT DISTINCT source FROM Quote)")
    Integer getCountAuthor();
    @Query("SELECT * FROM Quote ORDER BY Date DESC LIMIT 1")
    Quote getLastQuote();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long create(Quote quote);
    @Update
    void update(Quote quote);
    @Delete
    void delete(Quote quote);

}
