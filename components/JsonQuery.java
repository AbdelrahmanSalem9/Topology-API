package components;

import java.util.Map;

public interface JsonQuery {

    String getDeviceType();

    String getSpecsType();

    Map<String, Float> getSpecs();

    Map<String, String> getNetlist();
}
