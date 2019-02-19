package com.frcteam195.cyberscouter;

public interface IOnEventListener<T> {
    public void onSuccess(T object);
    public void onFailure(Exception e);
}
