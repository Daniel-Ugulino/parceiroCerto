package com.example.ChatService.Audit;

public class UserContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setUserId(String userId) {
        context.set(userId);
    }

    public static String getUserId() {
        return context.get();
    }

    static void clear() {
        context.remove();
    }
}
