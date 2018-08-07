package com.example.smmousavi.maktab_hw82_remindemelater.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UserTable;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.User;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
  public UserCursorWrapper(Cursor cursor) {
    super(cursor);
  }


  public User getUser() {
    String userId = getString(getColumnIndex(UserTable.Cols.USER_UUID));
    String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
    String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));

    User user = new User();
    user.setId(UUID.fromString(userId));
    user.setName(username);
    user.setPassword(password);

    return user;
  }

}
