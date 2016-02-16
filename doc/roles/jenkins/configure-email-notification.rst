Role jenkins/configure-email-notification
=========================================

Configure the email notification part of the jenkins configuration.

.. ansible:role:: jenkins/configure-email-notification

   :become: No

   :param jenkins_home: Jenkins instance home.
   :param smtp_host: SMTP host to configure. If undefined mailer is reset to null.
   :param smtp_port: SMTP port (default: 587).
   :param smtp_replyto: Sender email address.
   :param smtp_usessl: Use SSL for smtp connection? (default: false).
   :param smtp_user: Username for smtp authentication. This is optional.
   :param smtp_password: Password for smtp authentication.
   :param smtp_charset: Character set for emails (default: utf-8).

   If *smtp_host* is null then all other attributes are optional.

   If *smtp_host* is given then *smtp_replyto* is required too.

   If *smtp_username* is given then *smtp_password* is required too.

   Parameter *smtp_usessl* is about SSL not TLS!
