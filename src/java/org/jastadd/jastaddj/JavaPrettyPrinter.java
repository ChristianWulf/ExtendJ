package org.jastadd.jastaddj;
/*
 * The JastAdd Extensible Java Compiler (http://jastadd.org) is covered
 * by the modified BSD License. You should have received a copy of the
 * modified BSD license with this compiler.
 * 
 * Copyright (c) 2005-2008, Torbjorn Ekman
 * All rights reserved.
 */

import AST.*;

import java.util.*;
import java.io.*;


class JavaPrettyPrinter extends Frontend {
	public static void main(String args[]) {
		if(!compile(args))
			System.exit(1);
	}

	public static boolean compile(String args[]) {
		return new JavaPrettyPrinter().process(
				args,
				new BytecodeParser(),
				new JavaParser() {
					@Override
					public CompilationUnit parse(InputStream is, String fileName) throws IOException, beaver.Parser.Exception {
						return new parser.JavaParser().parse(is, fileName);
					}
				});
	}

	@Override
	protected void processErrors(Collection errors, CompilationUnit unit) {
		super.processErrors(errors, unit);
		System.out.println(unit);
	}
	@Override
	protected void processNoErrors(CompilationUnit unit) {
		System.out.println(unit);
	}

	@Override
	protected String name() {
		return "JastAddJ Java PrettyPrinter";
	}

	@Override
	protected String version() {
		return JastAddJResources.getVersion();
	}
}