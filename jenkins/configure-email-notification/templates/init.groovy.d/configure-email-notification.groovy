// {{ansible_managed}}
println("** Configuring email notification")

import jenkins.model.*

def inst = Jenkins.getInstance()
def desc = inst.getDescriptor("hudson.tasks.Mailer")

{% if smtp_host is none %}
// No smtp host configured. Reset everything

println("   * No smtp host configured. Resetting")
desc.smtpHost = null
desc.smtpPort = null
desc.replyToAddress = null
desc.charset = null
desc.setSmtpAuth(null, null)

{% else %}
// A smtp host is configured.

desc.smtpHost = "{{smtp_host}}"
desc.smtpPort = "{{smtp_port}}"
desc.useSsl = {{"true" if smtp_usessl else "false"}}
desc.replyToAddress = "{{smtp_replyto}}"
desc.charset = "{{smtp_charset}}"
{% if smtp_username is defined %}
println("   * Username/Password configured.")
desc.setSmtpAuth("{{smtp_username}}", "{{smtp_password}}")
{% else %}
println("   * Username/Password not configured.")
{% endif %}

{% endif %}

// Save the changes
desc.save()
