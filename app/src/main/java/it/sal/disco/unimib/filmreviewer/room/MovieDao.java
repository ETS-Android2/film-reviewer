package it.sal.disco.unimib.filmreviewer.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;

@Dao
public interface MovieDao {
    /**/
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insertNewsList(List<Movie> newsList);

    @Insert
    void insertAll(Movie... movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Delete
    void deleteAllWithoutQuery(Movie... movie);

    @Query("DELETE FROM movie WHERE id=:id_input")
    void deleteSpecificMovie(String id_input);
}