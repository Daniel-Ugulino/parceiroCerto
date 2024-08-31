package com.example.userservice.Audit;

public class UserContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setUserId(String userEmail) {
        context.set(userEmail);
    }

    static String getUserId() {
        return context.get();
    }

    static void clear() {
        context.remove();
    }
}
