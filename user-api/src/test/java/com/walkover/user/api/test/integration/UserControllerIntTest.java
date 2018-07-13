package com.walkover.user.api.test.integration;
//import com.walkover.user.api.controller.commons.MainController;
import com.natpryce.makeiteasy.MakeItEasy;
import com.walkover.user.api.model.maker.UserMaker;
import com.walkover.user.api.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import com.walkover.user.api.services.v1.UserService;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.utils.commens.JsonUtils;
import org.springframework.http.MediaType;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.resources.v1.UserResources;
import java.util.List;
import org.springframework.test.web.servlet.MvcResult;


/**
 *
 * @author aman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        {
                "classpath:configuration/mvc-dispatcher.xml"
        }
)
@Transactional
public class UserControllerIntTest {
     @Autowired
    private UserController usercontroller;
    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();
    }

    @Test
    public void createUser() throws Exception {
       User user=MakeItEasy.make(a(UserMaker.User));

        String API = "/user";
        //System.out.println(user.toString());
     
            
          MvcResult result=mockMvc.perform(post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("user", user)
                .content(JsonUtils.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body.name").value(user.getName()))
                .andReturn();

    }
    @Test
    public void findAllUser() throws Exception {
           User user=MakeItEasy.make(a(UserMaker.User));
           userRepository.save(user);
           List<User> user1=(List<User>)userRepository.findAll();
           
           String API = "/user";
           
           MvcResult result=mockMvc.perform(get(API)
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("user",user1 ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body[0].name").value(user1.get(0).getName()))
                .andReturn();
           
}   
    @Test
    public void shouldUpdateUser() throws Exception {
        User user=MakeItEasy.make(a(UserMaker.User));
        User updateUser=new User();
        updateUser.setId(101);
        updateUser.setEmailId("foo@bar.com");
        updateUser.setName("aman");
        userRepository.save(user);
        //String user1=(String)user.getEmailId();
        //UserResources userToUpdate = new UserResources(user1);
        
         String API = "/user";
        MvcResult result=mockMvc.perform(put(API)
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("user", updateUser)
                .content(JsonUtils.toJson(updateUser)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body.name").value(updateUser.getName()))
                .andReturn();
        
    }
}
