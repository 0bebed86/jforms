package jforms.util;

import java.util.function.Function;

public class CastProcessor<R, T> {

    protected Function<T, R> front;
    protected Function<R, T> back;

    public CastProcessor(Function<T, R> front, Function<R, T> back){
        this.front= front;
        this.back = back;
    }

    public R front(T value){
        return this.front.apply(value);
    }

    public T back(R value){
        return this.back.apply(value);
    }
}
