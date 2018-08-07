package com.example.smmousavi.maktab_hw82_remindemelater.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.AllTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.DoneTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.database.TaskDBSchema.UndoneTaskTable;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {

  public TaskCursorWrapper(Cursor cursor) {
    super(cursor);
  }

  public Task getAllTasks() {
    String uuidString = getString(getColumnIndex(AllTaskTable.Cols.TASK_UUID));
    String userIdString = getString(getColumnIndex(AllTaskTable.Cols.USER_UUID));
    String title = getString(getColumnIndex(AllTaskTable.Cols.TITLE));
    Long date = getLong(getColumnIndex(AllTaskTable.Cols.DATE));
    Long time = getLong(getColumnIndex(AllTaskTable.Cols.TIME));
    int isImportant = getInt(getColumnIndex(AllTaskTable.Cols.IMPORTANCE_STATUS));
    int isDone = getInt(getColumnIndex(AllTaskTable.Cols.DONE_STATUS));
    int isAlarmRequired = getInt(getColumnIndex(AllTaskTable.Cols.ALARM_REQUIRED_STATUS));

    /* here should use the Task(UUID, UUID) constructor, because we want to set
    *  both userId and taskId */
    Task task = new Task(UUID.fromString(uuidString), UUID.fromString(userIdString));
    task.setTitle(title);

    if (date != null)
      task.setDate(new Date(date));
    else
      task.setDate(null);
    if (date != null)
      task.setTime(new Date(time));
    else
      task.setTime(null);

    task.setImportant(isImportant == 1);
    task.setDone(isDone == 1);
    task.setAlarmRequired(isAlarmRequired == 1);


    /*String dateString = getString(getColumnIndex(AllTaskTable.Cols.DATE));
    Long dateLong = getLong(getColumnIndex(AllTaskTable.Cols.DATE));
    if (!dateString.equals(""))
      task.setDate(new Date(dateLong));

    else
      task.setDate(null);*/

    return task;
  }

  public Task getDoneTasks() {
    String uuidString = getString(getColumnIndex(DoneTaskTable.Cols.TASK_UUID));
    String userIdString = getString(getColumnIndex(DoneTaskTable.Cols.USER_UUID));
    String title = getString(getColumnIndex(DoneTaskTable.Cols.TITLE));
    Long date = getLong(getColumnIndex(DoneTaskTable.Cols.DATE));
    Long time = getLong(getColumnIndex(DoneTaskTable.Cols.TIME));
    int isImportant = getInt(getColumnIndex(DoneTaskTable.Cols.IMPORTANCE_STATUS));
    int isDone = getInt(getColumnIndex(DoneTaskTable.Cols.DONE_STATUS));
    int isAlarmRequired = getInt(getColumnIndex(DoneTaskTable.Cols.ALARM_REQUIRED_STATUS));

    /* here should use the Task(UUID, UUID) constructor, because we want to set
    *  both userId and taskId */
    Task task = new Task(UUID.fromString(uuidString), UUID.fromString(userIdString));
    task.setTitle(title);
    if (date != null)
      task.setDate(new Date(date));
    else
      task.setDate(null);
    if (date != null)
      task.setTime(new Date(time));
    else
      task.setTime(null);

    task.setImportant(isImportant == 1);
    task.setDone(isDone == 1);
    task.setAlarmRequired(isAlarmRequired == 1);

    return task;
  }

  public Task getUndoneTasks() {
    String uuidString = getString(getColumnIndex(UndoneTaskTable.Cols.TASK_UUID));
    String userIdString = getString(getColumnIndex(UndoneTaskTable.Cols.USER_UUID));
    String title = getString(getColumnIndex(UndoneTaskTable.Cols.TITLE));
    Long date = getLong(getColumnIndex(UndoneTaskTable.Cols.DATE));
    Long time = getLong(getColumnIndex(UndoneTaskTable.Cols.TIME));
    int isImportant = getInt(getColumnIndex(UndoneTaskTable.Cols.IMPORTANCE_STATUS));
    int isDone = getInt(getColumnIndex(UndoneTaskTable.Cols.DONE_STATUS));
    int isAlarmRequired = getInt(getColumnIndex(UndoneTaskTable.Cols.ALARM_REQUIRED_STATUS));

    /* here should use the Task(UUID, UUID) constructor, because we want to set
    *  both userId and taskId */

    Task task = new Task(UUID.fromString(uuidString), UUID.fromString(userIdString));
    task.setTitle(title);

    if (date != -1)
      task.setDate(new Date(date));

    else
      task.setDate(null);

    if (date != -1)
      task.setTime(new Date(time));

    else
      task.setTime(null);

    task.setImportant(isImportant == 1);
    task.setDone(isDone == 1);
    task.setAlarmRequired(isAlarmRequired == 1);

    return task;
  }


}