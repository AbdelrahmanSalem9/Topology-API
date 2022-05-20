package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import api.Topology;
import api.TopologyAPI;

public class TestQueries {

    @Test
    public void deleteQuery() {

        TopologyAPI topologyAPI = new TopologyAPI();
        Topology topo1 = new Topology("topo1");
        assertEquals(true, topologyAPI.addTopology(topo1));
        assertEquals(true, topologyAPI.deleteTopology("topo1"));
        assertEquals(0, topologyAPI.getToplogies().size());
    }

    @Test
    public void queryToplogies() {
        TopologyAPI topologyAPI = new TopologyAPI();
        Topology topo = new Topology("topo1");
        assertEquals(true, topologyAPI.addTopology(topo));
        assertEquals(true, topologyAPI.queryToplogies());
    }

    @Test
    public void queryDevices() {
        TopologyAPI topologyAPI = new TopologyAPI();
        Topology topo = new Topology("topo1");
        topo.newComponent("resistor", "res1", 2, 1, 3);
        topo.newComponent("nmos", "m1 ", 8, 5, 12);
        assertEquals(true, topologyAPI.addTopology(topo));
        assertEquals(true, topologyAPI.queryDevices("topo1"));
    }

    @Test
    public void readJsonFile() {
        TopologyAPI topologyAPI = new TopologyAPI();
        assertEquals(true, topologyAPI.readJSON("./bin/api/json files/topology.json"));
    }

    @Test
    public void writeJsonFile() {
        Topology topology = new Topology("topo7");
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.writeJSON(topology);
    }
}
