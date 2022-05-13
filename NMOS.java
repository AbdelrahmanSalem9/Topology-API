public class NMOS extends Device {

    private String nmosId;
    private Specs nmosSpecs;

    public NMOS(String nmosId, float defaultValue, float minValue, float maxValue) {
        super("nmos");
        this.nmosId = nmosId;
        nmosSpecs = new Specs("m(l)", defaultValue, minValue, maxValue);
    }

    @Override
    public void query() {
        System.out.println("Component: " + super.deviceType);
        System.out.println("id: " + nmosId);
        nmosSpecs.getSpecs();

    }

    @Override
    public String getComponentId() {
        return nmosId;
    }

}