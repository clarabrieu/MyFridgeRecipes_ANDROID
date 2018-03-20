package isen.fr.myfridgerecipes;

/**
 * Created by joris.lacroix on 30/01/2018.
 */

public interface CallBackInterface {
    void success(String json);
    void error();
}
