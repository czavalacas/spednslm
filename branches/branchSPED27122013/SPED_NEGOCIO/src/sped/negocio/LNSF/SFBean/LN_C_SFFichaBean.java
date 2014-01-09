package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import net.sf.dozer.util.mapping.MappingException;

import sped.negocio.BDL.IL.BDL_C_SFFichaLocal;
import sped.negocio.BDL.IL.BDL_C_SFUtilsLocal;
import sped.negocio.LNSF.IL.LN_C_SFFichaLocal;
import sped.negocio.LNSF.IR.LN_C_SFFichaRemote;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanFicha;
import sped.negocio.entidades.eval.Ficha;

@Stateless(name = "LN_C_SFFicha", mappedName = "mapLN_C_SFFicha")
public class LN_C_SFFichaBean implements LN_C_SFFichaRemote,
                                            LN_C_SFFichaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFFichaLocal bdL_C_SFFichaLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFFichaBean() {
    }
    
    public List<BeanFicha> getLstFichasByAttr_LN(){
        try{
            return mapperTransform(bdL_C_SFFichaLocal.getFichaByAttr_BDL());
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanFicha>();
        }
    }
    
    public List<BeanFicha> mapperTransform(List<Ficha> lstFichasBDL){
        try {
            BeanFicha beanFicha = new BeanFicha();
            List<BeanFicha> lstBeanFichas = new ArrayList<BeanFicha>();
            for (Ficha ficha : lstFichasBDL) {
                beanFicha = (BeanFicha) mapper.map(ficha, BeanFicha.class);
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_ficha", "evmfich", ficha.getTipoFicha());
                beanFicha.setDescripcionTipoFicha(constr.getDescripcionAMostrar());
                constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("tipo_ficha_curso", "evmfich", ficha.getTipoFichaCurso());
                beanFicha.setDescripcionTipoFichaCurso(constr.getDescripcionAMostrar());
                constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("estado_ficha", "evmfich", ficha.getEstadoFicha());
                beanFicha.setDescripcionEstadoFicha(constr.getDescripcionAMostrar());
                lstBeanFichas.add(beanFicha);
            }
            return lstBeanFichas;
        } catch (MappingException me) {
            me.printStackTrace();
            return null;
        }
    }
}