Jenkins
=======

.. ansible:role:: jenkins/core

   :sudo: No

   :default jenkins_download_mirror: Mirror to download from (default: http://mirrors.jenkins-ci.org/war)

   :param version: Jenkins version to install (eg. 1.643)
   :param checksum: Checksum for the jenkins archive (eg. 4b7ba7a5af0a5c395c0740fc011b59d1)
   :param catalina_base: Tomcat instance to install into.
   :param jenkins_home: Jenkins instance home.
   :param cache_directory: Where to cache downloaded artifacts for future reuse on play host.

   Download and drop jenkins into the tomcat at *catalina_base*.

   The instance is NOT started after dropping jenkins. Neither is it stopped before doing that.


.. ansible:role:: jenkins/plugins

   :sudo: No

   :default jenkins_plugins_download_url: The url to download from.

   :param jenkins_home: Jenkins instance home.
   :param plugins: List of plugins to install. See description (default: []).

   Download and install the plugins specified in *plugins*.

   An example

   .. code-block:: yaml

      - jenkins_plugins:

        # CVS Plug-in
        - name: cvs
          version: "2.12"
          enabled: true
          bundled: true
          pinned: yes

        # Javadoc Plugin
        - name: javadoc
          version: "1.3"
          enabled: true
          bundled: true
          pinned: yes

   If your plugins get overwritten after installation on a restart you need to look up the definition of
   `pinned plugins`_.

   To clone a jenkins instance executing the following script will give you the plugins in correct format

   .. code-block:: groovy

      for (plugin in Jenkins.instance.pluginManager.plugins)
      {
        pinned = plugin.pinned
        if (plugin.hasUpdate())
        {
          if (plugin.bundled)
          {
            pinned = false
          }
          version = plugin.updateInfo.version
        }
        else
        {
          version = plugin.version
        }

        println("""\
          # ${plugin.displayName}
          - name: ${plugin.shortName}
            version: "${version}"
            enabled: ${plugin.active}
            bundled: ${plugin.bundled}
            pinned: ${pinned}
          """.stripIndent())

      }

.. _pinned plugins: https://wiki.jenkins-ci.org/display/JENKINS/Pinned+Plugins


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


.. ansible:role:: jenkins/configuration

   :sudo: No

   :default jenkins_default_authentication_strategy:
      Default authentication strategy (*hudson_private*)
   :default jenkins_default_authorization_strategy:
      Default authorization strategy (*project_matrix*)

   :param jenkins_home: Jenkins instance home.
   :param authentication_strategy:
      Authentication strategy to configure (default: *jenkins_default_authentication_strategy*)
   :param authorization_strategy:
      Authorization strategy to configure (default: *jenkins_default_authorization_strategy*)

   :param bootstrap_git_repo: Repository with job-dsl scripts to bootstrap this jenkins.
   :param bootstrap_git_branch: Branch to checkout from git_repository (default: master)

   :param smtp_host: Smtp host to configure. If undefined mailer is reset to null.
   :param smtp_port: Smtp port.
   :param smtp_usessl: Use SSL for smtp connection? (default: true)
   :param smtp_user: Username for smtp authentication. This is optional.
   :param smtp_password: Password for smtp authentication.

   :param admin_email: Admin email address.
   :param url: Jenkins url.

   :param users: A list of users to create. Read the comments below.

   .. note:: This role is destined to be split into several smaller roles :).

   *authentication_strategy*

   Currently implemented are the following strategies.

   hudson_private
     TODO
   no_authentication
     TODO

   **authorization_strategy**

   Currently implemented are the following strategies.

   full_control_once_logged_in
     TODO
   global_matrix
     TODO
   no_authorization
     TODO
   project_matrix
     TODO

   **bootstrap_git_repo**

   If defined a bootstrap job will be created that checks out the repository at *bootstrap_git_repository* to follow
   *bootstrap_git_branch* and then executes all scripts that match the file glob `bootstrap/*.job` as a 'job-dsl'_
   script. This naturally assumes job-dsl is install and ready.

   **users**

   A List of hashes to define the users we should create.

   .. code-block:: yaml

      users: [
        {
        id: 'admin',
        password: 'admin',
        fullname: "Technical Administration Account",
        email: 'admin@example.com',
        permissions: [ "hudson.model.Hudson.Administer" ]
        },
        {
        id: 'mjansen',
        password: 'mjansen',
        email: 'mjansen@example.com',
        fullname: "Michael Jansen",
        permissions: [ "hudson.model.Hudson.Administer" ]
        },
        {
        id: 'test1',
        password: 'mjansen',
        email: 'mjansen@example.com',
        fullname: "Michael Jansen",
        permissions: [
          "hudson.model.Computer.Configure",
          "hudson.model.Item.Discover",
          "hudson.model.View.Delete",
          "hudson.model.Run.Update",
          "com.cloudbees.plugins.credentials.CredentialsProvider.Update" ]
        }
      ]

   * Only for authentication strategy *hudson_private* user can be created.

   * Only for authorization strategy *project_matrix* and *global_matrix* permission are configurable. Authentication
     strategy does not matter for permissions.

   * The password will never be changed if the user already exists.

   * The strings required for permission are best acquired by configuring them in a jenkins and then checking *TODO*.


.. _job-dsl: https://github.com/jenkinsci/job-dsl-plugin



