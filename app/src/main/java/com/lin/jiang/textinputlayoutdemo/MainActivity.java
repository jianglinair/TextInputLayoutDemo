package com.lin.jiang.textinputlayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jianglin
 */
public class MainActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private Button mLoginBtn;
    private TextInputLayout mUsernameWrapper;
    private TextInputLayout mPasswordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginBtn = findViewById(R.id.btn_login);
        mUsernameWrapper = findViewById(R.id.username_wrapper);
        mPasswordWrapper = findViewById(R.id.password_wrapper);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();

                String username = mUsernameWrapper.getEditText().getText().toString();
                String password = mPasswordWrapper.getEditText().getText().toString();
                if(!validateEmail(username)) {
                    mUsernameWrapper.setError("Not a valid email address!");
                } else if(!validatePassword(password)) {
                    mPasswordWrapper.setError("Not a valid password!");
                } else {
                    mUsernameWrapper.setErrorEnabled(false);
                    mPasswordWrapper.setErrorEnabled(false);
                    login();
                }
            }
        });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 5;
    }

    private void login() {
        Toast.makeText(this, mUsernameWrapper.getEditText().getText().toString() + " login!", Toast.LENGTH_SHORT).show();
    }
}
