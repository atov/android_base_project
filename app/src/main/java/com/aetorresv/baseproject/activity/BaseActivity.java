package com.aetorresv.baseproject.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.aetorresv.baseproject.Application;
import com.aetorresv.baseproject.R;
import com.aetorresv.baseproject.fragment.HomeFragment;
import com.aetorresv.baseproject.utils.AppSessionStore;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static butterknife.ButterKnife.findById;

public abstract class BaseActivity extends AppCompatActivity {

    @Getter
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.act_start_drawerlayout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    public ActionBarDrawerToggle mDrawerToggle = null;
    private AppSessionStore appSessionStore = null;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        try {
            ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        appSessionStore = AppSessionStore.getInstance(this);

        if (toolbar != null) {
            Timber.i("toolbar esta bien");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            setSupportActionBar(toolbar);
            setupNavigationView(toolbar);


        }else{
            Timber.e("-- toolbar null");
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null){
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        } else {
            super.onBackPressed();
        }
    }

    public AppSessionStore getAppSessionStore(){
        if (appSessionStore == null){
            appSessionStore = AppSessionStore.getInstance(this);
        }
        return appSessionStore;
    }

    protected abstract int getLayoutResource();

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null){
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null){
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setupNavigationView(Toolbar toolbar) {
        if (mDrawerLayout != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name ){
                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                    super.onDrawerOpened(drawerView);
                    //User user = null ;
                    NavigationView navView = (NavigationView)drawerView;
                    Timber.v("navView: " + navView);
                    /*
                    TextView name  = (TextView)navView.findViewById(R.id.list_header_name);
                    TextView label = (TextView)navView.findViewById(R.id.list_header_label);
                    ImageView img = (ImageView) navView.findViewById(R.id.rounded_profile);
                    ProgressBar loading = (ProgressBar)navView.findViewById(R.id.ticker_loading);

                    if (getAppSessionStore().isSigned()){

                        try {
                            user = (User) WebUtils.getDataGparsed(getAppSessionStore().restore(Constants.savedProfile) , User.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (user != null){

                            Timber.v("name: " + name + "user name: "+user.getLastname());
                            if (name != null){
                                name.setText(""+user.getName() + " "+user.getLastname());
                            }
                            name.invalidate();

                            if (label != null){
                                label.setText("ver profile");
                            }

                            String profileTemp = "http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg";
                            Picasso.with(BorraApp.appContext).load(profileTemp)
                                    .placeholder(R.mipmap.ic_launcher).fit()
                                    .transform(UIUtils.getRoundedViewTransformer()).into(img);

                            loading.setVisibility(View.GONE);
                        }
                    }else{
                        name.setText("Invitado");
                        label.setText("ingresa");
                        img.setImageResource(R.mipmap.ic_launcher);
                    }*/

                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            NavigationView navigationView = findById(this, R.id.navigation_view);

            if (navigationView != null) {
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switchFragment(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });


            }
        }else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    public void switchFragment(int menuId){
        Application.setCurrentNavItem(menuId);
        ActionBar actionBar = getSupportActionBar();
        switch (menuId){
            case R.id.m_home:
                setMenuTitle(actionBar ,R.string.menu_home);
                if (homeFragment == null){
                    homeFragment = HomeFragment.newInstance();
                }
                replaceMainFragment(homeFragment);
                break;
        }
    }

    private void setMenuTitle(ActionBar actionBar , int resource){
        if (actionBar != null) {
            actionBar.setTitle(resource);
        }
    }

    private void replaceMainFragment(Fragment fragment) {
        Timber.v("replaceMainFragment.. ");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .commit();
    }

}
