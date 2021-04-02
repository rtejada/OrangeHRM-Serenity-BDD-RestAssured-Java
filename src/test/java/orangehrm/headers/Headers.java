package orangehrm.headers;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Headers {

    @NotNull
    @Contract("_ -> new")
    public Header getAuthorizationHeader(String accessToken) {
        return new Header("Authorization", "Bearer " + accessToken);
    }

    @NotNull
    @Contract(" -> new")
    public Header getContentTypeHeader() {
        return new Header("Accept", ContentType.JSON.toString());
    }
}
