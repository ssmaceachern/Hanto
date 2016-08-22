/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.common.exceptions;

import hanto.common.HantoException;

/**
 * 
 * @author Sean
 *
 */
public class GameNotActiveException extends HantoException {
	
	/**
	 * @param type the invalid type being used
	 */
	public GameNotActiveException() {
		super("Game is over. Moves are invalid");
	}
}
