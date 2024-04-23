package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.site.SessionListUtil;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {

    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldId) {
        System.out.println("Session Id: " + oldId +" Changed to session Id "+ se.getSession().getId());
        SessionListUtil.updateSessionId(se.getSession(),oldId);

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session Id: " + se.getSession().getId() + " is Created.");
        SessionListUtil.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session Id: " + se.getSession().getId() + " is Destroyed.");
        SessionListUtil.removeSession(se.getSession());
    }
}
