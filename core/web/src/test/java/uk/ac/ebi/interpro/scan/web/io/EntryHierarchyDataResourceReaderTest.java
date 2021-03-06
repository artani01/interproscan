package uk.ac.ebi.interpro.scan.web.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.ac.ebi.interpro.scan.web.model.EntryHierarchyData;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link EntryHierarchyDataResourceReader}
 *
 * @author Matthew Fraser
 * @version $Id$
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class EntryHierarchyDataResourceReaderTest {

    @Resource
    private EntryHierarchyDataResourceReader reader;

    @Resource
    private org.springframework.core.io.Resource resource;

    @Test
    public void testRead() throws IOException {
        Map<String, EntryHierarchyData> result = reader.read(resource);
        assertNotNull(result);
        assertEquals(25, result.size());

        EntryHierarchyData ipr000014 = result.get("IPR000014");
        EntryHierarchyData ipr013655 = result.get("IPR013655");
        assertEquals(1, ipr000014.getHierarchyLevel());
        assertEquals(2, ipr013655.getHierarchyLevel());
        assertEquals(4, ipr000014.getEntriesInSameHierarchy().size());

        assertNotNull(ipr000014.getImmediateChildren());
        assertEquals(3, ipr000014.getImmediateChildren().size());

        assertNotNull(ipr000014.getRootEntry());
        assertEquals(ipr000014, ipr000014.getRootEntry());

        assertNotNull(ipr013655.getRootEntry());
        assertEquals(ipr000014, ipr013655.getRootEntry());

        assertNotNull(ipr013655.getImmediateChildren());
        assertEquals(0, ipr013655.getImmediateChildren().size());

        assertEquals(ipr000014.getEntriesInSameHierarchy(), ipr013655.getEntriesInSameHierarchy());

        EntryHierarchyData ipr001840 = result.get("IPR001840");
        assertEquals(3, ipr001840.getHierarchyLevel());

        EntryHierarchyData ipr009003 = result.get("IPR009003");
        EntryHierarchyData ipr000020 = result.get("IPR000020");

        assertNotNull(ipr001840.getRootEntry());
        assertEquals(ipr000020, ipr001840.getRootEntry());
        assertEquals(12, ipr009003.getEntriesInSameHierarchy().size());
    }

}
