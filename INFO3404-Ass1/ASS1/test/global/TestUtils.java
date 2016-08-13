package global;

import java.util.LinkedList;
import java.util.List;

import buffer.BufferFrame;
import buffer.policy.ReplacementPolicy;
import disk.Page;
import disk.PageId;

/**
 * Created by developer on 20/07/15.
 */
public class TestUtils {

    private static String ALPHABET = "ABCDEFGJIJKLMNOPQRSTUVWXYZabcdefghijklmnoprstuvqxyz";

    public static String generateAsciiString(int seed, int length) {
        String base = seed + ALPHABET;
        int repeat = base.length() / length + 1;
        for(int i = 0; i < repeat; i++) {
            base += base;
        }
        return base.substring(0, length);
    }

    public static List<BufferFrame> generateBufferFrameList(int size) {
        List<BufferFrame> pool = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            BufferFrame temp = new BufferFrame();
            try {
                temp.setPage(new PageId(i), new Page());
            } catch (BufferFrame.BufferFrameDirtyException e) {}
            pool.add(temp);
        }
        return pool;
    }

	public static void notifyMany(ReplacementPolicy policy, List<BufferFrame> pool, int... timesToNotify) {
        for(int i = 0; i < timesToNotify.length; i++) {
            int notify = timesToNotify[i];
            for(int j = 0; j < notify; j++) {
                policy.notify(pool, pool.get(i));
            }
        }
    }
}
