#!/bin/sh

export CATALINA_BASE="{{catalina_base}}"
"{{catalina_home}}/bin/shutdown.sh" "$@"
