package controller;

import model.UserUtil;

import org.restlet.data.ClientInfo;
import org.restlet.security.Enroler;
import org.restlet.security.Role;

import restlet.DMSystemApplication;
import dao.User;

public class DMSystemEnroler implements Enroler {
	private DMSystemApplication app;
	
	public DMSystemEnroler() {
		super();
	}
	
	public DMSystemEnroler(DMSystemApplication app) {
		super();
		
		this.app = app;
	}
	
	@Override  
    public void enrole(ClientInfo clientInfo) {
		UserUtil userUtil = new UserUtil();
		User user;
		try {
			user = userUtil.findByUsername(clientInfo.getUser().getIdentifier());
			
			if (user != null) {
				User.Authority authority = User.Authority.values()[user.getAuthority()];
				Role role =  this.app.getRole(authority);  
		        clientInfo.getRoles().add(role) ; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
    }
}
