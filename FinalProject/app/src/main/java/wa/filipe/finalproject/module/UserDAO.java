package wa.filipe.finalproject.module;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
    DBHelper dbHelper;

    public UserDAO(Context contexto){
        dbHelper = new DBHelper(contexto);
    }

    public boolean Insert(User user){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        data.put("favorite", user.getFavorite());
        boolean success = database.insert("User", null, data) > 0;
        database.close();
        return(success);
    }

    public boolean update(User user) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        data.put("favorite", user.getFavorite());
        boolean success = database.update("User", data, "id=?", new String[]{user.getId().toString()}) > 0;
        database.close();
        return (success);
    }

    public User getById(Integer id){
        SQLiteDatabase banco = dbHelper.getReadableDatabase();
        Cursor cursor = banco.query("User",
                new String[]{"id","username","password","favorite"},
                "id=?", new String[]{id.toString()},
                null, null, null);

        if(cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setFavorite(cursor.getString(cursor.getColumnIndex("favorite")));
            return(user);
        }
        return(null);
    }

    public User getByUsernamePassword(String username, String password){
        SQLiteDatabase banco = dbHelper.getReadableDatabase();
        Cursor cursor = banco.query("User",
                new String[]{"id","username","password","favorite"},
                "username=? AND password=?", new String[]{username,password},
                null, null, null);

        if(cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            user.setFavorite(cursor.getString(cursor.getColumnIndex("favorite")));
            return(user);
        }
        return(null);
    }
}
