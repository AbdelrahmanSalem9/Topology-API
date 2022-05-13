public interface DeviceQuery {

    void query();

    void connectNetListNode(String terminal, String node);

    boolean isConnected(String node);

    String getComponentId();
}
