## Changes in CTTV JSON schema from version 1.0 to 1.1

1.0 | 1.1 | Description
--------|--------|--------
|{"validated_against_schema_version"}|This is a **required field**. Please indicate which version of the CTTV schema you have validated your JSON data against.
|{"unique_association_fields"}|This is the same as before. Just to clarify, if you have an array of values that make a particular key unique, please provide these as comma-separated concatenated values. [See example](../help/README.md#7-what-is-the-unique_association_fields-codeblock-in-the-json).
{"provenance"}|{"evidence"}| Field has been renamed.
{"provenance"}{"type"}|{"evidence"}{"provenance_type"}|Field has been renamed.
{"biological_subject"}{"experimental_evidence_specific"}, {"provenance"}{"experimental_evidence_specific"}, {"biological_object"}{"experimental_evidence_specific"}|{"biological_subject"}{"experiment_specific"}, {"evidence"}{"experiment_specific"}, {"biological_object"}{"experiment_specific"}|Fields have been renamed.
{"biological_subject"}{"association_context"}|{"biological_subject"}{"target_type"}|Field has been renamed.
{"provenance"}{"type"}{"literature"}{"pubmed_refs"}|{"evidence"}{"provenance_type"}{"literature"}{"pubmed_refs"}|This used to be an array of integers. Now it is an array of "http://identifiers.org/pubmed/[0-9]+$" pattern-matched URIs.  [See help](../help/README.md#miriam-registry-questions)
{"biological_subject"}{"about"}|{"biological_subject"}{"about"}| Now an array of "^http://identifiers.org/.+/.+" pattern-matched URIs. [See help](../help/README.md#miriam-registry-questions)
{"biological_subject"}{"properties"}{"association_context"}|{"biological_subject"}{"properties"}{"target_type"}| Now a  "^http://identifiers.org/cttv[.]{1,1}target/.+$" pattern-matched URI. [See help](../json_schema/cttv_uris_namespaces.md)
{"biological_subject"}{"properties"}{"activity"}|{"biological_subject"}{"properties"}{"activity"}| Now a  "^http://identifiers.org/cttv[.]{1,1}activity/.+$" pattern-matched URI. [See help](../json_schema/cttv_uris_namespaces.md)
{"biological_object"}{"about"}|{"biological_object"}{"about"}| Now an array of "^http://identifiers.org/.+/.+" pattern-matched URIs. [See help](../help/README.md#miriam-registry-questions)
{"provenance"}{"evidence_codes"}|{"evidence"}{"evidence_codes"}|Now an array of "^http://identifiers.org/eco/ECO:[0-9]{7,7}$" pattern-matched URIs. [See help](../help/README.md#1-which-evidence-codes-should-i-use)
{"provenance"}{"association_score"}{"probability"}|{"evidence"}{"association_score"}{"probability"}|This is now a **required field**. [See help](../help/README.md#9-association_scoreprobability-is-a-required-field-but-what-if-my-dataset-doesnt-estimate-probability)
