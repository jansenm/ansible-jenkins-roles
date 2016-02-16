Role apache/tomcat-8/configuration-default
==========================================

   Copy the tomcat default configuration into `CATALINA_BASE`.

.. ansible:role:: apache/tomcat-8/configuration-default

   :become: No

   :dependency: apache/tomcat-defaults

   :param catalina_base: Base directory of the new tomcat instance.
   :param catalina_home: The apache tomcat install to use.

   This copies the conf/ directory from the tomcat archive into the instance/

