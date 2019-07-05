package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Yann MANCEL on 01/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.events
 */
public class SelectFavoriteEvent {

    // FIELDS --------------------------------------------------------------------------------------

    private Neighbour neighbour;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor.
     * @param neighbour
     */
    public SelectFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    // METHODS -------------------------------------------------------------------------------------

    public Neighbour getNeighbour() {
        return this.neighbour;
    }
}
