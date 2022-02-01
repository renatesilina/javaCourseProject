package musicapplication.views.songs;

/**
 * Enum for countries with spotify playlist: daily update of top 50 played tracks
 */
public enum Countries {
    GLOBAL("Global", "37i9dQZEVXbMDoHDwVN2tF"), ARGENTINA("Argentina", "37i9dQZEVXbMMy2roB9myp"),
    AUSTRALIA("Australia", "37i9dQZEVXbJPcfkRz0wJ0"), BRAZIL("Brazil", "37i9dQZEVXbMXbN3EUUhlg"),
    PHILIPPINES("Philippines", "37i9dQZEVXbNBz9cRCSFkY"), INDIA("India", "37i9dQZEVXbLZ52XmnySJg"),
    INDONESIA("Indonesia", "37i9dQZEVXbObFQZ3JLcXt"), ITALY("Italy", "37i9dQZEVXbIQnj7RRhdSX"),
    JAPAN("Japan", "37i9dQZEVXbKXQ4mDTEBXq"), MEXICO("Mexico", "37i9dQZEVXbO3qyFxbkOE1"),
    NETHERLANDS("Netherlands", "37i9dQZEVXbKCF6dqVpDkS"), POLAND("Poland", "37i9dQZEVXbN6itCcaL3Tt"),
    RUSSIA("Russia", "37i9dQZEVXbL8l7ra5vVdB"), SPAIN("Spain", "37i9dQZEVXbNFJfN1Vw8d9"),
    UK("United Kingdom", "37i9dQZEVXbLnolsZ8PSNw"), SWEDEN("Sweden", "37i9dQZEVXbLoATJ81JYXz"),
    TAIWAN("Taiwan", "37i9dQZEVXbMnZEatlMSiu"), TURKEY("Turkey", "37i9dQZEVXbIVYVBNw9D5K"),
    GERMANY("Germany", "37i9dQZEVXbJiZcmkrIHGU"), USA("USA", "5IMMxYtQ4fKKoiXvRDRH4q"),
    BELGIUM("Belgium", "37i9dQZEVXbJNSeeHswcKB"), BOLIVIA("Bolivia", "37i9dQZEVXbJqfMFK4d691"),
    BULGARIA("Bulgaria", "37i9dQZEVXbNfM2w2mq1B8"), CHILE("Chile", "37i9dQZEVXbL0GavIqMTeb"),
    COLOMBIA("Colombia", "37i9dQZEVXbOa2lmxNORXQ"), COSTA_RICA("Costa Rica", "37i9dQZEVXbMZAjGMynsQX"),
    DENMARK("Denmark", "37i9dQZEVXbL3J0k32lWnN"), DOMINICAN_REPUBLIC("Dominican Republic", "37i9dQZEVXbKAbrMR8uuf7"),
    ECUADOR("Ecuador", "37i9dQZEVXbJlM6nvL1nD1"), EGYPT("Egypt", "37i9dQZEVXbLn7RQmT5Xv2"),
    EL_SALVADOR("El Salvador", "37i9dQZEVXbLxoIml4MYkT"), ESTONIA("Estonia", "37i9dQZEVXbLesry2Qw2xS"),
    FINLAND("Finland", "37i9dQZEVXbMxcczTSoGwZ"), FRANCE("France", "37i9dQZEVXbIPWwFssbupI"),
    GREECE("Greece", "37i9dQZEVXbJqdarpmTJDL"), GUATEMALA("Guatemala", "37i9dQZEVXbLy5tBFyQvd4"),
    HONDURAS("Honduras", "37i9dQZEVXbJp9wcIM9Eo5"), HONG_KONG("Hong Kong", "37i9dQZEVXbLwpL8TjsxOG"),
    IRELAND("Ireland", "37i9dQZEVXbKM896FDX8L1"), ICELAND("Iceland", "37i9dQZEVXbKMzVsSGQ49S"),
    ISRAEL("Israel", "37i9dQZEVXbJ6IpvItkve3"), CANADA("Canada", "37i9dQZEVXbKj23U1GF4IR"),
    LATVIA("Latvia", "37i9dQZEVXbJWuzDrTxbKS"), LITHUANIA("Lithuania", "37i9dQZEVXbMx56Rdq5lwc"),
    LUXEMBOURG("Luxembourg", "37i9dQZEVXbKGcyg6TFGx6"), MALAYSIA("Malaysia", "37i9dQZEVXbJlfUljuZExa"),
    MOROCCO("Morocco", "37i9dQZEVXbJU9eQpX8gPT"), NICARAGUA("Nicaragua", "37i9dQZEVXbISk8kxnzfCq"),
    NORWAY("Norway", "37i9dQZEVXbJvfa0Yxg7E7"), NEW_ZEALAND("New Zealand", "37i9dQZEVXbM8SIrkERIYl"),
    PANAMA("Panama", "37i9dQZEVXbKypXHVwk1f0"), PARAGUAY("Paraguay", "37i9dQZEVXbNOUPGj7tW6T"),
    PERU("Peru", "37i9dQZEVXbJfdy5b0KP7W"), UKRAINE("Ukraine", "37i9dQZEVXbKkidEfWYRuD"),
    PORTUGAL("Portugal", "37i9dQZEVXbKyJS56d1pgi"), ROMANIA("Romania", "37i9dQZEVXbNZbJ6TZelCq"),
    SAUDI_ARABIA("Saudi Arabia", "37i9dQZEVXbLrQBcXqUtaC"), SWITZERLAND("Switzerland", "37i9dQZEVXbJiyhoAPEfMK"),
    SINGAPORE("Singapore", "37i9dQZEVXbK4gjvS1FjPY"), SLOVAKIA("Slovakia", "37i9dQZEVXbKIVTPX9a2Sb"),
    SOUTH_AFRICA("South Africa", "37i9dQZEVXbMH2jvi6jvjk"), SOUTH_KOREA("South Korea", "37i9dQZEVXbNxXF4SkHj9F"),
    THAILAND("Thailand", "37i9dQZEVXbMnz8KIWsvf9"), CZECH_REPUBLIC("Czech Republic", "37i9dQZEVXbIP3c3fqVrJY"),
    HUNGARY("Hungary", "37i9dQZEVXbNHwMxAkvmF8"), URUGUAY("Uruguay", "37i9dQZEVXbMJJi3wgRbAy"),
    VIETNAM("Vietnam", "37i9dQZEVXbLdGSmz6xilI"), AUSTRIA("Austria", "37i9dQZEVXbKNHh6NIXu36"),
    UNITED_EMIRATES("United Emirates", "37i9dQZEVXbM4UZuIrvHvA");

    private String playlistId;
    private String name;

    Countries(String name, String playlistId) {
        this.name = name;
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
