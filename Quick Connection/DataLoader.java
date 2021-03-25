import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {
    
    private CS400Graph<String> airportsGraph;
    private File vertexFile;
    private File edgeFile;

    public DataLoader(File vertexFile, File edgeFile, CS400Graph<String> airportsGraph)  {
        this.airportsGraph = airportsGraph;
        this.vertexFile = vertexFile;
        this.edgeFile = edgeFile;
        //loads vertices and edges into graph
        try {
            setVertices();
            setEdges();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    

    protected void setVertices() throws FileNotFoundException {
        if (vertexFile == null) {
          throw new FileNotFoundException("Error. No file found. Please use "
                + "valid file ending in .txt");
        }
        
        String airport;
        Scanner scan = new Scanner(vertexFile);

        while(scan.hasNext()) {
            airport = scan.nextLine().trim();
            airportsGraph.insertVertex(airport);

        }

        if (scan != null) {
            scan.close();
        }
      }
    protected void setEdges() throws FileNotFoundException {
      if (edgeFile == null) {
        throw new FileNotFoundException("Error. No file found. Please use "
              + "valid file ending in .txt");
      }
      
      String target;
      String source;
      int weight;
      Scanner scan = new Scanner(edgeFile);

      while(scan.hasNext()) {
          source = scan.next().trim();
          target = scan.next().trim();
          String tempString = scan.nextLine().trim();
          weight = Integer.parseInt(tempString);
          airportsGraph.insertEdge(source, target, weight);

      }

      if (scan != null) {
          scan.close();
      }
      
    }

}