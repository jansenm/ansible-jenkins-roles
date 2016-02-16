Role apache/tomcat-8/install
============================

   Download and install tomcat 8.

   The *prefix* directory needs to exist and writable.

   .. note::
      This does not create a running tomcat instance. It downloads and copies the archive to the host. To create
      a instance see :ansible:role:`role-apache/tomcat-8-instance`.

.. ansible:role:: apache/tomcat-8-install

   :become: No
   :dependency: apache/tomcat-defaults

   :param version: Tomcat version to install (eg. 8.0.29)
   :param checksum: Checksum for the tomcat archive (eg. 4b7ba7a5af0a5c395c0740fc011b59d1)
   :param prefix: Install prefix (default: :file:`{{apache_tomcat_prefix}}`).

