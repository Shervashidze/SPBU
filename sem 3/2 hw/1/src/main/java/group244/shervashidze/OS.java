package group244.shervashidze;

public class OS {
    /* name of the OS*/
    private String name;
    /* how easy to infect*/
    private double infectionProbability;

    /**
     *
     * @param newName - name of the OS
     * @param probability of the infection
     */
    public OS(String newName, double probability) {
        name = newName;
        infectionProbability = probability;
    }

    /**
     *
     * @return name of the OS
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return probability of the infection
     */
    public double getProbability() {
        return infectionProbability;
    }
}
