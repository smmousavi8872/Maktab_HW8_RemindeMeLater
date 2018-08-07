package com.example.smmousavi.maktab_hw82_remindemelater.database;

public class TaskDBSchema {
  public static final String VERSION = "version";

  public static final class AllTaskTable {
    public static final String NAME = "all_tasks";

    public static final class Cols {
      public static final String TASK_UUID = "task_uuid";
      public static final String USER_UUID = "user_uuid";
      public static final String TITLE = "title";
      public static final String DATE = "date";
      public static final String TIME = "time";
      public static final String DONE_STATUS = "done_status";
      public static final String IMPORTANCE_STATUS = "importance_status";
      public static final String ALARM_REQUIRED_STATUS = "alarm_required_status";
    }

  }// end of AllTaskTable{}


  public static final class UndoneTaskTable {
    public static final String NAME = "undone_tasks";

    public static final class Cols {
      public static final String TASK_UUID = "task_uuid";
      public static final String USER_UUID = "user_uuid";
      public static final String TITLE = "title";
      public static final String DATE = "date";
      public static final String TIME = "time";
      public static final String DONE_STATUS = "done_status";
      public static final String IMPORTANCE_STATUS = "importance_status";
      public static final String ALARM_REQUIRED_STATUS = "alarm_required_status";
    }

  }


  public static final class DoneTaskTable {
    public static final String NAME = "done_tasks";

    public static final class Cols {
      public static final String TASK_UUID = "task_uuid";
      public static final String USER_UUID = "user_uuid";
      public static final String TITLE = "title";
      public static final String DATE = "date";
      public static final String TIME = "time";
      public static final String DONE_STATUS = "done_status";
      public static final String IMPORTANCE_STATUS = "importance_status";
      public static final String ALARM_REQUIRED_STATUS = "alarm_required_status";
    }

  }

  public static final class UserTable {
    public static final String NAME = "user_table";

    public static final class Cols {
      public static final String USER_UUID = "task_uuid";
      public static final String USERNAME = "username";
      public static final String PASSWORD = "password";
    }

  }
}
