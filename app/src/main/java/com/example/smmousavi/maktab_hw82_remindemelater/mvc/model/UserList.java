package com.example.smmousavi.maktab_hw82_remindemelater.mvc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskBaseHelper;
import com.example.smmousavi.maktab_hw82_remindemelater.database.UserCursorWrapper;

import java.util.UUID;

import static com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UserTable;

public class UserList {

  private static UserList instance;
  private SQLiteDatabase mDatabase;
  private Context mContext;
  private UUID loggedInUserId;


  public static UserList getInstance(Context context) {
    if (instance == null)
      instance = new UserList(context);

    return instance;
  }

  private UserList(Context context) {
    mContext = context.getApplicationContext();
    mDatabase = new TaskBaseHelper(context).getWritableDatabase();
  }


  public UUID getLoggedInUserId() {
    return loggedInUserId;
  }

  public void setLoggedInUserId(UUID loggedInUserId) {
    this.loggedInUserId = loggedInUserId;
  }


  public void addUser(User user) {
    ContentValues values = getUserContentValues(user);
    mDatabase.insert(UserTable.NAME, null, values);

  }

  public void removeUser(User user) {
    String whereClause = UserTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {user.getId().toString()};
    mDatabase.delete(UserTable.NAME, whereClause, whereArgs);
  }

  public UUID getUser(String username, String password) {
    String whereClause = UserTable.Cols.USERNAME + " = ? and "
      + UserTable.Cols.PASSWORD + " = ? ";
    String[] whereArgs = {username, password};
    UserCursorWrapper cursor = queryUser(UserTable.NAME, whereClause, whereArgs);
    try {
      if (cursor.getCount() == 0)
        return null;

      cursor.moveToFirst();
      return cursor.getUser().getId();

    } finally {
      cursor.close();
    }

  }

  public User getUser(UUID userId) {
    String whereClause = UserTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {userId.toString()};
    UserCursorWrapper cursor = queryUser(UserTable.NAME, whereClause, whereArgs);
    try {
      if (cursor.getCount() == 0)
        return null;

      cursor.moveToFirst();
      return cursor.getUser();

    } finally {
      cursor.close();
    }

  }

  private UserCursorWrapper queryUser(String TableName, String whereClause, String[] whereArgs) {
    Cursor cursor = mDatabase.query(
      TableName,
      null,
      whereClause,
      whereArgs,
      null,
      null,
      null);

    return new UserCursorWrapper(cursor);

  }


  private ContentValues getUserContentValues(User user) {
    ContentValues values = new ContentValues();
    values.put(UserTable.Cols.USER_UUID, user.getId().toString());
    values.put(UserTable.Cols.USERNAME, user.getName());
    values.put(UserTable.Cols.PASSWORD, user.getPassword());

    return values;
  }
}
