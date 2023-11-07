package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 技记 包府
 */
@Component
public class SessionManager {
	public static final String SESSION_COOKIE_NAME = "mySessionId";
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

	/**
	 * 技记 积己
	 */
	public void createSession(Object value, HttpServletResponse response) {
//技记 id甫 积己窍绊, 蔼阑 技记俊 历厘
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);
//捻虐 积己
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(mySessionCookie);
	}

	/**
	 * 技记 炼雀
	 */
	public Object getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if (sessionCookie == null) {
			return null;
		}
		return sessionStore.get(sessionCookie.getValue());
	}

	/**
	 * 技记 父丰
	 */
	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if (sessionCookie != null) {
			sessionStore.remove(sessionCookie.getValue());
		}
	}

	private Cookie findCookie(HttpServletRequest request, String cookieName) {
		if (request.getCookies() == null) {
			return null;
		}
		return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(cookieName)).findAny()
				.orElse(null);
	}
}