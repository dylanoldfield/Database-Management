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

public class ClockReplacementPolicyTest {

    private ReplacementPolicy mReplacementPolicy;
    private List<BufferFrame> mPool;
    private BufferFrame A, B, C, D, E;

    @Before
    public void setUp() throws Exception {
        mReplacementPolicy = new ClockReplacementPolicy();
        mPool = TestUtils.generateBufferFrameList(5);
        A = mPool.get(0);
        B = mPool.get(1);
        C = mPool.get(2);
        D = mPool.get(3);
        E = mPool.get(4);
    }

    @Test(timeout = 1000)
    public void testChooseWhenOneFrameUnused() throws Exception {
        TestUtils.notifyMany(mReplacementPolicy, mPool, 1, 1, 0, 1, 1);
        // Test 4/5 used, but one never used
        assertEquals(C, mReplacementPolicy.choose(mPool));
    }

    @Test(timeout = 1000)
    public void testChooseFirst() throws Exception {
        TestUtils.notifyMany(mReplacementPolicy, mPool, 1, 1, 1, 1, 1);
        // Test 4/5 used, but one never used
        assertEquals(A, mReplacementPolicy.choose(mPool));
    }

//    @Test
//    public void testChooseClockWithPin() throws Exception {
//        TestUtils.notifyMany(mReplacementPolicy, mPool, 1, 1, 1, 1, 1);
//        // Test 4/5 used, but one never used
//        assertEquals(A, mReplacementPolicy.choose(mPool));
//        assertEquals(B, mReplacementPolicy.choose(mPool));
//        B.pin();
//        assertEquals(C, mReplacementPolicy.choose(mPool));
//        assertEquals(D, mReplacementPolicy.choose(mPool));
//        assertEquals(E, mReplacementPolicy.choose(mPool));
//        assertEquals(A, mReplacementPolicy.choose(mPool));
//        assertEquals(C, mReplacementPolicy.choose(mPool));
//    }
//
//    @Test
//    public void testIncrementsWhenPinned() throws Exception {
//        for(BufferFrame frame : mPool) {
//            frame.pin();
//        }
//        TestUtils.notifyMany(mReplacementPolicy, mPool, 1, 1, 0, 1, 1);
//        for(BufferFrame frame : mPool) {
//            frame.unpin();
//        }
//        assertEquals(C, mReplacementPolicy.choose(mPool));
//        assertEquals(A, mReplacementPolicy.choose(mPool));
//    }

    @Test(timeout = 1000)
    public void testClockLimit() throws Exception {
        TestUtils.notifyMany(mReplacementPolicy, mPool, 10, 10, 10, 10, 1);
        assertEquals(A, mReplacementPolicy.choose(mPool));

        TestUtils.notifyMany(mReplacementPolicy, mPool, 4, 4, 4, 4, 0);
        assertEquals(E, mReplacementPolicy.choose(mPool));
    }

    @Test(timeout = 1000)
    public void testMoveToNextFrameAfterChosen() throws Exception {
        TestUtils.notifyMany(mReplacementPolicy, mPool, 0, 0, 0, 0, 0);
        assertEquals(A, mReplacementPolicy.choose(mPool));
        assertEquals(B, mReplacementPolicy.choose(mPool));
        assertEquals(C, mReplacementPolicy.choose(mPool));
    }

    @Test(timeout = 1000)
    public void testChooseRecentlyReplacedFrame() throws Exception {
        TestUtils.notifyMany(mReplacementPolicy, mPool, 1, 1, 1, 1, 1);
        PageId replaceId = new PageId(10);
        Page replacePage = new Page();
        C.setPage(replaceId, replacePage);
        assertEquals(C, mReplacementPolicy.choose(mPool));
    }

    @After
    public void testNoChangeToList() {
        assertEquals(5, mPool.size());
    }

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