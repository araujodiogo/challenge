
/*
This class is responsible for creating new instances of maps that get added to the current world.
The position of the first map (map0) in the World is (y=0,x=0).
Depending on the movement of the player, when he goes out of bounds in a map, we must generate a new 
map that we also add to our world. If the user starts in position (y=0;x=0) in the map and goes north,
he will be in position (y=-1;x=0). Since every instance of a map is a 3x3 array, the player will be
out of bounds in the map. So, we must generate a new instance of a map, that we add to our current world.
The new position of this map in the world will be (y=-1;x=0). The player will now be in map1 in position
(y=2;x=0).
Everytime we create a new map, we populate him with pokemons, represented by 1.
*/

import java.util.Arrays;
public class Map {

    private int newMap[][]; // where we store a new map.

    protected Map(int mapPositionInWorld[], World world){
        this.newMap = new int[3][3]; // generate new map when we create the object
        populateMap(); // populate the map with pokemons
        world.addMapToWorld(this.newMap, mapPositionInWorld); // add map to list of maps in the world. We pass the map, and its position in the world.
        world.addActiveMap(this.newMap); // replace current active map with the new one in active map array.
    }

    private void populateMap(){ // method that fills all positions in map with 1. (1 represents a pokemon; 0 represents no pokemon)
        for (int i = 0; i < this.newMap.length; i++){
            Arrays.fill(this.newMap[i], 1);
        }
    }
}
