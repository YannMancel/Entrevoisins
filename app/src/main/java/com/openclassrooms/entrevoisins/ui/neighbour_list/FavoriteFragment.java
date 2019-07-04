package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseFragment;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.model.utils.SaveTools;
import com.openclassrooms.entrevoisins.ui.neighbour_profile.ProfileNeighbourActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * A {@link BaseFragment} subclass.
 */
public class FavoriteFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_favorite_rv_list_neighbours)
    RecyclerView mRecyclerView;

    private List<Neighbour> mNeighbours;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public FavoriteFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void configureDesign() {
        // Configures the RecyclerView
        this.configureRecyclerView();

        // Initializes the RecyclerView
        this.initList();
    }

    // INSTANCE ************************************************************************************

    /**
     * Returns a {@link FavoriteFragment}
     * @return a {@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    // CALLBACK OF RECYCLER VIEW *******************************************************************

    /**
     * Fired if the user clicks on a delete button
     * @param event a {@link DeleteNeighbourEvent}
     */
    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteEvent event) {
        // Remove neighbour of favorite
        this.removeNeighbourToFavorite(event.neighbour);

        // Initializes the RecyclerView
        this.initList();

        // Displays a message with the parent activity
        this.mSnackbarCallback.showSnackbarFromFragment("Delete favorite: " + event.neighbour.getName());
    }

    /**
     * Launches {@link BaseFragment.ItemOfRecyclerViewListener} (callback) if the user clicks on the item
     * @param event a {@link SelectNeighbourEvent}
     */
    @Subscribe
    public void onSelectNeighbour(SelectNeighbourEvent event) {
        // Warning: EvenBus throws 2 calls (1 with FavoriteFragment + 1 with NeighbourFragment
        this.mCallback.onItemClickedOfRecyclerView(event.neighbour);
    }

    // UI ******************************************************************************************

    /**
     * Configures {@link RecyclerView}
     */
    private void configureRecyclerView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(this.mContext, DividerItemDecoration.VERTICAL));
    }

    // INITIALISATION OF RECYCLER VIEW *************************************************************

    /**
     * Init the List of favorite neighbours
     */
    private void initList() {
        // Retrieves the data from SharedPreferences
        this.retrieveSharedPreferences();

        if (this.mNeighbours != null) {
            this.mRecyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(this.mNeighbours));
        }
    }

    // SHARED PREFERENCES **************************************************************************

    /**
     * Retrieves the data from {@link android.content.SharedPreferences}
     */
    private void retrieveSharedPreferences() {
        // Retrieves the neighbour list from SharedPreferences
        this.mNeighbours = SaveTools.loadListFromSharedPreferences(this.mContext, ProfileNeighbourActivity.PREF_NEIGHBOURS, Neighbour.class);
    }

    /**
     * Remove a {@link Neighbour} to the favorite {@link Neighbour} thanks to {@link android.content.SharedPreferences}
     */
    private void removeNeighbourToFavorite(final Neighbour neighbour) {
        // Removes the neighbour to the favorite list
        this.mNeighbours.remove(neighbour);

        // Save the favorite list
        SaveTools.saveSharedPreferencesWithJson(this.mContext, this.mNeighbours, ProfileNeighbourActivity.PREF_NEIGHBOURS);
    }
}
