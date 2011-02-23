/*
 * Copyright 2010-2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.androidannotations.generation;

import com.googlecode.androidannotations.model.Instruction;

/**
 * @author Benjamin Fellous
 * @author Pierre-Yves Ricau
 */
public class ItemLongClickInstruction implements Instruction {

	private static final String FORMAT_RETURN_TRUE = //
	"" + //
			"        ((android.widget.AdapterView<?>) findViewById(%s)).setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {\n" + //
			"			@Override\n" + //
			"			public boolean onItemLongClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {\n" + //
			"				%s(%s);\n" + //
			"				return true;\n" + //
			"			}\n" + //
			"		});\n" + //
			"\n";

	private static final String FORMAT_RETURN_RESULT = //
	"" + //
			"        ((android.widget.AdapterView<?>) findViewById(%s)).setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {\n" + //
			"			@Override\n" + //
			"			public boolean onItemLongClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {\n" + //
			"				return %s(%s);\n" + //
			"			}\n" + //
			"		});\n" + //
			"\n";

	private final String methodName;

	private final String clickQualifiedId;

	private final boolean returnMethodResult;

	private final String parameterQualifiedName;

	public ItemLongClickInstruction(String methodName, String clickQualifiedId, boolean returnMethodResult, String parameterQualifiedName) {
		this.methodName = methodName;
		this.clickQualifiedId = clickQualifiedId;
		this.returnMethodResult = returnMethodResult;
		this.parameterQualifiedName = parameterQualifiedName;
	}

	public ItemLongClickInstruction(String methodName, String clickQualifiedId, boolean returnMethodResult) {
		this(methodName, clickQualifiedId, returnMethodResult, null);
	}

	@Override
	public String generate() {
		String format = returnMethodResult ? FORMAT_RETURN_RESULT : FORMAT_RETURN_TRUE;
		
		String parameterValue = parameterQualifiedName != null ? "(" + parameterQualifiedName + ") parent.getAdapter().getItem(position)" : "";
		
		return String.format(format, clickQualifiedId, methodName, parameterValue);
	}

}