package io.github.gosyujin.minimumaccountmanagersample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Activity mActivity;
    TextView mTextView;
    AccountManager mAccountManager;
    Account[] mAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.helloworld);
        mActivity = this;
        mAccountManager = AccountManager.get(this);

        for (Account account : mAccountManager.getAccounts()) {
            System.out.println("MainActivity#onCreate " + account.toString());
        }

        mAccounts = mAccountManager.getAccounts();

        showAccountListDialog();
    }

    private void showAccountListDialog() {
        List<String> list = new ArrayList<String>();
        for (Account account : mAccounts) {
            list.add(account.name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("MainActivity#showAccountListDialog.click: " + mAccounts[which]);
                mAccountManager.getAuthToken(mAccounts[which], "jp.example", null, mActivity, mCallback, null);
            }
        });

        builder.show();
    }

    private AccountManagerCallback<Bundle> mCallback = new AccountManagerCallback<Bundle>() {
        @Override
        public void run(AccountManagerFuture<Bundle> future) {
            System.out.println("MainActivity#callback");
            String result = "empty";
            try {
                Bundle bundle = future.getResult();
                String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                System.out.println("MainActivity#callback: " + authToken);

                result = authToken;
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }

            mTextView.setText(result);
        }
    };
}
