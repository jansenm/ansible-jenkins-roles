Role jenkins/configure-proxy
============================

Configure the proxy settings in jenkins.

.. ansible:role:: jenkins/configure-proxy

   :sudo: No

   :param jenkins_home: Jenkins instance home.
   :param proxy_host: Proxy Hostname
   :param proxy_username: Username on proxy
   :param proxy_password: Password on proxy
   :param proxy_port: Proxy port
   :param proxy_noproxy: Use direct connections for these. One host per line.

   If *proxy_username* is defined *proxy_password* is required too.

   If *proxy_hostname* is defined *proxy_port* is required too.

   If *proxy_hostname* is undefined jenkins will be configured for direct connection.

   *no_proxy* is a list of newline separated hostnames.



