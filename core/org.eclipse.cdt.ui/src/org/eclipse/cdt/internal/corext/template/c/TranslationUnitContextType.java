/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     QNX Software System
 *     Anton Leherbauer (Wind River Systems)
 *******************************************************************************/

package org.eclipse.cdt.internal.corext.template.c;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.IFunctionDeclaration;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

/**
 * A context type for translation units.
 */
public abstract class TranslationUnitContextType extends TemplateContextType {

	protected static class ReturnType extends TemplateVariableResolver {
	 	public ReturnType() {
	 	 	super("return_type", TemplateMessages.getString("CContextType.variable.description.return.type")); //$NON-NLS-1$ //$NON-NLS-2$
	 	}
	 	public String resolve(TemplateContext context) {
			ICElement element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_METHOD);
			if (element == null) {
				element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_FUNCTION);
			}
			if (element == null) {
				return null;
			}

			if (element instanceof IFunctionDeclaration) {
				return ((IFunctionDeclaration) element).getReturnType();
			}
			return null;
		}
	}

	protected static class File extends TemplateVariableResolver {
		public File() {
			super("file", TemplateMessages.getString("CContextType.variable.description.file")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		public String resolve(TemplateContext context) {
			ITranslationUnit unit= ((TranslationUnitContext) context).getTranslationUnit();
			
			return (unit == null) ? null : unit.getElementName();
		}
	}

	protected static class EnclosingCElement extends TemplateVariableResolver {
		protected final int fElementType;
		
		public EnclosingCElement(String name, String description, int elementType) {
			super(name, description);
			fElementType= elementType;
		}
		public String resolve(TemplateContext context) {
			ICElement element= ((TranslationUnitContext) context).findEnclosingElement(fElementType);
			return (element == null) ? null : element.getElementName();
		}
	}
	
	protected static class Method extends EnclosingCElement {
		public Method() {
			super("enclosing_method", TemplateMessages.getString("CContextType.variable.description.enclosing.method"), ICElement.C_METHOD); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/*
	protected static class Type extends EnclosingCElement {
		public Type() {
			super("enclosing_type", TemplateMessages.getString("CContextType.variable.description.enclosing.type"), 0); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	protected static class SuperClass extends EnclosingCElement {
		public Type() {
			super("super_class", TemplateMessages.getString("CContextType.variable.description.type"), IJavaElement.TYPE);
		}
	}

	protected static class Package extends EnclosingCElement {
		public Package() {
			super("enclosing_package", TemplateMessages.getString("CContextType.variable.description.enclosing.package"), 0); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}	
*/
//	protected static class Project extends EnclosingCElement {
//		public Project() {
//			super("enclosing_project", TemplateMessages.getString("CContextType.variable.description.enclosing.project"), ICElement.C_PROJECT); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//	}	

	protected static class Project extends TemplateVariableResolver {
		public Project() {
			super("enclosing_project", TemplateMessages.getString("CContextType.variable.description.enclosing.project")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		public String resolve(TemplateContext context) {
			ITranslationUnit unit= ((CContext) context).getTranslationUnit();
			return (unit == null) ? null : unit.getCProject().getElementName();
		}
	}	

	protected static class Arguments extends TemplateVariableResolver {
		public Arguments() {
			super("enclosing_method_arguments", TemplateMessages.getString("CContextType.variable.description.enclosing.method.arguments")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		public String resolve(TemplateContext context) {
			ICElement element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_FUNCTION);
			if (element == null) {
				element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_FUNCTION_DECLARATION);
				if (element == null) {
					element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_METHOD);
					if (element == null) {
						element= ((TranslationUnitContext) context).findEnclosingElement(ICElement.C_METHOD_DECLARATION);
					}
				}
			}

			if (element instanceof IFunctionDeclaration) {
				String[] arguments= ((IFunctionDeclaration)element).getParameterTypes();
				StringBuffer buffer= new StringBuffer();
				
				for (int i= 0; i < arguments.length; i++) {
					if (i > 0)
						buffer.append(", "); //$NON-NLS-1$
					buffer.append(arguments[i]);				
				}
				
				return buffer.toString();
			}
			return null;
		}
	}

	/*
	 * @see TemplateContextType#TemplateContextType()
	 */
	public TranslationUnitContextType() {
		super();	
		// global
		addResolver(new GlobalTemplateVariables.Cursor());
		addResolver(new GlobalTemplateVariables.WordSelection());
		addResolver(new GlobalTemplateVariables.LineSelection());
		addResolver(new GlobalTemplateVariables.Dollar());
		addResolver(new GlobalTemplateVariables.Date());
		addResolver(new GlobalTemplateVariables.Year());
		addResolver(new GlobalTemplateVariables.Time());
		addResolver(new GlobalTemplateVariables.User());
		
		// translation unit
		addResolver(new File());
		addResolver(new ReturnType());
		addResolver(new Method());
		addResolver(new Project());
		addResolver(new Arguments());
	}

	public abstract TranslationUnitContext createContext(IDocument document, int offset, int length, ITranslationUnit translationUnit);
	public abstract TranslationUnitContext createContext(IDocument document, Position position, ITranslationUnit translationUnit);
}


