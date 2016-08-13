package buffer.policy;

import buffer.BufferFrame;

import java.util.List;

/**
 * Interface for Buffer Replacement Strategy
 */
public interface ReplacementPolicy {

	/**
	 * Returns a unique string for the replacement policy
	 */
	public String getName();

	/**
	 * Chooses the BufferFrame to replace
	 * 
	 * When a new page is requested, this is the method that is called to 
     * identify which frame to free. If a page is contained in this frame it 
     * will be removed, so further access to the page will require it to be 
     * re-loaded into the pool.
     * 
	 * @param pool current buffer pool
	 * @return chosen buffer frame for replacement
	 */
	public BufferFrame choose(List<BufferFrame> pool) throws BufferFrame.AllBufferFramesPinnedException;

	/**
	 * Tell the pool that a frame is is accessed.
	 * 
	 * This method is called by the BufferManager (@see buffer.BufferManager) 
	 * every time a page is requested. Some policies need to keep track of when 
	 * a page is used - for instance to update records of when or how frequently
	 * a page was used. 
	 * 
	 * @param pool current buffer pool
	 * @param frame the frame that was accessed
	 */
	public void notify(List<BufferFrame> pool, BufferFrame frame);

}