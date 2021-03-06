package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.view.View;

import com.openclassrooms.entrevoisins.base.BaseRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.SelectNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A {@link BaseRecyclerViewAdapter} subclass.
 */
public class MyNeighbourRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        super(items);
    }

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected void configureListeners(ViewHolder holder, Neighbour neighbour) {
        // User clicks on an item of the RecyclerView
        holder.mGlobalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SelectNeighbourEvent(neighbour));
            }
        });

        // User clicks on the delete icon
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }
}
