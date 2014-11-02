cttv-input-data-format
======================

Overview:

This folder is a Maven project (Java language) responsible for defining an object model that faithfully represents the CTTV CoreDB "evidence string" JSON format. The Maven artifact that the project defines will be a Java library, available in the public EBI maven repository, that Java developers can use to populate valid evidence strings from a team's existing data resources. This activity will take place when a group needs to produce JSON dump files at the time of release. Comprehensive tests should ensure evidence strings produced are valid, with the usual aim of reducing the cost of errors at a later stage.

It will also be possible to automatically generate a JSON schema, from the Java source code.

Updating the JSON schema:

It is intended that upon changing the schema (object model), we also update the project's version. To do this, update the 'version' value in pom.xml.

The current developers on the project are Antonio Fabregat Mundo (fabregat), Oscar Forner-Martinez (oforner) and Edward Turner (eddturner).


