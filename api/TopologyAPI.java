package api;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles all the client queries from
 * 1- read/write topology from/to json file
 * 2- query all the toplogies stored currently in the system memory
 * 3- delete specific topology from the memory
 * 4- query all the devices in specific toplogy
 * 5- query all the devices connected to specific node inside the toplogy
 * network
 */
public class TopologyAPI {

    private Map<String, Topology> topologies; // all topologies stored currently in the memory
    private JsonHandler jsonHandler; // class object that handles all the read/write operations

    public TopologyAPI() {
        topologies = new HashMap<>();
        jsonHandler = new JsonHandler();
    }

    /**
     * delete specific topology from the system and discard it from the memroy
     * 
     * @param topoId
     */
    public void deleteTopology(String topoId) {

        if (topologies.containsKey(topoId)) {
            topologies.remove(topoId);
        } else {
            System.out.println("No such Topology");
        }

    }

    /**
     * identifiy which json file that holding the topology network to read
     * 
     * @param fileName
     */
    public void readJSON(String fileName) {

        Topology topo = jsonHandler.readJsonFile(fileName);
        topologies.put(topo.getTopologyId(), topo);

    }

    /**
     * write the topology to json file and store it locally in pre-defined path
     * "./api/json files/"
     * 
     * @param topoId
     */
    public void writeJSON(String topoId) {

        if (topologies.containsKey(topoId)) {
            jsonHandler.writeTopo(topologies.get(topoId));
        }
    }

    /**
     * method overload for writing newly created topology object to json file
     * 
     * @param topo
     */
    public void writeJSON(Topology topo) {
        addTopology(topo);
        jsonHandler.writeTopo(topo);
    }

    /**
     * add topology to the system
     * 
     * @param topo
     */
    public void addTopology(Topology topo) {
        topologies.put(topo.getTopologyId(), topo);
    }

    /**
     * Iterate over all devices connected to that specific topology id
     * 
     * @param topoId
     */
    public void queryDevices(String topoId) {
        if (topologies.containsKey(topoId)) {
            topologies.get(topoId).queryTopologyDevices();
        } else {
            System.out.println("No such Topology");

        }
    }

    /**
     * Query all the currenly stored topologies id in the memory
     */
    public void queryToplogies() {

        for (Map.Entry<String, Topology> entry : topologies.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    /**
     * Speicify topology with node and retrive all the conneceted components to that
     * node
     * 
     * @param topoId
     * @param node
     */
    public void queryDevicesWithNetlistNode(String topoId, String node) {
        if (topologies.containsKey(topoId)) {
            topologies.get(topoId).queryDevicesWithNetlistNode(node);
            ;
        } else {
            System.out.println("No such Topology");

        }
    }
}
