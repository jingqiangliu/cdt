/*******************************************************************************
 * Copyright (c) 2013 Google, Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * 	   Sergey Prigogin (Google) - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.ui.preferences;

import org.eclipse.cdt.internal.ui.ICHelpContextIds;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/*
 * The preference page for configuring styles of include statements.
 */
public class IncludeStylePreferencePage extends ConfigurationBlockPropertyAndPreferencePage {
	public static final String PREF_ID = "org.eclipse.cdt.ui.preferences.IncludeStylePreferencePage"; //$NON-NLS-1$
	public static final String PROP_ID = "org.eclipse.cdt.ui.propertyPages.IncludeStylePreferencePage"; //$NON-NLS-1$

	public IncludeStylePreferencePage() {
		setPreferenceStore(CUIPlugin.getDefault().getPreferenceStore());
		// Only used when the page is shown programmatically.
		setTitle(PreferencesMessages.IncludeStylePreferencePage_title);
	}

	@Override
	protected OptionsConfigurationBlock createConfigurationBlock(IWorkbenchPreferenceContainer container) {
		return new IncludeStyleBlock(getNewStatusChangedListener(), getProject(), container);
	}

	@Override
	protected String getHelpId() {
		return ICHelpContextIds.INCLUDE_STYLE_PREFERENCE_PAGE;
	}

	@Override
	protected String getPreferencePageId() {
		return PREF_ID;
	}

	@Override
	protected String getPropertyPageId() {
		return null;
		// TODO(sprigogin): Project specific settings
		//		return PROP_ID;
	}
}
