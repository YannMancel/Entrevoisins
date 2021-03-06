package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour of favorite
 */
public class DeleteFavoriteEvent {

    // FIELDS --------------------------------------------------------------------------------------

    private Neighbour neighbour;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    // METHODS -------------------------------------------------------------------------------------

    public Neighbour getNeighbour() {
        return this.neighbour;
    }
}
