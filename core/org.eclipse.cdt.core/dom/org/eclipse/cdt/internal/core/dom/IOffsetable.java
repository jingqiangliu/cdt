/**********************************************************************
 * Created on Apr 7, 2003
 *
 * Copyright (c) 2002,2003 IBM/Rational Software Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v0.5
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors: 
 * IBM Ltd. - Rational Software - Initial API and implementation
************************************************************************/
package org.eclipse.cdt.internal.core.dom;


/**
 * @author jcamelon
 *
 */
public interface IOffsetable {

	/**
	 * @return
	 */
	public abstract int getStartingOffset();
	/**
	 * @return
	 */
	public abstract int getTotalLength();
	/**
	 * @param i
	 */
	public abstract void setStartingOffset(int i);
	/**
	 * @param i
	 */
	public abstract void setTotalLength(int i);
	
	public abstract void setTopLine( int lineNumber );
	public abstract void setBottomLine( int lineNumber );
	public abstract int  getTopLine();
	public abstract int  getBottomLine(); 
	
}