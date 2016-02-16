Role jenkins/configure
======================

Base configuration for jenkins.

- Authentication
- Authorization
- Users
- Admin email and url.

.. ansible:role:: jenkins/configuration

   :become: No

   :default jenkins_default_authentication_strategy:
         Default authentication strategy (*hudson_private*)
   :default jenkins_default_authorization_strategy:
         Default authorization strategy (*project_matrix*)

   :param jenkins_home: Jenkins instance home.
   :param authentication_strategy:
         Authentication strategy to configure (default: *jenkins_default_authentication_strategy*)
   :param authorization_strategy:
         Authorization strategy to configure (default: *jenkins_default_authorization_strategy*)

   :param users: A list of users to create. Read the comments below.

   :param admin_email: Admin email address.
   :param url: Jenkins url.
   :param system_message: System message. (default: "Provisioned with ansible, all changes will be lost")
   :param number_of_executors: Number of executors (default: 2)
   :param node_mode: Node usage method. Valid values are "NORMAL" and "EXCLUSIVE" (default: "NORMAL")
   :param node_labels: Node labels as string. (default: "")


   **Authentication**

   Set *authentication_strategy* to one of the following values.

   Jenkins' own user database
      hudson_private
   Disable Security
      no_authentication
   LDAP
      not yet implemented
   Unix user/group database
      not yet implemented

   **Authorization**

   Set *authorization_strategy* to one of the following values.

   Anyone can do anything
      no_authorization
   Logged-in user can do anything
      full_control_once_logged_in
   Matrix-based security
      global_matrix
   Project-base Matrix Authorization Strategy
      project_matrix

   **Permissions**

   Jenkins permissions have string presentation. They consist of <group>.<permission>.

   These are the group names for some permissions. The given category is from jenkins *Configure Global Sercurity* Page.
   To give a permission just append the permission to the group (eg. hudson.model.Hudson.Administer). In doubt
   configure the permission manually apply and check the :file:`jenkins/config.xml` file in :envvar:`JENKINS_HOME`

   Overall
      hudson.model.Hudson
   Credentials
      com.cloudbees.plugins.credentials.CredentialsProvider
   Slave
      hudson.model.Computer
   Job
      hudson.model.Item
   Run
      hudson.model.Run
   View
      hudson.model.View
   SCM
      hudson.scm.SCM

   **Users**

   If set *users* is expected to be a list of hashes to define the users to create.

   Only the authentication strategy *hudson_private* support creating users in jenkins.

   Only for authorization strategy *project_matrix* and *global_matrix* permissions are configurable. Authentication
   strategy does not matter for permissions. Unless its *no_authentication*.

   The password will never be changed if the user already exists.

   Example configuration:

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




.. _job-dsl: https://github.com/jenkinsci/job-dsl-plugin

