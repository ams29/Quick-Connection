
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class FrontEnd {
  private static Scanner sc;
  private static CS400Graph<String> graph;
  private static DataLoader airports;

  /**
   * Main method that makes our application functional and consistently takes in user input and
   * displays appropriate information about the graph until user decides to exit application
   * 
   * 
   */
  public static void main(String[] args) {
    sc = new Scanner(System.in);
    graph = new CS400Graph<>();
    airports = new DataLoader(new File("airports.txt"), new File("airportedges.txt"), graph);
    boolean done = false;

    int userInput = 0;
    while (!done) {
      try {
        printMainMenu();
        System.out.print("Enter a command: ");
        userInput = Integer.parseInt(sc.nextLine());
        switch (userInput) {
          // lookup a city
          case 1:
            System.out
                .print("Enter city airport abbreviation (use command 4 to list all airports): ");
            String city = sc.nextLine();
            city = checkMissSpell(city);

            if (graph.containsVertex(city)) {
              System.out.println(city + " is in the map");
            } else {
              System.out.println(city + " is not in the map");
            }
            break;
          // Find the shortest path between cities
          case 2:
            System.out.print("Enter starting city: ");
            String city1 = sc.nextLine();
            city1 = checkMissSpell(city1);

            if (!graph.containsVertex(city1)) {
              System.out.println(city1 + " is not the map.");
              break;
            }

            System.out.print("Enter destination city: ");
            String city2 = sc.nextLine();
            city2 = checkMissSpell(city2);

            if (!graph.containsVertex(city2)) {
              System.out.println(city2 + " is not the map.");
              break;
            }
            // if there is no path, print appropriate message to user
            try {
              String path = graph.shortestPath(city1, city2).toString();
              System.out.print("The shortest route between these two cities is: ");
              System.out.println(path);
            } catch (NoSuchElementException e) {
              System.out.println("There is not a path between those cities");
            }
            break;
          // return the shortest distance between cities
          case 3:
            System.out.print("Enter a starting city: ");
            String city3 = sc.nextLine();
            city3 = checkMissSpell(city3);
            if (!graph.containsVertex(city3)) {
              System.out.println(city3 + " is not the map.");
              break;
            }

            System.out.print("Enter a destination city: ");
            String city4 = sc.nextLine();
            city4 = checkMissSpell(city4);
            if (!graph.containsVertex(city4)) {
              System.out.println(city4 + " is not the map.");
              break;
            }

            // if there is no path, print appropriate message to user
            try {
              graph.getPathCost(city3, city4);
              System.out.print("The shortest distance between these two cities is: ");
              System.out.println(graph.getPathCost(city3, city4));
            } catch (NoSuchElementException e) {
              System.out.println("There is no path between " + city3 + " and " + city4 + "!");
            }
            break;
          // print all cities
          case 4:
            Scanner vertexList = new Scanner(new File("airports.txt"));
            while (vertexList.hasNextLine()) {
              System.out.println(vertexList.nextLine());
            }
            break;
          // exit
          case 5:
            done = true;
            break;
          default:
            System.out.println("The number " + userInput + " is not an option.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Please enter the correct format! Numbers only! (i.e. 1, 2, etc.)");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } // while
    System.out.println("Thanks for using the Airport Map");
    sc.close();
  }

  /**
   * Method that checks if a valid city is mistyped and corrects it to its proper form. Will then
   * ask user if this is the city they wanted, if it is, w
   * 
   * @param city that could be mistyped
   * @return city if not miss typed and in graph, return city in a corrected form if the corrected
   *         form is what the user originally meant and it exists in the graph, if that is not what
   *         they meant, or the corrected form does not exist in the graph, return the original city
   *         name that is miss spelled or does not exist.
   * 
   *         i.e. mke -> MKE (if yes) return MKE else return mke asdfjk -> ASDFIK (does not exist)
   *         return asdfik (which will fail in main method)
   */
  private static String checkMissSpell(String city) {
    if (graph.containsVertex(city))
      return city;

    String orig = city;
    city = city.toUpperCase();
    // if the corrected city name is in the graph, ask user if that is the city they wanted
    if (graph.containsVertex(city)) {
      System.out.println("Did you mean " + city + "?\n" + "Enter y/n: ");
      char input = sc.nextLine().charAt(0);
      if (input == 'y' || input == 'Y') {
        return city;
      }
    }
    return orig;
  }

  /**
   * Void method that displays menu options to users
   */
  private static void printMainMenu() {
    System.out.println("\nYou are in the Airport Map. Here are you choices: ");
    System.out.println("1. Lookup a city.");
    System.out.println("2. Find the shortest path between cities.");
    System.out.println("3. Return the shortest distance between cities.");
    System.out.println("4. Print all cities.");
    System.out.println("5. Exit");
  }

}