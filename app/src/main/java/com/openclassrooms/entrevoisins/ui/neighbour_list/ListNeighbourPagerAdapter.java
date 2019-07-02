package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = ListNeighbourPagerAdapter.class.getSimpleName();

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // METHODS -------------------------------------------------------------------------------------

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            // NeighbourFragment instance
            case 0: return NeighbourFragment.newInstance();
            // FavoriteFragment instance
            case 1: return FavoriteFragment.newInstance();
            default: {
                Log.e(TAG, "getItem: Error of number position in RecyclerView");
                return null;
            }
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}