package orangehrm.lib;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExample {

    public static void main(String[] args) {

        Faker faker = new Faker();
        Date date = faker.date().birthday();

        System.out.println(date);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String fechaTexto = formatter.format(date);
        System.out.println(fechaTexto);

    }
}
