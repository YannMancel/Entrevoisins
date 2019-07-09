package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_profile.ProfileNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.SaveTools;
import com.openclassrooms.entrevoisins.utils.SelectViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // FIELDS --------------------------------------------------------------------------------------

    private static int POSITION_ITEM = 0;
    private ListNeighbourActivity mActivity;
    private List<Neighbour> mNeighbourList = new DummyNeighbourApiService().getNeighbours();

    // RULES ---------------------------------------------------------------------------------------

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    // METHODS -------------------------------------------------------------------------------------

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        final int itemsCount = this.mNeighbourList.size();

        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(itemsCount));

        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(itemsCount - 1));
    }

    /**
     * When we click on an item, the user profile is shown
     */
    @Test
    public void myNeighboursList_selectAction_shouldDisplayProfileActivity() {
        // When : We perform a click on a global button at [POSITION_ITEM]
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, new SelectViewAction()));

        // Then : the user profile is shown
        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_coordinator_layout))
                .check(matches(isDisplayed()));
    }

    /**
     * When we click on an item, the user profile is shown and it displays the good personal data
     */
    @Test
    public void myNeighboursList_selectAction_shouldDisplayGoodPersonalDataInProfileActivity() {
        // Retrieves the neighbour at [POSITION_ITEM]
        Neighbour expectedNeighbour = this.mNeighbourList.get(POSITION_ITEM);

        // When : We perform a click on a global button at [POSITION_ITEM]
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, new SelectViewAction()));

        // Then : the user profile is shown and it displays the good personal data
        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_tv_name1))
                .check(matches(withText(expectedNeighbour.getName())));

        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_tv_name2))
                .check(matches(withText(expectedNeighbour.getName())));
    }

    /**
     * When we click on the favorite button, the user is shown in the favorite list
     */
    @Test
    public void myNeighboursList_selectAction_shouldBeFavorite() {
        // Retrieves the neighbour at [POSITION_ITEM]
        Neighbour expectedNeighbour = this.mNeighbourList.get(POSITION_ITEM);

        // Clear the SharedPreferences
        SaveTools.removeDataFromSharedPreferences(this.mActivity, ProfileNeighbourActivity.PREF_NEIGHBOURS);

        // When : We perform a click on favorite button of ProfileActivity

        // Clicks on item of RecyclerView
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, new SelectViewAction()));

        // Clicks on FAB of ProfileActivity
        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_fab))
                .perform((ViewAction) click());

        // Clicks on the Up button of the ToolBar [or pressBack()]
        onView(withContentDescription("Navigate up"))
                .perform(click());

        // Swipe from [My neighbour] to [Favorites] of ViewPager
        onView(ViewMatchers.withId(R.id.container))
                .perform(scrollRight());

        // Checks if the recyclerView is displayed and clicks on the item at [POSITION_ITEM]

        // Checks the list contains only one item
        onView(ViewMatchers.withId(R.id.fragment_favorite_rv_list_neighbours))
                .check(withItemCount(1));

        onView(ViewMatchers.withId(R.id.fragment_favorite_rv_list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, new SelectViewAction()));

        // Then : the user profile is shown and it displays the good personal data
        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_tv_name1))
                .check(matches(withText(expectedNeighbour.getName())));

        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_tv_name2))
                .check(matches(withText(expectedNeighbour.getName())));
    }

    /**
     * When we click on the favorite button, the user is shown in the favorite list
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveFavorite() {
        // Clear the SharedPreferences
        SaveTools.removeDataFromSharedPreferences(this.mActivity, ProfileNeighbourActivity.PREF_NEIGHBOURS);

        // Clicks on item of RecyclerView
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM, new SelectViewAction()));

        // Clicks on FAB of ProfileActivity
        onView(ViewMatchers.withId(R.id.activity_profile_neighbour_fab))
                .perform((ViewAction) click());

        // Clicks on the Up button of the ToolBar [or pressBack()]
        onView(withContentDescription("Navigate up"))
                .perform(click());

        // Swipe from [My neighbour] to [Favorites] of ViewPager
        onView(ViewMatchers.withId(R.id.container))
                .perform(scrollRight());

        // *****************************************************************************************

        // Given : We remove the element at [POSITION_ITEM]
        onView(ViewMatchers.withId(R.id.fragment_favorite_rv_list_neighbours)).check(withItemCount(1));

        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.fragment_favorite_rv_list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        // Then : the number of element is 0
        onView(ViewMatchers.withId(R.id.fragment_favorite_rv_list_neighbours)).check(withItemCount(0));
    }
}