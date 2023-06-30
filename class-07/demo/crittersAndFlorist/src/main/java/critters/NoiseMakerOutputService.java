package critters;

/**
 * This is a service that handles output for NoiseMakers
 */
public class NoiseMakerOutputService {

    // the logger is used by the service to output
    private Logger logger;

    /**
     * The constructor.  It requires a logger to work!
     * <p>
     * We've implemented two of them.  ConsoleLogger and TestLogger
     *
     * @param logger a Logger implementation
     */
    public NoiseMakerOutputService(Logger logger) {
        this.logger = logger;
    }

    /**
     * Will output the noise to the logger if it is "Loud" (All UPPER CASE)
     *
     * @param noiseMaker the NoiseMaker
     */
    public void output(NoiseMaker noiseMaker) {
        // get the noises
        String noise = noiseMaker.makeNoise();
        String compareNoise = noise.toUpperCase();

        // if the UPPERCASE noise is equal to noise
        if (compareNoise.equals(noise)) {
            // if we do this, we can't test our if statement
//            System.out.println(noise);

            // so instead, we have abstracted the logger.
            this.logger.log(noise);
        }
    }
}
