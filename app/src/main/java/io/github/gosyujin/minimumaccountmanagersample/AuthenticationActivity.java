package io.github.gosyujin.minimumaccountmanagersample;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ataka on 2016/03/01.
 */
public class AuthenticationActivity extends AccountAuthenticatorActivity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        System.out.println("AuthenticationActivity#onCreate");
        setContentView(R.layout.activity_authentication);

        final EditText usernameEdit = (EditText) findViewById(R.id.username);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                attemptAuth(username, password);

            }
        });

    }

    private void attemptAuth(String username, String password) {
        Account account = new Account(username, "jp.example");
        AccountManager accountManager = AccountManager.get(this);

        accountManager.addAccountExplicitly(account, password, null);
        finish();
    }
}
