import java.util.ArrayList;

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

    public void queryTopology() {

        if (devices.isEmpty()) {
            System.out.println("No Devices Found");
        } else {
            System.out.println("Topology: " + topologyId);
            for (Device d : devices) {
                d.query();
                System.out.println("----------------------------------------------------");

            }
        }
    }

    // TODO define the component terminals internally then check if terminal exist
    public void connect(String componentId, String terminal, String node) {

        for (Device d : devices) {

            if (d.getComponentId() == componentId) {
                d.connectNetListNode(terminal, node);
                return;
            }
        }
        System.out.println("Component Not Found");

    }

    // TODO return devices or void return
    public void queryDevicesWithNetlistNode(String node) {

        System.out.println("Connected Component to " + node);
        for (Device d : devices) {
            if (d.isConnected(node)) {
                System.out.println(d.getComponentId());
            }

        }
    }

}