package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.PriorityQueue;

import model.Graph.Edge;
import model.Graph.Vertex;

public class GraphUtil {
	
	 public static Graph<Location> createGraph (String filePath) throws IOException {
         Graph<Location> graph = new Graph<>();
         BufferedReader reader = new BufferedReader(new FileReader(filePath));
         String line;

         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             String from = parts[0].trim();
             String to = parts[1].trim();
             int distance = Integer.parseInt(parts[2].trim());

             Vertex<Location> fromVertex = new Vertex<>(new Location(from));
             Vertex<Location> toVertex = new Vertex<>(new Location(to));
             Edge<Location> edge = new Edge<>(distance, fromVertex, toVertex);

             fromVertex.addEdge(edge);
             graph.getVertices().add(fromVertex);
             graph.getVertices().add(toVertex);
             graph.getEdges().add(edge);
         }

         reader.close();
         return graph;
     }

     public static Vertex<Location> findVertexByName(Graph<Location> graph, String name) {
         for (Vertex<Location> vertex : graph.getVertices()) {
             if (vertex.getValue().getName().equals(name)) {
                 return vertex;
             }
         }
         return null;
     }

     public static Location findNearestHospital(Vertex<Location> start) {
         PriorityQueue<Edge<Location>> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
         pq.addAll(start.getEdges());

         if (pq.isEmpty()) {
             return null;
         }

         Vertex<Location> nearestHospitalVertex = pq.poll().getToVertex();
         return nearestHospitalVertex.getValue();
     }

     public static void saveNearestHospital(String filePath, Location nearestHospital) throws IOException {
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
         oos.writeObject(nearestHospital.getName());
         oos.close();
     }
 }

