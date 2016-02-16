Role apache/tomcat-8/instance
=============================

   Create a tomcat 8 instance.

.. ansible:role:: apache/tomcat-8-instance

   :become: No
   :dependency: apache/tomcat-defaults

   :default apache_tomcat_8_default_version:
      Default tomcat version to install (default: unset)
   :default apache_tomcat_8_instance_catalina_home:
      Default `CATALINA_HOME` to use for instance setup (default:
      `{{apache_tomcat_prefix}}/apache-tomcat-{{tomcat_version|default(apache_tomcat_8_default_version)}}`)
   :default apache_tomcat_8_instance_manager_apps:
      Default tomcat webapps to activate for the instance (default: all - see below)
   :default apache_tomcat_8_java_opts:
      Default options for the jvm
   :param manager_apps:
      Default tomcat webapp to activate for the instance (default: `"{{apache_tomcat_8_instance_manager_apps}}"`
   :param catalina_base: Base directory of the new tomcat instance.
   :param catalina_home: The apache tomcat install to use.
   :param java_home: Java installation to use for running tomcat.
   :param java_opts: Options for the jvm.
   :param server_port: Server port (default: :file:`{{apache_tomcat_server_port}}`).
   :param http_connector_port: Http connector port (default: :file:`{{apache_tomcat_http_connector_port}}`).
   :param ajp_connector_port: Ajp connector port (default: :file:`{{apache_tomcat_ajp_connector_port}}`).
   :param cache_directory: Where to cache downloaded artifacts for future reuse on play host.

   Tomcats comes with the following web applications

   - "host-manager"
   - "manager"
   - "ROOT"
   - "examples"
   - "docs"

