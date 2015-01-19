package sped.negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import sped.negocio.BDL.IL.BDL_C_SFLeccionLocal;
import sped.negocio.LNSF.IL.LN_C_SFLeccionLocal;
import sped.negocio.LNSF.IR.LN_C_SFLeccionRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCurso;
import sped.negocio.entidades.beans.BeanLeccion;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.sist.Leccion;

@Stateless(name = "LN_C_SFLeccion", mappedName = "map-LN_C_SFLeccion")
public class LN_C_SFLeccionBean implements LN_C_SFLeccionRemote, 
                                           LN_C_SFLeccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    
    @EJB
    private BDL_C_SFLeccionLocal bdL_C_SFLeccionLocal;

    public LN_C_SFLeccionBean() {
    }
    
    public List<BeanLeccion> getLeccionesbyCodigoVista(boolean vista, String codigo, int nidSede, int nidNivel){
        List<BeanLeccion> listaBean = new ArrayList();
        List<Leccion> lst = new ArrayList();
        if(vista){
            Aula a = new Aula();
            a.setNidAula(Utiles.transforString(codigo));
            lst = bdL_C_SFLeccionLocal.getLeccionesbyAula(a);            
        }else{
            Profesor p = new Profesor();
            p.setDniProfesor(codigo);
            lst = bdL_C_SFLeccionLocal.getLeccionesbyDNI(p, nidSede, nidNivel);
        }
        for(Leccion l : lst){
            BeanLeccion bean = new BeanLeccion();
            bean.setNidLecc(l.getNidLecc());
            bean.setNroDuracion(l.getNroDuracion());
            bean.setNroHoras(l.getNroHoras());
            bean.setNroHoras_aux(l.getNroHoras_aux());
            ///// Aula //////
            BeanAula aula = new BeanAula();
            aula.setNidAula(l.getAula().getNidAula());
            aula.setDescripcionAula(l.getAula().getDescripcionAula());
            bean.setAula(aula);
            //////Curso ////////
            BeanCurso curso = new BeanCurso();
            curso.setNidCurso(l.getCurso().getNidCurso());
            curso.setDescripcionCurso(l.getCurso().getDescripcionCurso());
            bean.setCurso(curso);
            /////Profesor /////
            BeanProfesor profesor = new BeanProfesor();
            profesor.setDniProfesor(l.getProfesor().getDniProfesor());
            profesor.setNombreCompleto(l.getProfesor().getNombres() + " " + l.getProfesor().getApellidos());
            bean.setProfesor(profesor);            
            //////auxiliar/////
            bean.setCodigoDniAula(vista ? bean.getProfesor().getDniProfesor() : bean.getAula().getNidAula().toString());
            bean.setTitulo(vista ? bean.getProfesor().getNombreCompleto() : bean.getAula().getDescripcionAula());
            listaBean.add(bean);
        }
        return listaBean;
    }
    
}
