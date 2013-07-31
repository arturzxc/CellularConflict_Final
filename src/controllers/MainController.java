
package controllers;

/**
 *
 * @author Artur Krzynowek
 */
public abstract class MainController {

    private long beforeTime = 1;
    private long timeDiff = 1;
    private long sleepTime = 1;

    

    /**
     *Method used by the view to render itself
     */
    protected abstract void viewRender();

    /**
     * Method used by the model to update itself
     */
    protected abstract void modelUpdate();

    /**
     * If there are any extras to update they can be added here.
     */
    protected abstract void mainLoop();

    /**
     * Gets frames per second
     */
    public double getFPS() {
        long st = sleepTime;
        if (st == 0) {
            st = Settings.getInstance().getDelay();
        }
        return 1000 / st;
    }

    /**
     * Once this method is invoked the whole program starts running
     */
    public int init() {
        while (Settings.getInstance().isRunning()) {

            beforeTime = System.nanoTime();
            modelUpdate();
            viewRender();
            mainLoop();
            timeDiff = (System.nanoTime() - beforeTime) / 1000000;
            sleepTime = timeDiff;

            if (sleepTime < Settings.getInstance().getDelay()) {
                sleepTime = Settings.getInstance().getDelay();
            }
            
            try {
                Thread.sleep(sleepTime); //sleep by allotted time (attempts to keep this loop to about 10ms)
            } catch (InterruptedException ex) {
            }
            beforeTime = System.nanoTime();
            
        }
        return 0;
    }
}
