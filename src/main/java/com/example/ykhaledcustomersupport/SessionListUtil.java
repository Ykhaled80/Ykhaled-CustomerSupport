package com.example.ykhaledcustomersupport;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SessionListUtil {
    private static final Map<String , HttpSession> Sessions = new Hashtable<>();

    public static void addSession(HttpSession session) {
        Sessions.put(session.getId(), session);
    }

    public static void updateSessionId(HttpSession session, String oldSession){
        synchronized (Sessions){
            Sessions.remove(oldSession);
            addSession(session);
        }
    }

    public static void removeSession(HttpSession session){
        Sessions.remove(session.getId());
    }

    public static List<HttpSession>getAllSessions(){
        return new ArrayList<>(Sessions.values());
    }

    public static int getNumberOfSessions(){
       return Sessions.size();
    }
}
