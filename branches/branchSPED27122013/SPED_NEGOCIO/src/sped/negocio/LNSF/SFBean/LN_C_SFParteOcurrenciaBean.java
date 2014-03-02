package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IL.LN_C_SFParteOcurrenciaLocal;
import sped.negocio.LNSF.IR.LN_C_SFParteOcurrenciaRenote;
import sped.negocio.entidades.beans.BeanParteOcurrencia;
import sped.negocio.entidades.beans.BeanPie;

/**
 * Clase que gestiona la logica de negocio para las consultas invoca a los BDL, mas no se
 * escriben queries aqui
 * @author dfloresgonz
 * @since 26.02.2014
 */
@Stateless(name = "LN_C_SFParteOcurrencia", mappedName = "mapLN_C_SFParteOcurrencia")
public class LN_C_SFParteOcurrenciaBean implements LN_C_SFParteOcurrenciaRenote, 
                                                      LN_C_SFParteOcurrenciaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFParteOcurrenciaLocal bdL_C_SFParteOcurrenciaLocal;

    public LN_C_SFParteOcurrenciaBean() {
    }

    public List<BeanParteOcurrencia> getListaPartesOcurrencia_LN(Date fechaMin, Date fechaMax, Integer nidProblema,
                                                                  String nombreProfesor, Integer nidSede,
                                                                  Integer nidUsuario) {
        List<BeanParteOcurrencia> lstPOs = bdL_C_SFParteOcurrenciaLocal.getListaPartesOcurrencia_BDL(fechaMin, fechaMax, nidProblema,
                                                                                                         nombreProfesor, nidSede, nidUsuario);
        if(lstPOs != null){
            if(lstPOs.size() > 0){
                BeanParteOcurrencia last = lstPOs.get(lstPOs.size() - 1);
                List lstPies = new ArrayList();
                BeanPie pie = null;
                for(BeanParteOcurrencia po : lstPOs){
                    pie = new BeanPie();
                    pie.setSerie(po.getDescProblema());
                    if(lstPies.contains(pie)){
                        pie = this.getPieBySerie(po.getDescProblema(),lstPies);
                        pie.setCantSlice((pie.getCantSlice() + 1));
                    }else{
                        pie.setCantSlice(1);
                        lstPies.add(pie);
                    }
                }
                last.setLstPies(lstPies);
            }
        }
        return lstPOs;
    }
    
    public BeanPie getPieBySerie(String serie,List lstPies){
        for(int i = 0; i < lstPies.size(); i++){
            BeanPie pie = (BeanPie) lstPies.get(i);
            if(pie.getSerie().equalsIgnoreCase(serie)){
                return pie;
            }else{
                return null;
            }
        }
        return null;
    }
}
