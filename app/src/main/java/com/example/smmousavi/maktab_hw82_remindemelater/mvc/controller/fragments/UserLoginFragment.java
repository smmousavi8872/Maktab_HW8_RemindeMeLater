package com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.smmousavi.maktab_hw82_remindemelater.R;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.controller.activities.TabLayoutActivity;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.UserList;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {

  EditText mUsernameEdt;
  EditText mPasswordEdt;
  Button mLoginBtn;

  public static UserLoginFragment newInstance() {

    Bundle args = new Bundle();

    UserLoginFragment fragment = new UserLoginFragment();
    fragment.setArguments(args);
    return fragment;
  }


  public UserLoginFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_user_login, container, false);

    mUsernameEdt = view.findViewById(R.id.edt_login_username);
    mPasswordEdt = view.findViewById(R.id.edt_login_password);
    mLoginBtn = view.findViewById(R.id.login_button);

    mLoginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String username = mUsernameEdt.getText().toString();
        String passowrd = mPasswordEdt.getText().toString();
        if (!username.equals("") && !passowrd.equals("")) {
          UUID loggedInUserId = UserList.getInstance(getActivity()).getUser(username, passowrd);
          if (loggedInUserId != null) {
            UserList.getInstance(getActivity()).setLoggedInUserId(loggedInUserId);
            Intent intent = TabLayoutActivity.newIntent(getActivity());
            startActivity(intent);
          } else {
            Snackbar.make(getView(), "Wrong Username Or Password", Snackbar.LENGTH_SHORT).show();

          }
        } else {
          Snackbar.make(getView(), "Enter Username and Password Both", Snackbar.LENGTH_SHORT).show();
        }
      }
    });

    return view;
  }

}
