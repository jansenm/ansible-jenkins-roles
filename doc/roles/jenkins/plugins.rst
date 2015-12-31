Role jenkins/plugins
====================

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

.. ansible:role:: jenkins/plugins

   :sudo: No

   :default jenkins_plugins_download_url: The url to download from.

   :param jenkins_home: Jenkins instance home.
   :param plugins: List of plugins to install. See description (default: []).

.. _pinned plugins: https://wiki.jenkins-ci.org/display/JENKINS/Pinned+Plugins


