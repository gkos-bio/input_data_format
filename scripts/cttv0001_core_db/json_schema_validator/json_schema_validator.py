#!/usr/bin/env python

"""
Authors: Michael Maguire, Samiul Hasan
"""

import json
import jsonschema
import argparse

#grab arguments
parser = argparse.ArgumentParser(description='Validate a json instance file against a json schema')
parser.add_argument('-s', dest='schema_file', action='store', help='json schema file', required=True )
parser.add_argument('-i', dest='instance_file', action='store', help='single json instance file', required=True )
args = parser.parse_args()

schema = json.loads(open(args.schema_file, 'r').read())
json_test = json.loads(open(args.instance_file, 'r').read())
jsonschema.validate(json_test, schema, format_checker=jsonschema.FormatChecker())
