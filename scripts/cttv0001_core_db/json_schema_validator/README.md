# json_schema_validator.py

##Required python libraries:
- jsonschema

**Authors: Michael Maguire, Samiul Hasan**

A very simple script to validate a single instance of a JSON file against a JSON schema. Below is an example of validating the [Reactome example](../../../examples/cttv0006_networks_reactome/example.json) against the [CTTV json schema](../../../json_schema/evidence_string_schema.json).

```javascript
pip install jsonschema

python json_schema_validator.py -s evidence_string_schema.json -i example.json

```
