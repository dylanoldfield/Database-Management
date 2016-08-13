package buffer.policy;

import buffer.BufferFrame;

import java.util.List;

/**
 * Clock Replacement Policy
 */
public class ClockReplacementPolicy implements ReplacementPolicy {

    @Override
    public String getName() {
        return "CLOCK Replacement";
    }

    @Override
    public BufferFrame choose(List<BufferFrame> pool) throws BufferFrame.AllBufferFramesPinnedException {
        return null;
    }

    @Override
    public void notify(List<BufferFrame> pool, BufferFrame frame) {
    }
}
