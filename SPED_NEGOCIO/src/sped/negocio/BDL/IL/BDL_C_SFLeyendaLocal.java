package sped.negocio.BDL.IL;

import java.util.List;

import javax.ejb.Local;

import sped.negocio.entidades.eval.CriterioIndicador;
import sped.negocio.entidades.eval.Leyenda;

@Local
public interface BDL_C_SFLeyendaLocal {
    Leyenda getLeyendabyEvaluacion(CriterioIndicador cri,
                                   int nidFicha,
                                   int valorValoracion);
    /**
     * Metodo que retorna la lista de leyendas segun el indicador, usado para mostrar en el aplicativo Movil
     * @param nidCriterioIndicador id del criterioIndicador
     * @author dfloresgonz
     * @since 07.02.2014
     * @return List<Leyenda>
     */
    List<Leyenda> getLeyendasByCriterioIndicador_BDL_WS(int nidCriterioIndicador);
    String getLeyendabyEvaluacion_BDL(int nidCriterioIndicador,
                                     /*int nidCriterio,
                                     int nidIndicador,*/
                                     int nidFicha,
                                     int valorValoracion);
}
