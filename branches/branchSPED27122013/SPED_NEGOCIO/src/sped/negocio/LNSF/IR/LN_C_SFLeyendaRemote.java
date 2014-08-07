package sped.negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.beans.BeanLeyendaWS;
import sped.negocio.entidades.eval.CriterioIndicador;

@Remote
public interface LN_C_SFLeyendaRemote {
    BeanLeyenda getLeyendabyEvaluacion(int nidCriterioIndicador,
                                       int nidFicha, 
                                       double valor);
    /**
     * Metodo que retorna la lista de leyendas segun el indicador, usado para mostrar en el aplicativo Movil
     * @param nidCriterioIndicador id del criterioIndicador
     * @author dfloresgonz
     * @since 07.02.2014
     * @return List<BeanLeyendaWS>
     */
    List<BeanLeyendaWS> getLeyendasByCriterioIndicador_LN_WS(int nidCriterioIndicador);
}
