/*******************************************************************************
 * Copyright (c) 2014 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Doug Schaefer
 *******************************************************************************/
package org.eclipse.cdt.launchbar.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchMode;

public interface ILaunchConfigurationDescriptor {

	/**
	 * Name to show in the launch configuration selector.
	 * 
	 * @return name of the launch configuration
	 */
	String getName();

	/**
	 * The type of launch configuration supported by this descriptor.
	 * 
	 * @return
	 */
	ILaunchConfigurationType getLaunchConfigurationType() throws CoreException;

	/**
	 * The corresponding launch configuration.
	 * If this launch config hasn't been created yet, it will be
	 * 
	 * @return the corresponding launch configuration
	 * @throws CoreException 
	 */
	ILaunchConfiguration getLaunchConfiguration() throws CoreException;

	/**
	 * Is this launch configuration managed by this descriptor.
	 * 
	 * @param launchConfiguration
	 * @return
	 */
	boolean matches(ILaunchConfiguration launchConfiguration);

	/**
	 * Return the list of launch targets this configuration can launcht to.
	 * 
	 * @return launch targets
	 */
	ILaunchTarget[] getLaunchTargets();
	
	/**
	 * Return the launch target with the given id.
	 * 
	 * @param id id of target
	 * @return launch target
	 */
	ILaunchTarget getLaunchTarget(String id);

	/**
	 * Set the active launch mode. Allows the descriptor to prepare for a
	 * launch in that mode.
	 * 
	 * @param mode the new active launch mode
	 */
	void setActiveLaunchMode(ILaunchMode mode);

	/**
	 * Set the active launch target. Allows the descriptor to prepare for
	 * a launch on that target.
	 * 
	 * @param target the new active launch target
	 */
	void setActiveLaunchTarget(ILaunchTarget target);

}
