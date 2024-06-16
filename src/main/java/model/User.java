package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String name;
    private String email;
    private Address address; // Nested Address class
    private String phone;
    private String website;
    private Company company;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geo {
        private String lat;
        private String lng;
    }
}
