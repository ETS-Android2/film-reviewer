package it.sal.disco.unimib.filmreviewer.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;
import it.sal.disco.unimib.filmreviewer.utils.Constants;


@Database(entities = {Movie.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MoviesRoomDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile MoviesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MoviesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesRoomDatabase.class, Constants.NEWS_DATABASE_NAME)
                            .addTypeConverter(new Converters())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}