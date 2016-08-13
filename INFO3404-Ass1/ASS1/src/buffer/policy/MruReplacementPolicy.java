package buffer.policy;

import buffer.BufferFrame;

import java.util.List;

/**
 * Most Recently Used Replacement Policy
 */
public class MruReplacementPolicy implements ReplacementPolicy {

    @Override
    public String getName() {
        return "MRU Replacement";
    }

    @Override
    public BufferFrame choose(List<BufferFrame> pool) throws BufferFrame.AllBufferFramesPinnedException {
        return null;
    }

    @Override
    public void notify(List<BufferFrame> pool, BufferFrame frame) {

    }
}
