package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFCalendarioLocal;
import sped.negocio.LNSF.IL.LN_C_SFCalendarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFCalendarioRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanCalendario;
import sped.negocio.entidades.beans.BeanComboInteger;
import sped.negocio.entidades.beans.BeanComboString;
import sped.negocio.entidades.beans.BeanComboStringCalend;

@Stateless(name = "LN_C_SFCalendario", mappedName = "mapLN_C_SFCalendario")
public class LN_C_SFCalendarioBean implements LN_C_SFCalendarioRemote, 
                                              LN_C_SFCalendarioLocal {
    @Resource
    SessionContext sessionContext;
    @EJB
    private BDL_C_SFCalendarioLocal bdL_C_SFCalendarioLocal;

    public LN_C_SFCalendarioBean() {
    }
    
    public List<BeanCalendario> getCalendarioActivo_LN(int mesNumero, int year){
        return bdL_C_SFCalendarioLocal.getCalendarioActivo_BDL(mesNumero,year);
    }
    
    public List<BeanCalendario> getCalendarioActivoByUsuario_LN(int mesNumero,int nidUsuario, int year){
        List<Object[]> c = bdL_C_SFCalendarioLocal.getCalendarioActivoByUsuario_BDL(mesNumero,nidUsuario, year);
        Iterator it = c.iterator();
        List<BeanCalendario> lstRet = new ArrayList<BeanCalendario>();
        while(it.hasNext()){
            Object[] b = (Object[]) it.next();
            BeanCalendario bc = new BeanCalendario();
            bc.setCuartil( (Integer) b[0]);
            bc.setDescripcionDia((String) b[1]);
            bc.setDiaNombre((String) b[2]);
            bc.setDiaNumero((Integer) b[3]);
            bc.setDiaSemanaNumero((Integer) b[4]);
            bc.setEsDiaSemana((Integer) b[5]);
            bc.setEsFeriado((Integer) b[6]);
            Long esLab = (Long) b[7];
            bc.setEsLaborable(esLab.intValue());
            bc.setEstado((String) b[8]);
            bc.setMesNombre((String) b[9]);
            bc.setMesNumero((Integer) b[10]);
            bc.setNidFecha((Date) b[11]);
            bc.setSemana((Integer) b[12]);
            bc.setYear((Integer) b[13]);
            bc.setEstilo((String) b[14]);
            bc.setTipoFalta((String) b[15]);
            lstRet.add(bc);
        }
        return lstRet;
    }
    
    public List<BeanComboStringCalend> getYearsCalendario(){
        return bdL_C_SFCalendarioLocal.getYearsCalendario();
    }
}