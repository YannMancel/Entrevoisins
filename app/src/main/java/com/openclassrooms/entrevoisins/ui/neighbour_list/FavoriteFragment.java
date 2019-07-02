package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.base.BaseFragment;

import butterknife.BindView;

/**
 * A {@link BaseFragment} subclass.
 */
public class FavoriteFragment extends BaseFragment {

    // FIELDS --------------------------------------------------------------------------------------

    @BindView(R.id.fragment_favorite_rv_list_neighbours)
    RecyclerView mRecyclerView;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public FavoriteFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void configureDesign() {
        this.configureRecyclerView();
    }

    // INSTANCE ************************************************************************************

    /**
     * Returns a {@link FavoriteFragment}
     * @return a {@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
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
}
