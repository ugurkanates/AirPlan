package Airplan.flight_manage;
import java.util.*;

public class ManagementFlights extends MatrixGraph {

    private Map<String, Integer> airports;
    private Vector<Flight> flights;
    private Set<Integer> flightNumbers;

    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     */
    public ManagementFlights() {
        super(23, false);
        airports = new HashMap<String, Integer>();
        flights = new Vector<>();
        flightNumbers = new HashSet<>();
        initialize_map();
        install_airway_network();
    }

    public void createFligth(String source, String dest, String flightType, String company, String date){
        int flightNo= (int) (Math.random()*1000);
        while (flightNumbers.contains(flightNo)){
            flightNo= (int) (Math.random()*1000);
        }
        String no="";
        if (company.equals("Turkish Airlines")){
            no += "TK"+flightNo;
        }
        else if (company.equals("Pegasus"))
        {
            no += "PC"+flightNo;
        }
        else{
            no += "8Q"+flightNo;
        }
        flights.add(new Flight(source, dest, flightType, no, date, shortest_path(this, airports.get(source), airports.get(dest))));
        flightNumbers.add(flightNo);
        return;
    }


    public ArrayList<String> getAirports(){
        return new ArrayList(airports.keySet());
    }


    /**
     *
     * @param graph the graph to be searched
     * @param start start vertex
     * @param pred arr that has parent info according to smallest distance
     * @param dist arr that has distance info
     */
    private void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {

        int numV = graph.getNumV();
        HashSet< Integer > vMinusS = new HashSet < Integer > (numV);
        // Initialize V–S.
        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }
        // Initialize pred and dist.
        for (int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        // Main loop
        while (vMinusS.size() != 0) {
            // Find the value u in V–S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS.
            vMinusS.remove(u);
            // Update the distances.


            int delete = -1;
            for (int v : vMinusS) {
                if (graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight();
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
                else {
                    if (u == -1)
                        delete = v;
                }
            }
            if (u == -1)
                vMinusS.remove(delete);
        }
    }

    /**
     *
     * @param g the graph to be searched
     * @param v1 start vertex
     * @param v2 end vertex
     * @return the shortest path
     */
    private Vector shortest_path(Graph g, int v1, int v2){
        int vertexSize = g.getNumV();
        if (v1<0 || v1>=vertexSize || v2<0 || v2>=vertexSize){
            System.err.println("Betwenn 0-"+vertexSize+" are vertexies");
            return null;
        }
        Vector<Integer> path = new Vector<>();

        if (is_connected(g, v1, v2)){

            int [] pred= new int[vertexSize];
            double [] dist= new double[vertexSize];
            try{
                dijkstrasAlgorithm(g, v1, pred, dist );
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println(v1+" to "+v2 + " doesn't exits!");
                return null;
            }

            Stack<Integer> tempPath = new Stack<>();
            int index= v2;
            int count = 0;

            tempPath.add(index);
            while (dist[index] != 0.0 && count != vertexSize){
                index= pred[index];
                tempPath.add(index);
                ++count;
            }

            while (!tempPath.isEmpty()){
                path.add(tempPath.pop());
            }
        }
        else
            System.err.println(v1+" to "+v2 + " doesn't have path!");

        return path;
    }


    /**
     *
     * @param g the graph to be searched
     * @param v1 start vertex
     * @param v2 end vertex
     * @return true if vertexies are connected
     */
    private boolean is_connected(Graph g, int v1, int v2){

        int vertexSize = g.getNumV();
        if (v1<0 || v1>=vertexSize || v2<0 || v2>=vertexSize){
            System.err.println("Betwenn 0-"+vertexSize+" are vertexies");
            return false;
        }

        Queue< Integer > theQueue = new LinkedList< Integer >();

        boolean[] visited = new boolean[vertexSize];

        visited[v1] = true;
        theQueue.offer(v1);

        while (!theQueue.isEmpty()) {

            int current = theQueue.remove();
            /* Examine each vertex, neighbor, adjacent to current. */
            Iterator< Edge > itr = g.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();

                int neighbor = edge.getDest();
                if ( neighbor == v2)
                    return true;

                if (!visited[neighbor]) {
                    // Mark it identified.
                    visited[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
                }
            }
            // Finished visiting current.
        }
        return false;
    }

    private void initialize_map(){
        airports.put("Adana Şakirpaşa Havalimanı",0); //--
        airports.put("Ankara Esenboğa Havalimanı",1); //-
        airports.put("Hatay Havalimanı",2);     //-
        airports.put("Antalya Havalimanı",3); //--
        airports.put("Milas-Bodrum Havalimanı",4);//
        airports.put("Bursa Yenişehir Havalimanı",5); //--
        airports.put("Muğla Dalaman Havalimanı",6); //--
        airports.put("Diyarbakır Havalimanı",7);  //--
        airports.put("Erzurum Havalimanı",8); //--
        airports.put("Eskişehir Anadolu Havalimanı",9); //--
        airports.put("Ordu-Giresun Havalimanı",10); //--
        airports.put("Gaziantep Havalimanı",11); //--
        airports.put("İstanbul Atatürk Havalimanı",12); //--
        airports.put("İstanbul Sabiha Gökçen Havalimanı",13); //-
        airports.put("İzmir Adnan Menderes Havalimanı",14); //
        airports.put("Kayseri Erkilet Havalimanı",15); //--
        airports.put("Konya Havalimanı",16); //--
        airports.put("Kütahya Zafer Havalimanı",17); //--
        airports.put("Malatya Erhaç Havalimanı",18); //-
        airports.put("Nevşehir Kapadokya Havalimanı",19); //-
        airports.put("Samsun Çarşamba Havalimanı",20); //--
        airports.put("Şanlıurfa GAP Havalimanı",21); //--
        airports.put("Trabzon Havalimanı",22); //--
    }
    private void install_airway_network(){
        insert(new Edge(0,16, 340)); // adana-konya
        insert(new Edge(0,2, 200)); // adana- hatay
        insert(new Edge(0,15, 300)); // adana-kayseri
        insert(new Edge(0,3, 550)); //  adana-antalya
        insert(new Edge(0,11, 220)); // adana-antep
        insert(new Edge(1,16, 285)); // ankara-konya
        insert(new Edge(1,15, 300)); // ankara-kayseri
        insert(new Edge(1,17, 290)); // ankara-kütahya
        insert(new Edge(1,9, 250)); // ankara-eskişehir
        insert(new Edge(1,3, 480)); // ankara-antalya
        insert(new Edge(1,20, 350)); // ankara-samsun
        insert(new Edge(1,19, 260)); // ankara-nevşehir
        insert(new Edge(1,12, 420)); // ankara-ist
        insert(new Edge(1,13, 420)); // ankara-ist


        insert(new Edge(21,11, 150)); // urfa-antep
        insert(new Edge(21,7, 180)); // urfa-diyarbakır
        insert(new Edge(7,18, 340)); // diyarbakır-malatya


        insert(new Edge(15,18, 320)); // kayseri-malatya
        insert(new Edge(12,5, 160)); // ist-bursa
        insert(new Edge(13,5, 160)); // ist-bursa

        insert(new Edge(5,17, 320)); // bursa-kütahya
        insert(new Edge(5,14, 330)); // bursa-izmir
        insert(new Edge(14,4, 220)); // izmir-bodrum
        insert(new Edge(4,6, 200)); // bodrum-dalaman
        insert(new Edge(5,9, 190)); // bursa-eskişehir

        insert(new Edge(20,10, 250)); // samsun-giresun
        insert(new Edge(10,22, 190)); // giresun-trabzon


        insert(new Edge(18,8, 400)); // malatya-erzurum
        insert(new Edge(8,22, 260)); // erzurum-trabzon
        insert(new Edge(6,3, 230)); // dalaman-antalya
        insert(new Edge(14,17, 340)); // izmir-kütahya

    }
}
