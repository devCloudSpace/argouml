// $Id$
// Copyright (c) 1996-2005 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

// $header$
package org.argouml.uml.ui.behavior.common_behavior;

import java.awt.event.ActionEvent;

import org.argouml.model.Model;
import org.argouml.model.ModelFacade;
import org.argouml.uml.ui.AbstractActionNewModelElement;

/**
 * @since Dec 15, 2002
 * @author jaap.branderhorst@xs4all.nl
 */
public abstract class ActionNewAction extends AbstractActionNewModelElement {

    /**
     * The constant defining the role the action
     * to be created plays for its parent.
     * For example, if one wishes to create
     * an entry action for a state, this is
     * filled with "entry". The values are defined
     * in the interface Roles
     */
    public static final String ROLE = "role";

    /**
     * Contains the roles definitions for UML actions.
     *
     */
    public static interface Roles {

        /**
         * The entry activity for some state
         */
        public static final String ENTRY = "entry";
        /**
         * The exit activity for some state
         */
        public static final String EXIT = "exit";
        /**
         * The doactivity with some state
         */
        public static final String DO = "do";
        /**
         * The action with some message
         */
        public static final String ACTION = "action";

        /**
         * The effect of some transition
         */
        public static final String EFFECT = "effect";
    }

    /**
     * Constructor for ActionNewAction.
     */
    protected ActionNewAction() {
        super();
    }

    /**
     * Implementors should create a concrete action like
     * a CallAction in this method.
     * @return Object
     */
    protected abstract Object createAction();

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object action = createAction();
        if (getValue(ROLE).equals(Roles.EXIT)) {
            Model.getStateMachinesHelper().setExit(getTarget(), action);
        } else
	    if (getValue(ROLE).equals(Roles.ENTRY)) {
		Model.getStateMachinesHelper().setEntry(getTarget(), action);
	    } else
		if (getValue(ROLE).equals(Roles.DO)) {
		    Model.getStateMachinesHelper().setDoActivity(getTarget(), action);
		} else
		    if (getValue(ROLE).equals(Roles.ACTION)) {
			Model.getCollaborationsHelper().setAction(getTarget(), action);
		    } else
			if (getValue(ROLE).equals(Roles.EFFECT)) {
			    Model.getStateMachinesHelper().setEffect(getTarget(), action);
			}

    }

    /**
     * @param role the role the action plays
     * @param t the transition or state to get the action for
     * @return the action
     */
    public static Object getAction(String role, Object t) {
        if (role.equals(Roles.EXIT)) {
            return ModelFacade.getExit(t);
        } else
            if (role.equals(Roles.ENTRY)) {
                return ModelFacade.getEntry(t);
            } else
                if (role.equals(Roles.DO)) {
                    return ModelFacade.getDoActivity(t);
                } else
                    if (role.equals(Roles.ACTION)) {
                        return ModelFacade.getAction(t);
                    } else
                        if (role.equals(Roles.EFFECT)) {
                            return ModelFacade.getEffect(t);
                        }
        return null;
    }
}
