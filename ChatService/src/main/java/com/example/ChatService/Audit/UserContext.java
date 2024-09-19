package com.example.ChatService.Audit;

public class UserContext {
    private static final ThreadLocal<Long> context = new ThreadLocal<>();

    public static void setUserId(Long id) {
        clear();
        context.set(id);
    }

    static Long getUserId() {
        return context.get();
    }

    static void clear() {
        context.remove();
    }
}
