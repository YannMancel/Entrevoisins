package com.openclassrooms.entrevoisins.ui.neighbour_profile;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseActivity;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.openclassrooms.entrevoisins.model.utils.JsonTools.convertJsonToJava;

/**
 * A {@link BaseActivity} subclass.
 */
public class ProfileNeighbourActivity extends BaseActivity {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.activity_profile_neighbour_coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

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

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_profile_neighbour;
    }

    @Override
    protected void configureDesign() {
        // Adds the Up button into the ToolBar
        this.configureToolBar();

        // Retrieves the neighbour from Intent
        this.retrieveNeighbourFromIntent();

        // Updates the UI
        this.updateUI();
    }

    // ACTIONS *************************************************************************************

    @OnClick(R.id.activity_profile_neighbour_fab)
    public void onViewClicked() {
        // TODO: 01/07/2019 Add or remove the profile to favorites (SharedPreferences)

        this.showSnackbar(this.mCoordinatorLayout, "Add/Remove favorite");
    }

    // INTENT **************************************************************************************

    /**
     * Retrieves {@link Neighbour} thanks to the {@link android.content.Intent}
     */
    private void retrieveNeighbourFromIntent() {
        // Retrieves the Intent
        Intent intent = getIntent();

        // Retrieves the Neighbour if the Intent is not null
        if (intent != null) {
            String json = intent.getStringExtra(ListNeighbourActivity.INTENT_NEIGHBOUR);
            this.mNeighbour = convertJsonToJava(json, Neighbour.class);
        }
    }

    // UI ******************************************************************************************

    /**
     * Updates the UI
     */
    private void updateUI() {
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
