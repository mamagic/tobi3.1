package com.kitec.learningtest.template;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}
