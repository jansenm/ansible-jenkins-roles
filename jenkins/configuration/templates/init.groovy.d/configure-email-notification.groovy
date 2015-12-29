// {{ansible_managed}}

import jenkins.model.*

def inst = Jenkins.getInstance()

def desc = inst.getDescriptor("hudson.tasks.Mailer")

desc.setSmtpHost("{{smtp_host}}")
{% if smtp_port is defined %}
desc.setSmtpPort("{{smtp_port}}")
{% endif %}
desc.setUseSsl({{ smtp_usessl | default("true")}})
{% if smtp_user is defined %}
desc.setSmtpAuth("{{smtp_user}}", "{{smtp_password}}")
{% endif %}
desc.setReplyToAddress("{{smtp_replyto}}")
desc.setCharset("{{smtp_characterset | default('utf-8')}}")

desc.save()
