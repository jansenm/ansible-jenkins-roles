// {{ansible_managed}}

import jenkins.model.*

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
{% if admin_email is defined -%}
jenkinsLocationConfiguration.setAdminAddress("{{admin_email}}")
{% endif -%}
{% if url is defined -%}
jenkinsLocationConfiguration.setUrl("{{url}}")
{% endif -%}
jenkinsLocationConfiguration.save()