package group244.shervashidze;

import java.util.*;

/**
 * class network - connects computers.
 */
public class Network {

    /* computers with connections */
    private Map<Computer, List<Computer>> computers;
    /* computers to notify this turn */
    private List<Computer> toNotify;

    public Network() {
        computers = new HashMap<>();
        toNotify = new LinkedList<>();
    }

    /**
     *
     * @param computer to add in network
     */
    public void addComputer(Computer computer) {
        computer.linkWithNetwork(this);
        computers.put(computer, new LinkedList<>());
    }

    /**
     *
     * @param first - computer to link with second
     * @param second - computer to link with first
     * @throws NoSuchComputerException when one of the computers is not in the network
     */
    public void addConnection(Computer first, Computer second) throws NoSuchComputerException {
        if (!computers.containsKey(first) & !computers.containsKey(second)) {
            throw new NoSuchComputerException();
        }

        computers.get(first).add(second);
        computers.get(second).add(first);

    }

    /**
     *
     * @return number of infected computers in network
     */
    public int numberOfInfected() {
        return (int) computers.keySet().stream().filter(Computer::isInfected).count();
    }

    /**
     * checks if new computers were infected, notify connected if need to
     */
    public void turn() {
        for (int i = 0; i< computers.size(); i++) {
            computers.keySet().forEach(Computer::turn);
        }
        for (Computer computer : toNotify) {
            computer.inDangerOfInfection();
        }

        toNotify.clear();
    }

    /**notifies linked computers.
     *
     * @param computer which was infected
     */
    public void wasInfected(Computer computer) {
        toNotify.addAll(computers.get(computer));
    }

    /**All computers and their infection status.
     *
     * @return string with status
     */
    public String showStatus() {
        List strings = new LinkedList();
        for (Computer computer : computers.keySet()) {
            StringBuilder status = new StringBuilder();
            status.append(computer.getName());
            if (computer.isInfected()) {
                status.append(": infected");
            } else {
                status.append(": not infected");
            }

            status.append("\n");
            strings.add(status.toString());
        }

        Collections.sort(strings);
        StringBuilder answer = new StringBuilder();
        for (Object string : strings) {
            answer.append(string);
        }

        return answer.toString();
    }
}
