/*
This class is responsible for initializing our world, maps and player.
It is also here where we handle the calls to the differents steps the
user is going to take in the world, received from the stdin.
*/
import java.util.Scanner;
import java.util.HashMap;

public class PokemonMinds {

    private static HashMap<String, Map> mapsInWorldList; // hashmap where we keep state of the map objects created.
    private static char stepsToTake[]; // var where we store the input value of steps the player will take.
    private static Scanner inputReader; // our scanner.
    private static String inputReceived; // where we store the string received. Also where we can input strings for testing, directly in the code.
    public static void main(String[] args) { 
        inputReader = new Scanner(System.in); // init the scanner.
        inputReceived = inputReader.nextLine();
        //inputReceived = ""; string where we load the tests in from the Test Strings text file.
        stepsToTake = inputReceived.toCharArray(); // we convert the string received in the stdin in a char array where every position represents a step that the player will take.
        mapsInWorldList = new HashMap<String, Map>(); // init the list responsible for keeping state of the map objects created.
        World world = new World(); // init the world were the maps will exist. Starting x & y coords in the world are (y=0;x=0).
        Map map0 = new Map(world.getMapXYcoordsInWorld().clone(), world); // init our starting map, in the world.
        Player player = new Player(); // initialize our player, in the map.
        mapsInWorldList.put("map0", map0); // Add our first map, where the player starts, into our world.
        checkIfPokemonWasCaught(player, world); // Check if the pokemon in our starting position was caught or not.

        for (char step : stepsToTake) { // for every element of our char array.
            switch (step) {
            case 'N': // if the step is to move north (norte)
                if (player.moveNorth()) { // the player moves one house north and we check if he is out of bounds.
                    // the player is out of bounds, change the map in the world, north of the current one.
                    world.setMapYcoordsInWorld(-1); // we decrement the Y coordinate of the map where the player is moving to, in the world.
                    player.setYPosition(2); // since the player will join a different map in the north, the starting position will be y=2 and x will stay the same.
                    if (!world.loadMapInWorld()) { // check if the map the player is going to already exists or not.
                        createNewMapInWorld(world); // if it doesnt, we create a new map where the player will be moving to.
                    }

                }
                checkIfPokemonWasCaught(player, world); // check for pokemon in the position of the map in the world the player is in.
                break;
            case 'S': // if the step is to move south (sul)
                if (player.moveSouth()) {
                    world.setMapYcoordsInWorld(1); 
                    player.setYPosition(0);
                    if (!world.loadMapInWorld()) {
                        createNewMapInWorld(world);
                    }
                }
                checkIfPokemonWasCaught(player, world);
                break;
            case 'E': // if the step is to move east (este)
                if (player.moveEast()) {
                    world.setMapXcoordsInWorld(1);
                    player.setXPosition(0);
                    if (!world.loadMapInWorld()) {
                        createNewMapInWorld(world);
                    }
                }
                checkIfPokemonWasCaught(player, world);
                break;
            case 'O': // if the step is to move west (oeste)
                if (player.moveWest()) {
                    world.setMapXcoordsInWorld(-1);
                    player.setXPosition(2);
                    if (!world.loadMapInWorld()) {
                        createNewMapInWorld(world);
                    }
                }
                checkIfPokemonWasCaught(player, world);
                break;
            default: // where we deal with bad input received.
                System.err.print("\nInvalid char detected, please restart and enter only ONE string with a combination of N S E O.\n");
                System.exit(0);
                break;
            }
        }
        System.out.println(player.showPokemonCollection()); // finally we print to the stdout the amount of pokemons caught.
    }

    private static void createNewMapInWorld(World world) { // init new map object in the current world.
        mapsInWorldList.put("map" + mapsInWorldList.size(), new Map(world.getMapXYcoordsInWorld().clone(), world));
    }

    private static void checkIfPokemonWasCaught(Player player, World world){ // check if a pokemon exists in the current position..
        if (world.pokemonCaught(player.getXPosition(), player.getYPosition())) { // if it does, catch him!
            player.catchPokemon();
        }
    }
}
