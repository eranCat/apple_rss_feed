package com.erank.applerssfeed.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.erank.applerssfeed.R;
import com.erank.applerssfeed.fragments.DataListFragment;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.utils.interfaces.Filterable;
import com.erank.applerssfeed.models.SortType;
import com.erank.applerssfeed.utils.interfaces.Sortable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getName();
    private static final String LIST_FRAGMENT = "listFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.nav_music);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                resetFragmentList();
                return true;
            }
        });
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String q) {
                filterInFragment(q);
                return true;
            }
        });

        return true;
    }

    private void resetFragmentList() {
        Fragment fragment = getListFragment();
        if (fragment instanceof Filterable) {
            ((Filterable) fragment).searchCanceled();
        }
    }

    private void filterInFragment(String q) {
        Fragment fragment = getListFragment();
        if (fragment instanceof Filterable) {
            ((Filterable) fragment).filter(q);
        }
    }

    private void sortFragment(SortType type) {
        Fragment fragment = getListFragment();
        if (fragment instanceof Sortable) {
            ((Sortable) fragment).sort(type);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_name:
                sortFragment(SortType.NAME);
                return true;
            case R.id.sort_date:
                sortFragment(SortType.DATE);
                return true;
            case R.id.sort_genre:
                sortFragment(SortType.GENRE);
                return true;
        }

        return false;
    }

    private Fragment getListFragment() {
        return getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_apps:
                updateFragment(MediaType.APPS);
                return true;
            case R.id.nav_tv:
                updateFragment(MediaType.SHOWS);
                return true;
            case R.id.nav_movies:
                updateFragment(MediaType.MOVIES);
                return true;
            case R.id.nav_music:
                updateFragment(MediaType.MUSIC);
                return true;
            case R.id.nav_pods:
                updateFragment(MediaType.PODCASTS);
                return true;
            default:
                return false;
        }
    }

    private void updateFragment(MediaType type) {

        Fragment fragment = DataListFragment.newInstance(type);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment, LIST_FRAGMENT)
                .commit();
    }
}
