package test._SFPrueba;

import java.util.List;

import javax.ejb.Remote;

import test.entidades.Evmeval;

@Remote
public interface SFTestRemoto {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Evmeval persistEvmeval(Evmeval evmeval);

    Evmeval mergeEvmeval(Evmeval evmeval);

    void removeEvmeval(Evmeval evmeval);

    List<Evmeval> getEvmevalFindAll();
}
