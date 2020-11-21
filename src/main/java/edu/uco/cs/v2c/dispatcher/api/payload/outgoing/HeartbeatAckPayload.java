/*
 * Copyright (c) 2020 Caleb L. Power, Everistus Akpabio, Rashed Alrashed,
 * Nicholas Clemmons, Jonathan Craig, James Cole Riggall, and Glen Mathew.
 * All rights reserved. Original code copyright (c) 2020 Axonibyte Innovations,
 * LLC. All rights reserved. Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions 
 * limitations under the License.
 */
package edu.uco.cs.v2c.dispatcher.api.payload.outgoing;

import java.util.UUID;

import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.payload.MalformedPayloadException;
import edu.uco.cs.v2c.dispatcher.api.payload.outgoing.OutgoingPayload.OutgoingAction;

/**
 * Encapsulates the outgoing {@link OutgoingAction#HEARTBEAT_ACK} payload.
 * 
 * @author Caleb L. Power
 */
public class HeartbeatAckPayload extends OutgoingPayload {
  
  private static final String APP_VAR = "app";
  private static final String KEY_VAR = "key";
  private static final String TIMESTAMP_VAR = "timestamp";
  
  private long timestamp = -1L;
  private String app = null;
  private UUID key = null;
  
  /**
   * Overloaded constructor to instantiate the payload.
   */
  public HeartbeatAckPayload() {
    super(OutgoingAction.HEARTBEAT_ACK);
  }
  
  /**
   * Retrieves the unique identifier of the application in question.
   * 
   * @return the application's unique identifier
   */
  public String getApp() {
    return app;
  }
  
  /**
   * Sets the unique identifier of the application in question.
   * 
   * @param app the appplication's unique identifier
   * @return this payload
   */
  public HeartbeatAckPayload setApp(String app) {
    this.app = app;
    return this;
  }
  
  /**
   * Retrieves the unique key of this particular wellness check.
   * 
   * @return the theoretically unique key
   */
  public UUID getKey() {
    return key;
  }
  
  /**
   * Sets the unique key of this particular wellness check.
   * 
   * @param key the unique key
   * @return this payload
   */
  public HeartbeatAckPayload setKey(UUID key) {
    this.key = key;
    return this;
  }
  
  /**
   * Retrieves the timestamp representing the time at which this payload was
   * generated by this server prior to dispatch.
   * 
   * @return the number of milliseconds after the UNIX epoch during which this
   *         wellness check was generated
   */
  public long getTimestamp() {
    return timestamp;
  }
  
  /**
   * Sets the timestamp that should represent the time at which this payload
   * was generated prior to dispatch.
   * 
   * @param timestamp the number of milliseconds after the UNIX epoch during
   *        which this payload was generated
   * @return this payload
   */
  public HeartbeatAckPayload setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public JSONObject serialize() throws MalformedPayloadException {
    if(timestamp < 0 || app == null || key == null)
      throw new MalformedPayloadException(action, "Invalid payload.");
    
    return new JSONObject()
        .put(ACTION_VAR, action.name())
        .put(APP_VAR, app)
        .put(KEY_VAR, key.toString())
        .put(TIMESTAMP_VAR, timestamp);
  }
  
}
