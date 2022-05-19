package api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import components.Device;

public class JsonHandler {

    private Topology topo;

    public Topology readJsonFile(String path) {

        JSONParser jsonParser = new JSONParser();
        try (FileReader fileReader = new FileReader(path)) {

            Object object = jsonParser.parse(fileReader);
            JSONObject topoJsonObj = (JSONObject) object;
            String topId = (String) topoJsonObj.get("id");
            JSONArray compJsonArray = (JSONArray) topoJsonObj.get("components");
            topo = new Topology(topId);

            for (int i = 0; i < compJsonArray.size(); i++) {
                JSONObject compJsonObject = (JSONObject) compJsonArray.get(i);
                String compType = (String) compJsonObject.get("type");
                if (compType.equals("resistor")) {
                    readResistor(compJsonObject);

                } else if (compType.equals("nmos")) {
                    readNMOS(compJsonObject);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO error");
        } catch (ParseException e) {
            System.out.println("Parsing Error");
        }

        return topo;
    }

    private void readResistor(JSONObject compJsonObject) {
        JSONObject resistanceJsonObject = (JSONObject) compJsonObject.get("resistance");
        String compId = (String) compJsonObject.get("id");
        topo.newComponent("resistor", compId,
                Float.parseFloat(resistanceJsonObject.get("default").toString()),
                Float.parseFloat(resistanceJsonObject.get("min").toString()),
                Float.parseFloat(resistanceJsonObject.get("max").toString()));
        JSONObject netlJsonObject = (JSONObject) compJsonObject.get("netlist");
        topo.connect(compId, "t1", netlJsonObject.get("t1").toString());
        topo.connect(compId, "t2", netlJsonObject.get("t2").toString());

    }

    private void readNMOS(JSONObject compJsonObject) {
        JSONObject nmosJsonObject = (JSONObject) compJsonObject.get("m(l)");
        String compId = (String) compJsonObject.get("id");
        topo.newComponent("nmos", compId,
                Float.parseFloat(nmosJsonObject.get("default").toString()),
                Float.parseFloat(nmosJsonObject.get("min").toString()),
                Float.parseFloat(nmosJsonObject.get("max").toString()));
        JSONObject netlJsonObject = (JSONObject) compJsonObject.get("netlist");
        topo.connect(compId, "drain", netlJsonObject.get("drain").toString());
        topo.connect(compId, "gate", netlJsonObject.get("gate").toString());
        topo.connect(compId, "source", netlJsonObject.get("source").toString());
    }

    public void writeTopo(Topology topology) {

        JSONObject topJsonObject = new JSONObject();
        JSONArray componentsArrayJson = new JSONArray();
        ArrayList<Device> componentsArray = topology.getDevices();
        topJsonObject.put("id", topology.getTopologyId());
        for (int i = 0; i < componentsArray.size(); i++) {
            JSONObject compJsonObject = new JSONObject();
            compJsonObject.put("type", componentsArray.get(i).getDeviceType());
            compJsonObject.put("id", componentsArray.get(i).getComponentId());
            if (componentsArray.get(i).getDeviceType().equals("resistor")) {
                JSONObject resistanceJsonObject = new JSONObject();
                Map<String, Float> specs = componentsArray.get(i).getSpecs();
                resistanceJsonObject.put("default", specs.get("default"));
                resistanceJsonObject.put("min", specs.get("min"));
                resistanceJsonObject.put("max", specs.get("max"));
                compJsonObject.put("resistance", resistanceJsonObject);
                Map<String, String> netlist = componentsArray.get(i).getNetlist();
                JSONObject netlistJsonObject = new JSONObject();
                netlistJsonObject.put("t1", netlist.get("t1"));
                netlistJsonObject.put("t2", netlist.get("t2"));
                compJsonObject.put("netlist", netlistJsonObject);

            } else if (componentsArray.get(i).getDeviceType().equals("nmos")) {
                JSONObject nmosJsonObject = new JSONObject();
                Map<String, Float> specs = componentsArray.get(i).getSpecs();
                nmosJsonObject.put("default", specs.get("default"));
                nmosJsonObject.put("min", specs.get("min"));
                nmosJsonObject.put("max", specs.get("max"));
                compJsonObject.put("m(l)", nmosJsonObject);
                Map<String, String> netlist = componentsArray.get(i).getNetlist();
                JSONObject netlistJsonObject = new JSONObject();
                netlistJsonObject.put("drain", netlist.get("drain"));
                netlistJsonObject.put("gate", netlist.get("gate"));
                netlistJsonObject.put("source", netlist.get("source"));
                compJsonObject.put("netlist", netlistJsonObject);

            }
            componentsArrayJson.add(compJsonObject);

        }
        topJsonObject.put("components", componentsArrayJson);
        try (FileWriter fileWriter = new FileWriter("./api/json files/" + topology.getTopologyId() + ".json")) {
            fileWriter.write(topJsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Can't Write the Topology");

        }
    }

}
