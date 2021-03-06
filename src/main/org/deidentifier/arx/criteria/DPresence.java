/*
 * ARX: Efficient, Stable and Optimal Data Anonymization
 * Copyright (C) 2012 - 2013 Florian Kohlmayer, Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.deidentifier.arx.criteria;

import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.DataSubset;
import org.deidentifier.arx.framework.CompressedBitSet;
import org.deidentifier.arx.framework.check.groupify.HashGroupifyEntry;
import org.deidentifier.arx.framework.data.DataManager;

/**
 * The d-presence criterion
 * Published in:
 * Nergiz M, Atzori M, Clifton C. 
 * Hiding the presence of individuals from shared databases. 
 * Proceedings of the 2007 ACM SIGMOD international conference on Management of data. 2007:665�676. 
 * Available at: http://portal.acm.org/citation.cfm?id=1247480.1247554.
 * 
 * @author Prasser, Kohlmayer
 */
public class DPresence extends PrivacyCriterion{
    
    private static final long serialVersionUID = 8534004943055128797L;
    
    /** Delta min*/
    private final double dMin;
    /** Delta max*/
    private final double dMax;
    /** The research subset, a set of tuple ids*/
    private DataSubset subset;
    /** A compressed representation of the research subset*/
    private CompressedBitSet bitset;
    
    /**
     * Creates a new instance
     * @param dMin Delta min
     * @param dMax Delta max
     * @param subset Research subset
     */
    public DPresence(double dMin, double dMax, DataSubset subset) {
        super(false);
        this.dMin = dMin;
        this.dMax = dMax;
        this.subset = subset;
        this.bitset = subset.getBitSet();
    }
        
    @Override
    public void initialize(DataManager manager) {
        // Nothing to do
    }

    @Override
    public int getRequirements(){
        // Requires two counters
        return ARXConfiguration.REQUIREMENT_COUNTER |
               ARXConfiguration.REQUIREMENT_SECONDARY_COUNTER;
    }

    @Override
    public boolean isAnonymous(HashGroupifyEntry entry) {
        if (entry.count > 0) {
            double dCurrent = (double) entry.count / (double) entry.pcount;
            // current_delta has to be between delta_min and delta_max
            return (dCurrent >= dMin) && (dCurrent <= dMax);
        } else {
            return true;
        }
    }

    /**
     * Returns the research subset
     * @return
     */
    public CompressedBitSet getResearchSubset() {
        return this.bitset;
    }
    
    /**
     * Returns the size of the research subset
     * @return
     */
    public int getResearchSubsetSize() {
        return this.subset.getSortedIndices().length;
    }

    /**
     * Returns dMin
     * @return
     */
    public double getDMin() {
        return dMin;
    }
    

    /**
     * Returns dMax
     * @return
     */
    public double getDMax() {
        return dMax;
    }
}