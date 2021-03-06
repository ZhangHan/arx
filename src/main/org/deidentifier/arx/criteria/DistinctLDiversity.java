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

import org.deidentifier.arx.framework.check.groupify.HashGroupifyEntry;

/**
 * The distinct l-diversity privacy criterion
 * @author Prasser, Kohlmayer
 */
public class DistinctLDiversity extends LDiversity{

    private static final long serialVersionUID = -7973221140269608088L;
    
    /**
     * Creates a new instance
     * @param l
     */
    public DistinctLDiversity(String attribute, int l){
        super(attribute, l);
    }

    @Override
    public boolean isAnonymous(HashGroupifyEntry entry) {
        return entry.distribution.size() >= l;
    }
}
