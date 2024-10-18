package jforms.util.vector;

import java.util.function.Function;

public interface IVector<T> {
    public IVector<T> get();
    public T[] getElements();
    public T getElementByOrdinal(int ordinal);
    public int getSize();

    public IVector<T> set(IVector<T> another);
    public IVector<T> setElements(T[] elements);
    public void setElementByOrdinal(int ordinal, T element);

    public IVector<T> invoke(IVector<T> another, boolean copy, Function<T, T> function);
}
