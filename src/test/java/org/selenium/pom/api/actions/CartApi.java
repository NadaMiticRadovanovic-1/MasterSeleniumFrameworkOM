package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;

    public CartApi() {

    }


    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Response addToCart(int productId, int quantity) {

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        if (cookies == null) {
            cookies = new Cookies();
        }

        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
                log().all().
                when().
                post("/?wc-ajax=add_to_cart").
                then().
                log().all().
                extract().
                response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add the product" + productId + " to the cart"
                    + ", HTTP Status Code: " + response);
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
