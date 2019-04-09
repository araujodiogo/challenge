/*
This class is responsible for creating the world and managing which map is active. 
This means that, everytime we create a new instance of a map, we add it to the world,
and give it a position according to maps that already exist in the world.
If the player moves to a position where a map already exists, we simply load the map
the player is moving to.
If the player moves to a position where a map does not exist, we create a new map and
add it to the world.
The starting position of the first map, in the world, is (y=0;x=0). Every map is 3x3.
*/
import java.util.HashMap;

public class World{

    protected int activeMap [][]; // where we are going to store the map the player is in. So, the active map.
    private HashMap<int[][], int[]> mapListInWorld; // where we are going to store the maps created in the world. Key -> Map Object; Value -> [Y;X] (coordinates of map in the world)
    private int mapXYcoordsInWorld[]; // where we store the coordinates of the active map in the world. 

    protected World(){
        this.activeMap = new int[3][3]; // init the size of the array that will receive the map. This represents the activeMap (where the player is) in the world. Only one map is active at a time.
        this.mapListInWorld = new HashMap<int[][], int[]>(); // hashmap where we store existant maps in the world, and their coordinates.
        this.mapXYcoordsInWorld = new int[2]; // init the array with 2 positions. One for Y and another for X.
        initializeWorldXYCoords(); // init the starting position of the map, in the world.
    }

    protected int getMapXcoordsInWorld(){ // return the coordinate X of the map in the world
        return this.mapXYcoordsInWorld[1];
    }

    protected int getMapYcoordsInWorld(){ // return the coordinate Y of the map in the world
        return this.mapXYcoordsInWorld[0];
    }

    protected int[] getMapXYcoordsInWorld(){ // return both the coordinates of the map in the world
        return this.mapXYcoordsInWorld;
    }

    protected void setMapXcoordsInWorld(int x){ // set the coordinate X of the map in the world
        this.mapXYcoordsInWorld[1] += x; // X coordinate of map position in the world
    }

    protected void setMapYcoordsInWorld(int y){ // set the coordinate Y of the map in the world
        this.mapXYcoordsInWorld[0] += y; // Y coordinate of map position in the world
    }

    private void initializeWorldXYCoords(){ // init the x and y coordinate of the map in the world.
        this.mapXYcoordsInWorld[0] = 0;
        this.mapXYcoordsInWorld[1] = 0;
    }

    protected void addActiveMap(int map[][]) { // replace the previous map the user was in with the active map array he will be in next.
        this.activeMap = map;
    }

    protected void addMapToWorld(int[][] map, int mapPosition[] ){ // everytime a new map is created, we add it, and its coordinates in the world, to the hashmap.
        mapListInWorld.put(map, mapPosition); 
    }

    protected boolean pokemonCaught(int x, int y) { // checks if the player already caught the pokemon in the active map he is in.
        if (this.activeMap[y][x] == 0) { // we already caught the pokemon in that house!
            return false;
        } else if (this.activeMap[y][x] == 1) { // new pokemon caught!
            this.activeMap[y][x] = 0; // we change the value of the user position to 0. (0 represents pokemon caught)
            return true;
        } else{
            return false;
        }
    }

    protected boolean loadMapInWorld() { // this method checks if the position of the next map the user is going to, already exists or not. 
        for (int[][] map : mapListInWorld.keySet()) { // for every key (map object) in our hashmap
            int[] value = mapListInWorld.get(map); // we get the value (world coordinates of map object) of that key
            if (value[0] == getMapYcoordsInWorld() && value[1] == getMapXcoordsInWorld()) { // if the X and Y of the map are already associated with a map in our hashmap
                addActiveMap(map); // we load the existing map object from our hashmap into our activeMap array.
                return true; // success!
            }
        }
        return false; // the map doesnt exist in the world, for this position. A new map will have to be created and added.
    }
}