Role apache/tomcat-8/configuration-copy
=======================================

   Copy a custom tomcat configuration into `CATALINA_BASE`.

.. ansible:role:: apache/tomcat-8/configuration-copy

   :become: No

   :dependency: apache/tomcat-defaults

   :param catalina_home: The apache tomcat install to use.
   :param configuration: Directory with the custom tomcat configuration.

   This copies the content of +configuration+ into the tomcat instances `conf` directory.
