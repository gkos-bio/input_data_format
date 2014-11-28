#!/usr/bin/env python
'''
Script to build a CTTV JSON file (schema version 1.1) for project CTTV018_IBD_GWAS.
It takes a CSV file as input for the JSON.
2014-11-28:
Corrected nesting of the "evidence_chain" object by placing it under "evidence".
Removed redundant "proerties" object that contained ""experiment_specific" objects in
evidence chain methods.
'''
import csv
import json
from collections import defaultdict
from pprint import pprint

__author__ = "Michael Maguire"
__copyright__ = ""
__credits__ = ["Sarah Spain", "Samiul Hasan"]
__license__ = ""
__version__ = "1"
__maintainer__ = "Michael Maguire"
__email__ = "mmaguire@ebi.ac.uk"
__status__ = "Test"

class ParseFlatFile:
    '''
    Read in a CSV file and build all required data structures.
    '''
    def __init__(self, csv_file, biol_sub_idx, biol_obj_idx, snp_id_idx):
        '''
        Provide the CSV file path and the column indexes for the biological subject (Ensembl gene ID), disease (EFO term)
        and variant ID (RS ID for SNP).
        Assumes file is tab-delimited. Instance method "biol_sub_obj_snp_row_map()" is called in __init__.
        '''
        self.csv_file = csv_file
        (self.biol_sub_idx, self.biol_obj_idx, self.snp_id_idx) = (biol_sub_idx, biol_obj_idx, snp_id_idx)
        fh = open(self.csv_file, 'r')
        self.reader = csv.reader(fh, delimiter='\t')
        header = self.reader.next()
        self.data = []
        for row in self.reader:
            self.data.append(row)
        fh.close()
        self.biol_sub_obj_snp_row_map = self.__get_biol_sub_obj_snp_row_map(self.biol_sub_idx, self.biol_obj_idx, self.snp_id_idx)
    def get_data(self):
        '''
        Return file data as list of lists where each row is a list.
        '''
        return self.data
    def get_biol_sub_obj_snp_row_map(self):
        '''
        Return dictionary with tuple of gene, disease and SNP Id as keys mapping to entire file row for that combination.
        '''
        return self.biol_sub_obj_snp_row_map
    def __get_biol_sub_obj_snp_row_map(self, biol_sub_idx, biol_obj_idx, snp_id_idx):
        '''
        Build and return a hash where the keys are a tuple of biological subject name, biological object name
        and variant name and the value is a list consisting the entire row contents.
        '''
        biol_sub_obj_snp_row_map = {}
        for row in self.data:
            (biol_sub, biol_obj, snp_id) = (row[biol_sub_idx], row[biol_obj_idx], row[snp_id_idx])
            if biol_sub =='NA': continue
            biol_sub_obj_snp_row_map[(biol_sub, biol_obj, snp_id)] = row
        return biol_sub_obj_snp_row_map
    def get_evstr_chain_attribute_map(self, biol_sub, biol_obj, variant_id, evstr_chain_idx_map):
        '''
        Given the biol.subj. and obj. names with the variant (snp) ID, get the row keyed by a
        tuple composed of these values from "self.biol_sub_obj_snp_row_map". Use the given
        hash mapping evidence chain properties to data row indexes to build a dictionary
        mapping the input dictionary key names to the values in the row where the input dictionary
        values are indexes for the data row.
        This method can be used for evidence chain 0 and evidence chain 1.
        '''
        evstr_chain_attributes = {}
        row = self.biol_sub_obj_snp_row_map[(biol_sub, biol_obj, variant_id)]
        for key in evstr_chain_idx_map.keys():
            evstr_chain_attributes[key] = row[evstr_chain_idx_map[key]]
        return evstr_chain_attributes
    def get_biol_obj_sub_snps_map(self):
        '''
        Return a dictionary mapping biol. subj. and obj. tuple key to a list of SNPS for
        that tuple combination.
        '''
        biol_obj_sub_snps_map = defaultdict(list)
        for key in self.biol_sub_obj_snp_row_map.keys():
            biol_obj_sub_snps_map[key[0], key[1]].append(key[2])
        return biol_obj_sub_snps_map
class MakeJson:
    '''
    Class to build IBD GWAS JSON. The class vars "main_json_str", "evidence0_json_str", and "evidence1_json_str"
    are used as targets for the methods that insert the appropriate values into these "skeleton" JSONs.
    '''
    main_json_str = '''{
            "validated_against_schema_version": 1.1,
            "unique_association_fields": {
                "geneId": "http://identifiers.org/ensembl/",
                "diseaseId": "http://identifiers.org/efo/",
                "projectName": "CTTV018"
            },
            "biological_subject": {
                "about": [
                    "http://identifiers.org/ensembl/"
                ],
                "properties": {
                    "target_type": "http://identifiers.org/cttv.target/gene",
                    "activity": "http://identifiers.org/cttv.activity/up_or_down"
                }
            },
            "evidence": {
                "date_asserted": "2014-09-11T10:59:43.511Z",
                "is_associated": true,
                "association_score": {
                    "probability": {
                        "value": null,
                        "method": "GSprob"
                    },
                    "pvalue": {
                        "value": null,
                        "method": null
                    }
                },
                "evidence_codes": [
                    "http://identifiers.org/eco/ECO:0000177",
                    "http://identifiers.org/eco/ECO:0001113"
                ],
                "provenance_type": {
                    "expert": {
                        "status": true
                    }
                },
                "urls": {
                    "linkouts": [
                        {
                            "nice_name": "Original immunochip study",
                            "url": "http://identifiers.org/pubmed/23128233"
                        }
                    ]
                }
            },
            "biological_object": {
                "about": [
                    "http://identifiers.org/efo/"
                ]
            }
        }
    '''
    evidence0_json_str = '''{
            "biological_object": {
                "about": [
                    "http://identifiers.org/dbsnp/"
                ]
            },
            "biological_subject": {
                "about": [
                    "http://identifiers.org/ensembl/"
                ]
            },
            "evidence": {
                "association_score": {
                    "probability": {
                        "method": null,
                        "value": null
                    },
                    "pvalue": {
                        "method": null,
                        "value": null
                    }
                },
                "evidence_codes": [
                    "http://identifiers.org/eco/ECO:0000053"
                ],
                "experiment_specific": {
                    "Polyphen": null,
                    "cadd": null,
                    "most_severe_consequence": null,
                    "regulomeDB_score": null,
                    "sift": null,
                    "snp_gene_assertion_method": null,
                    "tssdistance": null
                }
            }
        }'''
    evidence1_json_str ='''
        {
            "biological_object": {
                "about": [
                    "http://identifiers.org/efo/"
                ]
            },
            "biological_subject": {
                "about": [
                    "http://identifiers.org/dbsnp/"
                ]
            },
            "evidence": {
                "association_score": {
                    "probability": {
                        "method": null,
                        "value": null
                    },
                    "pvalue": {
                        "method": null,
                        "value": null
                    }
                },
                "evidence_codes": [
                    "http://identifiers.org/eco/ECO:0000177",
                    "http://identifiers.org/eco/ECO:0001113"
                ],
                "experiment_specific": {
                    "a1(effect_allele)": null,
                    "a2(other_allele)": null,
                    "chromosome_position": null,
                    "freq_a1": null,
                    "genetic_association_pvalue": null,
                    "genome_build": null,
                    "hd_signal": null,
                    "odds ratio [std error]": null,
                    "rank": null
                }
            }
        }    
    '''
    def __init__(self, biol_sub, biol_obj):
        '''
        Provide a gene ID and a disease ID as arguments.
        '''
        self.biol_sub = biol_sub
        self.biol_obj = biol_obj
    def make_main_json(self):
        '''
        Read the variable "main_json_str" into a Python data structure and populate the appropriate locations
        with biological subject and object.
        Return the data structure.
        '''
        self.main_json = json.loads(MakeJson.main_json_str)
        self.main_json['unique_association_fields']['geneId'] += self.biol_sub
        self.main_json['unique_association_fields']['diseaseId'] += self.biol_obj
        self.main_json['biological_subject']['about'][0] += self.biol_sub
        self.main_json['biological_object']['about'][0] += self.biol_obj
        return self.main_json
    def make_evstr_chain0(self, variant_id, evstr_chain0_attr_map):
        '''
        Build JSON evidence string element 0.
        Read the variable "evidence0_json_str" into a Python data structure and use the
        arguments "variant_id" and the dictionary "evstr_chain0_attr_map" to populate
        it.
        Return the data structure.
        '''
        self.evstr_chain0_json = json.loads(self.evidence0_json_str)
        self.evstr_chain0_json['biological_subject']['about'][0] += self.biol_sub
        if variant_id.lower().startswith('rs'):
            self.evstr_chain0_json['biological_object']['about'][0] += variant_id
        else:
            self.evstr_chain0_json['biological_object']['about'][0] += variant_id
        for key in evstr_chain0_attr_map.keys():
            self.evstr_chain0_json['evidence']['experiment_specific'][key] = evstr_chain0_attr_map[key]
        return self.evstr_chain0_json
    def make_evstr_chain1(self, variant_id, evstr_chain1_attr_map):
        '''
        Build JSON evidence string element 1.
        Read the variable "evidence1_json_str" into a Python data structure and use the
        arguments "variant_id" and the dictionary "evstr_chain1_attr_map" to populate
        it.
        Return the data structure.
        '''
        self.evstr_chain1_json = json.loads(self.evidence1_json_str)
        if variant_id.lower().startswith('rs'):
            self.evstr_chain1_json['biological_subject']['about'][0] += variant_id
        else:
            self.evstr_chain1_json['biological_subject']['about'][0] += variant_id
        self.evstr_chain1_json['biological_object']['about'][0] += self.biol_obj
        self.evstr_chain1_json['evidence']['association_score']['probability'] = evstr_chain1_attr_map['value']
        del evstr_chain1_attr_map['value']
        for key in evstr_chain1_attr_map.keys():
            self.evstr_chain1_json['evidence']['experiment_specific'][key] = evstr_chain1_attr_map[key]
        return self.evstr_chain1_json
if __name__ == '__main__':
    '''
    Main script. The indexes for the target columns are set here (ZERO-BASED column 1 = index 0).
    If the input file columns change, this script will need to change.    
    To run, make script executable and set "csv_file" variable below to path to target input CSV file.
    '''
    csv_file = '/Users/mmaguire/CTTV/json/ibd_json/IBD_finemap_credibleSNPs_171114_CTTV_added_column.txt'
    (biol_sub_idx, biol_obj_idx, snp_id_idx) = (1, 7, 8)
    pff = ParseFlatFile(csv_file, biol_sub_idx, biol_obj_idx, snp_id_idx)
    evidence0_index_map = {
        'Polyphen': 26,
        'cadd': 24,
        'most_severe_consequence': 13,
        'regulomeDB_score': 21,
        'sift': 25,
        'snp_gene_assertion_method': 2,
        'tssdistance': 20}
    evidence1_index_map = {
        'value': 4,
        'genetic_association_pvalue': 14,
        'rank': 27,
        'hd_signal': 5,
        'a1(effect_allele)': 17,
        'a2(other_allele)': 18,
        'freq_a1': 19,
        'odds ratio [std error]': 15,
        'chromosome_position': 28}
    json_list = []
    biol_sub_obj_snp_row_map = pff.get_biol_sub_obj_snp_row_map()
    biol_obj_sub_snps_map = pff.get_biol_obj_sub_snps_map()
    for key in biol_obj_sub_snps_map.keys():
        mj = MakeJson(key[0], key[1])
        main_json = mj.make_main_json()
        evidence_chain = []
        for variant_id in biol_obj_sub_snps_map[key[0], key[1]]:
            evstr_chain0_attr_map = pff.get_evstr_chain_attribute_map(key[0], key[1], variant_id, evidence0_index_map)
            evstr_chain1_attr_map = pff.get_evstr_chain_attribute_map(key[0], key[1], variant_id, evidence1_index_map)
            evidence_chain.append(mj.make_evstr_chain0(variant_id, evstr_chain0_attr_map))
            evidence_chain.append(mj.make_evstr_chain1(variant_id, evstr_chain1_attr_map))
        main_json['evidence']['evidence_chain'] = evidence_chain
        json_list.append(main_json)
    main_json_str = json.dumps(json_list)
    print main_json_str
    