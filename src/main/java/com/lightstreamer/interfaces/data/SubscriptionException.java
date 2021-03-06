/*
 *  Copyright (c) Lightstreamer Srl
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      https://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.lightstreamer.interfaces.data;

import javax.annotation.Nullable;

/**
 * Thrown by the subscribe and unsubscribe methods in DataProvider if the
 * request cannot be satisfied.
 *
 * @author              Dario Crivelli
 * last author:         $Author: Aalinone $
 * @version             $Revision: 17783 $
 * last modified:       $Modtime: 17/01/07 16.14 $
 * last check-in:       $Date: 17/01/07 16.15 $
 * @see DataProvider
 */
public class SubscriptionException extends Exception {

    /**
     * Constructs a SubscriptionException with a supplied error message text.
     *
     * @param msg The detail message.
     */
    public SubscriptionException(@Nullable String msg) {
        super(msg);
    }
}


/*--- Formatted in Lightstreamer Java Convention Style on 2004-10-29 ---*/
