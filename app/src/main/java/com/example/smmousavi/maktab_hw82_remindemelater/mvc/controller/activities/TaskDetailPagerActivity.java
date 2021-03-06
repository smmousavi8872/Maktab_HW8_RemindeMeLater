package com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.smmousavi.maktab_hw82_remindemelater.R;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.fragments.TaskDetailFragment;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.Task;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.TaskList;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.UserList;

import java.util.List;
import java.util.UUID;


public class TaskDetailPagerActivity extends AppCompatActivity {

  public static final String EXTRA_TASK_ID =
    "com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.activities.extra_task_id";
  public static final String EXTRA_TASK_LIST_ID =
    "com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.activities.extra_task_list_id";

  private ViewPager viewPager;
  private List<Task> tasksList;
  private UUID taskId;
  private UUID loggedInUser;
  private Integer taskListId;


  public static Intent newIntent(Context orgin, UUID taskId, Integer taskListId) {
    Intent intent = new Intent(orgin, TaskDetailPagerActivity.class);
    intent.putExtra(EXTRA_TASK_ID, taskId);
    intent.putExtra(EXTRA_TASK_LIST_ID, taskListId);

    return intent;
  }// end of newIntent()


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_task_detail_pager);

    loggedInUser = UserList.getInstance(TaskDetailPagerActivity.this).getLoggedInUserId();

    viewPager = (ViewPager) findViewById(R.id.task_detail_view_pager);

    TaskList instance = TaskList.getInstance(TaskDetailPagerActivity.this);
    Bundle extras = getIntent().getExtras();
    taskId = (UUID) extras.getSerializable(EXTRA_TASK_ID);
    taskListId = extras.getInt(EXTRA_TASK_LIST_ID);

    if (taskId != null)
      setTitle("Update Task");

    else if (taskId == null)
      setTitle("New Task");

    switch (taskListId) {
      case 0:
        tasksList = instance.getAllTasks(loggedInUser);
        break;
      case 1:
        tasksList = instance.getUndoneTasks(loggedInUser);
        break;
      case 2:
        tasksList = instance.getDoneTasks(loggedInUser);
    }

    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        UUID id = tasksList.get(position).getTaskId();
        return TaskDetailFragment.newInstance(id);
      }

      @Override
      public int getCount() {
        return tasksList.size();
      }

    }); /* end of setAdapter({...}) */
    viewPager.setCurrentItem(findCurrentItem());

  }// end of onCreate()


  private int findCurrentItem() {
    for (int i = 0; i < tasksList.size(); i++) {
      if (tasksList.get(i).getTaskId().equals(taskId))
        return i;

    }
    return -1;
  }// end of findCurrentItem()


}