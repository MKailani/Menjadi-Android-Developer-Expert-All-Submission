package com.dicoding.kailani.submission.moviecatalogue.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.kailani.submission.moviecatalogue.R;
import com.dicoding.kailani.submission.moviecatalogue.view.fragment.FavoriteFragment;
import com.dicoding.kailani.submission.moviecatalogue.view.fragment.NowPlayingFragment;
import com.dicoding.kailani.submission.moviecatalogue.view.fragment.SearchMovieFragment;
import com.dicoding.kailani.submission.moviecatalogue.view.fragment.UpCommingFragment;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 4 Aplikasi Movie Catalogue UI/UX DATABASE
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 21/01/19.
 */
public class MainActivity extends BaseActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String EXTRA_CURRENT_FRAGMENT = "extra:current_fragment";

    private Fragment mCurrentFragment = null;
    private FragmentManager fragmentManager;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.bottom_navigation)
    protected BottomNavigationView toolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup View
        this.fragmentManager = getSupportFragmentManager();
        setupToolbar();
        setupListener();

        //Selected First Fragment
        if(savedInstanceState == null)
            onNavigationItemSelected(toolbarBottom.getMenu().getItem(0));
        else
            findFragment(savedInstanceState.getString(EXTRA_CURRENT_FRAGMENT));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_CURRENT_FRAGMENT,getCurrentFragment().getTag());
    }

    private void findFragment(String tagFragment){
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(tagFragment);
        replaceFragment(mCurrentFragment,tagFragment);
    }


    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);

    }

    @Override
    public void changeTitleBar(String text) {
        if(getSupportActionBar() !=null)
            getSupportActionBar().setTitle(text);
    }

    @Override
    public void replaceFragment(Fragment currentFragment, String tag) {
        try{
            this.mCurrentFragment = currentFragment;
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Clear Backstack if user click Now Playing
            if(mCurrentFragment instanceof NowPlayingFragment)
            {
                 if(fragmentManager.getBackStackEntryCount() - 2 >= 0){
                    goToTopFragment();
                }

            }

            // Check backstack fragment
            if(TextUtils.isEmpty(tag)){
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fl_screen,mCurrentFragment);
            }else{
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.replace(R.id.fl_screen,mCurrentFragment,tag);
            }

            fragmentTransaction.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void setCheckedToolbarBottom(int resId){
        int index =0;
        switch (resId){
            case  R.id.bottom_now_playing:
                index = 0;
                break;
            case  R.id.bottom_up_comming:
                index = 1;
                break;
            case  R.id.bottom_favorite:
                index = 2;
                break;
            case  R.id.bottom_search:
                index = 3;
                break;
        }

        toolbarBottom.getMenu().getItem(index).setChecked(true);
    }

    private Fragment getCurrentFragment() {
        mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.fl_screen);
        return mCurrentFragment;
    }

    @Override
    public void setupListener() {

        // Toolbar Listener
        toolbarBottom.setOnNavigationItemSelectedListener(this);

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getCurrentFragment() !=null)
                {
                    if(getCurrentFragment() instanceof NowPlayingFragment){
                        setCheckedToolbarBottom(R.id.bottom_now_playing);
                    }else if(getCurrentFragment() instanceof UpCommingFragment){
                        setCheckedToolbarBottom(R.id.bottom_up_comming);
                    }else if(getCurrentFragment() instanceof FavoriteFragment){
                        setCheckedToolbarBottom(R.id.bottom_favorite);
                    }else if(getCurrentFragment() instanceof SearchMovieFragment){
                        setCheckedToolbarBottom(R.id.bottom_search);
                    }
                }

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        setCheckedToolbarBottom(menuItem.getItemId());

        switch (menuItem.getItemId()){
            case R.id.bottom_now_playing:
                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.newInstance();
                replaceFragment(nowPlayingFragment,NowPlayingFragment.TAG);
                break;
            case R.id.bottom_up_comming:
                UpCommingFragment upCommingFragment = UpCommingFragment.newInstance();
                replaceFragment(upCommingFragment,UpCommingFragment.TAG);
                break;
            case R.id.bottom_favorite:
                FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
                replaceFragment(favoriteFragment,FavoriteFragment.TAG);
                break;

            case R.id.bottom_search:
                SearchMovieFragment searchMovieFragment = SearchMovieFragment.newInstance();
                replaceFragment(searchMovieFragment,SearchMovieFragment.TAG);
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        backStackPopper();
    }

    private void goToTopFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting_language:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                break;
        }
        return true;
    }
}
