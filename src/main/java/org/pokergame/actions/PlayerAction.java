// This file is part of the 'texasholdem' project, an open source
// Texas Hold'em poker application written in Java.
//
// Copyright 2009 Oscar Stigter
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.pokergame.actions;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Player action.
 * 
 * @author Oscar Stigter
 */
public abstract class PlayerAction implements Serializable {
    
    /** Player went all-in. */
    public static final PlayerAction ALL_IN = new AllInAction();

    /** Bet. */
    public static final PlayerAction BET = new BetAction(BigDecimal.ZERO);
    
    /** Posting the big blind. */
    public static final PlayerAction BIG_BLIND = new BigBlindAction();
    
    /** Call. */
    public static final PlayerAction CALL = new CallAction();
    
    /** Check. */
    public static final PlayerAction CHECK = new CheckAction();
    
    /** Continue. */
    public static final PlayerAction CONTINUE = new ContinueAction();
    
    /** Fold. */
    public static final PlayerAction FOLD = new FoldAction();
    
    /** Raise. */
    public static final PlayerAction RAISE = new RaiseAction(BigDecimal.ZERO);
    
    /** Posting the small blind. */
    public static final PlayerAction SMALL_BLIND = new SmallBlindAction();

    /** Timed out action. */
    public static final PlayerAction TIMED_OUT = new TimedOutAction();

    /** Timed out action. */
    public static final PlayerAction EXIT = new ExitAction();
    
    /** The action's name. */
    private final String name;
    
    /** The action's verb. */
    private final String verb;
    
    /** The amount (if appropriate). */
    private final BigDecimal amount;

    /**
     * Constructor.
     * 
     * @param name
     *            The action's name.
     * @param verb
     *            The action's verb.
     */
    public PlayerAction(String name, String verb) {
        this(name, verb, BigDecimal.ZERO);
    }
    
    /**
     * Constructor.
     * 
     * @param name
     *            The action's name.
     * @param verb
     *            The action's verb.
     * @param amount
     *            The action's amount.
     */
    public PlayerAction(String name, String verb, BigDecimal amount) {
        this.name = name;
        this.verb = verb;
        this.amount = amount;
    }
    
    /**
     * Returns the action's name.
     * 
     * @return The action's name.
     */
    public final String getName() {
        return name;
    }
    
    /**
     * Returns the action's verb.
     * 
     * @return The action's verb.
     */
    public final String getVerb() {
        return verb;
    }
    
    /**
     * Returns the action's amount.
     * 
     * @return The action's amount.
     */
    public final BigDecimal getAmount() {
        return amount;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return name;
    }

}
