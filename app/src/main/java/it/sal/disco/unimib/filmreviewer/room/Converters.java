package it.sal.disco.unimib.filmreviewer.room;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.sal.disco.unimib.filmreviewer.customObj.Actor;
import it.sal.disco.unimib.filmreviewer.customObj.Poster;
import it.sal.disco.unimib.filmreviewer.customObj.PosterBox;


@ProvidedTypeConverter
public class Converters {

    @TypeConverter
    public String fromValuesToList(List<Actor> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Actor>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<Actor> toOptionValuesList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Actor>>() {
        }.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public String fromValuesToList2(PosterBox value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<PosterBox>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public PosterBox toOptionValuesList2(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<PosterBox>() {
        }.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public String fromValuesToList3(List<Poster> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Poster>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<Poster> toOptionValuesList3(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Poster>>() {
        }.getType();
        return gson.fromJson(value, type);
    }
}