package net.skideo.model.enums;

public enum RoleFootball  {
    GK("Goalkeeper"), //GK (ГК) - Goalkeeper ( вратарь )
    SW("Free Defender"), //SW (ЦЗ) - Free Defender ( свободный защитник )
    RB("Right Defender"), //RB (ПЗ) - Right Defender ( правый защитник )
    LB("Left Defender"), //LB (ЛЗ) - Left Defender ( левый защитник )
    CB("Central Defender"), //CB (ЦЗ) - Central Defender ( центр защитник )
    RBW("Right Attacking Defender"), //RWB (ЗПФ) - Right Attacking Defender ( правый атакующий защитник )
    LBW("Left Attacking Defender"), //LWB (ЗЛФ) - Left Attacking Defender ( левый атакующий защитник )
    CDM("Central Defensive Midfielder"), //CDM (ЦОПЗ) - Central Defensive Midfielder ( опорный/оборонительный полузащитник )
    RM("Right Midfielder"), //RM (ППЗ) - Right Midfielder ( правый полузащитник )
    CM("Central Midfielder"), //CM (ЦПЗ) - Central Midfielder ( центральный полузащитник )
    LM("Left Midfielder"), //LM (ЛПЗ) - Left Midfielder   ( левый полузащитник )
    RMM("Right Side Midfielder"), //RWM (ПЗПФ) - Right Side Midfielder ( правый атакующий полузащитник )
    LMM("Left Side Midfielder"), //LWM (ПЗЛФ) -   Left Side Midfielder ( левый атакующий полузащитник )
    CAM("Central Attacking Midfielder"), //CAM (ЦАПЗ) - Central Attacking Midfielder ( центральный атакующий полузащитник )
    RF("Right Forward"), //RF (ПН) - Right Forward ( правый оттянутый форвард )
    CF("Central Forward"), //CF (СН) - Central Forward ( центральный оттянутый форвард )
    LF("Left Forward"), //LF (ЛН) - Left Forward ( левый оттянутый форвард )
    RS("Right Striker"), //RS (ПФ) - Right Striker ( правый конечный форвард )
    LS("Left Striker"), //LS (ЛФ) - Left Striker ( левый конечный форвард )
    ST("Striker"); //ST (ЦФ) - Striker ( центр форвард )

    String value;

    RoleFootball(String value) {
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }

}
