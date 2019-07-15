package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_profile.ProfileNeighbourActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test on Profile Neighbour Activity
 */
@RunWith(JUnit4.class)
public class ProfileNeighbourActivityTest {

    // METHODS -------------------------------------------------------------------------------------

    @Test
    public void ProfileNeighbourActivity_checkIfFavorite() {
        ProfileNeighbourActivity profileActivity = new ProfileNeighbourActivity();

        Neighbour goodNeighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com");
        Neighbour badNeighbour = new Neighbour(2, "Silvain", "http://www.xxxxx.com");
        List<Neighbour> neighbourList = Collections.singletonList(goodNeighbour);

        final boolean isFavorite = profileActivity.checkIfFavorite(neighbourList, goodNeighbour);
        assertTrue("Neighbour is into the List", isFavorite);

        final boolean isNotFavorite = profileActivity.checkIfFavorite(neighbourList, badNeighbour);
        assertFalse("Neighbour is not into the List", isNotFavorite);
    }
}
