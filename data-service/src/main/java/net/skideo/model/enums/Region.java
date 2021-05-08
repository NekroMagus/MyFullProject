package net.skideo.model.enums;

public enum Region {

    CIS("СНГ"),
    WESTERN_EUROPE("Западная Европа"),
    NORTHERN_EUROPE("Северная Европа"),
    SOUTHERN_EUROPE("Южная Европа"),
    EASTERN_EUROPE("Восточная Европа"),
    EAST_ASIA("Восточная Азия"),
    CENTRAL_ASIA("Центральная Азия"),
    NEAR_EAST("Ближний Восток"),
    LATIN_AMERICA("Латинская Америка"),
    CENTRAL_AMERICA("Центральная Америка"),
    NORTH_AMERICA("Северная Америка"),
    NORTH_AFRICA("Северная Африка"),
    CENTRAL_AFRICA("Центральная Африка"),
    SOUTH_AFRICA("Южная Африка");

    String name;

    Region(String name) {
        this.name=name;
    }

}
