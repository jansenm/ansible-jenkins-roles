Role apache/tomcat-8/instance
=============================

   Create a tomcat 8 instance.

   Currently the instance will have all apps running.

   * host-manager
   * manager
   * ROOT
   * examples
   * docs

.. ansible:role:: apache/tomcat-8-instance

   :sudo: No
   :dependency: apache/tomcat-defaults

   :param catalina_base: Base directory of the new tomcat instance.
   :param catalina_home: The apache tomcat install to use.
   :param java_home: The java to use.
   :param server_port: Server port (default: :file:`{{apache_tomcat_server_port}}`).
   :param http_connector_port: Http connector port (default: :file:`{{apache_tomcat_http_connector_port}}`).
   :param ajp_connector_port: Ajp connector port (default: :file:`{{apache_tomcat_ajp_connector_port}}`).
   :param cache_directory: Where to cache downloaded artifacts for future reuse on play host.
