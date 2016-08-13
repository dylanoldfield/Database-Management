package buffer.policy;

import buffer.BufferFrame;

import java.util.LinkedList;
import java.util.List;

/**
 * Least Recently Used Replacement Policy
 */
public class LruReplacementPolicy implements ReplacementPolicy {
	
	LinkedList <BufferFrame> Lru;

    @Override
    public String getName() {
        return "LRU Replacement";
    }

    @Override
    public BufferFrame choose(List<BufferFrame> pool) {
    	
    	if(Lru == null)
    	{
    		Lru = new LinkedList<BufferFrame>(pool);
    	}
    	
    	return Lru.getFirst();
    	
    }

    @Override
    public void notify(List<BufferFrame> pool, BufferFrame frame) {
    	
    	if(Lru == null)
    	{
    		Lru = new LinkedList<BufferFrame>(pool);
    	}
    	
    	Lru.remove(frame);
    	Lru.add(frame);
    	
    }

}