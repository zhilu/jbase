package jsoap;

import java.util.Properties;

public class UseProxy {
    public UseProxy() {
        Properties prop = System.getProperties();
        prop.setProperty("http.proxyHost", Constant.proxyHost);
        prop.setProperty("http.proxyPort",  Constant.proxyPort);
    }

}
