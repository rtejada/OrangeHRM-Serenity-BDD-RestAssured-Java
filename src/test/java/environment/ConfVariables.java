package environment;


import java.util.Optional;

public class ConfVariables {

    public static  String getClientId(){
        return Optional.ofNullable(System.getenv("HRMClientId"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMClientId"));

    }

    public static  String getClientSecret(){
        return Optional.ofNullable(System.getenv("HRMClientSecret"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMClientSecret"));

    }

    public static  String getUrlToken(){
        return Optional.ofNullable(System.getenv("HRMAccessTokenURL"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMAccessTokenURL"));

    }

    public static  String getGrantType(){
        return Optional.ofNullable(System.getenv("HRMGrantType"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMGrantType"));

    }

    public static  String getUrlOrganization(){
        return Optional.ofNullable(System.getenv("HRMOrganization"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMOrganization"));

    }

    public static  String getUrlBase(){
        return Optional.ofNullable(System.getenv("HRMUrlBase"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMUrlBase"));

    }

    public static  String getPath(){
        return Optional.ofNullable(System.getenv("HRMPath"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMPath"));

    }


    public static  String getEmployeeId(){
        return Optional.ofNullable(System.getenv("HRMEmployeeId"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMEmployeeId"));
    }

    public static  String getName(){
        return Optional.ofNullable(System.getenv("HRMName"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMName"));

    }
    public static  String getSurname(){
        return Optional.ofNullable(System.getenv("HRMSurnameName"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMSurnameName"));

    }


    public static  String getEmployeeUpdatedId(){
        return Optional.ofNullable(System.getenv("HRMEmployeeUpdated"))
                .orElse((String) ApplicationProperties.getInstance().get("HRMEmployeeUpdated"));

    }

}
