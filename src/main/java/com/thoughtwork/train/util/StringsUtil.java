package com.thoughtwork.train.util;

public class StringsUtil {
	
	public static boolean isEmpty(final String cs) {
        return cs == null || cs.length() == 0;
    }
	
	/**
	 * 为字符串添加 '-'， Example: change ABC to A-B-C
	 * @param target
	 * @return
	 */
	public static String addDash(String target) {
		StringBuilder sb = new StringBuilder();
		sb.append(target.charAt(0));
		for(int i = 1; i <= target.length() - 1; i++) {
			sb.append("‐").append(target.charAt(i));
		}
		return sb.toString();
	}
}
