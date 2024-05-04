package com.stephen.todolist.util;

import com.stephen.todolist.pojo.Credential;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CredentialUpdater {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private Credential credential;

    public void setCredential(Credential credential) {
        writeLock.lock();
        try {
            this.credential = credential;
        } finally {
            writeLock.unlock();
        }
    }

    public Credential getCredential() {
        readLock.lock();
        try {
            return credential;
        } finally {
            readLock.unlock();
        }
    }

}
