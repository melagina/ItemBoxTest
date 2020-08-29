package ru.test.exception;

public class DuplicateItemException extends Exception {

    private static final long serialVersionUID = 6662907571420578618L;

    public DuplicateItemException() {
        super("Duplicate item foud! Check your xml");
    }

    public DuplicateItemException(int id) {
        super("Duplicate item with id " + id + " foud! Check your xml");
    }

}
