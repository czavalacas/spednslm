package sped.negocio.BDL.IL;

import javax.ejb.Local;

import sped.negocio.entidades.admin.Main;

@Local
public interface BDL_T_SFMainLocal {
    Main persistMain(Main main);

    Main mergeMain(Main main);

    void removeMain(Main main);
}
