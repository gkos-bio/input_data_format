## Scripts written to prepare GWAS catalog data into CTTV evidence strings

Author: Tony Burdett

Python script to fetch the GWAS catalog data in CTTV json format.  This script should run from clean with no dependencies, although data is acquired using a SPARQL query against an internal dev instance of the EBI RDF platform - so you need to be connected to the EBI internal network for this to work.

Run using:
```
>: python gwas-sparql2json.py
```

This should automatically fetch GWAS data from the SPARQL endpoint, save a cached version of the SPARQL query results in the current working directory, and then output the file 'gwas.json' in CTTV format.

Note the the SNP->Gene mappings file, snp-gene-mappings.txt, must be present in the current working directory for this script to execute correctly
