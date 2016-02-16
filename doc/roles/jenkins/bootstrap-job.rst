Role jenkins/bootstrap-job
==========================

Install a bootstrap job into jenkins

A bootstrap job will be created that checks out from a :abbr:`vcs(version control system)` and then executes all scripts
that match the file glob `bootstrap/*.job` as a job-dsl script. This naturally assumes the `job-dsl plugin`_ is
installed and ready.

Currently only git is supported but adding support for other vcs is a simple matter.

.. ansible:role:: jenkins/bootstrap-job

   :become: No

   :param jenkins_home: Jenkins instance home.
   :param git_repo: Git repository with job-dsl scripts to bootstrap the jenkins instance.
   :param git_branch: Branch to checkout from git_repository (default: master)


.. include:: ../../_references.txt

