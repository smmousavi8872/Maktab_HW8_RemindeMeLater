package com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.smmousavi.maktab_hw82_remindemelater.R;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.fragments.UserLoginFragment;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.fragments.UserSignupFragment;

import java.util.ArrayList;
import java.util.List;

public class UserLoginPagerActivity extends AppCompatActivity {

  private List<Fragment> fragments;
  private List<String> tabTitles;


  public static Intent newIntent(Context orgin) {
    Intent intent = new Intent(orgin, UserLoginPagerActivity.class);
    return intent;
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_login_pager);
    ViewPager viewPager = (ViewPager) findViewById(R.id.login_view_pager);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.login_tab_layout);

    fragments = new ArrayList<>();
    tabTitles = new ArrayList<>();

    addFragment(UserLoginFragment.newInstance(), "Login");
    addFragment(UserSignupFragment.newInstance(), "Sign up");

    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        return fragments.get(position);
      }

      @Override
      public int getCount() {
        return fragments.size();
      }

      @Override
      public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
      }
    });

    tabLayout.setupWithViewPager(viewPager);

  }

  public void addFragment(Fragment fragment, String tabTitle) {
    fragments.add(fragment);
    tabTitles.add(tabTitle);
  }
}
