package group244.shervashidze;

import java.util.Random;

/**
 * Class Computer with Os, can be linked with Network
 */
public class Computer {

    /* OS in thiscomputer*/
    private OS osType;
    /* name of the computer*/
    private String name;
    /*network where computer exists(if)*/
    private Network network;
    /*is computer infected*/
    private boolean isInfected = false;
    /*is computer in danger of infection*/
    private boolean isInfectionStarted = false;

    public Computer(String name, OS newOsType) {
        this.name = name;
        osType = newOsType;
    }

    /**
     *
     * @param network to link computer with
     */
    public void linkWithNetwork(Network network) {
        this.network = network;
    }

    /**
     *
     * @return true if computer infected
     */
    public boolean isInfected() {
        return isInfected;
    }

    /**
     *
     * @return name of the computer
     */
    public String getName() {
        return name;
    }

    /**
     * informs computer that it is in danger of infection
     */
    public void inDangerOfInfection() {
        if (!isInfectionStarted) {
            isInfectionStarted = true;
        }
    }

    /**
     * checks if computer was infected
     */
    public void turn() {
        if (isInfected) {
            return;
        }

        if (isInfectionStarted) {
            Random random = new Random();
            if (random.nextFloat() < osType.getProbability()) {
                isInfected = true;
                notifyConnected();
            }
        }
    }

    /**
     * sets computer as infected
     */
    public void setInfected() {
        isInfected = true;
        notifyConnected();
    }

    /**
     * notifying linked computers in network that this computer was infected
     */
    private void notifyConnected() {
        if (network == null) {
            return;
        }

        network.wasInfected(this);
    }
}
