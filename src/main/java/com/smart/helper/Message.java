package com.smart.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component("message")
public class Message {
private String content;
private String type;


public Message() {
	super();
	// TODO Auto-generated constructor stub
}
public Message(String content, String type) {
	super();
	this.content = content;
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public void removeMessageFromSession() {
    try {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");
    } catch (Exception e) {
        // Log exception
    }
}
}
