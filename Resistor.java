public class Resistor extends Device {

    private String resistorId;
    private Specs resistorSpecs;

    public Resistor(String resistorId, float defaultValue, float minValue, float maxValue) {
        super("resistor");
        this.resistorId = resistorId;
        resistorSpecs = new Specs("Resistance", defaultValue, minValue, maxValue);
    }

    @Override
    public void query() {
        System.out.println("Component: " + super.deviceName);
        System.out.println("id: " + resistorId);
        resistorSpecs.getSpecs();

    }
}
