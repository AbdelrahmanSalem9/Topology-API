package components;

import java.util.HashMap;
import java.util.Map;

public class Specs {

    private String specName;
    private Map<String, Float> specs;

    Specs(String specName) {

        this.specName = specName;
        specs = new HashMap<>();

    }

    protected void addSpecs(String key, float value) {
        specs.put(key, value);
    }

    void displaySpecs() {
        System.out.println(specName);
        for (Map.Entry<String, Float> entry : specs.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    Map<String, Float> getSpecs() {
        return specs;
    }

}
