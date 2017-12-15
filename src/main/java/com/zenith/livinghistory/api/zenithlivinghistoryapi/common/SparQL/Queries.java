package com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL;

public final class Queries {

    //region Semantic Body query
    /**
     * @param - The text that will be searched as city and person.
     */
    public  static String semanticBody =
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                    + "PREFIX  dbo: <http://dbpedia.org/ontology/>"
                    + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "SELECT ?thing ?name ?type "
                    + "WHERE  {"
                    + "{"
                    + "SELECT ?thing ?name (\"City\" AS ?type) WHERE"
                    + "{"
                    + "?thing a dbo:Place;"
                    + "a dbo:City;"
                    + "rdfs:label ?name."
                    + "FILTER langMatches(lang(?name),\"en\")"
                    + "FILTER(CONTAINS(?name, \"%1$s\"))"
                    + "}"
                    + "}"
                    + "UNION"
                    + "{"
                    + "SELECT ?thing ?name (\"Person\" AS ?type) WHERE"
                    + "{"
                    + "?thing a foaf:Person;"
                    + "a dbo:Person;"
                    + "foaf:name ?name."
                    + "FILTER langMatches(lang(?name),\"en\")"
                    + "FILTER(CONTAINS(?name, \"%1$s\"))"
                    + "}"
                    + "}"
                    + "}";
    //endregion

    //region Query to differentiate whether iri is city or not.
    public static String isCity =
            "PREFIX  dbo: <http://dbpedia.org/ontology/>\n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "SELECT"
            + "(\"true\" AS ?result)"
            + "WHERE"
            + "{"
	        + "<%1$s> rdf:type ?type."
	        + "<%1$s> rdfs:label ?label."
            + "FILTER langMatches( lang(?label), \"EN\" )"
            + "FILTER (?type IN (dbo:City))"
            + "}"
            + "GROUP BY"
            + "?label";
    //endregion

    //region Query to get properties of city.
    public static String getCityProperties =
            "PREFIX  dbo: <http://dbpedia.org/ontology/>\n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "SELECT"
            + "(?label 	 		        AS ?name)"
	        + "(SAMPLE(?counrtyLabel)   AS ?counrtyName)"
            + "(SAMPLE(?peopleCount)    AS ?population)"
            + "(SAMPLE(?thumbnail)      AS ?image)"
            + "(SAMPLE(?webSite) 	    AS ?webPage)"
            + "WHERE"
            + "{"
            + "<%1$s> rdfs:label               ?label."
            + "<%1$s> dbo:country              ?ruler."
            + "<%1$s> dbo:populationTotal      ?peopleCount."
            + "<%1$s> dbo:thumbnail            ?thumbnail."
            + "<%1$s> dbo:wikiPageExternalLink ?webSite."
            + "?ruler rdfs:label ?counrtyLabel."
            + "FILTER langMatches( lang(?label), \"EN\" )"
            + "}"
            + "GROUP BY "
            + "?label "
            + "LIMIT 1";
    //endregion

    //region Query to get properties of individual.
    public static String getIndividualProperties =
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX  dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
            "SELECT * \n" +
            "WHERE \n" +
            "{\n" +
            "OPTIONAL { \n" +
            "<%1$s> foaf:name ?name.\n" +
            "FILTER langMatches( lang(?name), \"EN\" )\n" +
            "}\n" +
            "OPTIONAL { \n" +
            "<%1$s> dbo:birthDate?birthDate.\n" +
            "}\n" +
            "OPTIONAL { \n" +
            "<%1$s> dbo:birthPlace ?homeTown.\n" +
            "?homeTown rdfs:label ?birthPlace\n" +
            "FILTER langMatches( lang(?birthPlace), \"EN\" )\n" +
            "}\n" +
            "OPTIONAL {\n" +
            "<%1$s> dbo:occupation ?occupation.\n" +
            "?occupation dbo:title ?jobTitle.\n" +
            "}\n" +
            "OPTIONAL { \n" +
            "<%1$s> dbo:thumbnail ?image. \n" +
            "}\n" +
            "OPTIONAL { \n" +
            "<%1$s> dbo:wikiPageExternalLink ?webPage.\n" +
            "}\n" +
            "}";
    //endregion

    //region Query to get tags of an individual or a city.

    public static String getTags = "" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX  dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +

            "SELECT DISTINCT\n" +
            "%1$s\n" +
            "WHERE {" +
            "OPTIONAL {\n" +
            "<%2$s> %3$s ?value\n" +
            "%4$s"+
            "}\n" +
            "}";

    public static String langFilter = "FILTER langMatches( lang(?value), \"EN\" )\n";
    public static String subQuery =
            "OPTIONAL {" +
            "?value %1$s ?label\n" +
            "FILTER langMatches( lang(?label), \"EN\" )\n" +
            "}\n";

    //endregion
}