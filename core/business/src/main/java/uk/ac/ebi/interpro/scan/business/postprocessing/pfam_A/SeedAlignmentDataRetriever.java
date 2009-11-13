package uk.ac.ebi.interpro.scan.business.postprocessing.pfam_A;

import uk.ac.ebi.interpro.scan.model.Protein;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Used in post-processing to load seed alignment data into memory
 * for the batch of proteins currently being analysed.
 *
 * @author Phil Jones
 * @version $Id: SeedAlignmentDataRetriever.java,v 1.5 2009/11/04 16:24:31 craigm Exp $
 * @since 1.0
 */
public class SeedAlignmentDataRetriever {

    /**
     * Retrieves a Map of UPI (protein accession) to List<SeedAlignment> for use in
     * post processing.
     * @param proteinIds for which to return the seed alignment data.
     * @return a Map of UPI (protein accession) to List<SeedAlignment> for use in
     * post processing.
     * @throws java.sql.SQLException in the event of a database problem.
     */
    public SeedAlignmentData retrieveSeedAlignmentData (Set<String> proteinIds){
        SeedAlignmentData seedAlignmentData = null;

        // TODO - Get the seed alignment data from somewhere...
        // TODO this can wait until after the first milestone however.

        return seedAlignmentData;
    }

    public class SeedAlignmentData {

        private final Map<String, List<SeedAlignment>> data =  new HashMap<String, List<SeedAlignment>>();

        private SeedAlignmentData(){
        }

        private void put(final String upi, final SeedAlignment seedAlignment){
            List<SeedAlignment> seedAlignmentList = data.get(upi);
            if (seedAlignmentList == null){
                seedAlignmentList = new ArrayList<SeedAlignment>();
                seedAlignmentList.add(seedAlignment);
                data.put(upi, seedAlignmentList);
            }
            else {
                seedAlignmentList.add(seedAlignment);
            }
        }

        public List<SeedAlignment> getSeedAlignments(final String proteinId){
            List<SeedAlignment> seedAlignmentList = data.get(proteinId);
            if (seedAlignmentList == null){
                seedAlignmentList = Collections.emptyList();
            }
            return seedAlignmentList;
        }
    }
}
