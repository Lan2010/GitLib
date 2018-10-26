package com.tianzhixing.app.exception;

public class GrpcException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GrpcException() {
		super();
	}
	public GrpcException(String msg) {
		super(msg);
	}
}
