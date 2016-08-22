/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.common;

import hanto.common.HantoCoordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *  
 * @author Sean
 *
 */
public class HantoCoordinateImpl implements HantoCoordinate, Comparable<HantoCoordinateImpl> {

	private int x, y;
	
	/** Creates a new Hanto Coordinate with the given x and y coordinates
	 * 
	 * @param x The x coordinate of the hanto coordinate
	 * @param y The y coordinate of the hanto coordinate
	 */
	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Copy constructor that creates an instance of HantoCoordinateImpl from an
	 * object that implements HantoCoordinate.
	 * @param hc an object that implements the HantoCoordinate interface.
	 */
	public HantoCoordinateImpl(HantoCoordinate hc)
	{
		this(hc.getX(), hc.getY());
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	/** Calculates the distance between this coordinate and the given coordinate
	 * 
	 * @param hc - The coordinate to calculate the distance between
	 * @return The distance between the coordinates
	 */
	public int distFromCoordinate(HantoCoordinateImpl hc) {
		int dx = hc.getX() - x;
		int dy = hc.getY() - y;

		int distance;

		if ((dx < 0 && dy < 0) || (dx >= 0 && dy >= 0)) {
			distance = Math.abs(dx + dy);
		} else {
			distance = Math.max(Math.abs(dx), Math.abs(dy));
		}
		return distance;
	}
	
	/** Returns the list of coordinates adjacent to this coordinate
	 * 
	 * @return The list of coordinates adjacent to this coordinate
	 */
	public List<HantoCoordinateImpl> Neighbors() {		
		List<HantoCoordinateImpl> neighbors = new ArrayList<HantoCoordinateImpl>();
		
		/**
		 * Top, Bottom
		 */
		neighbors.add(new HantoCoordinateImpl(x, y + 1));
		neighbors.add(new HantoCoordinateImpl(x, y - 1));
		
		/**
		 * Right Top, Right Bottom
		 */
		neighbors.add(new HantoCoordinateImpl(x + 1, y));
		neighbors.add(new HantoCoordinateImpl(x + 1, y - 1));
		
		/**
		 * Left Top, Left Bottom
		 */
		neighbors.add(new HantoCoordinateImpl(x - 1, y));
		neighbors.add(new HantoCoordinateImpl(x - 1, y + 1));
		
		return neighbors;
	}
	
	/** Determines whether the given coordinate is adjacent to this coordinate
	 * 
	 * @param hc The coordinate to check
	 * @return True if the coordinate is adjacent, false otherwise
	 */
	public boolean isAdjacentTo(HantoCoordinateImpl hc) {
		return distFromCoordinate(hc) == 1;
	}
	
	/** Calculates the neighboring coordinates in the given radius
	 * 
	 * @param radius The radius
	 * @return The neighboring coordinates
	 */
	public List<HantoCoordinateImpl> getAdjacentCoordsRadius(int radius) {
		List<HantoCoordinateImpl> adjCoords = Neighbors();
		adjCoords.add(this);
		
		List<HantoCoordinateImpl> newCoords = Neighbors();
		for(int i = 1; i < radius; i++) {
			List<HantoCoordinateImpl> nextNew = new ArrayList<HantoCoordinateImpl>();
			
			for(HantoCoordinateImpl coord: newCoords) {
				List<HantoCoordinateImpl> neighbors = coord.Neighbors();
				
				for(HantoCoordinateImpl c: neighbors) {
					
					if(!adjCoords.contains(c)) {
						adjCoords.add(c);
						if(!nextNew.contains(c)) {
							nextNew.add(c);
						}
					}
					
				}
			}
			newCoords = nextNew;
		}
		adjCoords.remove(this);
		return adjCoords;
	}
	
	/** Increments Y by the given amount
	 * 
	 * @param val - The amount to increment Y by
	 */
	public void shiftY(int val) {
		y += val;
	}
	
	/** Increment X by the given amount
	 * 
	 * @param val - The amount to add to x
	 */
	public void shiftX(int val) {
		x += val;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Equals method for coordinates
	 * @param hc - coordinate to compare
	 * @return true if both coordinates contain the same position
	 */
	public boolean equals(HantoCoordinate hc){
		return (x == hc.getX()) && (y == hc.getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HantoCoordinateImpl)) {
			return false;
		}
		final HantoCoordinateImpl other = (HantoCoordinateImpl) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(HantoCoordinateImpl hc) {
		return 0;
	}

}
