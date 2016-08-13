package buffer.policy;

import buffer.BufferFrame;

import java.util.List;
import java.util.Random;

/**
 * Random Replacement Policy
 * Randomly selects a page from the pool for replacement.
 * 
 * This demonstrates how a class should implement the ReplacementPolicy 
 * interface (@see ReplacementPolicy)
 */
public class RandomReplacementPolicy implements ReplacementPolicy {

	/*
	 *  We need to generate random numbers, so this class keeps a 
	 *  Random class member (@see java.util.Random)
	 */
    private Random mGenerator;

    /*
     *  The constructor sets up the policy object. In this case we just need to
     *  create the random number generator to be used later.
     */
    public RandomReplacementPolicy() {
        mGenerator = new Random();
    }

    /**
     * Return the name of the policy. Trivial.
     */
    @Override
    public String getName() {
        return "RANDOM POLICY";
    }

    /**
     * This implementation just picks a frame at random, by generating a random 
     * integer number between 0 and the highest frame number, and returning the
     * corresponding frame.
     */
    @Override
    public BufferFrame choose(List<BufferFrame> pool) {
        assert pool.size() > 0 : "Expects a pool of at least size 1";
        // Pick a random index, try and get that frame
        int randomIndex = mGenerator.nextInt(pool.size());
        return pool.get(randomIndex);
    }

    /**
     * This policy doesn't care how many times a page is used, so this method
     * doesn't do anything.
     */
    @Override
    public void notify(List<BufferFrame> pool, BufferFrame frame) {}
}
