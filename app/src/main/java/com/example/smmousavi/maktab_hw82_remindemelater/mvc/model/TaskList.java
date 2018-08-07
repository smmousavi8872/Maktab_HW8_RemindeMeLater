package com.example.smmousavi.maktab_hw82_remindemelater.mvc.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskBaseHelper;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskCursorWrapper;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.AllTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.DoneTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UndoneTaskTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskList {

  private static TaskList mTasks;
  private Context mContext;
  private SQLiteDatabase mDatabase;
  private UUID loggedInUserId;


  private TaskList(Context context) {
    mContext = context.getApplicationContext();
    mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();

  }// end of TaskList()


  public static TaskList getInstance(Context context) {
    if (mTasks == null)
      mTasks = new TaskList(context);

    return mTasks;
  }// end of getInstance()


  public Task getTask(UUID taskId, UUID userId) {
    String whereClause = AllTaskTable.Cols.TASK_UUID + " = ? and " +
      AllTaskTable.Cols.USER_UUID + " = ? ";

    String[] whereArgs = {taskId.toString(), userId.toString()};
    TaskCursorWrapper cursor = queryTasks(AllTaskTable.NAME, whereClause, whereArgs);
    try {
      if (cursor.getCount() == 0)
        return null;

      cursor.moveToFirst();
      return cursor.getAllTasks();

    } finally {
      cursor.close();
    }

  }// end of getTasks()


  public void addTask(Task task) {
    ContentValues values = getTaskContentValues(task);
    mDatabase.insert(AllTaskTable.NAME, null, values);
  }// end of addTask()


  public void updateTask(Task task) {
    String taskIdString = task.getTaskId().toString();
    String userIdString = task.getUserId().toString();
    ContentValues values = getTaskContentValues(task);
    String whereClause = AllTaskTable.Cols.TASK_UUID + " = ? and " +
      AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {taskIdString, userIdString};
    mDatabase.update(AllTaskTable.NAME, values, whereClause, whereArgs);

  }


  public List<Task> getAllTasks(UUID userId) {
    List<Task> allTasks = new ArrayList<>();
    String whereClasue = AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {userId.toString()};
    TaskCursorWrapper cursor = queryTasks(AllTaskTable.NAME, whereClasue, whereArgs);
    if (cursor.getCount() > 0) {
      try {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
          allTasks.add(cursor.getAllTasks());
          cursor.moveToNext();

        }
      } finally {
        cursor.close();
      }
    }
    return allTasks;
  }// end of getTasks()


  public void removeTask(Task task) {
    String whereClause = AllTaskTable.Cols.TASK_UUID + " = ? and " +
      AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {task.getTaskId().toString(), task.getUserId().toString()};
    mDatabase.delete(AllTaskTable.NAME, whereClause, whereArgs);
  }// end of removeTasks()


  public void addDoneTask(Task task) {
    ContentValues values = getTaskContentValues(task);
    mDatabase.insert(DoneTaskTable.NAME, null, values);
  }// end of addUndoneTasks()


  public void updateDoneTask(Task task) {
    String taskIdString = task.getTaskId().toString();
    String userIdString = task.getUserId().toString();
    ContentValues values = getTaskContentValues(task);
    String where = DoneTaskTable.Cols.TASK_UUID + " = ? and " +
      DoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {taskIdString, userIdString};
    mDatabase.update(UndoneTaskTable.NAME, values, where, whereArgs);
  }


  public List<Task> getDoneTasks(UUID userId) {
    List<Task> doneTasks = new ArrayList<>();
    String whereClasue = DoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {userId.toString()};
    TaskCursorWrapper cursor = queryTasks(DoneTaskTable.NAME, whereClasue, whereArgs);
    if (cursor.getCount() > 0) {
      try {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
          doneTasks.add(cursor.getDoneTasks());
          cursor.moveToNext();
        }
      } finally {
        cursor.close();
      }
    }
    return doneTasks;
  }// end of getUndoneTasks()


  public void removeDoneTask(Task task) {
    String whereClause = DoneTaskTable.Cols.TASK_UUID + " = ? and " +
      DoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {task.getTaskId().toString(), task.getUserId().toString()};
    mDatabase.delete(DoneTaskTable.NAME, whereClause, whereArgs);
  }// end of removeUndoneTasks()


  public void addUndoneTask(Task task) {
    ContentValues values = getTaskContentValues(task);
    mDatabase.insert(UndoneTaskTable.NAME, null, values);
  }// end of addUndoneTasks()


  public void updateUndoneTask(Task task) {
    String taskIdString = task.getTaskId().toString();
    String userIdString = task.getUserId().toString();
    ContentValues values = getTaskContentValues(task);
    String where = UndoneTaskTable.Cols.TASK_UUID + " = ? and " +
      UndoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {taskIdString, userIdString};
    mDatabase.update(UndoneTaskTable.NAME, values, where, whereArgs);
  }


  public List<Task> getUndoneTasks(UUID userId) {
    List<Task> undoneTasks = new ArrayList<>();
    String whereClause = UndoneTaskTable.Cols.USER_UUID + " = ?";
    String[] whereArgs = {userId.toString()};
    TaskCursorWrapper cursor = queryTasks(UndoneTaskTable.NAME, whereClause, whereArgs);
    if (cursor.getCount() > 0) {
      try {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
          undoneTasks.add(cursor.getUndoneTasks());
          cursor.moveToNext();
        }
      } finally {
        cursor.close();
      }
    }
    return undoneTasks;
  }// end of getUndoneTasks()


  public void removeUndoneTask(Task task) {
    String whereClause = UndoneTaskTable.Cols.TASK_UUID + " = ? and " +
      UndoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] whereArgs = {task.getTaskId().toString(), task.getUserId().toString()};
    mDatabase.delete(UndoneTaskTable.NAME, whereClause, whereArgs);
  }// end of removeUndoneTasks()


  private TaskCursorWrapper queryTasks(String tableName, String whereClause, String[] whereArgs) {
    Cursor cursor = mDatabase.query(
      tableName,
      null, /* cols == null returns all cols */
      whereClause,
      whereArgs,
      null,
      null,
      null);

    return new TaskCursorWrapper(cursor);
  } // end of queryAllTasks()


  public void clearAllTasks(UUID userId) {
    String allWhereClause = AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] allWhereArgs = {userId.toString()};
    String doneWhereClause = DoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] doneWhereArgs = {userId.toString()};
    String undoneWhereClause = UndoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] undoneWhereArgs = {userId.toString()};

    mDatabase.delete(AllTaskTable.NAME, allWhereClause, allWhereArgs);
    mDatabase.delete(DoneTaskTable.NAME, doneWhereClause, doneWhereArgs);
    mDatabase.delete(UndoneTaskTable.NAME, undoneWhereClause, undoneWhereArgs);

  }// end of clearAllTasks()


  public void clearDoneTasks(UUID userId) {
    String allWhereClause = AllTaskTable.Cols.DONE_STATUS + " = 1 and " +
      AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] allWhereArgs = {userId.toString()};

    String doneWhereClasue = DoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] doneWehreArgs = {userId.toString()};

    /* String[] whereArgs = {" 1 "}; surprisingly whereArgs here isn't detected by the sqlite*/
    mDatabase.delete(AllTaskTable.NAME, allWhereClause, allWhereArgs);
    mDatabase.delete(DoneTaskTable.NAME, doneWhereClasue, doneWehreArgs);

  }// end of clearDoneTasks()


  public void clearUndoneTasks(UUID userId) {
    String allWhereClause = AllTaskTable.Cols.DONE_STATUS + " = 0 and " +
      AllTaskTable.Cols.USER_UUID + " = ? ";
    String[] allWhereArgs = {userId.toString()};
    String undoneWhereClause = UndoneTaskTable.Cols.USER_UUID + " = ? ";
    String[] undoneWhereArgs = {userId.toString()};

    /* String[] whereArgs = {" 1 "}; surprisingly here whereArgs isn't detected by the sqlite*/
    mDatabase.delete(AllTaskTable.NAME, allWhereClause, allWhereArgs);
    mDatabase.delete(UndoneTaskTable.NAME, undoneWhereClause, undoneWhereArgs);

  }// end of clearUndoneTasks()


  private static ContentValues getTaskContentValues(Task task) {
    ContentValues values = new ContentValues();
    values.put(AllTaskTable.Cols.TASK_UUID, task.getTaskId().toString());
    values.put(AllTaskTable.Cols.USER_UUID, task.getUserId().toString());
    values.put(AllTaskTable.Cols.TITLE, task.getTitle());
    if (task.getDate() != null)
      values.put(AllTaskTable.Cols.DATE, task.getDate().getTime());
    else
      values.put(AllTaskTable.Cols.DATE, new Date().getTime());
    /*else
      values.put(AllTaskTable.Cols.DATE, "");*/
    if (task.getTime() != null)
      values.put(AllTaskTable.Cols.TIME, task.getTime().getTime());
    else
      values.put(AllTaskTable.Cols.TIME, new Date().getTime());
    /*else
        values.put(AllTaskTable.Cols.DATE, "");*/


    values.put(AllTaskTable.Cols.IMPORTANCE_STATUS, task.isImportant() ? 1 : 0);
    values.put(AllTaskTable.Cols.DONE_STATUS, task.isDone() ? 1 : 0);
    values.put(AllTaskTable.Cols.ALARM_REQUIRED_STATUS, task.isAlarmRequired() ? 1 : 0);

    return values;
  }
}