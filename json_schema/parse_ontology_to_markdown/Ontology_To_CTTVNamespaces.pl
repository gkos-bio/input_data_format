#!/usr/bin/perl
#!/usr/bin/perl -d:ptkdb
#
# Samiul Hasan,S-1-5-21-1229272821-1123561945-682003330-1629074
# Sun Nov  2 14:25:55 2014
#
# 
#

BEGIN {use lib '/GWD/bioinfo/projects/neuro_public_platform/libs/perl/lib/perl5/site_perl/5.8.5';}

use vars qw($VERSION);

$VERSION=".01";

use strict;
use warnings;
use diagnostics;
use Getopt::Long;
use Pod::Usage;
use Data::Dumper;
use Log::Log4perl qw(:easy);

my ($opt_debug, $opt_help, $opt_man, $opt_versions);

GetOptions(
  'debug'   => \$opt_debug,
  'help!'     => \$opt_help,
  'man!'      => \$opt_man,
  'versions!' => \$opt_versions,
) or pod2usage(-verbose => 1) && exit;

pod2usage(-verbose => 2) && exit if defined $opt_help;
pod2usage(-verbose => 2) && exit if defined $opt_man;

if ($opt_debug) {
    Log::Log4perl->easy_init(
        {
            level  => $DEBUG,
            layout => '%p [%r] %c %L %m%n',
            file   => ">debug.log"
        }
    );

    print STDERR "Debug info in debug.log\n";

}
else {
    Log::Log4perl->easy_init(
        { level => $INFO, layout => '%p [%r] %c %L %m%n' } );
}

###############PARAMETERS
my $ONTOFILE = "../../ontology/cttv_core.owl";
my $cmd = "rdf.arq --data $ONTOFILE --query list_all_classes.rq --results TSV | grep cttv";
my %ns = (
  "http://www.targetvalidation.org/cttv_core" => { 'prefix' => "cttv", 'desc' => 'CTTV core: For internal CTTV-core-ontology mapping' } ,
  "http://www.targetvalidation.org/cttv_core/experiment" => { 'prefix' => "cttvexp", 'desc' => 'CTTV experiment: For association_context OR activity fields' }
);
###############PARAMETERS

# # # #

#read ontologies from cmd line

my %hits;

open(ONTO, "$cmd|");
while (my $o = <ONTO>) {
    chomp($o);
    my ($uri, $res) = ($o =~ /\<(\S+)\/(\S+)\>/);
    my $comment = ($o =~ /\t\"(.+)\"/) ? "**$1**" : "-";
    $comment =~ s/\s*\*\*/\*\*/;
    
    #print "$uri -> $res -> $comment\n";
    
    push(@{$hits{$uri}}, [$res, $comment]);
    
}
close(ONTO);
#exit();

open(OUT, ">../cttv_uris_namespaces.md");
print OUT "# Namespace prefixes and resources in CTTV ontology\n";
print OUT "**These are all the resources specified in the current CTTV core ontology:**\n\n";
foreach my $n (sort keys %ns) {
    
        
        my ($url, $prefix, $desc) = ($n, $ns{$n}->{prefix}, $ns{$n}->{desc});
        
        print OUT "## $desc:\n";
        print OUT "resource | comment | Where to use in JSON | prefix | url\n";
        print OUT ":-------:|:------:|:---:|:-------:|:-------------------:\n";
        
        DEBUG "url = $url; prefix = $prefix; desc = $desc";
        
        foreach my $h (sort @{$hits{$url}}) {
            
            my ($res, $com) = ($h->[0], $h->[1]);
            DEBUG "$h\n";
            
            print OUT "**$prefix:$res** | $com | $desc | $prefix | $url/$res\n";
            
        }
        print "\n\n";
        
}
close(OUT);

INFO "Wrote to ../cttv_uris_namespaces.md";



# # # #

END{
  if(defined $opt_versions){
    print
      "\nModules, Perl, OS, Program info:\n",
      "  Pod::Usage            $Pod::Usage::VERSION\n",
      "  Getopt::Long          $Getopt::Long::VERSION\n",
      "  strict                $strict::VERSION\n",
      "  Perl version          $]\n",
      "  Perl executable       $^X\n",
      "  OS                    $^O\n",
      "  $0\n",
      "\n\n";
  }
}

=head1 TITLE

 

=head1 SYNOPSIS

=head1 DESCRIPTION

Prints out the namespaces, uris in the CTTV core ontology and writes these to a file
called ../cttv_uris_namespaces.md .

=head1 ARGUMENTS

 Place
 --help      print Options and Arguments
 --man       print complete man page

=head1 OPTIONS

 --versions   print Modules, Perl, OS, Program info
 --debug 0    don't print debugging information (default)
 --debug 1    print debugging information

=head1 LICENSE

This software is released under the same terms as perl itself.
If you don't know what that means visit http://perl.com/

=head1 AUTHOR

Copyright (C) Samiul Hasan,S-1-5-21-1229272821-1123561945-682003330-1629074 2014
All rights reserved

=cut

