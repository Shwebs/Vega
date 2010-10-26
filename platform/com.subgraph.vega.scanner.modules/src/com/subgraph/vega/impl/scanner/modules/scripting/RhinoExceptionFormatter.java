package com.subgraph.vega.impl.scanner.modules.scripting;

import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.WrappedException;

public class RhinoExceptionFormatter {
	private final String message;
	private final RhinoException e;
	public RhinoExceptionFormatter(String message, RhinoException e) {
		this.message = message;
		this.e = e;
	}
	
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(message +"\nSource file: "+ e.sourceName() + " at line "+ e.lineNumber() +" and column "+ e.columnNumber() + "\n");
		if(e.details() != null)
			sb.append(e.details() + "\n");
		if(e.lineSource() != null) {
			sb.append(e.lineSource() + "\n");
			if(e.columnNumber() != 0) {
				for(int i = 1; i < e.columnNumber(); i++)
					sb.append(" ");
				sb.append("^\n");
			}
		}
		if(e instanceof WrappedException) {
			WrappedException wrapped = (WrappedException) e;
			Throwable ex = wrapped.getWrappedException();
			for(StackTraceElement el: ex.getStackTrace()) {
				sb.append("\tat ");
				sb.append(el);
				sb.append("\n");

			}
			
		}
		return sb.toString();
	}

}
