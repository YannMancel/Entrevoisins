package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    // FIELDS --------------------------------------------------------------------------------------

    private Neighbour neighbour;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    // METHODS -------------------------------------------------------------------------------------

    public Neighbour getNeighbour() {
        return this.neighbour;
    }
}
