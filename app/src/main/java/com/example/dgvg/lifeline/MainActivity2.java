package com.example.dgvg.lifeline;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPreferences settings;
    ViewPagerAdapter adapter;
    ArrayList<String> s1;
    static FirstPageFragmentListener firstPageListener;
    public static final String PREFS_NAME = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setId(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.black), ContextCompat.getColor(this, R.color.white));
    }



    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("login",false);
        adapter.addFragment(new OneFragment(), "Donate");
        adapter.addFragment(new SecondFragment(), "Request");
        adapter.addFragment(new ThirdFragment(), "Redeem");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private FragmentManager mFragmentManager = null;
        public Fragment mFragmentAtPos0;
        private Context context;
        FirstPageListener listener = new FirstPageListener();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            mFragmentManager = manager;
        }
        public final class FirstPageListener implements
                FirstPageFragmentListener {
            public void onSwitchToNextFragment() {
                notifyDataSetChanged();
            }
        }
        //private String[] titles = { "VER FACTURAS", "VER CONSUMO", "INTRODUCIR LECTURA" };



        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new OneFragment();
                case 1:
                    return new SecondFragment();
                case 2:

                    return new ThirdFragment();



            }
            return null;
        }
        @Override
        public int getItemPosition(Object object)
        {
//            if (object instanceof ThirdFragment &&
//                    mFragmentAtPos0 instanceof FourthFragment) {
//                return POSITION_NONE;
//            }
//            if (object instanceof FourthFragment &&
//                    mFragmentAtPos0 instanceof ThirdFragment) {
//                return POSITION_NONE;
//            }
            return POSITION_UNCHANGED;
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.aboutus) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kvsc.in"));
//            startActivity(browserIntent);
//            return true;
//        }
        return true;

    }
    public void onlogoutPressed() {
//        if(viewPager.getCurrentItem() == 2) {
//            if (adapter.getItem(2) instanceof FourthFragment) {
//                ((FourthFragment) adapter.getItem(2)).logoutPressed();
//            }
//            else if (adapter.getItem(2) instanceof ThirdFragment) {
//                finish();
//            }
//        }
    }


}
