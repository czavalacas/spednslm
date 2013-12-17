package test._SFPrueba;

import java.util.List;

import javax.ejb.Local;

import test.entidades.Evmeval;

@Local
public interface SFTestLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Evmeval persistEvmeval(Evmeval evmeval);

    Evmeval mergeEvmeval(Evmeval evmeval);

    void removeEvmeval(Evmeval evmeval);

    List<Evmeval> getEvmevalFindAll();
}
