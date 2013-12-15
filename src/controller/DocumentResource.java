package controller;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ServerResource;

import common.DocumentResourceInterface;

public class DocumentResource extends ServerResource implements
		DocumentResourceInterface {

	@Override
	public Representation retrive(Variant variant) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(Representation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Representation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Variant variant) {
		// TODO Auto-generated method stub

	}

}
