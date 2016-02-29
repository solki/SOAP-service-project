
package au.edu.unsw.soacourse;

import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

