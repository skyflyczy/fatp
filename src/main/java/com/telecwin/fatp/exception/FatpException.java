package com.telecwin.fatp.exception;

public class FatpException extends RuntimeException{

	private static final long serialVersionUID = 7428818080722071837L;
	
	private ErrorCode errorCode;

	public FatpException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
