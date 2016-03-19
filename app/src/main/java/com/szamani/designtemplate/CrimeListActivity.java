package com.szamani.designtemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Szamani on 3/16/2016.
 *
 * The main activity of the CriminalIntent app which is
 * completely adopted from the book
 * 'Android programming the big nerd ranch guide second edition'
 * This app is a very useful design template
 * so it can be used in the future to design and code other
 * similar apps without getting in trouble
 *
 * this app contains some important features:
 * * An abstract activity which can be extended and be used
 *   for many times to reduce the struggle of copy paste same codes
 * * SQLite Database to store things on the private storage
 *   without any permission to read or write on memory
 * * Really useful sample code to work with Bitmaps and image compression
 * * Practical implementation of the very efficient way of listing
 *   which is RecyclerView
 * * Really useful patterns for designing android app using fragments
 *   at all levels of coding from the main layout of an activity to a date picker dialog
 * * Perfect way of using fragments so that the fragment knows nothing about
 *   the parent activity and using interface to communicate with the parent activity
 *
 */
public class CrimeListActivity extends SingleFragmentActivity
    implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks{

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment crimeListFragment = (CrimeListFragment)
                getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        crimeListFragment.updateUI();
    }
}
