package components;

import java.util.Map;

public class NMOS extends Device {

    private String nmosId;
    private Specs nmosSpecs;

    public NMOS(String nmosId, float defaultValue, float minValue, float maxValue) {
        super("nmos");
        this.nmosId = nmosId;
        nmosSpecs = new Specs("m(l)");

        nmosSpecs.addSpecs("default", defaultValue);
        nmosSpecs.addSpecs("min", minValue);
        nmosSpecs.addSpecs("max", maxValue);
    }

    @Override
    public void query() {
        System.out.println("Component: " + super.deviceType);
        System.out.println("id: " + nmosId);
        nmosSpecs.displaySpecs();

    }

    @Override
    public String getComponentId() {
        return nmosId;
    }

    @Override
    public String getSpecsType() {
        return "m(l)";
    }

    @Override
    public Map<String, Float> getSpecs() {
        return nmosSpecs.getSpecs();
    }

}