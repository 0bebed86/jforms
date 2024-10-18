package jforms.util.vector;

import java.util.function.Function;

public class Vector3T<T> extends Vector2T<T> {
    public T z;

    public Vector3T(T x, T y, T z) {
        super(x, y);

        this.z = z;
    }

    public Vector3T(Vector3T<T> another) {
        super(another);

        this.z = another.z;
    }

    @Override
    public IVector<T> get() {
        return this;
    }

    @Override
    public T[] getElements() {
        return (T[]) (new Object[]{x, y, z});
    }

    @Override
    public T getElementByOrdinal(int ordinal) {
        switch (ordinal) {
            case 0:
                return x;

            case 1:
                return y;

            case 2:
                return z;

            default:
                break;
        }

        return null;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public IVector<T> set(IVector another) {
        Vector3T<T> value = (Vector3T<T>) another;

        this.x = value.x;
        this.y = value.y;
        this.z = value.z;

        return this;
    }

    @Override
    public IVector<T> setElements(T[] elements) {
        this.x = elements[0];
        this.y = elements[1];
        this.z = elements[2];

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

            case 2:
                z = element;
                break;

            default:
                break;
        }
    }

    @Override
    public IVector<T> invoke(IVector<T> another, boolean copy, Function<T, T> function) {
        Vector3T<T> instance = copy ? new Vector3T<T>(this) : this;
        Vector3T<T> value = (Vector3T<T>) another;

        instance.x = function.apply(value.x);
        instance.y = function.apply(value.y);
        instance.z = function.apply(value.z);

        return instance;
    }
}
