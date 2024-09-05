package com.example.FeedbackService.Audit;

public class UserContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setUserId(String userId) {
        context.set(userId);
    }

    static String getUserId() {
        return context.get();
    }

    static void clear() {
        context.remove();
    }
}
