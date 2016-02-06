Coding Standards
================

Global Variables
****************

install_prefix
   Installation prefix to use for software. The default value is `/opt`

   It should be used for all *optional* software not installed using the default distribution
   package manager. It should not be used directly but used to define a role default installation
   prefix to allow fine grained control of the installation directory. Eg::

      jenkins_prefix: "{{install_prefix}}/jenkins-ci"

   Remember to use software specifx names for the prefix var if applicable (eg. java_home, catalina_home).

cache_directory
   Base directory of the local artefact cache. See `artefact cache`_

.. _artefact cache:

Roles
*****

Role Variables
--------------

All role arguments should be documented in `role/vars/main.yml`. But we have to keep in mind that variables set
there have very high precedence. So the distinction between role argument and role default has to be done very
carefully.

Role Arguments
   Role arguments are hard to override. Starting with version 2.0 `ansible variable precedence`_ is more clearly
   defined and tells us that role variables are only second to

   - **role and include vars**
   - block vars (only for tasks in block)
   - task vars (only for the task)
   - extra vars

Role Defaults
   Role defaults lose out to all other kind of variables. Because of this they should be named carefully to minimize
   the change for an accidently override. They should be prefix by role name (including any directory parts) joined
   by undescore.

   .. code:: yaml

       # java/lang/oracle-sdk/defaults.yml
       java_lang_oracle_sdk_default_version_8: 8.0.65

Become User
-----------

The `become user (aka sudo or su)` feature is forbidden in roles. Every role has to work without sudo rights for the
installation user.

The rationale for this is that the role designer has no idea if all of his users have the possibility to use sudo on
their machines. This information is only available to the playbook/play designer. A role should clearly document its
requirements and thats it.

A role to install apache tomcat should NOT

- Create a tomcat user on the system
- Create a directory /srv/tomcat on the system as root

Instead it should document that

- it takes a parameter catalina_base and it needs the rights to create that directory/write to it. The playbook
  designer then has to make sure thats true. Either by creating a tomcat user and that directory in the playbook or by
  having the system administrators do it.

.. IMPORTANT::

   A role that installs tomcat AND creates all desired users/groups/directories utilizing become_user is unusable in
   environments where sudo is not allowed (companies, hosted servers). The role therefore is NOT reusable in those
   environments and that equals wasted effort maintaining two roles.

   A role should only do ONE thing. Playbooks/plays are responsible to chain roles together.

Artefact Cache
--------------

To make offline usage possible all artefacts acquired from external resources should be cached locally. A global
variable is available to use for the location::

   jenkins_cache: "{{cache_directory}}/jenkins-ci



.. _ansible variable precedence: http://docs.ansible.com/ansible/playbooks_variables.html#variable-precedence-where-should-i-put-a-variable
