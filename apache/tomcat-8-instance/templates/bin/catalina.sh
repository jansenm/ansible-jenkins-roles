#!/bin/sh
# {{ansible_managed}}

export CATALINA_BASE="{{catalina_base}}"
"{{catalina_home}}/bin/catalina.sh" "$@"