package buffer.policy;

import buffer.BufferFrame;
import disk.Page;
import disk.PageId;
import global.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneralisedClockReplacementPolicyTest {

    private static final int POOLSIZE = 5;
	private ReplacementPolicy policy;
    private List<BufferFrame> pool;
    private BufferFrame A, B, C, D, E;

    @Before
    public void setUp() throws Exception {
    	policy = new GeneralisedClockReplacementPolicy(4);
    	// Always start off with a pool of 5 frames    	
        pool = TestUtils.generateBufferFrameList(POOLSIZE);
        // Keep pointers to each page to check their contents.
        A = pool.get(0);
        B = pool.get(1);
        C = pool.get(2);
        D = pool.get(3);
        E = pool.get(4);
    }

//    @Test//(timeout = 1000)
//    public void testChooseWhenOneFrameUnused() throws Exception {
//    	// Access all pages except the middle (C)
//        TestUtils.notifyMany(policy, pool, 1, 1, 0, 1, 1);
//
//        assertEquals("Chosen frame should be the one containing the unused page", C, policy.choose(pool));
//    }

    @Test//(timeout = 1000)
    public void testChooseMiddleFrame() throws Exception {
        TestUtils.notifyMany(policy, pool, 4, 3, 4, 4, 4);
        // Test 4/5 used, but one never used
        assertEquals("Chosen frame should be the one containing the least-used page", B, policy.choose(pool));
    }

//    @Test
//    public void testChooseCountdownWithPin() throws Exception {
//        TestUtils.notifyMany(mReplacementPolicy, mPool, 4, 3, 2, 1, 1);
//        // Test 4/5 used, but one never used
//        assertEquals(D, mReplacementPolicy.choose(mPool));
//        D.pin();
//        assertEquals(E, mReplacementPolicy.choose(mPool));
//        E.pin();
//        assertEquals(C, mReplacementPolicy.choose(mPool));
//        C.pin();
//        assertEquals(B, mReplacementPolicy.choose(mPool));
//        B.pin();
//        assertEquals(A, mReplacementPolicy.choose(mPool));
//    }
//
//    @Test
//    public void testIncrementsWhenPinned() throws Exception {
//        for(BufferFrame frame : mPool) {
//            frame.pin();
//        }
//        TestUtils.notifyMany(mReplacementPolicy, mPool, 4, 4, 0, 4, 4);
//        for(BufferFrame frame : mPool) {
//            frame.unpin();
//        }
//        assertEquals(C, mReplacementPolicy.choose(mPool));
//    }

//    @Test//(timeout = 1000)
//    public void testClockLimit() throws Exception {
//        TestUtils.notifyMany(policy, pool, 10, 4, 4, 4, 4);
//        assertEquals("All pages have been accessed up to the policy limit, so chosen page should just be first in pool", A, policy.choose(pool));
//
//        TestUtils.notifyMany(policy, pool, 4, 4, 4, 4, 10);
//        // Trigger the clock hand to move round and reduce the counts on each frame
//        policy.choose(pool); 
//        
//        // Cause all but E's count to go back up again.
//        TestUtils.notifyMany(policy, pool, 4, 4, 4, 4, 0);
//        assertEquals("Chosen frame should be the one containing the least-used page", E, policy.choose(pool));
//    }
//
//    @Test//(timeout = 1000)
//    public void testMoveToNextFrameAfterChosen() throws Exception {
//        TestUtils.notifyMany(policy, pool, 0, 0, 0, 0, 0);
//        assertEquals("First chosen frame should be the first in the pool", A, policy.choose(pool));
//        assertEquals("Second chosen frame should be the second in the pool", B, policy.choose(pool));
//        assertEquals("Third chosen frame should be the third in the pool", C, policy.choose(pool));
//    }
//
//    @Test//(timeout = 1000)
//    public void testChooseRecentlyReplacedFrame() throws Exception {
//        TestUtils.notifyMany(policy, pool, 1, 1, 1, 1, 1);
//        PageId replaceId = new PageId(10);
//        Page replacePage = new Page();
//        C.setPage(replaceId, replacePage);
//        
//        // Consider what happens when a frame is replaced in the pool. 
//        // The BufferFrame object will stay the same (for memory reasons), but 
//        // the contents change. You may need to make some changes to BufferFrame
//        // to conform to your implementation of GCLOCK when frames are replaced.
//        
//        assertEquals("Chosen frame should be the one containing changed page", C, policy.choose(pool));
//    }
//
//    @After
//    public void testNoChangeToList() {
//        assertEquals("Pool should not change in size",POOLSIZE, pool.size());
//    }

//    @Test(expected= BufferFrame.AllBufferFramesPinnedException.class)
//    public void testChooseAllFramesPinned() {
//        A.pin();
//        B.pin();
//        C.pin();
//        D.pin();
//        E.pin();
//        mReplacementPolicy.choose(mPool);
//    }
}