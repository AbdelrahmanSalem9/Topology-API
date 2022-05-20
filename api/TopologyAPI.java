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
     * 
     * @param topoId
     * @return true if delete operation done successfully
     */
    public boolean deleteTopology(String topoId) {

        if (topologies.containsKey(topoId)) {
            topologies.remove(topoId);
            return true;
        } else {
            System.out.println("No such Topology");
            return false;
        }

    }

    /**
     * identifiy which json file that holding the topology network to read
     * 
     * @param fileName
     * @return true if reading topology file successfully
     */
    public boolean readJSON(String fileName) {

        Topology topo = jsonHandler.readJsonFile(fileName);
        if (topo != null) {
            topologies.put(topo.getTopologyId(), topo);
            return true;

        }
        return false;
    }

    /**
     * write the topology to json file and store it locally in pre-defined path
     * "./api/json files/"
     * 
     * @param topoId
     * @return true if write opeation done successfully
     */
    public boolean writeJSON(String topoId) {

        if (topologies.containsKey(topoId)) {
            jsonHandler.writeTopo(topologies.get(topoId));
            return true;
        }
        return false;
    }

    /**
     * method overload for writing newly created topology object to json file
     * 
     * @param topo
     * @return true if write operation done successfully
     */
    public boolean writeJSON(Topology topo) {
        addTopology(topo);
        jsonHandler.writeTopo(topo);
        return true;
    }

    /**
     * add topology to the system
     * 
     * @param topo
     * @return true of adding new topology to the memory done successfully
     */
    public boolean addTopology(Topology topo) {
        try {
            topologies.put(topo.getTopologyId(), topo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Iterate over all devices connected to that specific topology id
     * 
     * @param topoId
     * @return true reteriving all devices successfully
     */
    public boolean queryDevices(String topoId) {
        if (topologies.containsKey(topoId)) {
            topologies.get(topoId).queryTopologyDevices();
            return true;
        } else {
            System.out.println("No such Topology");

        }
        return false;
    }

    /**
     * Query all the currenly stored topologies id in the memory
     * 
     * @return true when reteriving all the topologies from memory successfully
     */
    public boolean queryToplogies() {

        if (topologies.size() == 0) {
            return false;
        }

        for (Map.Entry<String, Topology> entry : topologies.entrySet()) {
            System.out.println(entry.getKey());
        }
        return true;
    }

    /**
     * Speicify topology with node and retrive all the conneceted components to that
     * node
     * 
     * @param topoId
     * @param node
     * @return true if operation done successfully
     */
    public boolean queryDevicesWithNetlistNode(String topoId, String node) {
        if (topologies.containsKey(topoId)) {
            topologies.get(topoId).queryDevicesWithNetlistNode(node);
            return true;
        } else {
            System.out.println("No such Topology");

        }
        return false;
    }

    /**
     * 
     * @return all Topologies currently in the system memory
     */
    public Map<String, Topology> getToplogies() {
        return topologies;
    }

}
