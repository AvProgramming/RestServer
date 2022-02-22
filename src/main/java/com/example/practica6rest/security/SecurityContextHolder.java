package com.example.practica6rest.security;

import com.example.practica6rest.model.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityContextHolder {

    private Client client;

    private static final ThreadLocal<SecurityContextHolder> threadLocalContext = new ThreadLocal<>();

    public static SecurityContextHolder get() {
        if (threadLocalContext.get() == null) {
            threadLocalContext.set(new SecurityContextHolder());
        }
        return threadLocalContext.get();
    }

    public static void drop() {
        threadLocalContext.remove();
    }
}
