package com.android.fronc.project_05;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_CODE = 1;
    static final String PACKAGE_NAME = "com.android.fronc.project_05";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void execute(View view) {
        Intent intent = new Intent(Intent.ACTION_RUN);

        EditText editTextArgument = (EditText)findViewById(R.id.editTextArg1);
        String arg1S = editTextArgument.getText().toString();
        editTextArgument = (EditText)findViewById(R.id.editTextArg2);
        String arg2S = editTextArgument.getText().toString();

        if (arg1S != null && arg2S != null && !arg1S.equals("") && !arg2S.equals("")) {
            Integer intArgValue = Integer.valueOf(arg1S);
            intent.putExtra(PACKAGE_NAME + getResources().getString(R.string.arg_1_name), intArgValue);
            intArgValue = Integer.valueOf(arg2S);
            intent.putExtra(PACKAGE_NAME + getResources().getString(R.string.arg_2_name), intArgValue);

            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            boolean isIntentSafe = activities.size() > 0;

            if (isIntentSafe) {
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_activity), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.to_few_arguments), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra(getResources().getString(R.string.operation)) && data.hasExtra(getResources().getString(R.string.result))) {
                String operation = data.getExtras().getString(getResources().getString(R.string.operation));
                Integer result = data.getExtras().getInt(getResources().getString(R.string.result));
                if (operation != null && result != null) {
                    TextView textView = (TextView)findViewById(R.id.textViewOperation);
                    textView.setText(operation);
                    textView = (TextView)findViewById(R.id.textViewResult);
                    textView.setText(result.toString());
                }
            }
        }
    }
}
