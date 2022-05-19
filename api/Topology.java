package api;

import java.util.ArrayList;
import components.*;

public class Topology {

    private String topologyId;
    private ArrayList<Device> devices;

    public Topology(String topologyId) {
        this.topologyId = topologyId;
        devices = new ArrayList<Device>();
    }

    public void newComponent(String deviceType, String deviceId, float defaultValue, float minValue, float maxValue) {

        if (deviceType.contains("resistor"))
            devices.add(new Resistor(deviceId, defaultValue, minValue, maxValue));

        else if (deviceType.contains("nmos"))
            devices.add(new NMOS(deviceId, defaultValue, minValue, maxValue));
    }

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

    public void connect(String componentId, String terminal, String node) {

        for (Device d : devices) {

            if (d.getComponentId() == componentId) {
                d.connectNetListNode(terminal, node);
                return;
            }
        }
        System.out.println("Component Not Found");

    }

    public void queryDevicesWithNetlistNode(String node) {

        System.out.println("Connected Component to " + node);
        for (Device d : devices) {
            if (d.isConnected(node)) {
                System.out.println(d.getComponentId());
            }

        }
    }

}