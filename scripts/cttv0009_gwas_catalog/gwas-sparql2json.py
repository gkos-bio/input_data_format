__author__ = 'Tony Burdett'

import sys
import datetime

import urllib
import csv

# clean existing output file
clean = open("gwas.json", "w")
clean.close()

# first, load the mappings file (rsid -> gene) as a dictionary
snpGeneMappings = {}
with open("snp-gene-mappings.txt") as mappings:
    for a in xrange(1):
        next(mappings)
    for line in csv.reader(mappings, delimiter="\t"):
        if not line[0].startswith("#"):
            if not line[0] in snpGeneMappings:
                snpGeneMappings[line[0]] = list()
            snpGeneMappings[line[0]].append(line[2])

# now get sparql results from GWAS catalog
sparqlurl = "http://rdf-hx-02.ebi.ac.uk:8171/sparql?default-graph-uri=&query=" \
            "PREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0A" \
            "PREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0A" \
            "PREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0A" \
            "PREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0A" \
            "PREFIX+dc%3A+%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Felements%2F1.1%2F%3E%0D%0A" \
            "PREFIX+dcterms%3A+%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Fterms%2F%3E%0D%0A" \
            "PREFIX+oban%3A+%3Chttp%3A%2F%2Fpurl.org%2Foban%2F%3E%0D%0A" \
            "PREFIX+ro%3A+%3Chttp%3A%2F%2Fwww.obofoundry.org%2Fro%2Fro.owl%23%3E%0D%0A" \
            "PREFIX+efo%3A+%3Chttp%3A%2F%2Fwww.ebi.ac.uk%2Fefo%2F%3E%0D%0A" \
            "PREFIX+gt%3A+%3Chttp%3A%2F%2Frdf.ebi.ac.uk%2Fterms%2Fgwas%2F%3E%0D%0A" \
            "PREFIX+gd%3A+%3Chttp%3A%2F%2Frdf.ebi.ac.uk%2Fdataset%2Fgwas%2F%3E%0D%0A%0D%0A" \
            "SELECT+DISTINCT+%3Fsnp+%3Fpubmed_id+%3Fdate+%3Fpvalue+%3Ftrait%0D%0A" \
            "WHERE+%7B%0D%0A++%3Fassociation+a+gt%3ATraitAssociation+%3B%0D%0A+++++++++++++++" \
            "gt%3Ahas_p_value+%3Fpvalue+%3B%0D%0A+++++++++++++++" \
            "oban%3Ahas_subject+%3Fsnpuri+%3B%0D%0A+++++++++++++++" \
            "oban%3Ahas_object+%3Ftrait+%3B%0D%0A+++++++++++++++" \
            "ro%3Apart_of+%3Fstudy+.%0D%0A%0D%0A++%3F" \
            "snpuri+rdfs%3Alabel+%3Fsnp+.%0D%0A%0D%0A++%3F" \
            "study+gt%3Ahas_pubmed_id+%3Fpubmed_id+.%0D%0A++%3F" \
            "study+gt%3Ahas_publication_date+%3Fdate+.%0D%0A%7D&format=text%2Ftab-separated-values&timeout=0&debug=on"
sparqlresults = urllib.urlretrieve(sparqlurl, "sparql-retrieved-results.txt")

# parse results and serialize to JSON
with open(sparqlresults[0]) as results:
    for b in xrange(1):
        next(results)
    for line in csv.reader(results, delimiter="\t"):
        if not line[0].startswith("#"):
            # grab snp, can we map?
            snpid = line[0]
            if not snpid in snpGeneMappings:
                print >>sys.stderr, "No mapping for '" + snpid + "'"
            else:
                for geneid in snpGeneMappings[snpid]:
                    print "Generating JSON for gene '" + geneid + " -> SNP '" + snpid + "'"
                    gene = {}
                    snp = {}
                    trait = {}

                    provenance = {}

                    gene["about"] = geneid
                    geneprov = dict([("evidence_codes", ["ECO_0000177", "ECO_0000053"])])

                    snp["about"] = snpid
                    snpprov = dict([("evidence_codes", ["ECO_0001113", "ECO_0000205", "ECO_0000033"])])

                    for i in range(len(line)):
                        if i == 1:
                            snpprov["type"] = {"literature": {"true": 1, "pubmed_refs": [line[i]]}}
                        if i == 2:
                            snpprov["data_asserted"] = line[i]
                        if i == 3:
                            snpprov["association_score"] = {"pvalue": line[i]}
                        if i == 4:
                            trait["about"] = line[i]

                    nextJson = {"biological_subject": gene, "biological_object": trait}

                    evidencechain = list()
                    evidencechain.append({"biological_subject": gene, "biological_object": snp, "provenance": geneprov})
                    evidencechain.append({"biological_subject": snp, "biological_object": trait, "provenance": snpprov})
                    provenance = {"experimental_evidence_specific": {"evidence_chain": evidencechain},
                                  "is_associated": 1,
                                  "date_asserted": str(datetime.datetime.now())
                                  }

                    nextJson["provenance"] = provenance

                    out = open("gwas.json", "a")
                    out.write(str(nextJson))
                    out.write("\n")
                    out.close()