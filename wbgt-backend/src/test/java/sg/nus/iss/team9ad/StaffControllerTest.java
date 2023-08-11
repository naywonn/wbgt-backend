package sg.nus.iss.team9ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import sg.nus.iss.team9ad.repo.StaffRepo;

import javax.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class StaffControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private StaffRepo staffRepo;

	private HttpSession httpSession;

	/*@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.httpSession = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
						.param("userid", "1")
						.param("password", "password"))
				.andExpect(status().isOk())
				.andReturn()
				.getRequest()
				.getSession();
	}

	@Test
	public void testLoginSuccess() throws Exception {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserid(1);
		loginRequest.setPassword("password1");

		String requestBody = new ObjectMapper().writeValueAsString(loginRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
						.session((MockHttpSession) httpSession))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Login successful"));
	}

	@Test
	public void testLogout() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/logout").session((MockHttpSession) httpSession))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Logout successful"));
	}*/
}
