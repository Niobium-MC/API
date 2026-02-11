package fr.sorway.niobium.api.commands;

public class ArgumentResult<T> {
    private final T value;
    private final boolean valid;

    public ArgumentResult(T value, boolean valid) {
        this.value = value;
        this.valid = valid;
    }

    public T get() {
        return value;
    }

    public boolean isValid() {
        return valid;
    }
}