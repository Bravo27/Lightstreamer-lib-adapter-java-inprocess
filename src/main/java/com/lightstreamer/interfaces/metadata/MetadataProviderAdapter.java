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


package com.lightstreamer.interfaces.metadata;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Provides a default implementation of all the MetadataProvider interface
 * methods. Overriding this class may facilitate the coding of simple 
 * Metadata Adapters.
 *
 * @author              Dario Crivelli
 * last author:         $Author: Aalinone $
 * @version             $Revision: 67057 $
 * last modified:       $Modtime: 31/01/08 12.15 $
 * last check-in:       $Date: 31/01/08 12.15 $
 */
public abstract class MetadataProviderAdapter implements MetadataProvider {

    /**
     * No-op initialization.
     *
     * @param  params  not used.
     * @param  configDir  not used.
     * @throws MetadataProviderException  never thrown.
     */
    public void init(@Nonnull Map params, @Nonnull File configDir)
            throws MetadataProviderException {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel as a preliminary check that a user is
     * enabled to make Requests to the related Data Providers.
     * <BR>In this default implementation, a simpler 2-arguments version of
     * the method is invoked, where the httpHeaders argument is discarded.
     * This also ensures backward compatibility with old adapter classes
     * derived from this one.
     * <BR>Note that, for authentication purposes, only the user and password
     * arguments should be consulted.
     *
     * @param user A User name.
     * @param password A password optionally required to validate the User.
     * @param httpHeaders A Map-type value object that contains a name-value
     * pair for each header found in the HTTP request that originated the call.
     * Not used.
     * @throws AccessException never thrown
     * @throws CreditsException never thrown
     */
    public void notifyUser(@Nullable String user, @Nullable String password, @Nonnull Map httpHeaders)
            throws AccessException, CreditsException {
        notifyUser(user, password);
    }

    /**
     * 2-arguments version of the User authentication method. In case the
     * 3-arguments version of the method is not overridden, this version
     * of the method is invoked.
     * <BR>In this default implementation, the Metadata Adapter poses no
     * restriction.
     *
     * @param  user  not used.
     * @param  password  not used.
     * @throws AccessException  never thrown.
     * @throws CreditsException  never thrown.
     */
    public void notifyUser(@Nullable String user, @Nullable String password)
            throws AccessException, CreditsException {
        // legal empty block
    }

    /**
     * Extended version of the User authentication method, invoked by the
     * Server in case it has been instructed (through the &lt;use_client_auth&gt;
     * configuration flag) to acquire the client principal from the client TLS/SSL
     * certificate, if available.
     * <BR>In this default implementation, the base 3-arguments version of
     * the method is invoked, where the clientPrincipal argument is discarded.
     * This also ensures backward compatibility with old adapter classes
     * derived from this one.
     * <BR>
     * <B>Edition Note:</B>
     * <BR>https is an optional feature, available
     * depending on Edition and License Type.
     * To know what features are enabled by your license, please see the License
     * tab of the Monitoring Dashboard (by default, available at /dashboard).
     *
     * @param user A User name.
     * @param password A password optionally required to validate the User.
     * @param httpHeaders A Map-type value object that contains a name-value
     * pair for each header found in the HTTP request that originated the call.
     * @param clientPrincipal the identification name reported in the client
     * TLS/SSL certificate supplied on the socket connection used to issue the
     * request that originated the call; it can be null if client has not
     * authenticated itself or the authentication has failed. Not used.
     * @throws AccessException never thrown
     * @throws CreditsException never thrown
     */
    public void notifyUser(@Nullable String user, @Nullable String password, @Nonnull Map httpHeaders,  @Nonnull String clientPrincipal)
            throws AccessException, CreditsException {
        notifyUser(user, password, httpHeaders);
    }

    /**
     * Called by Lightstreamer Kernel to ask for the bandwidth amount to be
     * allowed to a User for a push Session.
     * In this default implementation, the Metadata Adapter poses no
     * restriction.
     *
     * @param  user  not used.
     * @return always zero, to mean no bandwidth limit.
     */
    @Nonnull
    public double getAllowedMaxBandwidth(@Nullable String user) {
        return 0.0;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the ItemUpdate frequency
     * to be allowed to a User for a specific Item.
     * In this default implementation, the Metadata Adapter poses no
     * restriction; this also enables unfiltered dispatching for Items
     * subscribed in MERGE or DISTINCT mode.
     *
     * @param  user  not used.
     * @param  item  not used.
     * @return always zero, to mean no frequency limit.
     */
    @Nonnull
    public double getAllowedMaxItemFrequency(@Nullable String user, @Nonnull String item) {
        return 0.0;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the maximum allowed size
     * of the buffer internally used to enqueue subsequent ItemUpdates
     * for the same Item.
     * In this default implementation, the Metadata Adapter poses no
     * restriction.
     *
     * @param  user  not used.
     * @param  item  not used.
     * @return always zero, to mean no size limit.
     */
    public int getAllowedBufferSize(@Nullable String user, @Nonnull String item) {
        return 0;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the allowance of a publishing
     * Mode for an Item. A publishing Mode can or cannot be allowed depending
     * on the User.
     * In this default implementation, the Metadata Adapter poses no
     * restriction. As a consequence, conflicting Modes may be both allowed
     * for the same Item, so the Clients should ensure that the same Item
     * cannot be requested in two conflicting Modes.
     *
     * @param  user  not used.
     * @param  item  not used.
     * @param  mode  not used.
     * @return always true.
     */
    public boolean isModeAllowed(@Nullable String user, @Nonnull String item, @Nonnull Mode mode) {
        return true;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the allowance of a publishing
     * Mode for an Item (for at least one User).
     * In this default implementation, the Metadata Adapter poses no
     * restriction. As a consequence, conflicting Modes may be both allowed
     * for the same Item, so the Clients should ensure that the same Item
     * cannot be requested in two conflicting Modes.
     * <BR>This is just to simplify the development phase; the final
     * implementation of the method MUST be different, to ensure that
     * conflicting modes (i&#46;e&#46; MERGE, DISTINCT and COMMAND) are not both
     * allowed for the same Item.
     *
     * @param  item  not used.
     * @param  mode  not used.
     * @return always true.
     */
    public boolean modeMayBeAllowed(@Nonnull String item, @Nonnull Mode mode) {
        return true;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the allowance of a Selector
     * for an Item. Typically, a Selector is intended for one Item or for
     * a specific set of Items with some characteristics.
     * In this default implementation, selectors are always allowed.
     *
     * @param user not used.
     * @param item not used.
     * @param selector not used.
     * @return always true, to mean that the Selector is allowed.
     */
    public boolean isSelectorAllowed(@Nullable String user, @Nonnull String item, @Nonnull String selector) {
        return true;
    }

    /**
     * Called by Lightstreamer Kernel in order to filter events pertaining
     * to an ItemEventBuffer, if the related Item was requested within a Table
     * (i&#46;e&#46;: Subscription) with an associated Selector.
     * If the return value is true, the event is dispatched to the
     * ItemEventBuffer; otherwise, it is filtered out.
     * In this default implementation, no event is filtered out, regardless
     * of the Selector.
     *
     * @param user not used.
     * @param item not used.
     * @param selector not used.
     * @param event not used.
     * @return always true, to mean that the event is to be processed
     * by the ItemEventBuffer.
     */
    public boolean isSelected(@Nullable String user, @Nonnull String item, @Nonnull String selector, @Nonnull ItemEvent event) {
        return true;
    }

    /**
     * Called by Lightstreamer Kernel to know whether the Metadata Adapter
     * must or must not be given a chance to modify the values carried by the
     * updates for a supplied Item in a push Session owned by a supplied User.
     * In this default implementation, updates are never to be modified.
     *
     * @param user not used.
     * @param item not used.
     * @return always false, to mean that the Kernel should never ask for
     * update values modifications.
     */
    public boolean enableUpdateCustomization(@Nullable String user, @Nonnull String item) {
        return false;
    }
    
    /**
     * Called by Lightstreamer Kernel in order to customize events pertaining
     * to an ItemEventBuffer, if such customization has been requested
     * through the enableUpdateCustomization method.
     * In this default implementation, updates are never modified.
     *
     * @param user not used.
     * @param item not used.
     * @param event not used.
     */
    public void customizeUpdate(@Nullable String user, @Nonnull String item, @Nonnull CustomizableItemEvent event) {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel to ask for the minimum ItemEvent
     * frequency from the supplier Data Adapter at which the events for an Item are
     * guaranteed to be delivered to the Clients without loss of information.
     * In case of an incoming ItemEvent frequency greater than the specified
     * frequency, Lightstreamer Kernel may prefilter the events flow down to
     * this frequency.
     * In this default implementation, the Metadata Adapter can't set any
     * minimum frequency; this also enables unfiltered dispatching for Items
     * subscribed in MERGE or DISTINCT mode.
     *
     * @param  item  not used.
     * @return always zero, to mean that incoming ItemEvents must not be
     * prefiltered.
     */
    @Nonnull
    public double getMinSourceFrequency(@Nonnull String item) {
        return 0.0;
    }

    /**
     * Called by Lightstreamer Kernel to ask for the maximum allowed length
     * for a Snapshot of an Item that has been requested with publishing Mode
     * DISTINCT.
     * In this default implementation, 0 events are specified, so snapshot
     * will not be managed.
     *
     * @param item An Item Name.
     * @return a value of 0, to mean that no events will be kept in order to
     * satisfy snapshot requests.
     */
    public int getDistinctSnapshotLength(@Nonnull String item) {
        return 0;
    }

    /**
     * Called by Lightstreamer Kernel to forward a message received by a User.
     * In this default implementation, the Metadata Adapter does never
     * accept the message.
     *
     * @param  user  not used.
     * @param  sessionID  not used.
     * @param  message  not used.
     * @throws CreditsException  always thrown.
     * @throws NotificationException  never thrown.
     */
    public void notifyUserMessage(@Nullable String user, @Nonnull String sessionID, @Nonnull String message)
            throws CreditsException, NotificationException {
        throw new CreditsException(0, "Unsupported function");
    }

    /**
     * Called by Lightstreamer Kernel to check that a User is enabled to open
     * a new push Session.
     * <BR>In this default implementation, a simpler 2-arguments version of
     * the method is invoked, where the clientContext argument is discarded.
     * This also ensures backward compatibility with old adapter classes
     * derived from this one.
     *
     * @param user A User name.
     * @param sessionID The ID of a new Session.
     * @param clientContext not used.
     * @throws CreditsException never thrown
     * @throws NotificationException never thrown
     */
    public void notifyNewSession(@Nullable String user, @Nonnull String sessionID,  @Nonnull Map clientContext)
            throws CreditsException, NotificationException {
        notifyNewSession(user, sessionID);
    }

    /**
     * 2-arguments version of the User authentication method. In case the
     * 3-arguments version of the method is not overridden, this version
     * of the method is invoked.
     * <BR>In this default implementation, the Metadata Adapter poses no
     * restriction.
     *
     * @param user not used.
     * @param sessionID not used.
     * @throws CreditsException never thrown.
     * @throws NotificationException never thrown.
     */
    public void notifyNewSession(@Nullable String user, @Nonnull String sessionID)
            throws CreditsException, NotificationException {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel to notify the Metadata Adapter that
     * a push Session has been closed.
     * In this default implementation, the Metadata Adapter does nothing,
     * because it doesn't need to remember the open Sessions.
     *
     * @param  sessionID  not used.
     * @throws NotificationException  never thrown.
     */
    public void notifySessionClose(@Nonnull String sessionID)
            throws NotificationException {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel to know whether the Metadata Adapter
     * must or must not be notified any time a Table (i&#46;e&#46;: Subscription)
     * is added or removed from a push Session owned by a supplied User.
     * In this default implementation, the Metadata Adapter doesn't require
     * such notifications.
     *
     * @param  user  not used.
     * @return always false, to prevent being notified with notifyNewTables
     * and notifyTablesClose.
     */
    public boolean wantsTablesNotification(@Nullable String user) {
        return false;
    }

    /**
     * Called by Lightstreamer Kernel to check that a User is enabled to add
     * some Tables (i&#46;e&#46;: Subscriptions) to a push Session.
     * If the check succeeds, this also notifies the Metadata Adapter that
     * the Tables are being added to the Session.
     * In this default implementation, the Metadata Adapter poses no
     * restriction.
     * Unless the {@link #wantsTablesNotification} method is overridden,
     * this method will never be called by Lightstreamer Kernel.
     *
     * @param  user  not used.
     * @param  sessionID  not used.
     * @param  tables  not used.
     * @throws CreditsException  never thrown.
     * @throws NotificationException  never thrown.
     */
    public void notifyNewTables(@Nullable String user, @Nonnull String sessionID, @Nonnull TableInfo[] tables)
            throws CreditsException, NotificationException {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel to notify the Metadata Adapter that
     * some Tables (i&#46;e&#46;: Subscriptions) have been removed from a push Session.
     * In this default implementation, the Metadata Adapter does nothing,
     * because it doesn't need to remember the Tables used.
     * Unless the {@link #wantsTablesNotification} method is overridden,
     * this method will never be called by Lightstreamer Kernel.
     *
     * @param  sessionID  not used.
     * @param  tables  not used.
     * @throws NotificationException  never thrown.
     */
    public void notifyTablesClose(@Nonnull String sessionID, @Nonnull TableInfo[] tables)
            throws NotificationException {
        // legal empty block
    }

    /**
     * Called by Lightstreamer Kernel to check that a User is enabled to access
     * the specified MPN device.
     * In this default implementation, the Metadata Adapter poses no restriction.
     * <BR>
     * <B>Edition Note:</B>
     * <BR>Push Notifications is an optional feature,
     * available depending on Edition and License Type.
     * To know what features are enabled by your license, please see the License
     * tab of the Monitoring Dashboard (by default, available at /dashboard).
     * 
     * @param  user  not used.
     * @param  sessionID  not used.
     * @param  device  not used.
     * @throws CreditsException  never thrown.
     * @throws NotificationException  never thrown.
     */
    public void notifyMpnDeviceAccess(@Nullable String user, @Nonnull String sessionID, @Nonnull MpnDeviceInfo device)
            throws CreditsException, NotificationException {
        // legal empty block
    }
    
    /**
     * Called by Lightstreamer Kernel to check that a User is enabled 
     * to activate a Push Notification subscription.
     * In this default implementation, the Metadata Adapter poses no
     * restriction.
     * <BR>
     * <B>Edition Note:</B>
     * <BR>Push Notifications is an optional feature,
     * available depending on Edition and License Type.
     * 
     * @param  user  not used.
     * @param  sessionID  not used.
     * @param  table  not used.
     * @param  mpnSubscription  not used.
     * @throws CreditsException  never thrown.
     * @throws NotificationException  never thrown.
     */
    public void notifyMpnSubscriptionActivation(@Nullable String user, @Nonnull String sessionID, @Nonnull TableInfo table, @Nonnull MpnSubscriptionInfo mpnSubscription)
            throws CreditsException, NotificationException {
        // legal empty block
    }
    
    /**
     * Called by Lightstreamer Kernel to check that a User is enabled to change
     * the token of a MPN device. 
     * In this default implementation, the Metadata Adapter poses no
     * restriction.
     * <BR>
     * <B>Edition Note:</B>
     * <BR>Push Notifications is an optional feature,
     * available depending on Edition and License Type.
     * To know what features are enabled by your license, please see the License
     * tab of the Monitoring Dashboard (by default, available at /dashboard).
     * 
     * @param user  not used.
     * @param sessionID  not used.
     * @param device  not used.
     * @param newDeviceToken  not used.
     * @throws CreditsException  never thrown.
     * @throws NotificationException  never thrown.
     */
    public void notifyMpnDeviceTokenChange(@Nullable String user, @Nonnull String sessionID, @Nonnull MpnDeviceInfo device, @Nonnull String newDeviceToken)
            throws CreditsException, NotificationException {
        // legal empty block
    }
}


/*--- Formatted in Lightstreamer Java Convention Style on 2004-10-29 ---*/
