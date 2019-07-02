package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Yann MANCEL on 01/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.events
 */
public class SelectNeighbourEvent {

    // FIELDS --------------------------------------------------------------------------------------

    public Neighbour neighbour;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor.
     * @param neighbour
     */
    public SelectNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
