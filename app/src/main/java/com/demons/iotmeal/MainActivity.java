package com.demons.iotmeal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import common.constant.KeyConstants;
import common.model.ModuleHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ModuleHelper.doStartApplicationWithPackageName(MainActivity.this, KeyConstants.MEAL_MAIN_UI);
        finish();
    }
}
