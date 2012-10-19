package com.esprit.jsf.ctrl;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.stateless.KO;

@ManagedBean(name = "kk")
@SessionScoped
public class TestKO {

	private KO ko;

	public String test() {
		ko.tir("direct");
		return "";
	}

	@PostConstruct
	private void prepare() {
		Hashtable env = new Hashtable();
		env.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
		env.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		env.put("java.naming.provider.url", "remote://192.168.100.132:4447");
		env.put("jboss.naming.client.ejb.context", "true");
		env.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		
		InitialContext ctx = null;
		try {
			ctx = new InitialContext(env);
			ko = (KO) ctx.lookup("ejb:/ejb/KOBean!com.esprit.stateless.KO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
}
