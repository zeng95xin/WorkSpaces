/**
 * @Description session监听器
 */
package com.bola.nwcl.web.listener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bola.nwcl.web.model.SessionInfo;


public class SessionListener implements HttpSessionListener {
	
	private static Set<HttpSession> sessionis = new HashSet<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	sessionis.add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	sessionis.remove(se.getSession());
	}
    
    public static void addRefreshEvent(long id, Date time){
    	for(HttpSession s : sessionis){
    		SessionInfo sessionInfo=(SessionInfo)s.getAttribute("sessionInfo");
    		if(null == sessionInfo){
    			continue;
    		}
			if(id == sessionInfo.getId().longValue() && sessionInfo.getLoginTime().getTime() < time.getTime()){
				s.setAttribute("isrefresh", "true");
				sessionInfo.setLoginTime(new Date());
			}
    	}
    }
}
