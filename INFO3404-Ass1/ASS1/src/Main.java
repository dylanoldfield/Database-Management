import buffer.BufferManager;

import disk.PageId;
import disk.HeaderPage;
import global.DBComponent;

import java.io.IOException;

/**
 * Example Execution using Database Components
 * Creates a bunch of relation entries in the header page (causing multiple pages to be created)
 * Statistics of the operations are show on completion. You can change the replacement policy in DBComponent
 */
public class Main {

    public static void main(String[] args) throws IOException {
        DBComponent.initialiseComponents();
        // Get the buffer manager
        BufferManager bm = DBComponent.BUFFER_MANAGER();
        // Create a new page
        PageId A = bm.getNewPage();
        PageId B = bm.getNewPage();
        PageId C = bm.getNewPage();
        PageId D = bm.getNewPage();
        PageId E = bm.getNewPage();
        // Create a bunch of relation entries in the header pointing to this page
        bm.getPage(B);
        bm.getPage(A);
        bm.getPage(D);
        bm.getPage(A);
        bm.getPage(C);
        bm.getPage(E);
        bm.getPage(A);
        bm.getPage(A);
        bm.getPage(A);
        bm.getPage(A);

        System.out.printf("Disk I/O: %d, Cache Hits: %d", DBComponent.DISK_MANAGER().getPageAccesses(), bm.getCacheHits());
        DBComponent.DISK_MANAGER().destroy();
    }

}
