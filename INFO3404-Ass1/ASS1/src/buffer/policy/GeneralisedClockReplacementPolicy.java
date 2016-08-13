package buffer.policy;

import java.util.LinkedList;
import java.util.List;

import buffer.BufferFrame;

/**
 * GLOCK Replacement Policy
 */
public class GeneralisedClockReplacementPolicy implements ReplacementPolicy {

	private LinkedList<BufferFrame> gClock;
	private int index = 0;
	/**
	 * maximum value you should set the clock value (i.e. no single BufferFrame 
	 * should surpass that limit value).
	 */
    private final int limit;

    /**
     * Create new GCLOCK policy object 
     * @param limit Maximum allowed value for clock value.
     */
    public GeneralisedClockReplacementPolicy(int limit) {
        this.limit = limit;
    }

    @Override
    public String getName() {
        return "GCLOCK Replacement";
    }

    @Override
    public BufferFrame choose(List<BufferFrame> pool) {
        assert pool.size() > 0 : "Expects a pool of at least size 1";
    	if(gClock == null)
    	{
    		gClock = new LinkedList<BufferFrame>(pool);
    	}
    	
    	while(true)
    	{    		
    		if(gClock.get(index).clockCount() > 0)
    		{
    			gClock.get(index).deClock();
    			
    			index = (index + 1) % gClock.size();
    		}
    		else
    		{
    			int tmp = index;
    			index = (index + 1) % gClock.size();
    			return gClock.get(tmp);
    		}
    	}
    	

    }

    @Override
    public void notify(List<BufferFrame> pool, BufferFrame frame) {
    	if(gClock == null)
    	{
    		gClock = new LinkedList<BufferFrame>(pool);
    	}
    	
    	if (frame.clockCount() < this.limit)
    		frame.inClock();
    	
    }

}
