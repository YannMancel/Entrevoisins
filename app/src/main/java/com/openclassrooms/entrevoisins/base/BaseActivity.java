package com.openclassrooms.entrevoisins.base;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * A {@link AppCompatActivity} subclass.
 */
public abstract class BaseActivity extends AppCompatActivity {

    // METHODS -------------------------------------------------------------------------------------

    protected abstract int getActivityLayout();
    protected abstract Toolbar getToolBar();
    protected abstract void configureDesign();

    // ACTIVITY ************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Associates the layout file to this class
        setContentView(this.getActivityLayout());

        // Using the ButterKnife library
        ButterKnife.bind(this);

        // Configures the design of the activity
        this.configureDesign();
    }

    // OTHER ***************************************************************************************

    /**
     * Configures a {@link android.support.v7.widget.Toolbar} in adding the Up button
     */
    protected void configureToolBar() {
        // If ToolBar exists
        if (this.getToolBar() != null) {
            setSupportActionBar(this.getToolBar());
        }

        // Gets a Support ActionBar corresponding to this ToolBar
        ActionBar actionBar = getSupportActionBar();

        // Enables the Up Button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Shows a {@link Snackbar} with a message
     * @param coordinatorLayout a {@link CoordinatorLayout} that contains the view
     * @param message a {@link String} that contains the message to display
     */
    protected void showSnackbar(CoordinatorLayout coordinatorLayout, String message) {
        // Creates a Snackbar
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}
