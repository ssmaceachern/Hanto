/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.tournament;

import hanto.studentssmaceachern.common.HantoCoordinateImpl;

/** Keep track of where a potential move is coming from and where it is going
 * @author Sean
 *
 */
public class CoordinateMoveDistance {

	private HantoCoordinateImpl destination;
	private HantoCoordinateImpl currentPosition;
	
	/** Creates a new Coordinate Move Distance
	 * 
	 * @param destination The destination coordinate
	 * @param currentPosition The current position
	 */
	public CoordinateMoveDistance(HantoCoordinateImpl destination, HantoCoordinateImpl currentPosition) {
		this.destination = destination;
		this.currentPosition = currentPosition;
	}
	
	public HantoCoordinateImpl getDestination() {
		return destination;
	}
	
	public HantoCoordinateImpl getCurrentPosition() {
		return currentPosition;
	}
	
}
