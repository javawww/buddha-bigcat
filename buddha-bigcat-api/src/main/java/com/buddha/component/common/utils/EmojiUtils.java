package com.buddha.component.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmojiUtils {
	
	/**
	 * 三方登录昵称带有表情处理为“*”
	 * @param nickName
	 * @return
	 */
	public static String filterEmoji(String nickName) {
		//nick_name 所获取的用户昵称 
		if (nickName == null) {
			return nickName;
		}
		Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		Matcher emojiMatcher = emoji.matcher(nickName);
		if (emojiMatcher.find()) {
			//将所获取的表情转换为*
			nickName = emojiMatcher.replaceAll("*");
			return nickName;
		}
		return nickName;
	}
}
