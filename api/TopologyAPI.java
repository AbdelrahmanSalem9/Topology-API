package api;

import java.util.HashMap;
import java.util.Map;

public class TopologyAPI {

    private Map<String, Topology> topologies;
    private JsonHandler jsonHandler;

    public TopologyAPI() {
        topologies = new HashMap<>();
        jsonHandler = new JsonHandler();
    }

    public static void main(String[] args) {

        // Topology topo = new Topology("top1");
        // topo.newComponent("resistor", "res1", 1, 2, 3);
        // topo.newComponent("resistor", "res2", 4, 5, 6);
        // topo.newComponent("resistor", "res3", 7, 8, 9);
        // topo.newComponent("nmos", "m1", 7, 8, 9);
        // topo.newComponent("nmos", "m2", 10, 12, 13);

        // topo.connect("m1", "grain", "n1");
        // topo.connect("res1", "t2", "n1");
        // topo.queryDevicesWithNetlistNode("n1");
        // topo.queryTopology();

        JsonHandler jsonHandler = new JsonHandler();
        // Topology topology = jsonHandler.readJsonFile("E:/College/Master Micro/Stage
        // 1/Task 2/api/topology.json");
        // topology.queryTopology();
        // topology.queryDevicesWithNetlistNode("n1");

        Topology t1 = new Topology("topo9");
        t1.newComponent("resistor", "r9", 10, 8, 12);
        t1.newComponent("resistor", "r99", 11, 19, 88);
        t1.connect("r9", "t1", "n1");
        t1.connect("r9", "t2", "vcc");

        t1.connect("r99", "t1", "n2");
        t1.connect("r99", "t2", "gnd");

        t1.queryTopologyDevices();
        t1.queryDevicesWithNetlistNode("n1");

        jsonHandler.writeTopo(t1);

        // What is gradle and maven
        // API documentation
        // class level documentaion
        // Automatic testing on API level and and class level
        // code analysis tool

    }

    public void deleteTopology(String topoId) {

        if (topologies.containsKey(topoId)) {
            topologies.remove(topoId);
        } else {
            System.out.println("No such Topology");
        }

    }

    public void readJSON(String fileName) {

        Topology topo = jsonHandler.readJsonFile(fileName);
        topologies.put(topo.getTopologyId(), topo);

    }

    public void writeJSON(String topoId) {

        if (topologies.containsKey(topoId)) {
            jsonHandler.writeTopo(topologies.get(topoId));
        }
    }

    public void writeJSON(Topology topo) {
        addTopology(topo);
        jsonHandler.writeTopo(topo);
    }

    public void addTopology(Topology topo) {
        topologies.put(topo.getTopologyId(), topo);
    }

    public void queryDevices(String topoId) {
        if (topologies.containsKey(topoId)) {
            topologies.get(topoId).queryTopologyDevices();
        } else {
            System.out.println("No such Topology");

        }
    }

    public void queryToplogies() {

        for (Map.Entry<String, Topology> entry : topologies.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public void queryDevicesWithNetlistNode(Topology topo, String node) {
        topo.queryDevicesWithNetlistNode(node);
    }
}
