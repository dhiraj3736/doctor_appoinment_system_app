package com.example.doctor_appointment;

public class Endpoints {
    private static final String BASE_URL = "http://192.168.1.13:8080/api/";


    public static final String login = BASE_URL + "auth";
    public static final String register = BASE_URL + "register";

    public static final String get_user_info = BASE_URL + "user_info";

    public static final String get_service_name=BASE_URL + "service_name";

    public  static final String get_service_info=BASE_URL + "Service_info";

    public  static final String get_doctor_info=BASE_URL + "doctor_info";

    public  static final String get_doctor_info_for_userdashboard=BASE_URL + "doctor_info_for_userdashboard";

    public  static final String get_doctor_info_for_profile=BASE_URL + "doctor_info_for_profile";

    public  static final String service_name=BASE_URL + "service_name_for_fragment";

    public  static final String insert_rating=BASE_URL + "insert_rating";

    public  static final String insert_comment=BASE_URL + "insert_comment";


    public  static final String retrive_comment=BASE_URL + "retrive_comment";

    public  static final String get_rating=BASE_URL + "get_rating";

    public  static final String retrieveRatingsByDoctorId=BASE_URL + "retrieveRatingsByDoctorId";

    public  static final String saveAverageRating=BASE_URL + "saveAverageRating";

    public  static final String getAvailableTimeSlots=BASE_URL + "getAvailableTimeSlots";

    public  static final String saveAppoinment=BASE_URL + "saveAppoinment";


}