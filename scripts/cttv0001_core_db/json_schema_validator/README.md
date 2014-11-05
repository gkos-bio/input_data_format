# json_schema_validator.py

##Required python libraries:
- json
- jsonschema
- argparse

**Authors: Michael Maguire, Samiul Hasan**

A very simple script to validate a single instance of a JSON file against a JSON schema. Below is an example of validating the Reactome example against the CTTV json schema. This will work if you have pulled the whole CTTV github library.

```javascript
python json_schema_validator.py \
-s ../../../json_schema/evidence_string_schema.json \
-i ../../../examples/cttv0006_networks_reactome/example.json
```