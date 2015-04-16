package sped.negocio.BDL.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import sped.negocio.BDL.IL.BDL_C_SFProfesorLocal;
import sped.negocio.BDL.IR.BDL_C_SFProfesorRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.admin.Usuario;
import sped.negocio.entidades.beans.BeanCriterio;
import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.negocio.entidades.eval.Criterio;
import sped.negocio.entidades.eval.Evaluacion;

@Stateless(name = "BDL_C_SFProfesor", mappedName = "map-BDL_C_SFProfesor")
public class BDL_C_SFProfesorBean implements BDL_C_SFProfesorRemote, 
                                             BDL_C_SFProfesorLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;

    public BDL_C_SFProfesorBean() {
    }

    /** <code>select o from Profesor o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Profesor> getProfesorFindAll() {
        return em.createNamedQuery("Profesor.findAll", Profesor.class).getResultList();
    }
    
    /**
     * Metodo que devuelve Profesor por DNI. 
     * Modificado el metodo 9/5/14. Angeles 
     * @param dni
     * @return
     */
    public Profesor getProfesorBydni(String dni) {
        try{
            Profesor instance = em.find(Profesor.class, dni);
            return instance;
            /* String ejbQl = " SELECT ma " +
                           " FROM Profesor ma " +
                           " WHERE ma.dniProfesor='"+dni+"'";   
                Profesor eva = (Profesor)em.createQuery(ejbQl).getSingleResult();           
                return eva;  */        
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List<Profesor> findProfesorPorSede_ByOrden(String nidSede, String nidArea, String nidCurso, String nidNivel, String nidGrado) {
        try {
            String ejbQl =    " SELECT distinct pro from Profesor pro, Sede sed, Aula au, Main ma, Curso cu, AreaAcademica aca, Nivel niv, Grado gra"+
                              " where pro.dniProfesor=ma.profesor.dniProfesor "+ 
                              " and ma.aula.nidAula=au.nidAula "+ 
                              " and au.sede.nidSede=sed.nidSede "+
                              " and ma.curso.nidCurso=cu.nidCurso" +
                              " and cu.areaAcademica.nidAreaAcademica=aca.nidAreaAcademica" +
                              " and au.gradoNivel.grado.nidGrado=gra.nidGrado" +
                              " and au.gradoNivel.nivel.nidNivel=niv.nidNivel" ;
            if (nidSede != null) {               
                    ejbQl = ejbQl.concat(" and sed.nidSede="+nidSede);               
            }
            if (nidArea != null) {               
                    ejbQl = ejbQl.concat(" and aca.nidAreaAcademica="+nidArea);               
            }
            if(nidCurso != null){
                    ejbQl = ejbQl.concat(" and cu.nidCurso="+nidCurso);  
            }
            if(nidNivel != null){
                    ejbQl = ejbQl.concat(" and niv.nidNivel="+nidNivel);  
            }
            if(nidGrado != null){
                    ejbQl = ejbQl.concat(" and gra.nidGrado="+nidGrado);  
            }
            
            ejbQl = ejbQl.concat(" ORDER by pro.apellidos");
            
            List<Profesor> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    
    public int existeDni(String dni){
        String ejbQL = "SELECT  Count(1) FROM Profesor p " + 
                       "WHERE p.dniProfesor = :dni ";
        List<Object> lstObject = em.createQuery(ejbQL).setParameter("dni", dni)
                                                      .getResultList();
        int cont = 0;
        if(lstObject.size() > 0){
            cont = Integer.parseInt(lstObject.get(0).toString());
        }        
        return cont;
    }
    
    public List<Profesor>  getProfesores() {
        try{
            String ejbQl = " SELECT pro " +
                           " FROM Profesor pro " +
                           " WHERE 1=1 Order by pro.apellidos";   
            
            List<Profesor> lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;      
        }catch(Exception e){
            e.printStackTrace();  
            return null;
        }   
        }
    
    public List getNombreProfesor(){
        try{
            String ejbQl = " SELECT CONCAT(pro.nombres,' ',pro.apellidos) As Nombre" +
                           " FROM Profesor pro " +
                           " ORDER BY Nombre ASC";   
            List lst = em.createQuery(ejbQl).getResultList();
            return lst;
        }catch(Exception e){
            return null;
        }
    }
    
    public String getDniProfe(String nombreCompleto){
        try{
            String ejbQl = " SELECT pro.dniProfesor" +
                           " FROM Profesor pro " +
                           " where CONCAT(pro.nombres,' ',pro.apellidos) ='"+nombreCompleto+"'";   
            Object o = em.createQuery(ejbQl) .getSingleResult();
            String dni = "";
            if(o != null){
                dni = o.toString();
            }
            return dni;
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Profesor> getProfesoresPorSedeNivelYArea(String nidSede, String nidNivel, Integer nidAreaAcademica) {
        List<Profesor> lstMain = new ArrayList<Profesor>();
        try {
            String ejbQl =    " Select distinct prof " +
                              "   From Main ma,      " +
                              "        Curso cur ,   " +
                              "        Profesor prof," +
                              "        Aula au       " +
                              " Where prof.flgActi = 1 " +
                              " And prof.dniProfesor  = ma.profesor.dniProfesor  " +
                              " And ma.aula.nidAula   = au.nidAula " +
                              " And au.gradoNivel.nivel.nidNivel = " +nidNivel+
                              " And au.sede.nidSede   = " +nidSede+
                              " And ma.curso.nidCurso = cur.nidCurso" +
                              " And ma.estado=1";

            if (nidAreaAcademica != null) {
                if (nidAreaAcademica != 0){
                   if(nidAreaAcademica==12 || nidAreaAcademica==13) { //12 = Primer Ciclo 13 = Inicial                   
                    ejbQl = ejbQl.concat(" And cur.areaAcademica.nidAreaAcademica=" + nidAreaAcademica);    
                    }else{
                        ejbQl = ejbQl.concat(" And cur.nidAreaNativa =" + nidAreaAcademica);    
                    }
            }  }  
            ejbQl = ejbQl.concat(" Order By prof.apellidos");  
            System.out.println("::::::::::QUERY::::::::::::::"+ejbQl);
            lstMain = em.createQuery(ejbQl).getResultList();
            return lstMain;
        } catch (Exception e) {
            e.printStackTrace();
            return lstMain;
        }
    }    
    
    
    public List<Profesor> getProfesoresDistintoLista(List<String> lst_dni, BeanProfesor profesor){
        try{
            String strQuery = " SELECT distinct p FROM Profesor p " +
                           " WHERE 1 = 1 ";
            if(lst_dni != null && lst_dni.size() > 0){
                strQuery = strQuery.concat(" AND ( ");
                for(int i=0 ; i < lst_dni.size(); i++){
                    if(i != 0){
                        strQuery = strQuery.concat(" AND ");
                    }
                    strQuery = strQuery.concat(" p.dniProfesor != :dni"+i+" ");                    
                }
                strQuery = strQuery.concat(" ) ");
            }
            if(profesor.getApellidos() != null){
                strQuery = strQuery.concat(" AND upper(p.apellidos) like :p_ape ");
            }
            if(profesor.getNombres() != null){
                strQuery = strQuery.concat(" AND upper(p.nombres) like :p_nom ");
            }
            if(profesor.getDniProfesor() != null){
                strQuery = strQuery.concat(" AND p.dniProfesor like :p_dni ");
            }
            strQuery = strQuery.concat(" Order by p.apellidos ");            
            Query query = em.createQuery(strQuery);
            if(lst_dni != null && lst_dni.size() > 0){
                for(int i=0 ; i < lst_dni.size(); i++){
                    query.setParameter("dni"+i, lst_dni.get(i));                  
                }
            }
            if(profesor.getApellidos() != null){
                query.setParameter("p_ape", "%"+profesor.getApellidos()+"%");  
            }
            if(profesor.getNombres() != null){
                query.setParameter("p_nom", "%"+profesor.getNombres()+"%");  
            }
            if(profesor.getDniProfesor() != null){
                query.setParameter("p_dni", "%"+profesor.getDniProfesor()+"%"); 
            }
            return query.getResultList(); 
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public String getColorProfe(String dni){
        try{
            String ejbQl = " SELECT p.color" +
                           " FROM Profesor p " +
                           " where p.dniProfesor = :dni";   
            Object o = em.createQuery(ejbQl).setParameter("dni", dni).getSingleResult();
            String color = "";
            if(o != null){
                color = o.toString();
            }else{
                color = "647687";
            }
            return color;
        }catch(Exception e){
            return null;
        }
    }
    
}
