package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {
        String userName = "demoUserNada" + new FakerUtils().generateRandomNumber();
        String password = "demoPSW";
        User user = new User().
                setUserName(userName).
                setPassword(password).
                setEmail(userName + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        System.out.println("REGISTER COOKIES" + signUpApi.getCookies());
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println( cartApi.getCookies());
    }
}
