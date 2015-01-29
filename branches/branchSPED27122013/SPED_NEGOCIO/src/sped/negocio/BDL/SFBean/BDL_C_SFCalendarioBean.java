package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sped.negocio.BDL.IL.BDL_C_SFCalendarioLocal;
import sped.negocio.BDL.IR.BDL_C_SFCalendarioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboInteger;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.sist.Calendario;

@Stateless(name = "BDL_C_SFCalendario", mappedName = "mapBDL_C_SFCalendario")
public class BDL_C_SFCalendarioBean implements BDL_C_SFCalendarioRemote,
                                               BDL_C_SFCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFCalendarioBean() {
    }

    public List<BeanCalendario> getCalendarioActivo_BDL(int mesNumero, int year) {
        try {
            String qlString = "SELECT NEW sped.negocio.entidades.beans.BeanCalendario(c.cuartil,c.descripcionDia," +
                                "c.diaNombre,c.diaNumero,c.diaSemanaNumero,c.esDiaSemana,c.esFeriado,c.esLaborable," +
                                "c.estado,c.mesNombre,c.mesNumero,c.nidFecha,c.semana,c.year,c.estilo) " +
                              "FROM Calendario c " +
                              "WHERE c.estado = '1' " +
                              "AND c.mesNumero = :mesNumero " +
                              "And c.year = :year " +
                              "ORDER BY c.diaNumero ASC ";
            return em.createQuery(qlString).setParameter("mesNumero",mesNumero).setParameter("year",year).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Object[]> getCalendarioActivoByUsuario_BDL(int mesNumero,int nidUsuario, int year){
        try {
            String qlString = "SELECT c.cuartil,c.descripcionDia,c.diaNombre,c.dia,c.diaSemana,c.esDiaSemana,c.esFeriado \n" + 
                              "       ,c.esLaborable,c.estado,c.mesNombre,c.mes,c.pk_fecha,c.semana,c.year,c.estilo,null \n" + 
                              "FROM calendario c " +
                              "WHERE c.estado = '1' AND c.mes = "+mesNumero+" And c.year = "+year+" " +
                              "  AND NOT EXISTS (SELECT c1.pk_fecha \n" + 
                              "			   FROM calendario c1,\n" + 
                              "				addusca uc,   \n" + 
                              "				admusua u     \n" + 
                              "		          WHERE c1.pk_fecha  = uc.pk_fecha   \n" + 
                              "			    AND   u.nidUsuario = uc.nidUsuario \n" + 
                              "                     AND   u.nidUsuario = ?             \n "+
                              "                     And   c1.year      = ?             \n "+
                              "			    AND   c1.pk_fecha  = c.pk_fecha)   \n" + 
                              "UNION \n" + 
                              "SELECT c1.cuartil,uc.desc_diaLibre,c1.diaNombre,c1.dia,c1.diaSemana,c1.esDiaSemana,c1.esFeriado \n" + 
                              "       ,0,c1.estado,c1.mesNombre,c1.mes,c1.pk_fecha,c1.semana,c1.year,uc.estilo,uc.tipoFalta    \n" + 
                              "FROM calendario c1,  \n" + 
                              "	    addusca uc,     \n" + 
                              "	    admusua u       \n" + 
                              "WHERE c1.pk_fecha = uc.pk_fecha    \n" + 
                              "AND   u.nidUsuario = uc.nidUsuario \n" + 
                              "AND   u.nidUsuario = ?             \n "+
                              "AND   c1.mes = ?                   \n "+
                              "And   c1.year = ?                  \n"+
                              "ORDER BY 12 ASC ";
            return em.createNativeQuery(qlString).setParameter(1,nidUsuario).setParameter(2,year)
                                                 .setParameter(3,nidUsuario).setParameter(4,mesNumero).setParameter(5,year).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Calendario findCalendarioById(Date id) {
        try {
            Calendario instance = em.find(Calendario.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<BeanComboString> getYearsCalendario(){
        try{
            String qlString = "Select NEW sped.negocio.entidades.beans.BeanComboString(Cast(e.year As Char),Cast(e.year As Char) ) " +
                              "From Calendario e "+
                              "Group By e.year "+
                              "Order By e.year Asc ";
            List<BeanComboString> lstYears = em.createQuery(qlString).getResultList();
            return lstYears;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanComboString>();
        }
    }
}