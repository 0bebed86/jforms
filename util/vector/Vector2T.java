package jforms.util.vector;

import java.util.function.Function;

public class Vector2T<T> implements IVector<T> {
    public T x, y;

    public Vector2T(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Vector2T(Vector2T<T> another) {
        this.set(another);
    }

    @Override
    public IVector<T> get() {
        return this;
    }

    @Override
    public T[] getElements() {
        return (T[]) (new Object[]{x, y});
    }

    @Override
    public T getElementByOrdinal(int ordinal) {
        switch (ordinal) {
            case 0:
                return x;

            case 1:
                return y;

            default:
                break;
        }

        return null;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public IVector<T> set(IVector another) {
        Vector2T<T> value = (Vector2T<T>) another;

        this.x = value.x;
        this.y = value.y;

        return this;
    }

    @Override
    public IVector<T> setElements(T[] elements) {
        this.x = elements[0];
        this.y = elements[1];

        return this;
    }

    @Override
    public void setElementByOrdinal(int ordinal, T element) {
        switch (ordinal) {
            case 0:
                x = element;
                break;

            case 1:
                y = element;
                break;

            default:
                break;
        }
    }

    @Override
    public IVector<T> invoke(IVector<T> another, boolean copy, Function<T, T> function) {
        Vector2T<T> instance = copy ? new Vector2T<T>(this) : this;
        Vector2T<T> value = (Vector2T<T>) another;

        instance.x = function.apply(value.x);
        instance.y = function.apply(value.y);

        return instance;
    }
}
