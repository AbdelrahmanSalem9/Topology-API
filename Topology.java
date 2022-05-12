import java.util.ArrayList;

public class Topology {

    String topologyId;
    ArrayList<Device> devices;

    public Topology(String topologyId) {
        this.topologyId = topologyId;
        devices = new ArrayList<Device>();
    }

    public void newComponent(String deviceName, float defaultValue, float minValue, float maxValue) {

        if (deviceName.contains("res"))
            devices.add(new Resistor(deviceName, defaultValue, minValue, maxValue));

        else if (deviceName.contains("nmos"))
            devices.add(new NMOS(deviceName, defaultValue, minValue, maxValue));
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

}