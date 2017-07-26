package com.example.ian.materialtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    public FragmentManager fragmentManager;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    public static final int FRAGMENT_FRIST=0;
    public static final int FRAGMENT_SECOND=1;
    private int position;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                       showFragment(FRAGMENT_FRIST);
                        break;
                    case R.id.nav_friends:
                        showFragment(FRAGMENT_SECOND);
                        break;

                }


                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(POSITION,position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        showFragment(savedInstanceState.getInt(POSITION));
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void showFragment(int index){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        position = index;

        switch (index){
            case FRAGMENT_FRIST:
                setTitle("call");
                if (firstFragment==null){
                    firstFragment=new FirstFragment();
                    ft.add(R.id.fragment,firstFragment);
                }else {
                    ft.show(firstFragment);
                }
                break;
            case FRAGMENT_SECOND:
                setTitle("friends");
                if (secondFragment==null){
                    secondFragment=new SecondFragment();
                    ft.add(R.id.fragment,secondFragment);
                }else {
                    ft.show(secondFragment);
                }
        }
        ft.commit();
    }
    public void hideFragment(FragmentTransaction ft){
        if (firstFragment!=null){
            ft.hide(firstFragment);
        }
        if (secondFragment!=null){
            ft.hide(secondFragment);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
