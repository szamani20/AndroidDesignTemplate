package com.szamani.designtemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Szamani on 3/16/2016.
 *
 * AS the name suggests a PagerView to display each
 * crime in a single fragment called CrimeFragment
 * and swiping the page to show the neighbor crimes!
 *
 */
public class CrimePagerActivity extends AppCompatActivity
    implements CrimeFragment.Callbacks {
    private static final String EXTRA_CRIME_ID =
            "com.szamani.designtamplate.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimeList = CrimeLab.getInstance(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimeList.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimeList.size();
            }
        });

        for (int i = 0; i < mCrimeList.size(); i++)
            if (mCrimeList.get(i).getId().equals(crimeID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
    }

    public static Intent newIntent(Context context, UUID crimeID) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
