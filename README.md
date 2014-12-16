TypedMap is a simple way for creating Maps that don't lose the type information of the stored objects.
Every Key also contains the class of the values that can be mapped to it.
This constraint is enforced when adding new Objects to the Map so that Objects retrieved from the map 
can savely and automatically be cast to the class defined in the respective key.
