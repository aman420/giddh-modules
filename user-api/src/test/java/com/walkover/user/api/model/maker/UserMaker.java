package com.walkover.user.api.model.maker;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import org.joda.time.LocalDateTime;
import com.walkover.user.api.dao.model.User;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;

/**
 *
 * @author aman
 */
public class UserMaker {
     public static final Property<User, Long> id = new Property<>();
    public static final  Property<User, String> name = new Property<>();
    public static final Property<User, String> emailId = new Property<>();
    
    public final static Instantiator<User> User = (PropertyLookup<User> lookup) -> {
        User user = new User();
        user.setId(lookup.valueOf(id, (long) 101 ));
        user.setName(lookup.valueOf(name, "Foo"));
        user.setEmailId(lookup.valueOf(emailId, "foo@bar.com"));
        return user;
};
}            
