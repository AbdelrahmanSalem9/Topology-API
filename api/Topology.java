package api;

import java.util.ArrayList;
import components.*;

/**
 * This is a model to hold the topology information from id and all the devices
 * beholds
 */
public class Topology {

    private String topologyId;
    private ArrayList<Device> devices;

    /**
     * Topology constructor
     * 
     * @param topologyId
     */
    public Topology(String topologyId) {
        this.topologyId = topologyId;
        devices = new ArrayList<Device>();
    }

    /**
     * 
     * @param deviceType   used for component concrete class creating and add it to
     *                     the devices list
     * @param deviceId     no same component id within same topology
     * @param defaultValue is float number
     * @param minValue     is float number
     * @param maxValue     is float number
     */
    public void newComponent(String deviceType, String deviceId, float defaultValue, float minValue, float maxValue) {

        if (deviceType.contains("resistor"))
            devices.add(new Resistor(deviceId, defaultValue, minValue, maxValue));

        else if (deviceType.contains("nmos"))
            devices.add(new NMOS(deviceId, defaultValue, minValue, maxValue));
    }

    /**
     * to display all devices in topology
     */
    public void queryTopologyDevices() {

        if (devices.isEmpty()) {
            System.out.println("No Devices Found");
        } else {
            for (Device d : devices) {
                d.query();
                System.out.println("----------------------------------------------------");

            }
        }
    }

    public String getTopologyId() {
        return topologyId;
    }

    ArrayList<Device> getDevices() {
        return devices;
    }

    /**
     * connect one of the device terminal to one node
     * 
     * @param componentId select one of the deivces in the topology
     * @param terminal
     * @param node
     */
    public void connect(String componentId, String terminal, String node) {

        for (Device d : devices) {

            if (d.getComponentId() == componentId) {
                d.connectNetListNode(terminal, node);
                return;
            }
        }
        System.out.println("Component Not Found");

    }

    /**
     * Search for all devices that are connected to that node in the topology
     * network
     * 
     * @param node
     */
    public void queryDevicesWithNetlistNode(String node) {

        System.out.println("Connected Component to " + node);
        for (Device d : devices) {
            if (d.isConnected(node)) {
                System.out.println(d.getComponentId());
            }

        }
    }

}