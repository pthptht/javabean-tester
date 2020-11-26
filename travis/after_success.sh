#!/bin/bash
#
# JavaBean Tester (https://github.com/hazendaz/javabean-tester)
#
# Copyright 2012-2020 Hazendaz.
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of The Apache Software License,
# Version 2.0 which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0.txt
#
# Contributors:
#     CodeBox (Rob Dawson).
#     Hazendaz (Jeremy Landis).
#


# Get Commit Message
commit_message=$(git log --format=%B -n 1)
echo "Current commit detected: ${commit_message}"

# We build for several JDKs on Travis.
# Some actions, like analyzing the code (Coveralls) and uploading
# artifacts on a Maven repository, should only be made for one version.
 
# If the version is 1.8, then perform the following actions.
# 1. Notify Coveralls.
#    a. Use -q option to only display Maven errors and warnings.
#    b. Use --settings to force the usage of our "settings.xml" file.
# 2. Deploy site
#    a. Use -q option to only display Maven errors and warnings.
#    b. Use --settings to force the usage of our "settings.xml" file.

if [ $TRAVIS_REPO_SLUG == "hazendaz/javabean-tester" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ] && [[ "$commit_message" != *"[maven-release-plugin]"* ]]; then

  if [ ${TRAVIS_JDK_VERSION} == "oraclejdk8" ]; then

    # Deploy coverage to coveralls
    ./mvnw clean test jacoco:report coveralls:report -q --settings ./mvn/settings.xml
    echo -e "Successfully ran coveralls under Travis job ${TRAVIS_JOB_NUMBER}"

    # Deploy site to ghpages
    # Cannot currently run site this way
    # ./mvnw site site:deploy -q --settings ./mvn/settings.xml
    # echo -e "Successfully deploy site under Travis job ${TRAVIS_JOB_NUMBER}"
  else
    echo "Java Version does not support additonal activity for travis CI"
  fi
else
  echo "Travis Pull Request: $TRAVIS_PULL_REQUEST"
  echo "Travis Branch: $TRAVIS_BRANCH"
  echo "Travis build skipped"
fi