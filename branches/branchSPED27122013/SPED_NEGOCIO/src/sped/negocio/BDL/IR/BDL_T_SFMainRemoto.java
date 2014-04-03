package sped.negocio.BDL.IR;

import javax.ejb.Remote;

import sped.negocio.entidades.admin.Main;

@Remote
public interface BDL_T_SFMainRemoto {
    Main persistMain(Main main);

    Main mergeMain(Main main);

    void removeMain(Main main);
}
