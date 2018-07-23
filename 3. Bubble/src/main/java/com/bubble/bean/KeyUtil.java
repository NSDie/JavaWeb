package com.bubble.bean;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public final class KeyUtil {
    private static final Key key = MacProvider.generateKey();

    private KeyUtil() {
    }

    public static Key getKey() {
        return key;
    }
}
