package io.fabianterhorst.fastlayout.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Default
        //setContentView(new ActivityMainLayout(this));
        //The cache is reusing the object to improve the performance
        ActivityMainLayout layout = LayoutCache.getInstance().getLayout(this, LayoutCache.Activity_Main_Layout);
        setContentView(layout);
    }
}
