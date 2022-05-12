public class Specs {

    private String specName;

    // todo could be different type of specs
    private float defaultValue;
    private float minValue;
    private float maxValue;

    public Specs(String specName, float defaultValue, float minValue, float maxValue) {

        this.specName = specName;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    void getSpecs() {
        System.out.println(
                specName + ": { " + "default: " + defaultValue + ", min: " + minValue + ", max: " + maxValue + " }");
    }

}
