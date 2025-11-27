# Homebrew (Apple Silicon)
/opt/homebrew/bin/brew shellenv | source

# Java 17 setup
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH="$JAVA_HOME/bin:$PATH"

