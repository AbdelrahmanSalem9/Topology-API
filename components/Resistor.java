package components;

import java.util.Map;

public class Resistor extends Device {

    private String resistorId;
    private Specs resistorSpecs;

    public Resistor(String resistorId, float defaultValue, float minValue, float maxValue) {
        super("resistor");
        this.resistorId = resistorId;
        resistorSpecs = new Specs("Resistance");

        // adding specs
        resistorSpecs.addSpecs("default", defaultValue);
        resistorSpecs.addSpecs("min", minValue);
        resistorSpecs.addSpecs("max", maxValue);

    }

    @Override
    public void query() {
        System.out.println("Component: " + super.deviceType);
        System.out.println("id: " + resistorId);
        resistorSpecs.displaySpecs();

    }

    @Override
    public String getComponentId() {
        return resistorId;
    }

    @Override
    public String getSpecsType() {
        return "resistance";
    }

    @Override
    public Map<String, Float> getSpecs() {
        return resistorSpecs.getSpecs();
    }

}
