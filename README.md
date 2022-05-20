<h1 align="center" id="title">Topology API</h1>

# Description
using OOP principles to design API java program to handle some requirements for the system, each topology consider as network of some electrical device
connected together and each topology have id to identify when performing topology queires

# System Requirments
1. Read and write topologies through json files parsing.
2. Store multiple topologies in the memory system and perform some queries.
3. Delete stored topologies.
4. Query about which devices are in a given topology.
5. Query about which devices are connected to a given netlist node in a given topology.

# Design Approach
I have used the OOP principle with factory design pattern to achieve the system requirments and tried to make it simple as possible

## Devices
Each electrical component encapsulated and has it's own type and specs, the system demo on just two types 
- Resistors
- NMOS

And each concerte class inhereit device class which implements some interfaces to deal with the higher layer from the system

## Topology
Then the upper layer is the topology class where each topology has specific id and some devices that deal with it abstractly not at specific electrical component, and consider more abstract layer to the concrete classes

## Json Handler
Class to deal with the read and write json files locally from parsing and constructing the topology object

## Topology API
This is the class model where the client layer deal with, basiclly contain object of json handler and some topology map represents the currently stored topopolgies in the system
and can perform some queries on the topologies

## Testing
Some simple testing methods using Juint testing on API level

# Additional notes
- During my design I have assumed that each .json file contain only one topolgy with containing components and each components has netlist on it's own
- I have used jdk-13.0.2 and import .json jar library externally
- The system is open for extension for each level data, controller, and the api level
