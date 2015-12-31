Role jenkins/configuration
==========================

Configure jenkins.

.. note:: This role is destined to be split into several smaller roles :).

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

