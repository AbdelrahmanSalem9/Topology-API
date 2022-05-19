package components;

import java.util.HashMap;
import java.util.Map;

public abstract class Device implements DeviceQuery, JsonQuery {

    protected String deviceType;
    private Map<String, String> netList;

    protected Device(String deviceType) {
        this.deviceType = deviceType;
        netList = new HashMap<String, String>();

    }

    public void connectNetListNode(String terminal, String node) {
        netList.put(terminal, node);
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }

    @Override
    public boolean isConnected(String node) {
        return netList.containsValue(node);

    }

    @Override
    public Map<String, String> getNetlist() {
        return netList;
    }
}
