package com.appkamus.submission.appkamus.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.view.fragment.BahasaToEnglishFragment;
import com.appkamus.submission.appkamus.view.fragment.EnglishToBahasaFragment;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class MainActivity extends BaseActivity
        implements MainView,NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.top_toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.tv_title_toolbar)
    protected TextView titleBar;

    @BindView(R.id.tv_sub_title_toolbar)
    protected TextView subTitle;


    @BindView(R.id.tv_sub_toolbar_desc)
    protected TextView tvSubToolbarDesc;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    private ActionBarDrawerToggle toggle;

    private Fragment mCurrentFragment = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListener();
        setupToolbar();

        // Call Index 0
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }


    @Override
    protected void onResume() {
        super.onResume();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        if(closeDrawer()){
            backStackPopper();
        }
    }

    private void backStackPopper(){
        int backstackCount = fragmentManager.getBackStackEntryCount();
        if(backstackCount - 2 >=0){
            String backstackName = fragmentManager.getBackStackEntryAt(backstackCount - 2).getName();
            backstackName = backstackName != null ? backstackName : "";

            if(TextUtils.isEmpty(backstackName)){
                goToHomeScreen();
            }else{
                fragmentManager.popBackStackImmediate();
            }
        }else{
            goToHomeScreen();
        }
    }


    public void setTvSubToolbarDesc(String textDesc){
        if(!TextUtils.isEmpty(textDesc))
            this.tvSubToolbarDesc.setText(textDesc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.menu_search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.text_search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if(getCurrentFragment() instanceof  EnglishToBahasaFragment){
                        ((EnglishToBahasaFragment)getCurrentFragment()).searchKamus(newText);
                    }else if(getCurrentFragment() instanceof BahasaToEnglishFragment){
                        ((BahasaToEnglishFragment)getCurrentFragment()).searchKamus(newText);
                    }
                    return true;
                }
            });

            // Detect Expands and collapse for button click search view
            searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    tvSubToolbarDesc.setVisibility(View.VISIBLE);
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    tvSubToolbarDesc.setVisibility(View.GONE);

                }
            });

        }

        return true;
    }

    private boolean closeDrawer(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_english_to_indonesia:
                replaceFragment(EnglishToBahasaFragment.newInstance(),
                                EnglishToBahasaFragment.TAG);
                break;

            case R.id.nav_indonesia_to_english:
                replaceFragment(BahasaToEnglishFragment.newInstance(),
                        BahasaToEnglishFragment.TAG);
                break;
        }

        closeDrawer();
        return true;
    }


    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() !=null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void changeTitleBar(String text) {
        if(getSupportActionBar() !=null)
            titleBar.setText(text);
    }

    @Override
    public void changeSubTitleBar(String text) {
        if(getSupportActionBar() !=null)
            subTitle.setText(text);
    }


    private void goToTopFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void replaceFragment(Fragment currentFragment, String tag) {
        try{
            this.mCurrentFragment = currentFragment;
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Clear Backstack if user click Now Playing
            if(mCurrentFragment instanceof EnglishToBahasaFragment)
            {
                if(fragmentManager.getBackStackEntryCount() - 2 >= 0){
                    goToTopFragment();
                }

            }

            // Check backstack fragment
            if(TextUtils.isEmpty(tag)){
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fl_content,mCurrentFragment);
            }else{
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.replace(R.id.fl_content,mCurrentFragment,tag);
            }

            fragmentTransaction.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private Fragment getCurrentFragment() {
        mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.fl_content);
        return mCurrentFragment;
    }


    @Override
    public void setupListener() {
        fragmentManager = getSupportFragmentManager();

        // Listener Navigation Item Selected
        navigationView.setNavigationItemSelectedListener(this);

        // Listener Back Stack
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getCurrentFragment() !=null)
                {
                    if(getCurrentFragment() instanceof EnglishToBahasaFragment){
                        navigationView.getMenu().getItem(0).setChecked(true);
                    }else if(getCurrentFragment() instanceof BahasaToEnglishFragment){
                        navigationView.getMenu().getItem(1).setChecked(true);
                    }
                }

            }
        });
    }
}
