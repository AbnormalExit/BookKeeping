package com.android.book.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.book.R;
import com.android.book.ui.fragment.BillFragment;
import com.android.book.ui.fragment.HomeFragment;
import com.android.book.ui.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {
    private static final String CURRENT_FRAGMENT = "currentFragment";
    private static final String TAG = "ssx";
    private BottomNavigationView nav_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        nav_bottom = findViewById(R.id.nav_bottom);
        nav_bottom.setOnNavigationItemSelectedListener(new BottomNavigationItemSelectedListener());
        if (savedInstanceState != null) {
            int selectItemId = savedInstanceState.getInt(CURRENT_FRAGMENT);
            Log.d(TAG, "selectItemId: " + selectItemId);
            nav_bottom.setSelectedItemId(selectItemId);
        } else {
            Log.d(TAG, "new fragment: ");
            HomeFragment homeFragment = new HomeFragment();
            showAndHideFragment(homeFragment);
        }
    }

    private class BottomNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    HomeFragment homeFragment = new HomeFragment();
                    showAndHideFragment(homeFragment);
                    return true;
                case R.id.action_bill:
                    BillFragment billFragment = new BillFragment();
                    showAndHideFragment(billFragment);
                    return true;
                case R.id.action_my:
                    MyFragment myFragment = new MyFragment();
                    showAndHideFragment(myFragment);
                    return true;
                default:
                    return false;
            }
        }
    }

    protected void showAndHideFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d(TAG, "selectItemId: " + nav_bottom.getSelectedItemId());
        outState.putInt("currentFragment", nav_bottom.getSelectedItemId());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int selectItemId = savedInstanceState.getInt(CURRENT_FRAGMENT);
        Log.d(TAG, "selectItemId: " + selectItemId);
        nav_bottom.setSelectedItemId(selectItemId);
    }
}