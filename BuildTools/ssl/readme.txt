add the below in tomcat server.xml
to create certificate
keytool -genkey -alias famstack -keyalg RSA -keystore c:\openssl\famstack.jks

  <Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS"
			   keystoreFile="conf/famstack.jks"
               keystoreType="JKS"
               keystorePass="famstack"
               keyPass="famstack" />
