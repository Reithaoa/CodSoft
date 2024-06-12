package org.reitumetse;

import netscape.javascript.JSObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Struct;
import java.util.Scanner;

public class currencyConverter {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type the currency you want to convert from: ");
        // todo: allow user to use the currency symbol instead, like 'R' or '$'
        // todo : add currency switch case for symbol or actual currency name
        // todo : if they add the actual currency and then  don't prompt for country selection
        // todo : when thy use the '$' sign, ask then to select an intended country from the list of all the ones that use the same sign.
        String originalCurrency = scanner.nextLine();

        System.out.println("Type the currency you want to convert to: ");
        // todo: allow user to use the currency symbol instead, like 'R' or '$'
        String convertedCurrency = scanner.nextLine();


        System.out.println("Type the amount: ");
        // todo: check and make sure that it is a number, whether decimal or not.
        BigDecimal amount = scanner.nextBigDecimal();

        String urlString =  "https://open.er-api.com/v6/latest?base=" + originalCurrency.toUpperCase();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject = jsonObject.getJSONObject("rates");
        BigDecimal rate = ratesObject.getBigDecimal(convertedCurrency.toUpperCase());

        BigDecimal result = rate.multiply(amount);
        System.out.println(result);

    }
}
