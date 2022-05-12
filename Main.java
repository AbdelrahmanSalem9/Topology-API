public class Main {

    public static void main(String[] args) {

        // Device[] devices = new Device[3];

        // devices[0] = new Resistor("res1", 3, 2, 5);
        // devices[1] = new NMOS("nmos1", 10, 8, 12);
        // devices[2] = new Resistor("res2", 3, 2, 5);

        // for (Device d : devices) {
        // d.query();
        // System.out.println("----------------------------------------------------");
        // }
        Topology topo = new Topology("top1");
        topo.newComponent("res1", 1, 2, 3);
        topo.newComponent("res2", 4, 5, 6);
        topo.newComponent("res3", 7, 8, 9);
        topo.newComponent("nmos1", 7, 8, 9);
        topo.newComponent("nmos2", 10, 12, 13);

        topo.queryTopology();

    }
}