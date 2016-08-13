package buffer.policy;

import buffer.BufferFrame;
import global.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class LruReplacementPolicyTest {

    private ReplacementPolicy mReplacementPolicy;
    private List<BufferFrame> mPool;
    private BufferFrame A, B, C, D, E;

    @Before
    public void setUp() throws Exception {
        mReplacementPolicy = new LruReplacementPolicy();
        mPool = TestUtils.generateBufferFrameList(5);
        A = mPool.get(0);
        B = mPool.get(1);
        C = mPool.get(2);
        D = mPool.get(3);
        E = mPool.get(4);
    }

    @Test(timeout = 1000)
    public void testChooseWhenOneFrameUnused() throws Exception {
        mReplacementPolicy.notify(mPool, A);
        mReplacementPolicy.notify(mPool, B);
        mReplacementPolicy.notify(mPool, D);
        mReplacementPolicy.notify(mPool, E);
        // Test 4/5 used, but one never used
        assertEquals(C, mReplacementPolicy.choose(mPool));
    }

    @Test(timeout = 1000)
    public void testChooseNormal() throws Exception {
        mReplacementPolicy.notify(mPool, A);
        mReplacementPolicy.notify(mPool, B);
        mReplacementPolicy.notify(mPool, D);
        mReplacementPolicy.notify(mPool, E);
        mReplacementPolicy.notify(mPool, C);
        // Test that oldest frame is chosen
        assertEquals(A, mReplacementPolicy.choose(mPool));
    }

    @Test(timeout = 1000)
    public void testChooseOutOfOrder() throws Exception {
        mReplacementPolicy.notify(mPool, A);
        mReplacementPolicy.notify(mPool, B);
        mReplacementPolicy.notify(mPool, A);
        mReplacementPolicy.notify(mPool, D);
        mReplacementPolicy.notify(mPool, E);
        mReplacementPolicy.notify(mPool, C);
        // Test that oldest frame is chosen
        assertEquals(B, mReplacementPolicy.choose(mPool));
    }

    @After
    public void testNoChangeToList() {
        assertEquals(5, mPool.size());
    }

    @Test(timeout = 1000)
    public void testChooseOne() throws Exception {
        List<BufferFrame> pool = TestUtils.generateBufferFrameList(1);
        BufferFrame first = pool.get(0);
        for(int i = 0; i < 10; i++) {
            assertSame(first, mReplacementPolicy.choose(pool));
        }
    }
}