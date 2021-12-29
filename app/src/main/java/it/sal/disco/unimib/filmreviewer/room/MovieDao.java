package it.sal.disco.unimib.filmreviewer.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;

@Dao
public interface MovieDao {
    //Not used
    @Insert
    void insertNewsList(List<Movie> newsList);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Delete
    void deleteAllWithoutQuery(Movie... movie);

    //Used
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movie... movie);

    @Query("DELETE FROM movie WHERE id=:id_input")
    void deleteSpecificMovie(String id_input);

    @Query("SELECT * FROM movie WHERE id=:id_input")
    Movie getMovieFromID(String id_input);

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE private_fav=1")
    List<Movie> getAllFav();
}