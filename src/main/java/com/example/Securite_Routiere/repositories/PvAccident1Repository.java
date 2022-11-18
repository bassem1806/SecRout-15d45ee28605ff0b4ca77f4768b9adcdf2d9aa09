package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.PvAccident1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository


public interface PvAccident1Repository extends PagingAndSortingRepository<PvAccident1, Long> {


    static Page<PvAccident1> findsort(Pageable pageable) {

        return PvAccident1Repository.findsort(pageable);
    }


    @Query(value = "SELECT COUNT(g.gouvernorat_name) ," +
            " g.gouvernorat_name, " +
            "YEAR (p.dateaccid)," +
            " monthname(p.dateaccid)" +
            " FROM sec_routierev0.pv_accident1 p " +
            " left join sec_routiereV0.Delegation d " +
            " on p.delegation_id=d.delegation_id " +
            " left join sec_routierev0.Gouvernorat g " +
            " on  g.gouvernorat_id = d.gouvernorat_id " +

            " WHERE ( (p.dateaccid) between '2013-01-01' and  '2014-12-31')" +
            "GROUP BY g.gouvernorat_name ,year(p.dateaccid)  ", nativeQuery = true)
    Object[] countTotalaccidByGov();





/*

    @Query(value = "SELECT COUNT(g.gouvernorat_name),"+
            "YEAR (p.dateaccid),"+
            "monthname(p.dateaccid)"+
           " FROM sec_routierev0.pv_accident1 p"+
            "left join sec_routiereV0.Delegation d"+
           " on p.delegation_id=d.delegation_id"+
           " left join sec_routierev0.Gouvernorat g"+
            "on  g.gouvernorat_id = d.gouvernorat_id"+
           " WHERE YEAR (p.dateaccid) = 2022  "+
          "  GROUP BY  year(p.dateaccid) ,MONTHname (p.dateaccid)"+
            "ORDER BY Year, MONTHname", nativeQuery = true)
    Object[] countTotalaccidByperiode();


*/

/*

    @Query(value="SELECT   "+
            "p.pvaccid_id,p.dateaccid,c.casuseaccident_name,p.cause_principale,p.dateimatric,p.point_kmaccid,p.numimatric,u.unite_name,d.delegation_name,g.gouvernorat_name,sc.signe,tr.typeroute_name,str.sitroute_name,tmp.temps_name,pt.part_name,bl.firstname_blesse,bl.cin_blesse,bl.sexe_blesse,bl.age_blesse,bl.etat_blesse,bl.observation_blesse "+
            "FROM sec_routierev0.pv_accident1 p" +
            "left join sec_routierev0.Unite u"+
            "on p.unite_id=u.unite_id "+
            "left join sec_routierev0.Delegation d"+
            "on p.delegation_id=d.delegation_id"+
            "left join sec_routierev0.Gouvernorat g"+
            "on  g.gouvernorat_id = d.gouvernorat_id"+
            "left join sec_routierev0.signaux_circulation sc"+
            "on p.signe_id=sc.signe_id"+
            "left join sec_routierev0.type_route tr"+
            "on p.typeroute_id=tr.typeroute_id"+
            "left join sec_routierev0.situation_route str"+
            "on p.situationroute_id=str.situationroute_id"+
            "left join sec_routierev0.temps tmp"+
            "on p.temps_id=tmp.temps_id"+
            "LEFT JOIN sec_routierev0.pvaccident_causeaccident pc"+
            "ON pc.pvaccid_id = p.pvaccid_id"+
            "LEFT JOIN sec_routierev0.cause_accident c "+
            "ON c.causeaccident_id = pc.causeaccident_id"+
            "LEFT JOIN sec_routierev0.pvaccident_part pp"+
            "ON pp.pvaccid_id = p.pvaccid_id"+
            "LEFT JOIN sec_routierev0.part pt"+
            "ON pt.part_id = pp.part_id"+
            "LEFT JOIN sec_routierev0.pvaccident_blesse pvb"+
            "ON pvb.pvaccid_id = p.pvaccid_id"+
            "LEFT JOIN sec_routierev0.blesse bl"+
            "ON bl.blesse_id = pvb.blesse_id	"+
            " WHERE ( (p.dateaccid) between '2021-01-01' and  '2022-12-31')"+
            "GROUP BY p.pvaccid_id,p.addreaccid,p.dateaccid,c.casuseaccident_name,p.cause_principale,p.dateimatric,p.point_kmaccid,p.numimatric,u.unite_name,d.delegation_name,g.gouvernorat_name,sc.signe,tr.typeroute_name,str.sitroute_name,tmp.temps_name,pt.part_name,bl.firstname_blesse,bl.cin_blesse,bl.sexe_blesse,bl.age_blesse,bl.etat_blesse,bl.observation_blesse",nativeQuery=true)
    List<PvAccident1> findAllpvAccidentrecent();

*/

    @Query(value = "SELECT * FROM pv_accident1 p WHERE ( (p.dateaccid) between '2022-01-01' and  '2022-12-31')  ", nativeQuery = true)
    List<PvAccident1> findAllpvAccidentrecent();


    @Query(value = "SELECT * FROM pv_accident1 p WHERE (p.dateaccid =:dateaccid  or p.annee_Pv=:anneePv or p.unite_id=:uniteId or p.pvnum=:pvnum or p.delegation_id=:gouvernoratId1 )", nativeQuery = true)
    List<PvAccident1> findBydateaccid(@Param("dateaccid") String dateaccid, @Param("anneePv") String anneePv, @Param("uniteId") String unite_id, @Param("pvnum") String pvnum, @Param("gouvernoratId1") String delegation_id);


    @Query(value = "SELECT * FROM pv_accident1 p WHERE (p.dateaccid between '$dateaccid1' AND '$dateaccid2' )", nativeQuery = true)
    List<PvAccident1> findBybetwendateaccid(@Param("dateaccid1") String dateaccid1, @Param("dateaccid2") String dateaccid2);


}



