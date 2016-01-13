package com.aetorresv.baseproject.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aetorresv.baseproject.R;

public class BaseFragment extends Fragment {
    public static String screenTitle = "";
    public static String NAME = "";

    public void setScreenTitle(String screenTitleIN) {
        screenTitle = screenTitleIN;
        NAME = screenTitleIN;
        setHasOptionsMenu(true);
    }

    public static String getScreenTitle(){
        return screenTitle;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        try {
            Log.e("BASEFRAGMENT", "Tratando de poner el titulo!!!");
            getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);

            MenuItem titulo = (MenuItem) menu.findItem(R.id.action_titulo);
            titulo.setTitle("" + screenTitle);
            String titleCondensed = "";
            if (screenTitle.length() > 25) {
                titleCondensed = screenTitle.substring(0, 20) + "..";
                titulo.setTitleCondensed(titleCondensed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
