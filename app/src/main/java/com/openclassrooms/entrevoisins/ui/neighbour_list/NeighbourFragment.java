package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseFragment;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * A {@link BaseFragment} subclass.
 */
public class NeighbourFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.list_neighbours)
    RecyclerView mRecyclerView;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_neighbour_list;
    }

    @Override
    protected void configureDesign() {
        // Configures the RecyclerView
        this.configureRecyclerView();

        initList();
    }

    // FRAGMENT ************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mApiService = DI.getNeighbourApiService();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // UI ******************************************************************************************

    /**
     * Configures {@link RecyclerView}
     */
    private void configureRecyclerView() {
        Context context = getContext();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    // INSTANCE ************************************************************************************

    /**
     * Returns a {@link NeighbourFragment}
     * @return a {@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    // CALLBACK OF RECYCLER VIEW *******************************************************************

    /**
     * Fired if the user clicks on a delete button
     * @param event a {@link DeleteNeighbourEvent}
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        this.mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    /**
     * Launches {@link BaseFragment.ItemOfRecyclerViewListener} (callback) if the user clicks on the item
     * @param event a {@link SelectNeighbourEvent}
     */
    @Subscribe
    public void onSelectNeighbour(SelectNeighbourEvent event) {
        this.mCallback.onItemClickedOfRecyclerView(event.neighbour);
    }

    // INITIALISATION ******************************************************************************

    /**
     * Init the List of neighbours
     */
    private void initList() {
        this.mNeighbours = this.mApiService.getNeighbours();
        this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(this.mNeighbours));
    }
}
