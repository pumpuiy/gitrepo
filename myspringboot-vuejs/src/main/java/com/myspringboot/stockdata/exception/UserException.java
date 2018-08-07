package com.myspringboot.stockdata.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.myspringboot.stockdata.common.ResponseCode;


public class UserException extends Exception {

	private static final long serialVersionUID = -1L;
	private ResponseCode errorCode;

	public UserException() {
		this(ResponseCode.USER_PASSWORD_INVALID.getDefaultMessage());
	}

	@SuppressWarnings("rawtypes")
	public UserException(String s) {
		super(s);
		contextInfo = new ArrayList();
	}

	@SuppressWarnings("rawtypes")
	public UserException(Throwable throwable) {
		super(toString(throwable), cause(throwable));
		contextInfo = new ArrayList();
	}

	private static String toString(Throwable throwable) {
		if (throwable != null && throwable.getClass() == (UserException.class)) {
			if (throwable.getCause() != null) {
				String s = throwable.getCause().getMessage();
				if (s != null)
					return s;
				else
					return throwable.getCause().toString();
			}
			String s1 = throwable.getMessage();
			if (s1 != null)
				return s1;
			else
				return throwable.toString();
		}
		if (throwable != null)
			return throwable.toString();
		else
			return null;
	}

	private static Throwable cause(Throwable throwable) {
		if (throwable != null) {
			if (throwable.getClass() == (UserException.class))
				if (throwable.getCause() != null) {
					return throwable.getCause();
				} else {
					return throwable;
				}
			return throwable;
		} else {
			return throwable;
		}
	}

	@SuppressWarnings("rawtypes")
	public UserException(String s, Throwable throwable) {
		super(s, cause(throwable));
		contextInfo = new ArrayList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserException(String s, Throwable throwable, List list) {
		super(s, cause(throwable));
		contextInfo = new ArrayList();
		if (list != null)
			contextInfo.addAll(list);
	}

	@SuppressWarnings("unchecked")
	public void addServiceContext(String s) {
		contextInfo.add(s);
	}

	public String toString() {
		Throwable throwable = getCause();
		String s = getClass().getName();
		String s1 = getLocalizedMessage();
		if (throwable != null) {
			if (s1 == null)
				return s + ": caused by: " + throwable.toString();
			String s2 = throwable.getLocalizedMessage();
			if (s1.equals(s2))
				return s + ": caused by: " + throwable.toString();
			else
				return s + ": " + s1 + ": caused by: " + throwable.toString();
		} else {
			return s1 == null ? s : s + ": " + s1;
		}
	}

	@SuppressWarnings("rawtypes")
	public void printStackTrace(PrintStream printstream) {
		Throwable throwable = getCause();
		if (throwable != null)
			synchronized (printstream) {
				throwable.printStackTrace(printstream);
				if (!contextInfo.isEmpty()) {
					printstream.println("SCA context: ");
					for (Iterator iterator = contextInfo.iterator(); iterator.hasNext(); printstream.println(iterator.next()))
						;
					printstream.println();
				}
				printstream.println("Wrapped by:");
				super.printStackTrace(printstream);
				printstream.println();
			}
		else
			synchronized (printstream) {
				super.printStackTrace(printstream);
				if (!contextInfo.isEmpty()) {
					printstream.println("SCA context: ");
					for (Iterator iterator1 = contextInfo.iterator(); iterator1.hasNext(); printstream.println(iterator1.next()))
						;
					printstream.println();
				}
				printstream.println();
			}
	}

	@SuppressWarnings("rawtypes")
	public void printStackTrace(PrintWriter printwriter) {
		Throwable throwable = getCause();
		if (throwable != null)
			synchronized (printwriter) {
				throwable.printStackTrace(printwriter);
				if (!contextInfo.isEmpty()) {
					printwriter.println();
					printwriter.println("SCA context: ");
					for (Iterator iterator = contextInfo.iterator(); iterator.hasNext(); printwriter.println(iterator.next()))
						;
					printwriter.println();
				}
				printwriter.println("Wrapped by:");
				super.printStackTrace(printwriter);
				printwriter.println();
			}
		else
			synchronized (printwriter) {
				super.printStackTrace(printwriter);
				if (!contextInfo.isEmpty()) {
					printwriter.println();
					printwriter.println("SCA context: ");
					for (Iterator iterator1 = contextInfo.iterator(); iterator1.hasNext(); printwriter.println(iterator1.next()))
						;
					printwriter.println();
				}
				printwriter.println();
			}
	}

	@SuppressWarnings("rawtypes")
	private final List contextInfo;

	@Deprecated
	public String getMessageCode() {
		ResponseCode code = getErrorCode();
		return code.getCode();
	}

	@Deprecated
	public void setMessageCode(String messageCode) {
		errorCode = ResponseCode.getResponseCodeByString(messageCode);
	}

	public ResponseCode getErrorCode() {
		if (errorCode == null) {
			errorCode = ResponseCode.USER_PASSWORD_INVALID;
		}
		return errorCode;
	}

	public void setErrorCode(ResponseCode errorCode) {
		this.errorCode = errorCode;
	}
}
