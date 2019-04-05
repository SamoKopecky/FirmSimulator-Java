package com.vutbr.feec.console_interface;

public enum Option {
    ADD_EMPLOYEE("Pridat zamestnanca"),
    ADD_JOB("Pridat pracu"),
    DECREASE_JOB_DURATION("Znizit dlzku prace / Zrusit pracu"),
    ACTIVATE_EMPLOYEE("Aktivovat zamestnanca"),
    FIRE_EMPLOYEE("Vymazat zamestnanca"),
    SICK_EMPLOYEE("Onemocneni zamestnanca"),
    HEALTHY_EMPLOYEE("Ozdravit zamestnanca"),
    SET_CONTRACT_DURATION("Nastavit dlzku uvazku"),
    PRINT_JOBS("Vypis zemstnancov na poziciach a jej aktualne volne uvazky"),
    PRINT_MONTHLY_EXPENSES("Vypis vyuzitia mesacnych financnych prostriedkov"),
    PRINT_EMPLOYEES("Vypis zamestnancov a vsetkcy ich vlasnosti"),
    DB_EXPORT("Export database do suboru"),
    DB_IMPORT("Import database do programu");


    private String desc;

    Option(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
