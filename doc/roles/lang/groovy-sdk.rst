Role lang/groovy-sdk
====================

Download and install `apache groovy`_.

.. ansible:role:: lang/groovy-sdk

   :sudo: No
   :default lang_groovy_sdk_server: The download server to use (default: `"http://dl.bintray.com"`).
   :default lang_groovy_default_version: The default version to install. (default: `"2.4.5"`).
   :default lang_groovy_install_prefix: The installation_prefix (default: `"{{install_prefix}}/groovy-sdk"`).
   :param version: The groovy  version number to install (default: `"{{lang_groovy_default_version}}"`).
   :param prefix: Installation prefix (default: `{{lang_groov_install_prefix}}`).
   :param download_url: Url for the artefact (default: `"{{lang_groovy_sdk_download_server}}/groovy/maven/{{artifact}}"`).
   :param artifact: Name of the artifact to download (default: `"apache-groovy-sdk-{{version}}.zip"`).
   :param cache_directory: Where to cache downloaded artifacts for future reuse on play host.

   The *prefix* directory needs to exist and writable by the remote user.

   The *cache* directory needs to exist and writable by the local user.

   .. note:: The resulting :envvar:`GROOVY_HOME` is :file:`{{prefix}}/groovy-sdk-{{version}}`

.. _apache groovy: http://www.groovy-lang.org/
