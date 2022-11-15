package com.example.lamthu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lamthu.fragment.Fragment_GioiThieu;
import com.example.lamthu.fragment.Fragment_Home;
import com.example.lamthu.fragment.Fragment_LienHe;
import com.example.lamthu.fragment.Fragment_Profile;
import com.example.lamthu.fragment.Fragment_TuSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;

    private static final int fragment_Home = 0;
    private static final int fragment_GioiThieu = 1;
    private static final int fragment_LienHe = 2;
    private static final int fragment_Profile = 3;
    private static final int fragment_TuSach = 4;

    private int mcurent = fragment_Home;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_View);
        bottomNavigationView = findViewById(R.id.nav_bottom);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Fragment_Home());
        navigationView.setCheckedItem(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_bottom_profile:
                        OpenFragmentProfile();
                        break;
                    case R.id.nav_bottom_KhamPhaSach:
                        OpenFragmentHome();
                        break;
                    case R.id.nav_bottom_TuSach:
                        OpenFragmentTuSach();
                        break;
                }
                return true;
            }
        });


        // Set title cho toobar
        setTitleToobar();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                OpenFragmentHome();
                navigationView.setCheckedItem(R.id.nav_home);
                break;
            case R.id.nav_gioiThieu:
                OpenFragmentGioiThieu();
                navigationView.setCheckedItem(R.id.nav_gioiThieu);
                break;
            case R.id.nav_lienHe:
                OpenFragmentLienHe();
                navigationView.setCheckedItem(R.id.nav_lienHe);
                break;

        }
        setTitleToobar();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    private void OpenFragmentHome(){
        if (mcurent != fragment_Home) {
            replaceFragment(new Fragment_Home());
            navigationView.setCheckedItem(R.id.nav_home);
            mcurent = fragment_Home;
        }
    }
    private void OpenFragmentGioiThieu(){
        if (mcurent != fragment_GioiThieu) {
            replaceFragment(new Fragment_GioiThieu());
            navigationView.setCheckedItem(R.id.nav_gioiThieu);
            mcurent = fragment_GioiThieu;
        }
    }
    private void OpenFragmentLienHe(){
        if (mcurent != fragment_LienHe) {
            replaceFragment(new Fragment_LienHe());
            navigationView.setCheckedItem(R.id.nav_lienHe);
            mcurent = fragment_LienHe;
        }
    }
    private void OpenFragmentProfile(){
        if (mcurent != fragment_Profile) {
            replaceFragment(new Fragment_Profile());
            navigationView.setCheckedItem(R.id.nav_bottom_profile);
            mcurent = fragment_Profile;
        }
    }
    private void OpenFragmentTuSach(){
        if (mcurent != fragment_TuSach) {
            replaceFragment(new Fragment_TuSach());
            navigationView.setCheckedItem(R.id.nav_bottom_TuSach);
            mcurent = fragment_TuSach;
        }
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();

    }
    private void setTitleToobar(){
        String title = "";
        switch (mcurent){
            case fragment_Home:
                title = getString(R.string.nav_home);
                break;
            case fragment_GioiThieu:
                title = getString(R.string.nav_gioithieu);
                break;
            case fragment_LienHe:
                title = getString(R.string.nav_lienHe);
                break;

        }
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }

    }


}