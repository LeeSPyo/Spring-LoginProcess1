package hello.login.web.session;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import hello.login.domain.member.Member;

class SessionManagerTest {
	SessionManager sessionManager = new SessionManager();

	@Test
	void sessionTest() {
		
		//技记 积己
		MockHttpServletResponse response = new MockHttpServletResponse();
		Member member = new Member();
		sessionManager.createSession(member, response);
		
		//夸没俊 览翠 捻虐 历厘
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(response.getCookies());
		
		//技记 炼雀
		Object result = sessionManager.getSession(request);
		assertThat(result).isEqualTo(member);
		
		//技记 父丰
		sessionManager.expire(request);
		Object expired = sessionManager.getSession(request);
		assertThat(expired).isNull();
	}
}