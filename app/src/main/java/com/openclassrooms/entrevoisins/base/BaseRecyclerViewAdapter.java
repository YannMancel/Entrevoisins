package com.openclassrooms.entrevoisins.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A {@link RecyclerView.Adapter} subclass.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    // FIELDS --------------------------------------------------------------------------------------

    private final List<Neighbour> mNeighbours;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public BaseRecyclerViewAdapter(List<Neighbour> items) {
        this.mNeighbours = items;
    }

    // METHODS -------------------------------------------------------------------------------------

    protected abstract void configureListeners(final ViewHolder holder, final Neighbour neighbour);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Retrieves the neighbour at position
        Neighbour neighbour = this.mNeighbours.get(position);

        // Configures the design
        this.configureDesign(holder, position, neighbour);

        // Configures the Listeners
        this.configureListeners(holder, neighbour);
    }

    @Override
    public int getItemCount() {
        return this.mNeighbours.size();
    }

    // UI ******************************************************************************************

    /**
     * Configures the UI
     * @param holder a{@link ViewHolder}
     * @param position a integer that correspond to the position into the {@link List}
     */
    private void configureDesign(final ViewHolder holder, int position, final Neighbour neighbour) {
        // USERNAME
        holder.mNeighbourName.setText(neighbour.getName());

        // USER IMAGE
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
    }

    // INNER CLASS ---------------------------------------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {

        // FIELDS ----------------------------------------------------------------------------------

        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_global_button)
        public ImageButton mGlobalButton;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        // CONSTRUCTORS ----------------------------------------------------------------------------

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

