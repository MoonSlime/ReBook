package com.cksrb.rebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.Fragment.DealFragment;
import com.cksrb.rebook.Fragment.NormalFragment;
import com.cksrb.rebook.Fragment.UniFragment;
import com.cksrb.rebook.Fragment.WishFragment;
import com.cksrb.rebook.NavActivity.DeveloperActivity;
import com.cksrb.rebook.NavActivity.InfoActivity;
import com.cksrb.rebook.NavActivity.NoticeActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
     private SectionsPagerAdapter mSectionsPagerAdapter;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int position = item.getItemId();

        switch (position){
            case R.id.userInfoNav:
                Toast.makeText(getApplicationContext(),"info",Toast.LENGTH_SHORT).show();
                break;
            case R.id.noticeNav:
                Toast.makeText(getApplicationContext(),"notice",Toast.LENGTH_SHORT).show();
                break;
            case R.id.developerNav:
                Toast.makeText(getApplicationContext(),"developer",Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Duggy
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return new UniFragment();
            else if(position == 1)
                return new NormalFragment();
            else if(position == 2)
                return new DealFragment();
            else
                return new WishFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "대학서적";
                case 1:
                    return "일반서적";
                case 2:
                    return "거래현황";
                case 3:
                    return "장바구니";
            }
            return null;
        }
    }

}
