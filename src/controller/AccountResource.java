package controller;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ServerResource;

import common.AccountResourceInterface;

public class AccountResource extends ServerResource implements
		AccountResourceInterface {

	@Override
	public Representation retrive() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation add(Form form) {
		if (isInRole("Admin")) {
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updatePassword(Variant variant) {
		return null;		
	}


	@Override
	public Representation remove() {
		return null;
	}
}
