package core.physics;

/**
 * Whenever an agent harvest information from physics a list of PhyInfo object
 * ill be returned that apply to that agent.
 *
 * @author Artur Krzynowek
 */
public class PhyInfo {

    public Spatial spatial;
    public float distance;

    public PhyInfo(Spatial spatial, float distance) {
        this.spatial = spatial;
        this.distance = distance;
    }

    public PhyInfo() {
    }

    @Override
    public String toString() {
        return "Spatial: " + spatial.toString() + " distance: " + distance;
    }
}
