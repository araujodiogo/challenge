/*
This class is responsible for the interactions of the player in the map. 
It stores the current X and Y position of the player in the map and the 
number of pokemons he caught so far.
It also verifies if a player is out of bounds after taking a step in the map.
For example, if the user is in map0 (y=0;x=0) and takes one step north,
the y coordinate will be y=-1, therefore he will be out of bounds.
A new map will have to be generated, added to the world and made the active map.
The coordinates of the player also need to be adjusted.
*/
public class Player {

    private int playerCurrentX; // current X position he is in
    private int playerCurrentY; // current Y position he is in
    private int pokemonsCaught; // amount of pokemons caught 

    protected Player(){
        this.playerCurrentX = 0; // starting X position in map is 0
        this.playerCurrentY = 0; // starting Y position in map is 0
        this.pokemonsCaught = 0; // starts with 0 pokemons caught
    }

    protected boolean moveNorth(){ // player moves one step north
        this.playerCurrentY--; // go one house up
        return outOfBounds(); // check if player is out of bounds after moving, and return true or false
    }

    protected boolean moveSouth(){ // player moves one step south
        this.playerCurrentY++; // go one house down
        return outOfBounds(); // check if player is out of bounds after moving, and return true or false
    }

    protected boolean moveEast(){ // player moves one step east
        this.playerCurrentX++; // go one house left
        return outOfBounds(); // check if player is out of bounds after moving, and return true or false
    }

    protected boolean moveWest(){ // player moves one step west
        this.playerCurrentX--; // go one house right
        return outOfBounds(); // check if player is out of bounds after moving, and return true or false
    }

    protected void catchPokemon(){ // everytime the player catches a pokemon we increment one to the total count
        pokemonsCaught++;
    }

    protected int showPokemonCollection(){ // return the amount of pokemons caught 
        return this.pokemonsCaught;
    }

    protected int getXPosition(){ // return the X coordinate of the player in the active map
        return this.playerCurrentX;
    }

    protected int getYPosition(){ // return the Y coordinate of the player in the active map
        return this.playerCurrentY;
    }

    protected void setXPosition(int newX){ // set the X coordinate of the player in the active map
        this.playerCurrentX = newX;
    }

    protected void setYPosition(int newY){ // set the Y coordinate of the player in the active map
        this.playerCurrentY = newY;
    }

    private boolean outOfBounds() { // everytime a player takes a step, we must check if he is out of bounds in the active map.
        if (this.playerCurrentY > 2 || this.playerCurrentY < 0){ // if the player is out of bounds in the Y coordinate
            /*return true indicates that the player is out of bounds. A new map will have to be created in the world.
            Either to the north or the south of the current active map*/
            return true;
        } else if (this.playerCurrentX < 0 || this.playerCurrentX >2 ) {
            /*return true indicates that the player is out of bounds. A new map will have to be created in the world.
            Either to the west or the east of the current active map*/
            return true;
        } else {
            // return false indicates that we are not out of bounds, we can stay on the current active map
            return false;
        }
    }
}