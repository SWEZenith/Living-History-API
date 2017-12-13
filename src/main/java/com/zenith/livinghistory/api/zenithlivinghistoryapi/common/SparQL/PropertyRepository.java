package com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.DTO.QueryProperty;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.enums.IRITypes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PropertyRepository {

    //region Private Members

    private static Map<IRITypes, List<QueryProperty>> repository;

    //endregion

    //region Constructor

    private PropertyRepository() {}

    static {

        repository = new LinkedHashMap<>();

        List<QueryProperty> individualProperties = new ArrayList<>();
        individualProperties.add(new QueryProperty("rdfs:label", true, ""));
        individualProperties.add(new QueryProperty("dbo:birthPlace", false, ""));
        individualProperties.add(new QueryProperty("dbo:almaMater", false, ""));
        individualProperties.add(new QueryProperty("dbo:child", false, ""));
        individualProperties.add(new QueryProperty("dbo:occupation", false, "dbo:title"));
        individualProperties.add(new QueryProperty("dbo:parent", false, ""));
        individualProperties.add(new QueryProperty("dbo:party", false, ""));
        individualProperties.add(new QueryProperty("<http://purl.org/dc/terms/subject>", false, ""));
        individualProperties.add(new QueryProperty("dbo:notableWork", false, ""));
        individualProperties.add(new QueryProperty("<http://purl.org/linguistics/gold/hypernym>", false, ""));
        individualProperties.add(new QueryProperty("dbo:board", false,""));
        individualProperties.add(new QueryProperty("dbo:title", true, ""));
        individualProperties.add(new QueryProperty("dbo:battle", false, ""));
        individualProperties.add(new QueryProperty("dbo:office", true, ""));
        individualProperties.add(new QueryProperty("dbo:nationality", false,""));
        individualProperties.add(new QueryProperty("dbo:religion",false,""));
        individualProperties.add(new QueryProperty("dbo:team", false,""));

        repository.put(IRITypes.Individual, individualProperties);

        List<QueryProperty> cityProperties = new ArrayList<>();
        cityProperties.add(new QueryProperty("rdfs:label", true, ""));
        cityProperties.add(new QueryProperty("dbo:country", false, ""));
        cityProperties.add(new QueryProperty("dbo:leaderParty", false, ""));
        cityProperties.add(new QueryProperty("<http://purl.org/linguistics/gold/hypernym>", false, ""));

        repository.put(IRITypes.City, cityProperties);

    }

    //endregion

    //region Public Methods

    public static List<QueryProperty> getProperties(IRITypes type){

        return repository.get(type);
    }

    //endregion

}
