package controller;

import org.restlet.security.SecretVerifier;

public class DMSystemSecreteVerifier extends SecretVerifier {
	@Override
	public int verify(String identifier, char[] secret) {
		int result = RESULT_INVALID;
		if (("admin".equals(identifier)) && compare("admin".toCharArray(),
				secret)) {
			
			result = RESULT_VALID;
		} else if (("admin".equals(identifier)) && compare("admin".toCharArray(),
				secret)) {
			result = RESULT_VALID;
		}
		return result;
	}
}