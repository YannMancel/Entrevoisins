package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseActivity;
import com.openclassrooms.entrevoisins.base.BaseFragment;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_profile.ProfileNeighbourActivity;

import butterknife.BindView;

import static com.openclassrooms.entrevoisins.model.utils.JsonTools.convertJavaToJson;

/**
 * A {@link BaseActivity} subclass.
 */
public class ListNeighbourActivity extends BaseActivity implements BaseFragment.ItemOfRecyclerViewListener {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    public static final String INTENT_NEIGHBOUR = "com.openclassrooms.entrevoisins.ui.neighbour_list.INTENT_NEIGHBOUR";

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_list_neighbour;
    }

    @Override
    protected void configureDesign() {
        setSupportActionBar(this.mToolbar);

        this.mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        this.mViewPager.setAdapter(mPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.mTabLayout));
        this.mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager));
    }

    // CALLBACK ************************************************************************************

    @Override
    public void onItemClickedOfRecyclerView(final Neighbour neighbour) {
        this.startProfileNeighbourActivity(neighbour);
    }

    // LAUNCHER OF ACTIVITY ************************************************************************

    /**
     * Starts a {@link ProfileNeighbourActivity}
     */
    private void startProfileNeighbourActivity(final Neighbour neighbour) {
        // Creates an Intent
        Intent intent = new Intent(this, ProfileNeighbourActivity.class);

        // Add the neighbour to the intent in Json format
        intent.putExtra(INTENT_NEIGHBOUR, convertJavaToJson(neighbour));

        startActivity(intent);
    }
}
