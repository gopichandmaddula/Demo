package imcs.util;

import java.util.ArrayList;
import java.util.List;

import imcs.entity.Authority;

public class StaticCache {
	public static List<Authority> getAuthority() {
		List<Authority> authorities = new ArrayList<>();
		
		authorities.add(new Authority("ROLE_USER"));
		authorities.add(new Authority("ROLE_TELLER"));
		authorities.add(new Authority("ROLE_ADMIN"));
		
		return authorities;
	}
}
