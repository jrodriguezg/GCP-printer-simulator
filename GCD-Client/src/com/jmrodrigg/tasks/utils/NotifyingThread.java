package com.jmrodrigg.tasks.utils;

import com.jmrodrigg.tasks.ThreadCompleteListener;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Author: jrodriguezg
 * Date: 02/01/17
 */
public abstract class NotifyingThread extends Thread {
    private final Set<ThreadCompleteListener> listeners
            = new CopyOnWriteArraySet<ThreadCompleteListener>();
    public final void addListener(final ThreadCompleteListener listener) {
        listeners.add(listener);
    }
    public final void removeListener(final ThreadCompleteListener listener) {
        listeners.remove(listener);
    }
    private final void notifyListeners() {
        for (ThreadCompleteListener listener : listeners) {
            listener.notifyThreadComplete(this);
        }
    }
    @Override
    public final void run() {
        try {
            doRun();
        } finally {
            notifyListeners();
        }
    }
    public abstract void doRun();
}