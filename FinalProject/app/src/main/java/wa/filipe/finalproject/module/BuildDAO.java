package wa.filipe.finalproject.module;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildDAO {
    DBHelper dbHelper;

    public BuildDAO(Context contexto){
        dbHelper = new DBHelper(contexto);
    }

    public boolean Insert(Build build){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("slots", build.getSlots());
        data.put("rank", build.getRank());
        data.put("catalyst", build.getCatalyst());
        data.put("mods", build.getMods());
        boolean success = database.insert("Build", null, data) > 0;
        database.close();
        return(success);
    }

    public boolean update(Build build) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("slots", build.getSlots());
        data.put("rank", build.getRank());
        data.put("catalyst", build.getCatalyst());
        data.put("mods", build.getMods());
        boolean success = database.update("Build", data, "id=?", new String[]{build.getId().toString()}) > 0;
        database.close();
        return (success);
    }

    public Build getById(Integer id){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("Build",
                new String[]{"id","slots","rank","catalyst", "mods"},
                "id=?", new String[]{id.toString()},
                null, null, null);
        if(cursor.moveToNext()){
            Build build = new Build();
            build.setId(cursor.getInt(cursor.getColumnIndex("id")));
            build.setSlots(cursor.getInt(cursor.getColumnIndex("slots")));
            build.setRank(cursor.getInt(cursor.getColumnIndex("rank")));
            build.setCatalyst(cursor.getInt(cursor.getColumnIndex("catalyst")));
            build.setMods(cursor.getString(cursor.getColumnIndex("mods")));
            return(build);
        }
        return(null);
    }

    public List<Build> getAll(){
        List<Build> listaBuilds = new ArrayList<Build>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("Build",
                new String[]{"id","slots","rank","catalyst","mods"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Build b = new Build();
            b.setId(cursor.getInt(cursor.getColumnIndex("id")));
            b.setSlots(cursor.getInt(cursor.getColumnIndex("slots")));
            b.setRank(cursor.getInt(cursor.getColumnIndex("rank")));
            b.setCatalyst(cursor.getInt(cursor.getColumnIndex("catalyst")));
            b.setMods(cursor.getString(cursor.getColumnIndex("mods")));
            listaBuilds.add(b);
        }
        return(listaBuilds);
    }
}
