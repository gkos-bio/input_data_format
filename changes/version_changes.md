## Changes in CTTV JSON schema from version 1.0 to 1.1

1.0 | 1.1 | Description
--------|--------|--------
-|{"validated_against_schema_version"}|This is a **required field**. Please indicate which version of the CTTV schema you have validated your JSON data against.
{"unique_association_fields"}|{"unique_association_fields"}|This is the same as before and is a **required field**. Just to clarify, if you have an array of values that make a particular key unique, **please provide these as comma-separated concatenated values. [See example](../help/README.md#7-what-is-the-unique_association_fields-codeblock-in-the-json)**.
{"provenance"}{"association_score"}|{"evidence"}{"association_score"}|This is now a **required field**. Ability to set to 'null' if it does not apply. [See help](../help/README.md#9-evidenceassociation_score-is-a-required-field-but-what-if-my-dataset-doesnt-estimate-this)
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
Note about [identifiers.org URIs](http://www.ebi.ac.uk/miriam/main/collections/)|Note about [identifiers.org URIs](http://www.ebi.ac.uk/miriam/main/collections/)|**Please make sure you use the correct URIs!** e.g. experimental factors ontology (EFO) = [http://identifiers.org/efo/nnnnnnn](http://www.ebi.ac.uk/miriam/main/collections/MIR:00000391), evidence codes ontology (ECO) = [http://identifiers.org/eco/ECO:nnnnnnn](http://www.ebi.ac.uk/miriam/main/collections/MIR:00000055), Orphanet = [http://identifiers.org/orphanet/nnnnn](http://www.ebi.ac.uk/miriam/main/collections/MIR:00000220)
{provenance}{type}{database}{dbxref}{id}|{evidence}{provenance_type}{database}{dbxref}{id}|This now accepts only identifier.org namespaces e.g. http://identifiers.org/orphanet
{provenance}{type}{database}{dbxref}{url}|{evidence}{provenance_type}{database}{dbxref}{url}|This now accepts only identifier.org namespaces e.g. http://identifiers.org/orphanet/93298
