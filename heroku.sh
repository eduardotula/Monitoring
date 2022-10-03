#!/bin/sh
# =============================================================================
# This script automatically splits the Heroku ENV DATABASE_UR variable
# into the three JDBC variables needed from Quarkus.
#
# It will only do the split if the DB_HEROKU_SPL is set to "true".
#
# If you set DB_HEROKU_SPL to 'false', you must pass the Quarkus parameters:
#   - DB_JDBC_URL;
#   - DB_JDBC_USER;
#   - DB_JDBC_PASSWORD.
#
# For test purposes, you can set the DB_ECHO_VALUES to 'true' and check if the
# values are correct.
#
# Pattern of DATABASE_UR from Heroku:
# --------------------------------------
#   postgres://username:password@host:port/databasename
#
# Pattern of JDBC variables of Quarkus:
# -------------------------------------
#   quarkus.datasource.jdbc.url=jdbc:postgresql://host:port/databasename
#   quarkus.datasource.username=username
#   quarkus.datasource.password=password
#
# =============================================================================

echo DB_HEROKU_SPL=[$DB_HEROKU_SPL]

# check for 'true' in string (case insensitive)
if [[ "${DB_HEROKU_SPL}" == "true" ]]; then

  # cut the DATABASE_UR after '@'
  export DB_JDBC_URL=jdbc:postgresql://${DATABASE_UR#*@}

  # substring the DATABASE_UR between '//' and ':'
  export DB_JDBC_USER=$(expr $DATABASE_UR : '.*/\([^:]*\):.*')

  # substring the DATABASE_UR between ':' and '@'
  export DB_JDBC_PASSWORD=$(expr $DATABASE_UR : '.*:\([^@]*\)@.*')

fi

# check for 'true' in string (case insensitive)
if [[ "${DB_ECHO_VALUES,,}" == "true" ]]; then

  echo DATABASE_UR=[$DATABASE_UR]
  echo DB_JDBC_URL=[$DB_JDBC_URL]
  echo DB_JDBC_USER=[$DB_JDBC_USER]
  echo DB_JDBC_PASSWORD=[$DB_JDBC_PASSWORD]

fi