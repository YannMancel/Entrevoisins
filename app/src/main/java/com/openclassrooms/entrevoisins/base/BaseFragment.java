package com.openclassrooms.entrevoisins.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * A {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    // INTERFACES ----------------------------------------------------------------------------------

    public interface ItemOfRecyclerViewListener {
        void onItemClickedOfRecyclerView(final Neighbour neighbour);
    }

    public interface SnackbarListener {
        void showSnackbarFromFragment(final String message);
    }

    // FIELDS --------------------------------------------------------------------------------------

    protected Context mContext;
    protected ItemOfRecyclerViewListener mCallback;
    protected SnackbarListener mSnackbarCallback;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public BaseFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    protected abstract int getFragmentLayout();
    protected abstract void configureDesign();

    // FRAGMENT ************************************************************************************

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Retrieve the Context of the Fragment
        this.mContext = context;

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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        // TODO: 05/07/2019 To avoid the duplication of EventBus classes [onStop() to onPause()]
        // https://stackoverflow.com/questions/24697951/view-pager-and-fragment-lifecycle

        EventBus.getDefault().unregister(this);
    }

    // CALLBACK OF ACTIVITY ************************************************************************

    /**
     * Configures {@link ItemOfRecyclerViewListener} and {@link SnackbarListener}(callbacks) to the parent activity
     */
    private void configureCallbackToParentActivity() {
        // Initializes the ItemOfRecyclerViewListener (callback) field
        try {
            this.mCallback = (ItemOfRecyclerViewListener) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement ItemOfRecyclerViewListener");
        }

        // Initializes the SnackbarListener (callback) field
        try {
            this.mSnackbarCallback = (SnackbarListener) getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException(e.toString() + " must implement SnackbarListener");
        }
    }
}
