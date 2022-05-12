public abstract class Device implements DeviceQuery {

    String deviceName;
    // private Map<String, String> netList;

    protected Device(String deviceName) {
        this.deviceName = deviceName;

    }


}
