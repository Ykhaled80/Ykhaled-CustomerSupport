package com.example.ykhaledcustomersupport.site;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SessionListUtil {
    private static final Map<String , HttpSession> SESSIONS = new Hashtable<>();

    public static void addSession(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }

    public static void updateSessionId(HttpSession session, String oldSession){
        synchronized (SESSIONS){
            SESSIONS.remove(oldSession);
            addSession(session);
        }
    }

    public static void removeSession(HttpSession session){
        SESSIONS.remove(session.getId());
    }

    public static List<HttpSession>getAllSessions(){
        return new ArrayList<>(SESSIONS.values());
    }

    public static int getNumberOfSessions(){
       return SESSIONS.size();
    }
}
