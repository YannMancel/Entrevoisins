package com.openclassrooms.entrevoisins.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.ButterKnife;

/**
 * A {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface ItemOfRecyclerViewListener {
        void onItemClickedOfRecyclerView(final Neighbour neighbour);
    }

    // FIELDS --------------------------------------------------------------------------------------

    protected ItemOfRecyclerViewListener mCallback;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public BaseFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    protected abstract int getFragmentLayout();
    protected abstract void configureDesign();

    // FRAGMENT ************************************************************************************

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Configures the callback to the parent activity
        this.configureCallbackToParentActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(this.getFragmentLayout(), container, false);

        // Using the ButterKnife library
        ButterKnife.bind(this, view);

        // Configures the design
        this.configureDesign();

        return view;
    }

    // CALLBACK OF ACTIVITY ************************************************************************

    /**
     * Configures the {@link ItemOfRecyclerViewListener} (callback) to the parent activity
     */
    private void configureCallbackToParentActivity() {
        // Initializes the callback field
        try {
            this.mCallback = (ItemOfRecyclerViewListener) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemOfRecyclerViewListener");
        }
    }
}
