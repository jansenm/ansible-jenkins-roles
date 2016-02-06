Role apache/tomcat/defaults
===========================

A convenience role that provides some common defaults for all tomcat related roles.

You should not need to call it directly unless you implement a tomcat role.

.. ansible:role:: apache/tomcat-defaults

   :sudo: No

   :default apache_tomcat_download_server: Mirror to download from (default: "http://mirror.synyx.de/apache/tomcat")
   :default apache_tomcat_prefix: Default installation prefix (default: :file:`{{install_prefix}}/apache-tomcat`).
   :default apache_tomcat_server_port: Tomcat server port (default: 8005).
   :default apache_tomcat_http_connector_port: The nopn-ssl/tls http/1.1 connector port (default: 8080).
   :default apache_tomcat_ajp_connector_port: The ajp connector port (default: 8009).
