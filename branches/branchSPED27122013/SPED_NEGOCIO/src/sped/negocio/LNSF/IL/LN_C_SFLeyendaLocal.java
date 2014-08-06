package sped.negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.beans.BeanLeyenda;
import sped.negocio.entidades.beans.BeanLeyendaWS;
import sped.negocio.entidades.eval.CriterioIndicador;

@Local
public interface LN_C_SFLeyendaLocal {
    BeanLeyenda getLeyendabyEvaluacion(CriterioIndicador cri,
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
