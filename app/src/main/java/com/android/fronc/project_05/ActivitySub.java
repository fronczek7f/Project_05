package com.android.fronc.project_05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Bundle bundle = getIntent().getExtras();
        Integer arg1 = bundle.getInt(MainActivity.PACKAGE_NAME + getResources().getString(R.string.arg_1_name), 0);
        Integer arg2 = bundle.getInt(MainActivity.PACKAGE_NAME + getResources().getString(R.string.arg_2_name), 0);

        Integer result = arg1 - arg2;

        Intent intent = new Intent();
        intent.putExtra(getResources().getString(R.string.result), result);
        intent.putExtra(getResources().getString(R.string.operation), getResources().getString(R.string.subtraction));
        setResult(RESULT_OK, intent);

        super.finish();
    }
}
