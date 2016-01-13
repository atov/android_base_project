package com.aetorresv.baseproject.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aetorresv.baseproject.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        switchFragment(R.id.m_home);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.home_activity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
