package com.example.smmousavi.maktab_hw82_remindemelater.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.AllTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.DoneTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UndoneTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UserTable;

public class TaskBaseHelper extends SQLiteOpenHelper {

  public static final int VERSION = 1;
  public static final String DATABASE_NAME = "taskBase.db";

  public TaskBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, VERSION);
  }

  public static final String CREATE_ALL_TASK_TABLE =
    "create table if not exists " + AllTaskTable.NAME + "(" +
      "_id integer primary key autoincrement, " +
      AllTaskTable.Cols.TASK_UUID + "," +
      AllTaskTable.Cols.USER_UUID + "," +
      AllTaskTable.Cols.TITLE + "," +
      AllTaskTable.Cols.DATE + "," +
      AllTaskTable.Cols.TIME + "," +
      AllTaskTable.Cols.IMPORTANCE_STATUS + "," +
      AllTaskTable.Cols.DONE_STATUS + "," +
      AllTaskTable.Cols.ALARM_REQUIRED_STATUS +

      ")";// end of CREATE_ALL_TASK_TABLE


  public static final String CREAET_UNDONE_TASK_TABLE =
    "create table if not exists " + UndoneTaskTable.NAME + "(" +
      "_id integer primary key autoincrement, " +
      UndoneTaskTable.Cols.TASK_UUID + "," +
      UndoneTaskTable.Cols.USER_UUID + "," +
      UndoneTaskTable.Cols.TITLE + "," +
      UndoneTaskTable.Cols.DATE + "," +
      UndoneTaskTable.Cols.TIME + "," +
      UndoneTaskTable.Cols.IMPORTANCE_STATUS + "," +
      UndoneTaskTable.Cols.DONE_STATUS + "," +
      UndoneTaskTable.Cols.ALARM_REQUIRED_STATUS +

      ")";// end of CREATE_UNDONE_TASK_TABLE


  public static final String CREATE_DONE_TASK_TABLE =
    "create table if not exists " + DoneTaskTable.NAME + "(" +
      "_id integer primary key autoincrement, " +
      DoneTaskTable.Cols.TASK_UUID + "," +
      DoneTaskTable.Cols.USER_UUID + "," +
      DoneTaskTable.Cols.TITLE + "," +
      DoneTaskTable.Cols.DATE + "," +
      DoneTaskTable.Cols.TIME + "," +
      DoneTaskTable.Cols.IMPORTANCE_STATUS + "," +
      DoneTaskTable.Cols.DONE_STATUS + "," +
      DoneTaskTable.Cols.ALARM_REQUIRED_STATUS +

      ")";// end of CREATE_DONE_TASK_TABLE

  public static final String CREATE_USER_TABLE =
    "create table if not exists " + UserTable.NAME + "(" +
      "_id integer primary key autoincrement, " +
      UserTable.Cols.USER_UUID + "," +
      UserTable.Cols.USERNAME + "," +
      UserTable.Cols.PASSWORD +

      ")";// end of CREATE_DONE_TASK_TABLE


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    sqLiteDatabase.execSQL(CREATE_ALL_TASK_TABLE);
    sqLiteDatabase.execSQL(CREAET_UNDONE_TASK_TABLE);
    sqLiteDatabase.execSQL(CREATE_DONE_TASK_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }

}
