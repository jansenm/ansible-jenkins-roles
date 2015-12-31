Role apache/tomcat/defaults
===========================

A convenience role that provides some common defaults for all tomcat related roles.

You should not need to call it directly unless you implement a tomcat role.

.. ansible:role:: apache/tomcat-defaults

   :sudo: No

   :default apache.tomcat.download_url: URL for tomcat archive download (default: "http://mirror.synyx .de/apache/tomcat")
   :default apache.tomcat.prefix: Default installation prefix (default: :file:`/opt/default`).
   :default apache.tomcat.server_port: Tomcat server port (default: 8005).
   :default apache.tomcat.http_connector_port: The nopn-ssl/tls http/1.1 connector port (default: 8080).
   :default apache.tomcat.ajp_connector_port: The ajp connector port (default: 8009).
