package ru.test.exception;

public class DuplicateBoxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5069693906201506399L;

	public DuplicateBoxException() {
		super("Duplicate box foud! Check your xml");
	}

	public DuplicateBoxException(int id) {
		super("Duplicate box with id " + id + " foud! Check your xml");
	}
}
