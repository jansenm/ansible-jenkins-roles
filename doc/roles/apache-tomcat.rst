Apache Tomcat
=============

A selection of roles to download install and configure `apache tomcat`_

.. ansible:role:: apache/tomcat-defaults

   :sudo: No

   :default apache.tomcat.download_url: URL for tomcat archive download (default: "http://mirror.synyx .de/apache/tomcat")
   :default apache.tomcat.prefix: Default installation prefix (default: :file:`/opt/default`).
   :default apache.tomcat.server_port: Tomcat server port (default: 8005).
   :default apache.tomcat.http_connector_port: The nopn-ssl/tls http/1.1 connector port (default: 8080).
   :default apache.tomcat.ajp_connector_port: The ajp connector port (default: 8009).

   A convenience role that provides some common defaults for all tomcat related roles.

   You should not need to call it directly unless you implement a tomcat role.


.. ansible:role:: apache/tomcat-8-install

   :sudo: No
   :dependency: apache/tomcat-defaults

   :param version: Tomcat version to install (eg. 8.0.29)
   :param checksum: Checksum for the tomcat archive (eg. 4b7ba7a5af0a5c395c0740fc011b59d1)
   :param prefix: Install prefix (default: :file:`{{apache.tomcat.prefix}}`).

   Download and install tomcat 8.

   The *prefix* directory needs to exist and writable.

   .. note:: This does not create a running tomcat instance. It downloads and copies the archive to the host. To create
      a instance see :ansible:role:`role-apache/tomcat-8-instance`.

.. ansible:role:: apache/tomcat-8-instance

   :sudo: No
   :dependency: apache/tomcat-defaults

   :param catalina_base: Base directory of the new tomcat instance.
   :param catalina_home: The apache tomcat install to use.
   :param java_home: The java to use.
   :param server_port: Server port (default: :file:`{{apache.tomcat.server_port}}`).
   :param http_connector_port: Http connector port (default: :file:`{{apache.tomcat.http_connector_port}}`).
   :param ajp_connector_port: Ajp connector port (default: :file:`{{apache.tomcat.ajp_connector_port}}`).
   :param cache_directory: Where to cache downloaded artifacts for future reuse on play host.

   Create a tomcat 8 instance.

   The setup done here is the one described in `tomcat advanced configuration`_.

   Currently the instance will have all apps running.

   * host-manager
   * manager
   * ROOT
   * examples
   * docs

.. _apache tomcat: https://tomcat.apache.org/
.. _tomcat advanced configuration: https://tomcat.apache.org/tomcat-8.0-doc/RUNNING.txt
