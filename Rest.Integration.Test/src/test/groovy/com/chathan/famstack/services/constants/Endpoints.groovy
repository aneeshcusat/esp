package com.chathan.famstack.services.constants

import java.util.Properties;

class Endpoints {

    private static final String HOST = "host"
    private static final String PORT = "port"
    private static final String SSLPORT = "sslport"
    private static final String CAPORT = "caport"

    private static Properties properties

    public static baseUrl() {
        "http://${hostname()}:${port()}"
    }

    public static httpsBaseUrl() {
        "https://${hostname()}:${sslport()}"
    }

    public static hostname() {
        properties.get(HOST)
    }

    public static port() {
        properties.get(PORT)
    }

    public static sslport() {
        properties.get(SSLPORT)
    }

    static {
        properties = new Properties()
        try {
            InputStream inputStream = Endpoints.class.getClassLoader().getResourceAsStream("test.properties")

            if (inputStream != null) {
                properties.load(inputStream)
            } else {
                println "Can't find test.properties file on classpath, setting default values"
                initialiseDefault()
            }
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }

    private static void initialiseDefault() {
        properties.put(HOST, "localhost")
        properties.put(PORT, "7003")
        properties.put(SSLPORT, "7103")
    }
}
