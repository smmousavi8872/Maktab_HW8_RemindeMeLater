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
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.User;
import com.example.smmousavi.maktab_hw82_remindemelater.mvc.model.UserList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSignupFragment extends Fragment {

  EditText mUsernameEdt;
  EditText mPasswordEdt;
  EditText mRepeatPasswordEdt;
  Button mSignUpBtn;

  public static UserSignupFragment newInstance() {

    Bundle args = new Bundle();

    UserSignupFragment fragment = new UserSignupFragment();
    fragment.setArguments(args);
    return fragment;
  }


  public UserSignupFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_user_signup, container, false);
    mUsernameEdt = view.findViewById(R.id.edt_signup_username);
    mPasswordEdt = view.findViewById(R.id.edt_signup_password);
    mRepeatPasswordEdt = view.findViewById(R.id.edt_signup_repeat_password);
    mSignUpBtn = view.findViewById(R.id.btn_signup);

    mSignUpBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        User user = new User();
        user.setName(mUsernameEdt.getText().toString());
        String password = mPasswordEdt.getText().toString();
        String passwordRepeat = mRepeatPasswordEdt.getText().toString();
        if (!password.equals("") && password.equals(passwordRepeat)) {
          user.setPassword(mPasswordEdt.getText().toString());
          UserList.getInstance(getActivity()).setLoggedInUserId(user.getId());
          UserList.getInstance(getActivity()).addUser(user);
          Intent intent = TabLayoutActivity.newIntent(getActivity());
          startActivity(intent);

        } else {
          Snackbar.make(getView(), "Passwrods don't match", Snackbar.LENGTH_SHORT).show();
        }
      }
    });

    return view;
  }

}
