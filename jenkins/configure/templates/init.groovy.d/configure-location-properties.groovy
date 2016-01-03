// {{ansible_managed}}
println("** Configuring location properties")
import jenkins.model.*

// System Message
instance = Jenkins.instance
instance.systemMessage = "{{system_message}}"
instance.numExecutors = {{number_of_executors}}
// There is an assertion to make sure only valid values come through.
instance.mode = "{{node_mode | upper}}"
instance.labelString = "{{ node_labels }}"


// Location Properties
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress("{{admin_email | default('')}}")
jenkinsLocationConfiguration.setUrl("{{url|default('')}}")
jenkinsLocationConfiguration.save()
