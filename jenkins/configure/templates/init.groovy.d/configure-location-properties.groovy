// {{ansible_managed}}
println("** Configuring location properties")
import jenkins.model.*

// System Message
instance = Jenkins.instance
instance.systemMessage = "{{system_message}}"
instance.numExecutors = {{number_of_executors}}

// Location Properties
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress("{{admin_email | default('')}}")
jenkinsLocationConfiguration.setUrl("{{url|default('')}}")
jenkinsLocationConfiguration.save()
