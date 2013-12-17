package controller;

import model.UserUtil;

import org.restlet.security.SecretVerifier;

import dao.User;

public class DMSystemSecreteVerifier extends SecretVerifier {
	@Override
	public int verify(String identifier, char[] secret) {
		int result = RESULT_INVALID;

		if (identifier == null || identifier.isEmpty() || secret.length == 0) {
			return result;
		}

		UserUtil userUtil = new UserUtil();
		try {
			User user = userUtil.findByUsername(identifier);
			if (compare(user.getPassword().toCharArray(), secret)) {
				result = RESULT_VALID;
			} else {
				result = RESULT_INVALID;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}