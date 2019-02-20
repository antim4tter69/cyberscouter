package com.frcteam195.cyberscouter;

public interface IOnEventListener<T> {
    void onSuccess(T object);
    void onFailure(Exception e);
}
