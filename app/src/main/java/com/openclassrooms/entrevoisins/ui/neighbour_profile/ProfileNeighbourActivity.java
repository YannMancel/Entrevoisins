package com.openclassrooms.entrevoisins.ui.neighbour_profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseActivity;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.utils.SaveTools;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.openclassrooms.entrevoisins.utils.JsonTools.convertJsonToJava;

/**
 * A {@link BaseActivity} subclass.
 */
public class ProfileNeighbourActivity extends BaseActivity {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.activity_profile_neighbour_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.activity_profile_neighbour_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_profile_neighbour_image)
    ImageView mUserImage;
    @BindView(R.id.activity_profile_neighbour_tv_name1)
    TextView mUserName1;
    @BindView(R.id.activity_profile_neighbour_tv_name2)
    TextView mUserName2;
    @BindView(R.id.activity_profile_neighbour_tv_address)
    TextView mAddress;
    @BindView(R.id.activity_profile_neighbour_tv_phone)
    TextView mPhone;
    @BindView(R.id.activity_profile_neighbour_tv_url)
    TextView mUrl;
    @BindView(R.id.activity_profile_neighbour_tv_introduction)
    TextView mIntroduction;

    @BindView(R.id.activity_profile_neighbour_fab)
    FloatingActionButton mFAB;

    private Neighbour mNeighbour;
    private boolean mIsFavorite;
    private List<Neighbour> mFavoriteNeighbours;

    public static final String PREF_NEIGHBOURS = "PREF_NEIGHBOURS";

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_profile_neighbour;
    }

    @Override
    protected Toolbar getToolBar() {
        return this.mToolbar;
    }

    @Override
    protected void configureDesign() {
        // Adds the Up button into the ToolBar
        this.configureToolBar();

        // Retrieves the neighbour from Intent
        this.mNeighbour = this.retrieveNeighbourFromIntent();

        // Retrieves the favorite neighbour from SharedPreferences
        this.mFavoriteNeighbours = this.retrieveFavoriteNeighbourFromSharedPreferences();

        // Checks if the neighbour is into favorite
        this.mIsFavorite = this.checkIfFavorite(this.mFavoriteNeighbours, this.mNeighbour);

        // Updates the UI
        this.updateUI();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.activity_profile_neighbour_fab)
    public void onFABClicked() {
        // Displays a message
        this.showSnackbar(this.mCoordinatorLayout, !this.mIsFavorite ? getString(R.string.add_favorite, this.mNeighbour.getName()) :
                                                                       getString(R.string.remove_favorite, this.mNeighbour.getName()));

        // Adds or removes the neighbour in SharedPreferences
        if (this.mIsFavorite) {
            // Removes the neighbour to the favorite list
            this.removeNeighbourToFavorite();
        } else {
            // Adds the neighbour to the favorite list
            this.addNeighbourToFavorite();
        }

        // Change the favorite state of neighbour (true or false)
        this.mIsFavorite = !this.mIsFavorite;

        // Updates FAB
        this.updateFAB();
    }

    // INTENT **************************************************************************************

    /**
     * Retrieves {@link Neighbour} thanks to the {@link Intent}
     * @return a {@link Neighbour}
     */
    public Neighbour retrieveNeighbourFromIntent() {
        // Retrieves the Intent
        Intent intent = getIntent();

        // Retrieves the Neighbour if the Intent is not null
        if (intent != null) {
            String json = intent.getStringExtra(ListNeighbourActivity.INTENT_NEIGHBOUR);

            return convertJsonToJava(json, Neighbour.class);
        }
        else {
            return null;
        }
    }

    // SHARED PREFERENCES **************************************************************************

    /**
     * Retrieves a {@link List} of {@link Neighbour} thanks to {@link SharedPreferences}
     * @return a {@link List} of {@link Neighbour}
     */
    public List<Neighbour> retrieveFavoriteNeighbourFromSharedPreferences() {
        return SaveTools.loadListFromSharedPreferences(this, ProfileNeighbourActivity.PREF_NEIGHBOURS, Neighbour.class);
    }

    /**
     * Adds a {@link Neighbour} to the favorite {@link Neighbour} thanks to {@link SharedPreferences}
     */
    private void addNeighbourToFavorite() {
        // Adds the neighbour to the favorite list
        if (this.mFavoriteNeighbours == null) {
            this.mFavoriteNeighbours = new ArrayList<>();
        }

        this.mFavoriteNeighbours.add(this.mNeighbour);

        // Save the favorite list
        SaveTools.saveSharedPreferencesWithJson(this, this.mFavoriteNeighbours, PREF_NEIGHBOURS);
    }

    /**
     * Remove a {@link Neighbour} to the favorite {@link Neighbour} thanks to {@link SharedPreferences}
     */
    private void removeNeighbourToFavorite() {
        // Removes the neighbour to the favorite list
        this.mFavoriteNeighbours.remove(this.mNeighbour);

        // Save the favorite list
        SaveTools.saveSharedPreferencesWithJson(this, this.mFavoriteNeighbours, PREF_NEIGHBOURS);
    }

    // CHECK IF FAVORITE ***************************************************************************

    /**
     * Checks if {@link Neighbour} is into favorite thanks to {@link SharedPreferences}
     * @param neighbourList a {@link List} of {@link Neighbour}
     * @param neighbour a {@link Neighbour}
     * @return a boolean
     */
    public boolean checkIfFavorite(final List<Neighbour> neighbourList, final Neighbour neighbour) {
        return (neighbourList != null) && neighbourList.contains(neighbour);
    }

    // UI ******************************************************************************************

    /**
     * Update {@link FloatingActionButton}
     */
    private void updateFAB() {
        // Changes image drawable of FAB
        this.mFAB.setImageDrawable(this.mIsFavorite ? getDrawable(R.drawable.ic_star_white_24dp) :
                getDrawable(R.drawable.ic_star_border_white_24dp));

        // Changes Tint color of FAB
        this.mFAB.setColorFilter(this.mIsFavorite ? getResources().getColor(R.color.fab_favorite_is_true) :
                getResources().getColor(R.color.fab_favorite_is_false));
    }

    /**
     * Updates the UI
     */
    private void updateUI() {
        // FAB
        this.updateFAB();

        // IMAGE
        Glide.with(this)
                .load(this.mNeighbour.getAvatarUrl())
                .apply(RequestOptions.noTransformation())
                .into(this.mUserImage);

        // NAME
        this.mUserName1.setText(this.mNeighbour.getName());
        this.mUserName2.setText(this.mNeighbour.getName());

        // TODO: 02/07/2019 Add the personal data
        // PERSONAL DATA
//        TextView mAddress;
//        TextView mPhone;
//        TextView mUrl;B

        // INTRODUCTION
//        TextView mIntroduction;
    }
}
