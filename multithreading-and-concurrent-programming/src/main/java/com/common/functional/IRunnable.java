package com.common.functional;

import java.util.function.Function;

import com.common.interfaces.IWorker;

@FunctionalInterface
public interface IRunnable extends Function<IWorker, Runnable> {

}
