// {{ansible_managed}}
import hudson.ProxyConfiguration
import jenkins.model.Jenkins
{% if proxy_host is defined %}
    {% if proxy_username is defined %}
proxy = new ProxyConfiguration(
    "{{proxy_host}}",
    {{proxy_port}},
    "{{proxy_username}}",
    "{{proxy_password}}",
    "{{proxy_no_proxy | replace ("\n", "\\n")}}")
    {% else %}
proxy = new ProxyConfiguration(
    "{{proxy_host}}",
    {{proxy_port}},
    null,
    null,
    "{{proxy_no_proxy | replace ("\n", "\\n")}}")
    {% endif %}
{% else %}
proxy = null
Jenkins.instance.proxy = proxy
{% endif %}