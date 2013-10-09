/**
 * Graph-Mining Tutorial for Ozone
 *
 * Copyright (C) 2013  Sebastian Schelter <ssc@apache.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.tuberlin.dima.aim3.graphmining.chainletter;

import eu.stratosphere.pact.common.stubs.Collector;
import eu.stratosphere.pact.common.stubs.MatchStub;
import eu.stratosphere.pact.common.type.PactRecord;
import eu.stratosphere.pact.common.type.base.PactBoolean;

public class ReceiveMessage extends MatchStub {

  private PactBoolean messageDelivered = new PactBoolean(true);

  @Override
  public void match(PactRecord recipient, PactRecord recipientState, Collector<PactRecord> collector) throws Exception {

    boolean alreadyReceivedMessage = recipientState.getField(1, PactBoolean.class).getValue();

    if (!alreadyReceivedMessage) {
      recipientState.setField(1, messageDelivered);
      collector.collect(recipientState);
    }
  }
}
