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
        topo.newComponent("resistor", "res1", 1, 2, 3);
        topo.newComponent("resistor", "res2", 4, 5, 6);
        topo.newComponent("resistor", "res3", 7, 8, 9);
        topo.newComponent("nmos", "m1", 7, 8, 9);
        topo.newComponent("nmos", "m2", 10, 12, 13);

        topo.connect("m1", "grain", "n1");
        topo.connect("res1", "t2", "n1");
        topo.queryDevicesWithNetlistNode("n2");

        // TODO read and write json files locally handle delete topologies objects
        // What is gradle and maven
        // API documentation
        // class level documentaion
        // Automatic testing on API level and and class level
        // code analysis tool

    }
}
