import api.Topology;
import api.TopologyAPI;

public class Main {

    public static void main(String[] args) {

        TopologyAPI topologyAPI = new TopologyAPI();

        Topology topo = new Topology("topo1");
        topo.newComponent("resistor", "res1", 1, 2, 3);
        topo.newComponent("resistor", "res2", 4, 5, 6);
        topo.newComponent("resistor", "res3", 7, 8, 9);
        topo.newComponent("nmos", "m1", 7, 8, 9);
        topo.newComponent("nmos", "m2", 10, 12, 13);

        topo.connect("m1", "gate", "n1");
        topo.connect("res1", "t2", "n1");

        topologyAPI.readJSON("./api/json files/topo9.json");
        topologyAPI.queryToplogies();
        topologyAPI.queryDevices("topo9");
        topologyAPI.addTopology(topo);
        topologyAPI.queryToplogies();
        topologyAPI.queryDevices("topo1");
        topologyAPI.writeJSON("topo1");
    }

}
