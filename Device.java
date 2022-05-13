import java.util.HashMap;
import java.util.Map;

public abstract class Device implements DeviceQuery {

    String deviceType;
    Map<String, String> netList;

    protected Device(String deviceType) {
        this.deviceType = deviceType;
        netList = new HashMap<String, String>();

    }

    public void connectNetListNode(String terminal, String node) {
        netList.put(terminal, node);
    }

    @Override
    public boolean isConnected(String node) {
        return netList.containsValue(node);

    }
}
